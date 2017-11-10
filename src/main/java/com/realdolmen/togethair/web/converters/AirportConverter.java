package com.realdolmen.togethair.web.converters;

import com.realdolmen.togethair.domain.location.Airport;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Airport.class)
public class AirportConverter implements Converter {
    public Object getAsObject(FacesContext context, UIComponent component, String airportCode) {
        if (airportCode == null || airportCode.trim().equals(""))
            return null;

        Airport airport = new Airport();

        if (true) {

        } else {
            FacesMessage errMsg = new FacesMessage("//");
            throw new ConverterException(errMsg);
        }

        return airport;
    }

    public String getAsString(FacesContext context, UIComponent component, Object airport){
        return ((Airport) airport).getName();
    }
}
