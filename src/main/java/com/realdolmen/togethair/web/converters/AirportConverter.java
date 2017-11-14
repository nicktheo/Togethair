package com.realdolmen.togethair.web.converters;

import com.realdolmen.togethair.domain.location.Airport;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Map;
import java.util.WeakHashMap;

@FacesConverter(forClass = Airport.class)
public class AirportConverter implements Converter {

    private static Map<String, Airport> airports = new WeakHashMap<>();

    @Override
    public Airport getAsObject(FacesContext context, UIComponent component, String icaoCode) {
        return airports.get(icaoCode);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object airportObject){
        Airport airport = (Airport) airportObject;
        airports.put(airport.getIcaoCode(), airport);
        return airport.getIcaoCode();
    }
}
