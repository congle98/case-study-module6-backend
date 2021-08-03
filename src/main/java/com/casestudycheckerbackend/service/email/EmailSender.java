package com.casestudycheckerbackend.service.email;

import com.casestudycheckerbackend.models.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailSender {
    void send(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;
}
