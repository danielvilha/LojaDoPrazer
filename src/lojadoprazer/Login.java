/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojadoprazer.controller.UserController;
import lojadoprazer.dto.User;
import lojadoprazer.dto.Users;
import lojadoprazer.menu.MenuClient;
import lojadoprazer.menu.MenuCompany;
import lojadoprazer.menu.MenuEmployee;

/**
 *
 * @author danielvilha
 */
public class Login {
    
    public final static void initLogin() {
        System.out.println("//                      ██████╗ ███████╗███╗   ███╗    ██╗   ██╗██╗███╗   ██╗██████╗  ██████╗                             ");
        System.out.println("//                      ██╔══██╗██╔════╝████╗ ████║    ██║   ██║██║████╗  ██║██╔══██╗██╔═══██╗                            ");
        System.out.println("//                      ██████╔╝█████╗  ██╔████╔██║    ██║   ██║██║██╔██╗ ██║██║  ██║██║   ██║                            ");
        System.out.println("//                      ██╔══██╗██╔══╝  ██║╚██╔╝██║    ╚██╗ ██╔╝██║██║╚██╗██║██║  ██║██║   ██║                            ");
        System.out.println("//                      ██████╔╝███████╗██║ ╚═╝ ██║     ╚████╔╝ ██║██║ ╚████║██████╔╝╚██████╔╝                            ");
        System.out.println("//                      ╚═════╝ ╚══════╝╚═╝     ╚═╝      ╚═══╝  ╚═╝╚═╝  ╚═══╝╚═════╝  ╚═════╝                             ");
        System.out.println("//                                                                                                                        ");
        System.out.println("//   █████╗     ██╗      ██████╗      ██╗ █████╗     ██████╗  ██████╗     ██████╗ ██████╗  █████╗ ███████╗███████╗██████╗ ");
        System.out.println("//  ██╔══██╗    ██║     ██╔═══██╗     ██║██╔══██╗    ██╔══██╗██╔═══██╗    ██╔══██╗██╔══██╗██╔══██╗╚══███╔╝██╔════╝██╔══██╗");
        System.out.println("//  ███████║    ██║     ██║   ██║     ██║███████║    ██║  ██║██║   ██║    ██████╔╝██████╔╝███████║  ███╔╝ █████╗  ██████╔╝");
        System.out.println("//  ██╔══██║    ██║     ██║   ██║██   ██║██╔══██║    ██║  ██║██║   ██║    ██╔═══╝ ██╔══██╗██╔══██║ ███╔╝  ██╔══╝  ██╔══██╗");
        System.out.println("//  ██║  ██║    ███████╗╚██████╔╝╚█████╔╝██║  ██║    ██████╔╝╚██████╔╝    ██║     ██║  ██║██║  ██║███████╗███████╗██║  ██║");
        System.out.println("//  ╚═╝  ╚═╝    ╚══════╝ ╚═════╝  ╚════╝ ╚═╝  ╚═╝    ╚═════╝  ╚═════╝     ╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝  ╚═╝");
        System.out.println();
        String username = getUserName("Username: ");
        String password = getPassword("Password:");
        runLogin(username, password);
    }
    
    static void runLogin(String inpUser, String inpPass) {
        try {
            Users userList = Util.getUsers();
            
            User u = UserController.existLogin(userList, inpUser, inpPass);

            if (u != null && u.getLogin() != null) {
                System.out.println();
                System.out.println();
                System.out.println();
                Util.clearConsole();
                switch(u.getType()) {
                    case 1:
                        new MenuEmployee().createMenu(u.getId());
                        break;
                    case 2:
                        new MenuClient().createMenu(u.getId());
                        break;
                    case 3:
                        new MenuCompany().createMenu(u.getId());
                        break;
                }
            } else {
                System.out.print("Login ou senha inválidos!\n");
                Util.clearConsole();
                initLogin();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    static String getUserName(String prompt){
        String username = "";
        System.out.print(prompt);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            username = br.readLine();
        } 
        catch (IOException e) {
            System.out.println("Error trying to read your name!");
            System.exit(1);
        }
        return username;
    }
 
 
    static String getPassword(String prompt) {
        String password = "";
        System.out.print(prompt + " ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            password = in.readLine();
        }
        catch (IOException e){
            System.out.println("Error trying to read your password!");
            System.exit(1);
        }
 
        System.out.print("\b");
 
        return password;
    }
}
