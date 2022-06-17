/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kelompok_6.suggestme_model;

import java.sql.Connection;

/**
 *
 * @author wwijo
 */
public interface DBInterface {
    public void setConnection();
    public Connection getConnection();
}
