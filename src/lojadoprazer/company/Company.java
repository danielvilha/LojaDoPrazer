/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lojadoprazer.employee.Employee;
import lojadoprazer.purchase.Purchase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author danielvilha
 */
public class Company extends Employee {
    
    public void listProducts() {
        try {
            File fXmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/product/products.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("product");
            
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*       LISTA DE PRODUTOS       *");
            System.out.println("*                               *");
            System.out.println("*********************************");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("ID: " + Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    System.out.println("SKU: " + eElement.getElementsByTagName("sku").item(0).getTextContent());
                    System.out.println("Tipo: " + eElement.getElementsByTagName("type").item(0).getTextContent());
                    System.out.println("Valor: " + eElement.getElementsByTagName("value").item(0).getTextContent());
                    System.out.println("Quantidade: " + eElement.getElementsByTagName("amount").item(0).getTextContent());
                    System.out.println("Nome: " + eElement.getElementsByTagName("itemName").item(0).getTextContent());
                    System.out.println("Descrição: " + eElement.getElementsByTagName("itemDescription").item(0).getTextContent());
                    System.out.println("\n");
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public void listCustomers() {
        try {
            File fXmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/user/users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("user");
            
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*       LISTA DE CLIENTES       *");
            System.out.println("*                               *");
            System.out.println("*********************************");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (Integer.parseInt(eElement.getElementsByTagName("type").item(0).getTextContent()) == 2) {
                        System.out.println("UID: " + Integer.parseInt(eElement.getElementsByTagName("uid").item(0).getTextContent()));
                        System.out.println("Nome: " + eElement.getElementsByTagName("userName").item(0).getTextContent());
                        System.out.println("CPF: " + eElement.getElementsByTagName("cpf").item(0).getTextContent());
                        System.out.println("\n");
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public void listEmployees() {
        try {
            File fXmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/user/users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("user");
            
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*     LISTA DE FUNCIONÁRIOS     *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            
            ArrayList<Integer> uidList = new ArrayList<>();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (Integer.parseInt(eElement.getElementsByTagName("type").item(0).getTextContent()) == 2) {
                        uidList.add(Integer.parseInt(eElement.getElementsByTagName("uid").item(0).getTextContent()));
                    }
                }
            }
            
            if (!uidList.isEmpty()) {
                for(int temp = 0; temp < uidList.size(); temp++) {
                    Employee emp = new Employee().getEmployeeById(temp);
                    System.out.println("UID: " + emp.getUid());
                    System.out.println("Matricula: " + emp.getMatricula());
                    System.out.println("Tipo do Empregado" + emp.getEmployeeType());
                    System.out.println("Salário: " + emp.getSalario());
                    System.out.println("CPF: " + emp.getCpf());
                    System.out.println("Nome: " + emp.getUserName());
                    System.out.println("\n");
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public void listPurchase() {
        ArrayList<Purchase> list = new Purchase().getPurchaseList();
        Double total = 0.0;
        
        System.out.println("*********************************");
        System.out.println("*                               *");
        System.out.println("*        LISTA DE VENDAS        *");
        System.out.println("*                               *");
        System.out.println("*********************************");
        
        if (list.size() < 0) {
            for (int i = 0; i < list.size(); i++) {
                Purchase item = list.get(i);
                System.out.println("ID: " + item.getId());
                System.out.println("Valor: " + item.getValue());
                System.out.println("Cliente: " + item.getClient().getUserName());
                System.out.println("Empregado: " + item.getEmployee().getUserName());
                System.out.println("Tipo: " + item.getPurchaseType());
                for (int j = 0; j < item.getProducts().size(); j++) {
                    System.out.println("Produto: " + item.getProducts().get(j).getItemName() +
                            " Valor: R$" + item.getProducts().get(j).getAmount());
                }
                System.out.println("\n");
                
                total += item.getValue();
            }
            
            System.out.println("---------------------------- Arrecadação total: R$" + total);
            System.out.println("\n");
        } else {
            System.out.println("Não existe compras cadastradas.");
        }
    }
    
    public void createEmployee() {
        
    }
}
