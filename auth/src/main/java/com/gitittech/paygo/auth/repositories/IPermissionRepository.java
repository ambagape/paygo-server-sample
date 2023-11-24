package com.gitittech.paygo.auth.repositories;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.gitittech.paygo.entities.JpaPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ambag
 */
@Repository
public interface IPermissionRepository extends JpaRepository<JpaPermission, String>{
    
}
