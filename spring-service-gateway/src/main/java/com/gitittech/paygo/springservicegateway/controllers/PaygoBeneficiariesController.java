package com.gitittech.paygo.springservicegateway.controllers;

import com.gitittech.paygo.beneficiary.api.IBeneficiary;
import com.gitittech.paygo.beneficiary.dtos.BankBeneficiary;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paygo")
public class PaygoBeneficiariesController {

    private final IBeneficiary paygoBeneficiaryService;

    @Autowired
    public PaygoBeneficiariesController(@Qualifier("paygo") IBeneficiary paygoBeneficiaryService) {
        this.paygoBeneficiaryService = paygoBeneficiaryService;
    }

    @GetMapping("/beneficiaries")
    public ResponseEntity getPaygoBeneficiaries(
            @RequestParam(value = "isSearch", defaultValue = "false") Boolean isSearch,
            @RequestParam(value = "filter", defaultValue = "") String filter,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "property") List<String> properties) throws Throwable {
            return ResponseEntity.ok(paygoBeneficiaryService.getAllBeneficiaries(isSearch, filter, page, size, direction, properties));
      
    }

}
