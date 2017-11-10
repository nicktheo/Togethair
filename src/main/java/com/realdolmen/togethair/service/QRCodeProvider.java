package com.realdolmen.togethair.service;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import javax.enterprise.context.RequestScoped;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * Created by JCEBF12 on 10/11/2017.
 */
@RequestScoped
public class QRCodeProvider {

    public OutputStream generateQrCode(long bookingId) {
        ByteArrayOutputStream qrCode = QRCode.from("http://localhost:8080/togethair/bookinginfo.xhtml?bookingId=" + Long.toString(bookingId))
                .withSize(250,250).to(ImageType.PNG).stream();

        return qrCode;
    }
}
