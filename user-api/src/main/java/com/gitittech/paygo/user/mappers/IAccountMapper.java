package com.gitittech.paygo.user.mappers;

import com.gitittech.paygo.entities.accounts.JpaAccount;
import com.gitittech.paygo.user.dtos.beneficiaries.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAccountMapper {

    IAccountMapper INSTANCE = Mappers.getMapper(IAccountMapper.class );

    Account toAccount(JpaAccount account);
}
