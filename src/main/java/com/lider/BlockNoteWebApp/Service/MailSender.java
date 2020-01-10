package com.lider.BlockNoteWebApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    public void sendWithFiles(String emailTo, String subject, String message, String fileName) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

            helper.setFrom(username);
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setText(message);
            helper.addAttachment("happyBirthday", new File(fileName));
            mailSender.send(mailMessage);
        } catch (Exception e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendWithFiles(String emailTo, String subject, String message, List<String> fileNames) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

            helper.setFrom(username);
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setText(message);
            for (String s : fileNames) {
                helper.addAttachment(s.substring(s.length()-10), new File(s));
            }
            mailSender.send(mailMessage);
        } catch (Exception e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }
}
