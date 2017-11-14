package com.realdolmen.togethair.repository;

import com.realdolmen.togethair.domain.booking.Booking;
import com.realdolmen.togethair.domain.booking.BookingLine;
import com.realdolmen.togethair.domain.booking.PercentagePricingAdapter;
import com.realdolmen.togethair.domain.booking.PricingAdapter;
import com.realdolmen.togethair.domain.flight.*;
import com.realdolmen.togethair.domain.identity.Airline;
import com.realdolmen.togethair.domain.identity.Customer;
import com.realdolmen.togethair.domain.identity.SimplePassenger;
import com.realdolmen.togethair.domain.location.Airport;
import com.realdolmen.togethair.exceptions.DuplicateFlightException;
import com.realdolmen.togethair.exceptions.DuplicatePassengerException;
import com.realdolmen.togethair.exceptions.DuplicateSeatException;
import com.realdolmen.togethair.exceptions.NotEnoughSeatsException;
import com.realdolmen.togethair.service.AbstractPersistenceTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ejb.EJBTransactionRolledbackException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 14/11/2017.
 */
public class BookingRepositoryTest extends AbstractPersistenceTest{

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private BookingRepository bookingRepository;

    private Booking.Builder builder;
    private List<TravelClass> travelClasses;
    private Airport origin, destination;
    private Flight f;
    private SimplePassenger passenger1, passenger2;
    private Seat s;

    @Test(expected = NotEnoughSeatsException.class)
    public void testRollBackWhenSeatsTaken() throws DuplicateFlightException, DuplicatePassengerException, DuplicateSeatException {
        bookingRepository = new BookingRepository();
        bookingRepository.em = em;

        builder = new Booking.Builder();
        Customer c = new Customer("Jeroen", "cloetens", "jeroen.cloetens1994@gmail.com", "123456", null);
        TravelClass tc = new TravelClass(null, TravelClassType.ECONOMY, 100.0);
        this.travelClasses = new ArrayList<>();
        travelClasses.add(tc);
        origin = new Airport("origin", "origin", null, null);
        destination = new Airport("destination", "destination", null, null);
        this.f = new Flight(Airline.BEL, 100, origin, destination, LocalDateTime.now(), Duration.ZERO, travelClasses);
        travelClasses.get(0).setFlight(f);
        this.passenger1 = new SimplePassenger("foo", "bar", "741963852");
        this.passenger2 = new SimplePassenger("lorem", "ipsum", "132456789");
        PricingAdapter<BookingLine> p1 = new PercentagePricingAdapter(1.0);
        PricingAdapter<Booking> p2 = new PercentagePricingAdapter<Booking>(1.0);

        List<Seat> seats= new ArrayList<>();
        seats.add(new Seat(10,10, travelClasses.get(0), Availability.TAKEN));

        builder.setCustomer(c);
        builder.addFlight(travelClasses.get(0));
        builder.addPassenger(passenger1);
        builder.addPassenger(passenger2);
        builder.addPriceAdapter(p1, travelClasses.get(0));
        builder.addPriceAdapter(p2);

        MockitoAnnotations.initMocks(this);
        Mockito.when(flightRepository.getFreeSeats(travelClasses.get(0))).thenReturn(seats);

        bookingRepository.persistBooking(builder);
    }
}
