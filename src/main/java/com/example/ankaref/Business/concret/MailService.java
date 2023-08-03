package com.example.ankaref.Business.concret;

import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Users;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class MailService {
    private final JavaMailSender mailSender;
    private UserRepository kullanicilarRepository;

    @Autowired
    public MailService(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.kullanicilarRepository = userRepository;
    }

    public void sendEventNotification(String eventName, String eventDate) {
        List<Users> recipients = kullanicilarRepository.findAll();
        for (Users recipient : recipients) {
            sendEmail(recipient.getEmail(), eventName, eventDate);
        }
    }

    private void sendEmail(String recipientEmail, String eventName, String eventDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Etkinlik Bilgilendirmesi: " + eventName);
        message.setText("Merhaba,\n\n" +
                "Etkinlik: " + eventName + "\n" +
                "Tarih ve Saat: " + eventDate + "\n\n" +
                "Keyifli etkinlikler!");
        mailSender.send(message);
    }
}
