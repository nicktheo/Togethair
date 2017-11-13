package com.realdolmen.togethair.domain.booking;

import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.domain.identity.Customer;
import com.realdolmen.togethair.domain.identity.Passenger;
import com.realdolmen.togethair.exceptions.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Access(AccessType.PROPERTY)
public class Booking implements Bookable<Booking> {

    private Long id;

    private Customer customer;

    private List<Bookable<BookingLine>> bookingLines = new ArrayList<>();
    private double totalPrice;


    protected Booking() {}


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany
    public List<BookingLine> getBookingLines() {
        return bookingLines.stream()
                .map(Bookable::getBase)
                .collect(Collectors.toList());
    }

    public void setBookingLines(List<BookingLine> bookingLines) {
        this.bookingLines = new ArrayList<>();
        this.bookingLines.addAll(bookingLines);
    }

    public void addBookingLine(Bookable<BookingLine> bookingLine) {
        bookingLines.add(bookingLine);
    }

    public void addBookingLine(BookingLine bookingLine, PricingAdapter<BookingLine> priceAdapter) {
        if (priceAdapter == null)
            addBookingLine(bookingLine);
        else
            addBookingLine(priceAdapter.setBase(bookingLine));
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double amount) {
        this.totalPrice = amount;
    }


    @Override
    @Transient
    public double getPrice() {
        return bookingLines.stream()
                .mapToDouble(Bookable::getPrice)
                .sum();
    }

    @Override
    @Transient
    public List<PersonalTicket> getTickets() {
        return bookingLines.stream()
                .map(Bookable::getTickets)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static class Builder implements Serializable{

        private Booking booking = new Booking();

        private Map<TravelClass, List<Seat>> flights = new HashMap<>();
        private List<Passenger> passengers = new ArrayList<>();

        private PricingAdapter<Booking> bookingPriceAdapter;
        private Map<TravelClass, PricingAdapter<BookingLine>> priceAdapters = new HashMap<>();


        public Builder setCustomer(Customer c) {
            booking.setCustomer(c);
            return this;
        }

        public Collection<TravelClass> getFlights() {
            return flights.keySet();
        }

        public Builder addFlight(TravelClass flight) throws DuplicateFlightException {
            if (flights.containsKey(flight))
                throw new DuplicateFlightException();

            flights.put(flight, new ArrayList<>());

            return this;
        }

        public Builder addFlights(Collection<TravelClass> flights) throws DuplicateFlightException {
            for (TravelClass flight : flights)
                addFlight(flight);

            return this;
        }

        public Builder addPassenger(Passenger passenger) throws DuplicatePassengerException {
            if (passengers.contains(passenger))
                throw new DuplicatePassengerException();

            passengers.add(passenger);

            return this;
        }

        public Builder addPassengers(Collection<Passenger> passengers) throws DuplicatePassengerException {
            for (Passenger passenger : passengers)
                addPassenger(passenger);

            return this;
        }

        public Builder addSeat(Seat seat) throws NoSuchFlightException, DuplicateSeatException {
            List<Seat> seats = flights.get(seat.getTravelClass());

            if (seats == null)
                throw new NoSuchFlightException();
            if (seats.contains(seat))
                throw new DuplicateSeatException();
            if (seats.size() == passengers.size())
                throw new TooManySeatsException();

            seats.add(seat);

            return this;
        }

        public Builder addSeats(Collection<Seat> seats) throws NoSuchFlightException, DuplicateSeatException {
            for (Seat seat : seats)
                addSeat(seat);

            return this;
        }

        public Builder addPriceAdapter(PricingAdapter<Booking> adapter) {
            adapter.setBookable(bookingPriceAdapter);
            bookingPriceAdapter = adapter;

            return this;
        }

        public Builder addPriceAdapter(PricingAdapter<BookingLine> adapter, TravelClass flight) throws NoSuchFlightException {
            if (! flights.containsKey(flight))
                throw new NoSuchFlightException();

            adapter.setBookable(priceAdapters.get(flight));
            priceAdapters.put(flight, adapter);

            return this;
        }

        public Bookable<Booking> build() throws IllegalStateException {
            if (booking.getCustomer() == null)
                throw new IllegalStateException("No customer set");
            if (flights.size() == 0)
                throw new IllegalStateException("No flights in booking");

            for (Map.Entry<TravelClass, List<Seat>> flight : flights.entrySet())
                booking.addBookingLine(new BookingLine(passengers, flight.getValue()), priceAdapters.get(flight));

            if (bookingPriceAdapter == null)
                return booking;
            else
                return bookingPriceAdapter.setBase(booking);
        }
    }
}
