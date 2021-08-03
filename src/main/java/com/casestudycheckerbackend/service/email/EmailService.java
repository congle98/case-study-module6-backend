package com.casestudycheckerbackend.service.email;

import com.casestudycheckerbackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;

    public void send(User user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Verify your account");
        message.setText("Tạo mới thành công - chờ xét duyệt");
        mailSender.send(message);
    }
}
