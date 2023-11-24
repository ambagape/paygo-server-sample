package com.gitittech.paygo.commons;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gitittech.paygo.commons.exceptions.ResourceNotFoundException;

import java.util.Optional;

/**
 * @author Ambrose Ariagiegbe
 */
public class EnvironmentVariables {

    public static final String CLIENT_URL = Optional.ofNullable(System.getenv("CLIENT_URL")).orElseThrow(() -> new ResourceNotFoundException("CLIENT_URL Env not configured"));
    public static final String BASE_URL = Optional.ofNullable(System.getenv("BASE_URL")).orElseThrow(() -> new ResourceNotFoundException("BASE_URL Env not configured"));
    public static final String ENABLE_DEBUG = Optional.ofNullable(System.getenv("ENABLE_DEBUG")).orElseThrow(() -> new ResourceNotFoundException("ENABLE_DEBUG Env not configured"));
}
