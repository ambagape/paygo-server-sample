package com.gitittech.paygo.springservicegateway.controllers;

import com.gitittech.paygo.settings.api.ISetting;
import com.gitittech.paygo.settings.api.dtos.Setting;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SettingsController {

    private final ISetting settingsService;

    @Autowired
    public SettingsController(ISetting settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping("/settings")
    @SecurityRequirement(name = "Bearer Authentication")
    public Page<Setting> getSettings(@RequestParam(value = "isSearch", defaultValue = "false") Boolean isSearch,
            @RequestParam(value = "filter", defaultValue = "") String filter,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "property") List<String> properties) throws Exception {
        return settingsService.getSettings(isSearch, filter, page, size, direction, properties);
    }

}