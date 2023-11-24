package com.gitittech.paygo.user.mappers;

import com.gitittech.paygo.entities.JpaDoubleEntry;
import com.gitittech.paygo.user.dtos.transactions.DoubleEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IDoubleEntryMapper {

    IDoubleEntryMapper INSTANCE = Mappers.getMapper(IDoubleEntryMapper.class );

    DoubleEntry toDoubleEntry(JpaDoubleEntry account);
}
