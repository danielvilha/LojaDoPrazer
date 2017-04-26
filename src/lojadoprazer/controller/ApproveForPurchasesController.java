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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojadoprazer.Util;
import lojadoprazer.dto.ApproveForPurchase;
import lojadoprazer.dto.ApproveForPurchases;
import lojadoprazer.dto.ProductItem;
import lojadoprazer.dto.ProductsItens;
import lojadoprazer.menu.MenuCompany;

/**
 *
 * @author danielvilha
 */
public class ApproveForPurchasesController {
    
    public void printListApproveForPurchases(int id) {
        try {
            ApproveForPurchases approveForPurchasesList = Util.getApproveForPurchases();
            
            if (approveForPurchasesList != null && approveForPurchasesList.getApproveForPurchases() != null && !approveForPurchasesList.getApproveForPurchases().isEmpty()) {
                System.out.println("Você tem " + approveForPurchasesList.getApproveForPurchases().size() + " item(ns) para aprovar a compra.\n");

                if (approveForPurchasesList.getApproveForPurchases().size() > 0) {
                    ArrayList<ProductItem> productItemList = new ArrayList<>();
                    for (ApproveForPurchase item : approveForPurchasesList.getApproveForPurchases()) {
                        productItemList.add(new ProductItemController().getProductItemById(item.getId()));
                    }

                    Scanner scanner = new Scanner(System.in);
                    int selection = 0;

                    do {
                        System.out.println("********* APROVAR A COMPRA? *********");
                        System.out.println("[1] SIM");
                        System.out.println("[2] NÃO (Os itens serão removidos da lista!)");

                        System.out.print("Item selecionado: ");
                        selection = scanner.nextInt();

                        switch (selection) {
                            case 1:
                                System.out.print("Adicionando itens ao estoque!");
                                aprovePurchase(productItemList);
                                new MenuCompany().createMenu(id);
                                break;
                            case 2:
                                System.out.println("Compra reprovada, os itens serão removidos da lista.");
                                reprovePurchase();
                                new MenuCompany().createMenu(id);
                                break;
                            default:
                                System.out.println("Item selecionado é inválido");
                                break;
                        }
                    } while (selection != 2);
                }
            } else {
                System.out.println("Você não tem compras para aprovar.");
            }
        } catch (Exception ex) {
            Logger.getLogger(ApproveForPurchasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void aprovePurchase(ArrayList<ProductItem> pItemList) {
        try {
            ProductsItens productItemList = Util.getProductsItens();
            
            for (ProductItem itemI : pItemList) {
                int index = 0;
                for (ProductItem itemJ : productItemList.getProductsItens()) {
                    if (itemI.getId() == itemJ.getId()) {
                        productItemList.getProductsItens().get(index).setAmount(itemJ.getAmount() + 1);
                    }
                    
                    index++;
                }
            }
            
            File xmlFile = new File("./src/lojadoprazer/xml/product.xml");
            XStream xStream = new XStream();
            OutputStream outputStream = new FileOutputStream(xmlFile);
            Writer writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
            
            xStream.toXML(productItemList, writer);
            System.out.println("Itens comprados com sucesso!");
            reprovePurchase();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ApproveForPurchasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void reprovePurchase() {
        try {
            File xmlFile = new File("./src/lojadoprazer/xml/approveForPurchases.xml");
            XStream xStream = new XStream();
            OutputStream outputStream = new FileOutputStream(xmlFile);
            Writer writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
            
            xStream.toXML(new ApproveForPurchases(), writer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ApproveForPurchasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
