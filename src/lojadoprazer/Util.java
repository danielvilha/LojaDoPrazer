/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import lojadoprazer.dto.ApproveForPurchase;
import lojadoprazer.dto.ApproveForPurchases;
import lojadoprazer.dto.Employee;
import lojadoprazer.dto.Employees;
import lojadoprazer.dto.ProductItem;
import lojadoprazer.dto.ProductsItens;
import lojadoprazer.dto.Purchase;
import lojadoprazer.dto.Purchases;
import lojadoprazer.dto.User;
import lojadoprazer.dto.Users;

/**
 *
 * @author danielvilha
 */
public class Util {
    
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public final static Users getUsers() {
        File xmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/xml/user.xml");
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("Users", Users.class);      
        xstream.addImplicitCollection(Users.class, "users");
        xstream.alias("User", User.class);

        Users list = xstream.fromXML(xmlFile) != null ? (Users) xstream.fromXML(xmlFile) : new Users();
        
        return list;
    }
    
    public final static ProductsItens getProductsItens() {
        File xmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/xml/product.xml");
        XStream xstream = new XStream();
        xstream.alias("ProductsItens", ProductsItens.class);      
        xstream.addImplicitCollection(ProductsItens.class, "productsItens");
        xstream.alias("ProductItem", ProductItem.class);
        
        ProductsItens list = xstream.fromXML(xmlFile) != null ? (ProductsItens) xstream.fromXML(xmlFile) : new ProductsItens();
        
        return list;
    }
    
    public final static Employees getEmployees() {
        File xmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/xml/employee.xml");
        XStream xstream = new XStream();
        xstream.alias("Employees", Employees.class);      
        xstream.addImplicitCollection(Employees.class, "employees");
        xstream.alias("Employee", Employee.class);
        
        Employees list = xstream.fromXML(xmlFile) != null ? (Employees) xstream.fromXML(xmlFile) : new Employees();
        
        return list;
    }
    
    public final static Purchases getPurchases() {
        File xmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/xml/purchase.xml");
        XStream xstream = new XStream();
        xstream.alias("Purchases", Purchases.class);      
        xstream.addImplicitCollection(Purchases.class, "purchases");
        xstream.alias("Purchase", Purchase.class);
        
        Purchases list = xstream.fromXML(xmlFile) != null ? (Purchases) xstream.fromXML(xmlFile) : new Purchases();
        
        return list;
    }
    
    public final static ApproveForPurchases getApproveForPurchases() {
        File xmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/xml/approveForPurchases.xml");
        XStream xstream = new XStream();
        xstream.alias("ApproveForPurchases", ApproveForPurchases.class);      
        xstream.addImplicitCollection(ApproveForPurchases.class, "approveForPurchases");
        xstream.alias("ApproveForPurchase", ApproveForPurchase.class);
        
        ApproveForPurchases list = xstream.fromXML(xmlFile) != null ? (ApproveForPurchases) xstream.fromXML(xmlFile) : new ApproveForPurchases(); 
        
        return list;
    }
}
