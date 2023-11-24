package com.gitittech.paygo.springservicegateway.controllers;

import com.gitittech.paygo.integrations.dtos.PaystackTransferRecipientRes;
import com.gitittech.paygo.integrations.dtos.AcctResolutionRes;
import com.gitittech.paygo.beneficiary.api.IBeneficiary;
import com.gitittech.paygo.beneficiary.api.IPaymentPlatform;
import com.gitittech.paygo.beneficiary.dtos.*;
import com.gitittech.paygo.beneficiary.dtos.requests.CreateTransferRecipientRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestParam;
import com.gitittech.paygo.beneficiary.dtos.requests.DeleteBankBeneficiaryRequest;


@RequestMapping("/banks")
@RestController
public class BankBeneficiariesController {

    private final IBeneficiary bankBeneficiaryService;

    @Autowired
    public BankBeneficiariesController(@Qualifier("bank") IBeneficiary bankBeneficiaryService) {
        this.bankBeneficiaryService = bankBeneficiaryService;
    }

    @GetMapping("/beneficiaries")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity getBeneficiaries(@RequestParam(value = "isSearch", defaultValue = "false") Boolean isSearch,
            @RequestParam(value = "filter", defaultValue = "") String filter,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "property") List<String> properties) throws Throwable {        
        return ResponseEntity.ok(bankBeneficiaryService.getAllBeneficiaries(isSearch, filter, page, size, direction, properties));
    }

    @DeleteMapping("/beneficiaries")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity deleteBeneficiary(@Body @Valid DeleteBankBeneficiaryRequest beneficiary) throws Throwable {
        bankBeneficiaryService.deleteBeneficiay(beneficiary);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-paystack-recipient")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity createPaystackRecipient(@Body @Valid final CreateTransferRecipientRequest ptr) throws Throwable {
        var paymentPlatform = ((IPaymentPlatform) bankBeneficiaryService);
        PaystackTransferRecipientRes ptres = paymentPlatform.getPaymentPlatformRecipient(ptr);
        return ResponseEntity.ok(ptres);
    }

    @PostMapping("/resolve-account")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity resolveBankAccount(@Body @Valid final AccountResRequest accountResRequest) throws Throwable {
        AcctResolutionRes ptres = bankBeneficiaryService.resolveAccount(accountResRequest);
        return ResponseEntity.ok(ptres);
    }

    @GetMapping("/")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity getBanks() throws Throwable {
        final var paymentPlatform = ((IPaymentPlatform) bankBeneficiaryService);
        return ResponseEntity.ok(paymentPlatform.getBanks());
    }

}
