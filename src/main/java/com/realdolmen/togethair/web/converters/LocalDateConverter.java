package com.realdolmen.togethair.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter {

    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDate getAsObject(FacesContext context, UIComponent component, String date) {
        if (date == null || date.trim().length() == 0)
            return null;

        return LocalDate.parse(date, format);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object dateObject){
        LocalDate date = (LocalDate) dateObject;
        return date.format(format);
    }
}
