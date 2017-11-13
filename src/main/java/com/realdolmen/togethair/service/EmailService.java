package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.Booking;

import javax.enterprise.context.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by JCEBF12 on 13/11/2017.
 */
@RequestScoped
public class EmailService {

    public void sendEmail(Booking booking) {
        String from = "jeroen.cloetens@realdolmen.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(booking.getCustomer().getEmail()));

            message.setSubject("Flight booking invoice");
            message.setContent("<h3>Booking invoice</h3></br><p>Total booking cost is: " + booking.getTotalPrice() + " euro.</p>", "text/html");

            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
