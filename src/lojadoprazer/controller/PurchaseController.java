/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.controller;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojadoprazer.dto.ProductItem;
import lojadoprazer.dto.ProductsItens;
import lojadoprazer.dto.Purchase;
import lojadoprazer.enums.PurchaseType;

/**
 *
 * @author danielvilha
 */
public class PurchaseController {
    
    public Purchase getPurchaseById(int id) {
        Purchase purchase = new Purchase();
        try {
            File xmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/xml/purchase.xml");
            XStream xstream = new XStream();
            ArrayList<Purchase> purchaseList = (ArrayList) xstream.fromXML(new FileInputStream(xmlFile));
            
            for (Purchase item : purchaseList) {
                if (item.getId() == id) {
                    purchase = item;
                }
            }
            return purchase;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Purchase> getPurchaseList() {
        try {
            File xmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/xml/purchase.xml");
            XStream xstream = new XStream();
            ArrayList<Purchase> purchaseList = (ArrayList) xstream.fromXML(new FileInputStream(xmlFile));
            
            return purchaseList;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void printPurchaseList(String listStringId) {
        ProductsItens list = new ProductItemController().getProductItemArray(listStringId);
        
        if (!list.getProductsItens().isEmpty()) {
            for(int i = 0; i < list.getProductsItens().size(); i++) {
                String id = true == validaEstoque(list.getProductsItens().get(i).getAmount()) ? String.valueOf(list.getProductsItens().get(i).getId()) : "Item Indisponível";
                System.out.println("ID: " + id);
                System.out.println("Produto: " + list.getProductsItens().get(i).getItemName() + " Valor R$" + list.getProductsItens().get(i).getValue());
            }
            
            System.out.println();
        }
    }
    
    private boolean validaEstoque(int value) {
        if (value == 0) {
            return false;
        }
        return true;
    }
    
    public void buy(int id) {
        new ProductItemController().printProductItemList();
        
        Scanner scanner = new Scanner(System.in);
        int selection = -1;
        ArrayList<Integer> productList = new ArrayList<>();
        do {
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*        COMPRAR PRODUTOS       *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            System.out.println("[0] PARA SAIR");
            
            System.out.print("ID do produto que deseja comprar: ");
            selection = scanner.nextInt();
            switch (selection) {
                default:
                    productList.add(selection);
                    break;
            }
            
            
        } while (selection == 0);
        
        System.out.println();
        System.out.println();
        
        System.out.println("Selecione o Funcionário que está auxiliando na compra!");
        new EmployeeController().printEmployeeList();
        selection = -1;
        System.out.print("ID do funcionário: ");
        selection = scanner.nextInt();
        
        finishBuy(id, selection, productList);
    }
    
    private void finishBuy(int clientId, int employeeId, ArrayList<Integer> productList) {
        try {
            File xmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/xml/purchase.xml");
            XStream xstream = new XStream();OutputStream outputStream = new FileOutputStream(xmlFile);
            Writer writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
            
            ArrayList<Purchase> purchaseList = (ArrayList) xstream.fromXML(new FileInputStream(xmlFile));
            
            Double value = 0.0;
            Purchase purchaseItem = new Purchase();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            purchaseItem.setId(productList.size() + 1);
            purchaseItem.setPurchaseDate(dtf.format(now));
            purchaseItem.setPurchaseType(PurchaseType.venda);
            purchaseItem.setClient(UserController.getUserById(clientId));
            purchaseItem.setEmployee(new EmployeeController().getEmployeeById(employeeId));
            
            for(int item : productList) {
                ProductItem p = new ProductItemController().getProductItemById(item);
                value += p.getValue();
            }
            
            purchaseItem.setValue(value);
            purchaseItem.setProductList(new ProductItemController().listOfProducts(productList));
            
            purchaseList.add(purchaseItem);
            
            xstream.toXML(purchaseList, writer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
