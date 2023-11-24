package com.gitittech.paygo.settings.api.dtos;

import com.gitittech.paygo.entities.JpaSetting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ISettingMapper {

    ISettingMapper INSTANCE = Mappers.getMapper( ISettingMapper.class );

    Setting toSetting(JpaSetting jpaSetting);
}
