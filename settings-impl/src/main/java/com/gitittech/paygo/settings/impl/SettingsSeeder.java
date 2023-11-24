/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.settings.impl;

import com.gitittech.paygo.commons.seeders.ISeeder;
import com.gitittech.paygo.entities.JpaSetting;
import com.gitittech.paygo.entities.enums.SettingName;
import com.gitittech.paygo.settings.api.ISettingsReadRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ambag
 */
@Component
public class SettingsSeeder implements ISeeder{

    private final ISettingsCommandRepository commandRepository;
    
    @Autowired
    SettingsSeeder(ISettingsCommandRepository commandRepository){
        this.commandRepository = commandRepository;
    }
    
    @Override
    public void run() {
        for(final var settingName: SettingName.values()){
            final var setting = new JpaSetting();
            setting.setName(settingName.name());
            setting.setValue("1000");
            this.commandRepository.save(setting);
        }
    }

    @Override
    public void runDemo() {
       
    }
    
}
