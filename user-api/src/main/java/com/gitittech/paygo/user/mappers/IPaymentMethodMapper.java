package com.gitittech.paygo.user.mappers;

import com.gitittech.paygo.entities.JpaAccountDebit;
import com.gitittech.paygo.entities.JpaDebitCard;
import com.gitittech.paygo.entities.JpaPaymentMethod;
import com.gitittech.paygo.entities.JpaWallet;
import com.gitittech.paygo.user.dtos.payments.AccountDebit;
import com.gitittech.paygo.user.dtos.payments.DebitCard;
import com.gitittech.paygo.user.dtos.payments.PaymentMethod;
import com.gitittech.paygo.user.dtos.payments.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPaymentMethodMapper {

    IPaymentMethodMapper INSTANCE = Mappers.getMapper( IPaymentMethodMapper.class );

    Wallet toWallet(JpaWallet jpaWallet);

    AccountDebit toAccountDebit(JpaAccountDebit jpaAccountDebit);

    DebitCard toDebitCard(JpaDebitCard jpaDebitCard);

    default PaymentMethod toPaymentMethod(JpaPaymentMethod jpaPaymentMethod) {
        if (jpaPaymentMethod instanceof JpaWallet) {
            return toWallet((JpaWallet) jpaPaymentMethod);
        } else if (jpaPaymentMethod instanceof JpaAccountDebit) {
            return toAccountDebit((JpaAccountDebit) jpaPaymentMethod);
        } else {
            return toDebitCard((JpaDebitCard) jpaPaymentMethod);
        }
    }
}
