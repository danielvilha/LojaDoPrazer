/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.purchase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lojadoprazer.menu.MenuCompany;
import lojadoprazer.product.ProductItem;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author danielvilha
 */
public class ApproveForPurchases {
    
    
    public void listApproveForPurchases(int id) {
        try {
            File fXmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/purchase/approveForPurchases.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("product");
            ArrayList<ProductItem> pItemList = new ArrayList<>();
            if (nList.getLength() > 0) {
                System.out.println("Você tem " + nList.getLength() + " item(ns) para aprovar a compra.");
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        ProductItem item = new ProductItem().getProductItemById(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
                        pItemList.add(item);
                        System.out.println("Nome do Produto: " + item.getItemName());
                    }
                }
                
                Scanner scanner = new Scanner(System.in);
                int selection = 0;
                
                do {
                    System.out.println("********* APROVAR A COMPRA? *********");
                    System.out.println("[1] SIM");
                    System.out.println("[2] NÃO");
                    
                    System.out.print("Item selecionado: ");
                    selection = scanner.nextInt();

                    switch (selection) {
                        case 1:
                            System.out.print("Adicionando itens ao estoque!");
                            aprovePurchase(pItemList);
                            MenuCompany.createMenuCompany(id);
                            break;
                        case 2:
                            System.out.println("Compra reprovada, os itens serão removidos da lista.");
                            reprovePurchase(doc);
                            MenuCompany.createMenuCompany(id);
                            break;
                        default:
                            System.out.println("Item selecionado é inválido");
                            break;
                    }
                } while (selection != 2);
            } else {
                System.out.println("Você não tem produtos para aprovar a compra!");
            }
            
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void aprovePurchase(ArrayList<ProductItem> pItemList) {
        try {
            File fXmlFile = new File("/Users/danielvilha/Developer/Projects/Loja/LojaDoPrazer/src/lojadoprazer/product/products.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("product");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    for (int j = 0; j < pItemList.size(); j++) {
                        if (Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()) == pItemList.get(j).getId()) {
                            int amount = Integer.parseInt(eElement.getElementsByTagName("amount").item(0).getTextContent()) + 1;
                            eElement.getElementsByTagName("amount").item(0).setNodeValue(String.valueOf(amount));
                        }
                    }
                }
            }
            
            System.out.println("Itens adicionado com sucesso!");
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private void reprovePurchase(Document doc) {
        try {
            NodeList nList = doc.getElementsByTagName("product");
            if (nList.getLength() > 0) {
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    doc.removeChild(nNode);
                }
            }
        } catch (DOMException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
