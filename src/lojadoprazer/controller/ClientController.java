/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojadoprazer.Util;
import lojadoprazer.dto.Purchase;
import lojadoprazer.dto.Purchases;
import lojadoprazer.dto.User;
import lojadoprazer.enums.TypeName;

/**
 *
 * @author danielvilha
 */
public class ClientController {
    
    public void createUser() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        User usr = new User();
        try {
            System.out.print("Nome do Cliente: ");
            usr.setUserName(br.readLine());
            System.out.print("CPF do Cliente: ");
            usr.setCpf(br.readLine());
            System.out.print("Login do Cliente: ");
            usr.setLogin(br.readLine());
            System.out.print("Senha do Cliente: ");
            usr.setPassword(br.readLine());
            usr.setType(2);
            usr.setTypeName(TypeName.client);
            
            new UserController().createUser(usr);
        } 
        catch (IOException e) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void listMyPurchases(int id) {
        try {
            Purchases purchaseList = Util.getPurchases();
            boolean havePurhcase = false;
            for (Purchase purchase : purchaseList.getPurchases()) {
                if (purchase.getClientId() == id) {
                    purchase.setClient(UserController.getUserById(id));
                    purchase.setEmployee(new EmployeeController().getEmployeeById(purchase.getEmployeeId()));
                    System.out.println(purchase.toString());
                    System.out.println();
                    havePurhcase = true;
                }
            }
            
            if (!havePurhcase) {
                System.out.println("Você ainda não tem itens comprados.");
                System.out.println();
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
