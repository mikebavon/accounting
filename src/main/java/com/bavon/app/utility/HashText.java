package com.bavon.app.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashText {

    public String hash(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(text.getBytes());

        return new String(messageDigest.digest());
    }
}
