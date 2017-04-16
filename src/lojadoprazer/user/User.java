/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.user;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author danielvilha
 */
public class User {
    
    private int uid;
    private int userType;
    private String cpf;
    private String login;
    private String password;
    private String userName;
    
    public User() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    
    public static User existLogin(NodeList nList, String login, String password) {
        User user = new User();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getElementsByTagName("login").item(0).getTextContent() == null ? login == null : eElement.getElementsByTagName("login").item(0).getTextContent().equals(login)) {
                    if (eElement.getElementsByTagName("password").item(0).getTextContent() == null ? password == null : eElement.getElementsByTagName("password").item(0).getTextContent().equals(password)) {
                        user.setUid(Integer.parseInt(eElement.getElementsByTagName("uid").item(0).getTextContent()));
                        user.setUserType(Integer.parseInt(eElement.getElementsByTagName("type").item(0).getTextContent()));
                        user.setCpf(eElement.getElementsByTagName("cpf").item(0).getTextContent());
                        user.setLogin(eElement.getElementsByTagName("login").item(0).getTextContent());
                        user.setPassword(eElement.getElementsByTagName("password").item(0).getTextContent());
                        user.setUserName(eElement.getElementsByTagName("userName").item(0).getTextContent());
                    }
                }
            }
         }
        return user;
    }
    
    public static User getUserById(int uid) {
        User user = new User();
        try {
            File fXmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/user/users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("user");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (Integer.parseInt(eElement.getElementsByTagName("uid").item(0).getTextContent()) == uid) {
                        user.setUid(Integer.parseInt(eElement.getElementsByTagName("uid").item(0).getTextContent()));
                        user.setUserType(Integer.parseInt(eElement.getElementsByTagName("type").item(0).getTextContent()));
                        user.setLogin(eElement.getElementsByTagName("login").item(0).getTextContent());
                        user.setPassword(eElement.getElementsByTagName("password").item(0).getTextContent());
                        user.setUserName(eElement.getElementsByTagName("userName").item(0).getTextContent());
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return user;
    }
}
