package com.gitittech.paygo.message.dtos;

import com.gitittech.paygo.entities.JpaNotification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface INotificationMapper {

    INotificationMapper INSTANCE = Mappers.getMapper( INotificationMapper.class );

    JpaNotification toJpaNotification(Notification notification);

    Notification toNotification(JpaNotification jpaNotification);
}
