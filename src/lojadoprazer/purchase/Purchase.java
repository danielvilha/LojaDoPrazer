package lojadoprazer.purchase;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lojadoprazer.client.Client;
import lojadoprazer.employee.Employee;
import lojadoprazer.product.ProductItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danielvilha
 */
public class Purchase {
    
    private int id;
    private double value;
    private Client client;
    private Date purchaseDate;
    private Employee employee;
    private ArrayList<ProductItem> products;
    private PurchaseType purchaseType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ArrayList<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductItem> products) {
        this.products = products;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }
    
    public Purchase getPurchaseById(int id) {
        Purchase pur = new Purchase();
        
        try {
            File fXmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/purchase/purchase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("purchase");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()) == id) {
                        pur.setId(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
                        pur.setValue(Double.parseDouble(eElement.getElementsByTagName("value").item(0).getTextContent()));
                        pur.setPurchaseDate(new Date(format.parse(eElement.getElementsByTagName("purchaseDate").item(0).getTextContent()).getTime()));
                        pur.setEmployee(new Employee().getEmployeeById(Integer.parseInt(eElement.getElementsByTagName("employeeId").item(0).getTextContent())));
                        pur.setProducts(new ProductItem().getProductItemArray(eElement.getElementsByTagName("productList").item(0).getTextContent()));
                        pur.setPurchaseType(toPurchaseType(eElement.getElementsByTagName("pruchaseType").item(0).getTextContent()));
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(Purchase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
        }
        
        return pur;
    }
    
    public ArrayList<Purchase> getPurchaseList() {
        ArrayList<Purchase> list = new ArrayList<>();
        
        try {
            File fXmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/purchase/purchase.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("purchase");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    list.add(getPurchaseById(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent())));
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        return list;
    }
    
    private PurchaseType toPurchaseType(String pType) {
        if (pType.contains("compra")) {
            return PurchaseType.compra;
        } else if (pType.contains("venda")) {
            return PurchaseType.venda;
        } else {
            return PurchaseType.troca;
        }
    }
}
