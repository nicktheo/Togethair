package com.realdolmen.togethair.service;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Base64;

/**
 * Created by JCEBF12 on 10/11/2017.
 */
@RequestScoped
public class QRCodeProvider {

    public String generateBase64QrCode(long bookingId) {
//        ByteArrayOutputStream qrCode = QRCode.from("http://localhost:8080/togethair/bookingInfo.xhtml?bookingId=" + Long.toString(bookingId))
//                .withSize(250,250).to(ImageType.PNG).stream();
        byte[] qrCode = QRCode.from("http://localhost:8080/togethair/bookingInfo.xhtml?bookingId=" + Long.toString(bookingId))
                .withSize(250,250).to(ImageType.PNG).stream().toByteArray();

        return "data:image/png;base64," + Base64.getEncoder().encodeToString(qrCode);
    }
}
