/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.commons;

/**
 *
 * @author ambag
 */
public class BearerTokenMaker {
    
    public static String create(String token){
        return "Bearer "+token;
    }
}
