package com.casestudycheckerbackend.service.email;

import com.casestudycheckerbackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService implements EmailSender{
    @Autowired
    JavaMailSender mailSender;

    @Override
    public void send(User user,String siteURL) throws UnsupportedEncodingException, MessagingException {
        String verifyURL =siteURL + "/verify/" +user.getVerificationCode() ;
        System.out.println(verifyURL);
        String subject = "Please verify your registration";
        String senderName = "admin";
        String mailContent = "<p>Dear " + user.getUsername()+",</p>";
        mailContent += "<p>Please click the link below to verify your registration:</p>";
        mailContent += "<h3><a href=\""+verifyURL+"\">VERIFY</a></h3>";
        mailContent += "<p>Thank you<br>The CheckerViet</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("testsendemail6395@gmail.com",senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent,true);
        mailSender.send(message);
    }
}
