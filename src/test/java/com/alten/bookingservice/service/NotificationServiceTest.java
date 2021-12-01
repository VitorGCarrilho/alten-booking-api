package com.alten.bookingservice.service;

import com.alten.bookingservice.component.TemplateEngineWrapper;
import com.alten.bookingservice.domain.Notification;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    private final String from = "email@contact.com";

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private TemplateEngineWrapper templateEngine;

    private NotificationService notificationService;

    @Test
    public void shouldSendEmail() {
        Notification notification = SampleFactoryUtils.notification();
        String body = "<body></body>";

        NotificationService notificationService = new NotificationService(mailSender, templateEngine, from);


        Mockito.when(templateEngine.process(eq(notification.getNotificationType().getTemplate()), any())).thenReturn(body);



        notificationService.notify(notification);

        Mockito.verify(mailSender).send(any(MimeMessagePreparator.class));


    }

}