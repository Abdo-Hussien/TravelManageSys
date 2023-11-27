/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.asu.main;

import java.io.IOException;

import AccountManagement.UserSettings;

/**
 *
 * @author bmood
 */
public class TravelManageSys {

    public static void main(String[] args) throws IOException {
        System.out.println("Main Class is called 'TravelManageSys' is in the 'main' package");

        UserSettings user1=new UserSettings();
        //user1.create_acc();
        user1.login();
        
    }
}