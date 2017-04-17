/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class ProductItem {
    
    private int id;
    private int sku;
    private int amount;
    private ProductType productType;
    private double value;
    private String itemName;
    private String itemDescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    
    public ArrayList<ProductItem> getProductItemArray(String listStringId) {
        ArrayList<ProductItem> list = new ArrayList<ProductItem>();
        String[] strArray = listStringId.split(",");
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        
        if (intArray.length > 0) {
            for (int i = 0; i < intArray.length; i++) {
                list.add(getProductItemById(intArray[i]));
            }
        }
        
        return list;
    }
    
    public ProductItem getProductItemById(int id) {
        ProductItem item = new ProductItem();
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
                    if (Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()) == id) {
                        item.setId(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
                        item.setSku(Integer.parseInt(eElement.getElementsByTagName("sku").item(0).getTextContent()));
                        item.setAmount(Integer.parseInt(eElement.getElementsByTagName("amount").item(0).getTextContent()));
                        item.setProductType(toProductType(eElement.getElementsByTagName("productType").item(0).getTextContent()));
                        item.setValue(Double.parseDouble(eElement.getElementsByTagName("value").item(0).getTextContent()));
                        item.setItemName(eElement.getElementsByTagName("itemName").item(0).getTextContent());
                        item.setItemDescription(eElement.getElementsByTagName("itemDescription").item(0).getTextContent());
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return item;
    }

    public ProductType toProductType(String pType) {
        if (pType.contains("acessorio")) {
            return ProductType.acessorio;
        } else if (pType.contains("cosmetico")) {
            return ProductType.cosmetico;
        } else if (pType.contains("lingerie")) {
            return ProductType.lingerie;
        } else if (pType.contains("masturbador")) {
            return ProductType.masturbador;
        } else {
            return ProductType.vibrador;
        }
    }
}
