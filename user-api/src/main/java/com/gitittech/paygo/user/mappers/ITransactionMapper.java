package com.gitittech.paygo.user.mappers;

import com.gitittech.paygo.entities.JpaTransaction;
import com.gitittech.paygo.integrations.dtos.EditableTransaction;
import com.gitittech.paygo.user.dtos.transactions.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ITransactionMapper {

    ITransactionMapper INSTANCE = Mappers.getMapper( ITransactionMapper.class );

    EditableTransaction toEditableTransaction(EditableTransaction transaction);

    Transaction toTransaction(JpaTransaction transaction);
}
