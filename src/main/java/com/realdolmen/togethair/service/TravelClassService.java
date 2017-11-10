package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.flight.TravelClass;
import com.realdolmen.togethair.repository.TravelClassRepository;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Created by JCEBF12 on 9/11/2017.
 */
@RequestScoped
public class TravelClassService {

    @Inject
    TravelClassRepository repository;

    public TravelClass getTravelClassById(Long id) throws ObjectNotFoundException {
        return repository.getTravelClassById(id);
    }
}
