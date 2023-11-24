/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.commons.seeders;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ambag
 */
public class SeederExecutor {

    private final Set<Class<? extends ISeeder>> seeders;

    public SeederExecutor(Set<Class<? extends ISeeder>> seeders) {
        this.seeders = seeders;
    }

    public void execute() {
        seeders.forEach(seeder -> {
            try {
                ISeeder instance = (ISeeder) seeder.getDeclaredConstructor().newInstance();
                instance.runDemo();
            } catch (Exception ex) {
                Logger.getLogger(SeederExecutor.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
    }
}
