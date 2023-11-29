package com.bavon.app.utility;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Priority(100)
@Alternative
public class EncryptMd5 implements EncryptText{

    @Override
    public String encrypt(String text) {

        String encryptedText = null;

        try
        {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(text.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedText = s.toString();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return encryptedText;
    }
}
