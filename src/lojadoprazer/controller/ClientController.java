/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.controller;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojadoprazer.dto.Purchase;
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
            File xmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/xml/purchase.xml");
            XStream xstream = new XStream();
            ArrayList<Purchase> purchaseList = (ArrayList) xstream.fromXML(new FileInputStream(xmlFile));

            for (Purchase purchase : purchaseList) {
                if (purchase.getClient().getId() == id) {
                    purchase.toString();
                    System.out.println();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
