package com.alten.bookingservice.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class TemplateEngineWrapper {

    @Autowired
    private TemplateEngine templateEngine;

    public String process(String template, Context context) {
        return templateEngine.process(template, context);
    }

}
