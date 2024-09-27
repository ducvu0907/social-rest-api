package com.dev.SocialMedia.common;

import com.dev.SocialMedia.exception.CustomException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendMail(String toMail, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );

            messageHelper.setFrom(fromMail);
            messageHelper.setTo(toMail);
            messageHelper.setSubject(subject);
            messageHelper.setText(text);

            mailSender.send(message);

        } catch (Exception e) {
            throw new CustomException("fail to send mail: " + e.getMessage());
        }
    }
}
