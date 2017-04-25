/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.controller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojadoprazer.Util;
import lojadoprazer.dto.ProductItem;
import lojadoprazer.dto.ProductsItens;
import lojadoprazer.enums.ProductType;


/**
 *
 * @author danielvilha
 */
public class ProductItemController {
    
    public ProductItem getProductItemById(int id) {
        try {
            ProductItem productItem = new ProductItem();
            ProductsItens productItemList = Util.getProductsItens();
            
            for (ProductItem item : productItemList.getProductsItens()) {
                if (item.getId() == id) {
                    productItem = item;
                }
            }
            
            return productItem;
        } catch (Exception ex) {
            Logger.getLogger(ProductItemController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void printProductItemList() {
        try {
            ProductsItens productItemList = Util.getProductsItens();
            
            for (ProductItem item : productItemList.getProductsItens()) {
                    System.out.println(item.toString());
                    System.out.println();
            } 
        } catch (Exception ex) {
            Logger.getLogger(ProductItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printProductItemList(String listStringId) {
        try {
            ProductsItens productItemList = getProductItemArray(listStringId);
            
            for (ProductItem item : productItemList.getProductsItens()) {
                    System.out.println(item.toString());
                    System.out.println();
            } 
        } catch (Exception ex) {
            Logger.getLogger(ProductItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ProductsItens getProductItemArray(String listStringId) {
        ProductsItens list = new ProductsItens();
        String[] strArray = listStringId.split(",");
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        
        if (intArray.length > 0) {
            for (int i = 0; i < intArray.length; i++) {
                list.getProductsItens().add(getProductItemById(intArray[i]));
            }
        }
        
        return list;
    }
    
    public Double valueOfPurchase(String listStringId) {
        Double value = 0.0;
        ProductsItens pList = getProductItemArray(listStringId);
        
        for (int i = 0; i < pList.getProductsItens().size(); i++) {
            value += pList.getProductsItens().get(i).getValue();
        }
        
        return value;
    }
    
    public String listOfProducts(ArrayList<Integer> list) {
        String result = "";
        for (int item : list) {
            result += item;
            if(item == list.get(list.size())) {
                result += ",";
            }
        }
        return result;
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
