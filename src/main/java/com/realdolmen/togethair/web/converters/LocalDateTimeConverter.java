package com.realdolmen.togethair.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@FacesConverter(forClass = LocalDateTime.class)
public class LocalDateTimeConverter implements Converter {

    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy h:m");

    @Override
    public LocalDateTime getAsObject(FacesContext context, UIComponent component, String dateTime) {
        if (dateTime == null || dateTime.trim().length() == 0)
            return null;

        return LocalDateTime.parse(dateTime, format);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object dateTimeObject){
        LocalDateTime dateTime = (LocalDateTime) dateTimeObject;
        return dateTime.format(format);
    }
}
