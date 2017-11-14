package com.realdolmen.togethair.service;

import com.realdolmen.togethair.domain.booking.Booking;
import com.sun.mail.smtp.SMTPTransport;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Properties;

@Stateless
public class EmailService {

    public void sendEmail(Booking booking) {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("jeroen.cloetens1994@gmail.com", "jer19qs94");
                    }
                });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jeroen.cloetens1994@gmail.com"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(booking.getCustomer().getEmail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("jeroen.cloetens@realdolmen.com"));

            message.setSubject("Flight booking invoice");
            message.setContent("<h3>Booking invoice</h3></br><p>Total booking cost is: " + booking.getTotalPrice() + " euro.</p>", "text/html");

//            SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
//
//            t.connect("smtp.gmail.com", "jeroen.cloetens1994@gmail.com", "jer19qs94");
//            t.sendMessage(message, message.getAllRecipients());
//            t.close();
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
