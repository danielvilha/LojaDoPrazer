/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.controller;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojadoprazer.Util;
import lojadoprazer.dto.User;
import lojadoprazer.dto.Users;
import lojadoprazer.enums.TypeName;

/**
 *
 * @author danielvilha
 */
public class UserController {
    
    public static User existLogin(Users uList, String login, String password) {
        
        for (User user : uList.getUsers()) {
            if (user.getLogin().contains(login)) {
                if (user.getPassword().contains(password)) {
                    return user;
                }
            }
        }
        
        return null;
    }
    
    public static User getUserById(int id) {
        try {
            User user = new User();
            Users userList = Util.getUsers();
            
            for (User u : userList.getUsers()) {
                if (u.getId() == id) {
                    user = u;
                }
            }
            
            return user;
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int createUser(User user) {
        try {
            Users userList = Util.getUsers();
            user.setId(userList.getUsers().size() + 1);
            
            userList.getUsers().add(user);
            
            File xmlFile = new File("./src/lojadoprazer/xml/user.xml");
            XStream xStream = new XStream();
            OutputStream outputStream = new FileOutputStream(xmlFile);
            Writer writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
            xStream.toXML(userList.getUsers(), writer);
            
            return user.getId();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public static TypeName toTypeName(String tName) {
        if (tName.equals("client")) {
            return TypeName.client;
        } else if (tName.equals("employee")) {
            return TypeName.employee;
        } else if (tName.equals("gerente")) {
            return TypeName.gerente;
        } else {
            return TypeName.diretor;
        }
    }
}
    