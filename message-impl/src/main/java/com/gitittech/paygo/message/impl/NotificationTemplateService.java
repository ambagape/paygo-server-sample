package com.gitittech.paygo.message.impl;

import com.gitittech.paygo.commons.exceptions.NotFoundException;
import com.gitittech.paygo.entities.JpaNotification;
import com.gitittech.paygo.entities.JpaNotificationTemplate;
import com.gitittech.paygo.message.api.INotificationTemplate;
import com.gitittech.paygo.message.api.INotificationTemplateReadRepository;
import com.gitittech.paygo.message.dtos.INotificationMapper;
import com.gitittech.paygo.message.dtos.Notification;
import com.gitittech.paygo.user.dtos.User;
import com.gitittech.paygo.user.mappers.IUserMapper;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

/**
 * @author Ambrose Ariagiegbe
 */
@Service
public class NotificationTemplateService implements INotificationTemplate {

    final private INotificationTemplateReadRepository repository;

    @Autowired
    NotificationTemplateService(INotificationTemplateReadRepository repository) {
        this.repository = repository;
    }

    @Override
    public Notification loadFromTemplate(String tid, User recipient, Map<String, String> additionalParams)
            throws NotFoundException {
        final var msg = new AtomicReference<JpaNotification>();
        final var optional = repository.findById(tid);
        if (optional.isEmpty()) {
            throw new NotFoundException("Template not found");
        }
        optional.ifPresent((jpaNotificationTemplate) -> {
            String title = compile(((JpaNotificationTemplate) jpaNotificationTemplate).getTitle(), recipient, additionalParams);
            String body = compile(((JpaNotificationTemplate) jpaNotificationTemplate).getMessage(), recipient, additionalParams);
            msg.set(new JpaNotification(IUserMapper.INSTANCE.toJpaUser(recipient), title, body, ((JpaNotificationTemplate) jpaNotificationTemplate).getType()));
        });
        return INotificationMapper.INSTANCE.toNotification(msg.get());
    }

    private String compile(String stringTemplate, User recipient, Map<String, String> additionalParams) {
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        
        Context context = new Context();
        context.setVariable("recipientName", recipient.getFirstName());
        context.setVariable("recipientEmail", recipient.getEmail());
        context.setVariable("recipientPhone", recipient.getPhone());

        if (additionalParams != null) {
            Set<Entry<String, String>> entrySet = additionalParams.entrySet();
            for (Entry<String, String> entry : entrySet) {
                if (context.containsVariable(entry.getKey())) {
                    continue;
                }
                context.setVariable(entry.getKey(), entry.getValue());
            }
        }

        return templateEngine.process(stringTemplate, context);        
    }
}
