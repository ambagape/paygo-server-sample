package com.gitittech.paygo.message.dtos;

import com.gitittech.paygo.entities.JpaNotificationTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface INotificationTemplateMapper {

    INotificationTemplateMapper INSTANCE = Mappers.getMapper( INotificationTemplateMapper.class );

    JpaNotificationTemplate toJpaNotificationTemplate(NotificationTemplate template);

    NotificationTemplate toNotificationTemplate(JpaNotificationTemplate jpaNotificationTemplate);
}
