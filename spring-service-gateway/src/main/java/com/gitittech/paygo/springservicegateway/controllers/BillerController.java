package com.gitittech.paygo.springservicegateway.controllers;

import com.gitittech.paygo.beneficiary.api.IBiller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billers")
public class BillerController {

    private final IBiller billerService;

    @Autowired
    public BillerController(IBiller billerService) {
        this.billerService = billerService;
    }

    @GetMapping("/")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity getAllBills() throws Exception {
        return ResponseEntity.ok(billerService.getAllBillers());
    }

    @GetMapping("/validate-bill")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity validateBill(
            @RequestParam(value = "itemCode", defaultValue = "") String itemCode, 
            @RequestParam(value = "code", defaultValue = "") String code,
            @RequestParam(value = "customer", defaultValue = "") String customer) throws Exception {
        return ResponseEntity.ok(billerService.validate(itemCode, code, customer));
    }

}
