package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.flight.Seat;
import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.repository.SeatRepository;

import javax.ejb.ObjectNotFoundException;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by JCEBF12 on 9/11/2017.
 */
public class SeatService {

    @Inject
    SeatRepository repository;

    public List<Seat> getFreeSeatsPessimisticLock(TravelClass travelclass) throws ObjectNotFoundException {
        return repository.getFreeSeatsPessimisticLock(travelclass);
    }
}
