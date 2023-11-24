package com.gitittech.paygo.springservicegateway.controllers;

import com.gitittech.paygo.commons.exceptions.NotFoundException;
import com.gitittech.paygo.integrations.dtos.CustomerIdentificationResponse;
import com.gitittech.paygo.integrations.dtos.PaymentGatewayTransaction;
import com.gitittech.paygo.paymentmethod.api.IPaymentMethodContext;
import com.gitittech.paygo.paymentmethod.dtos.TransferResponse;
import com.gitittech.paygo.transaction.api.ITransaction;
import com.gitittech.paygo.user.api.IUser;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PaystackWebhook {

    private final IPaymentMethodContext paymentMethodContext;
    private final IUser userService;
    private final ITransaction transService;

    @Autowired
    public PaystackWebhook(IPaymentMethodContext paymentMethodContext, IUser userService, ITransaction transService
    ) {
        this.paymentMethodContext = paymentMethodContext;
        this.userService = userService;
        this.transService = transService;
    }

    /**
     *
     * @param event
     * @return
     * @throws NotFoundException
     * @throws Exception
     * @throws Throwable
     */
    @PostMapping("/paystack/webhook")
    public ResponseEntity receiveNotification(Event event) throws NotFoundException, Exception, Throwable {

        String eventName = event.event;
        Logger.getLogger(PaystackWebhook.class.getName()).info(String.format("This event was received: %s", eventName));
        switch (eventName) {
            case "charge.success":
                return this.completePayment((PaymentGatewayTransaction) event.data);
            case "customeridentification.success":
                return this.completeBvnVerification((CustomerIdentificationResponse) event.data);
            case "transfer.success":
                return this.completeTransfer((TransferResponse) event.data);
            case "transfer.reversed":
                return this.reverseTransfer(event.data);
            case "transfer.failed":
                return this.failTransfer(event.data);
            default:
                return ResponseEntity.ok().build();
        }
    }

    private ResponseEntity completeTransfer(TransferResponse tr) throws Exception {
        transService.findByReference(tr.data.reference).forEach(transaction -> {
            //Todo: implement method
        });
        return ResponseEntity.ok().build();
    }

    private ResponseEntity completeBvnVerification(CustomerIdentificationResponse cir) throws Throwable {
        userService.confirmBvnVerification(cir);
        return ResponseEntity.ok().build();

    }

    private ResponseEntity completePayment(PaymentGatewayTransaction pgt) throws Throwable {
        paymentMethodContext.completeTransaction(pgt);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity failTransfer(Object data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private ResponseEntity reverseTransfer(Object data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private record Event(String event, Object data) {};

}
