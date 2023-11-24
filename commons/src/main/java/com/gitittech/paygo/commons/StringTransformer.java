/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.commons;

/**
 *
 * @author ambag
 */
public class StringTransformer {

    public static String toCamelCase(String word) {
        final var builder = new StringBuilder();
        final var firstLetter = word.subSequence(0, 1);
        final var otherPart = word.substring(1);
        builder.append(firstLetter.toString().toLowerCase());
        builder.append(otherPart);
        return builder.toString();
    }
}
