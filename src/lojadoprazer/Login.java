/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer;

import lojadoprazer.user.User;
import java.io.BufferedReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import lojadoprazer.menu.MenuClient;
import lojadoprazer.menu.MenuCompany;
import lojadoprazer.menu.MenuEmployee;
import org.xml.sax.SAXException;

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
            File fXmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/user/users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("user");
            User u = User.existLogin(nList, inpUser, inpPass);

            if (u != null && u.getLogin() != null) {
                System.out.println();
                System.out.println();
                System.out.println();
                Util.clearConsole();
                switch(u.getUserType()) {
                    case 1:
                        MenuEmployee.createMenuEmployee(u.getUid());
                        break;
                    case 2:
                        MenuClient.createMenuClient(u.getUid());
                        break;
                    case 3:
                        MenuCompany.createMenuCompany(u.getUid());
                        break;
                }
            } else {
                System.out.print("Login ou senha inválidos!\n");
                Util.clearConsole();
                initLogin();
            }
            
        } catch (IOException | ParserConfigurationException | SAXException e) {
            
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
