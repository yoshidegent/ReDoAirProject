package com.realdolmen.redoairproject.entities;

import javax.persistence.MappedSuperclass;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@MappedSuperclass
public abstract class User extends AbstractEntity {
    private String username;
    private byte[] hashedPassword;

    public User(String username, String password)
    {
        this.username = username;
        this.hashedPassword = this.hashPassword(password);
    }

    public byte[] hashPassword(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            return md.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkPasswordIsValid(String password)
    {
        return getHashedPasswordAsString().equals(new String(this.hashPassword(password)));
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public String getHashedPasswordAsString() {
        return new String(hashedPassword);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }


}
