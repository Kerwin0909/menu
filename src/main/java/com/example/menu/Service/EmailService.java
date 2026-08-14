package com.example.menu.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String fromEmail;
    private final String toEmail;

    public EmailService(JavaMailSender mailSender,
                        @Value("${MAIL_USERNAME}") String fromEmail,
                        @Value("${MAIL_TO}") String toEmail) {
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
    }

    public void sendCardNotification(String cardName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("你的女朋友兑换了一张卡片");
        message.setText("她刚刚兑换了：" + cardName + "\n\n快去兑现吧！️");
        mailSender.send(message);
    }
}