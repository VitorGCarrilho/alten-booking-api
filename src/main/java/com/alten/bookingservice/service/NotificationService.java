package com.alten.bookingservice.service;

import com.alten.bookingservice.component.TemplateEngineWrapper;
import com.alten.bookingservice.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class NotificationService {

    private final String from;

    private final JavaMailSender mailSender;

    private final TemplateEngineWrapper templateEngine;

    @Autowired
    public NotificationService(JavaMailSender mailSender, TemplateEngineWrapper templateEngine, @Value("${spring.mail.username}") String from) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.from = from;
    }

    public void notify(Notification notification) {

        Context context = new Context();
        var book = notification.getBooking();

        if (book.getEmail() != null) {
            context.setVariable("email", notification.getBooking().getEmail());
        }

        if (book.getFromDate() != null) {
            context.setVariable("from", notification.getBooking().getFromDate());
        }

        if (book.getUntilDate() != null) {
            context.setVariable("until", notification.getBooking().getUntilDate());
        }

        if (book.getUntilDate() != null) {
            context.setVariable("room", notification.getBooking().getRoomNumber());
        }

        String body = templateEngine.process(notification.getNotificationType().getTemplate(), context);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(from);
            messageHelper.setTo(notification.getBooking().getEmail());
            messageHelper.setSubject(notification.getNotificationType().getSubject());
            messageHelper.setText(body, true);
        };
        mailSender.send(messagePreparator);
    }
}
