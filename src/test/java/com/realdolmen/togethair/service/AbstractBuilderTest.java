package com.realdolmen.togethair.service;

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
import org.junit.Before;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCEBF12 on 14/11/2017.
 */
public abstract class AbstractBuilderTest {

    protected Booking.Builder builder;

    protected SimplePassenger passenger;
    protected Flight f;
    protected Seat s;
    protected List<TravelClass> travelClasses;
    protected Airport origin, destination;

    @Before
    public void initialize() throws DuplicateFlightException, DuplicatePassengerException, DuplicateSeatException {
        builder = new Booking.Builder();
        Customer c = new Customer("Jeroen", "cloetens", "jeroen.cloetens1994@gmail.com", "123456", null);
        TravelClass tc = new TravelClass(null, TravelClassType.ECONOMY, 100.0);
        this.travelClasses = new ArrayList<>();
        travelClasses.add(tc);
        origin = new Airport("origin", "origin", null, null);
        destination = new Airport("destination", "destination", null, null);
        this.f = new Flight(Airline.BEL, 100, origin, destination, LocalDateTime.now(), Duration.ZERO, travelClasses);
        travelClasses.get(0).setFlight(f);
        this.passenger = new SimplePassenger("foo", "bar", "741963852");
        PricingAdapter<BookingLine> p1 = new PercentagePricingAdapter(1.0);
        PricingAdapter<Booking> p2 = new PercentagePricingAdapter<Booking>(1.0);
        this.s = new Seat(10,10, travelClasses.get(0), Availability.FREE);

        builder.setCustomer(c);
        builder.addFlight(travelClasses.get(0));
        builder.addPassenger(passenger);
        builder.addPriceAdapter(p1, travelClasses.get(0));
        builder.addPriceAdapter(p2);
        builder.addSeat(s);

    }
}
