package com.realdolmen.togethair.service;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import javax.ejb.Stateless;
import java.util.Base64;

@Stateless
public class QRCodeProvider {

    public String generateBase64QrCode(String bookingId) {
//        ByteArrayOutputStream qrCode = QRCode.from("http://localhost:8080/togethair/bookingInfo.xhtml?bookingId=" + Long.toString(bookingId))
//                .withSize(250,250).to(ImageType.PNG).stream();
        byte[] qrCode = QRCode.from("http://localhost:8080/togethair/booking/details.xhtml?bookingId=" + bookingId)
                .withSize(250,250).to(ImageType.PNG).stream().toByteArray();

        return "data:image/png;base64," + Base64.getEncoder().encodeToString(qrCode);
    }
}
