/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lojadoprazer.enums.ProductType;

/**
 *
 * @author danielvilha
 */
@XStreamAlias("productItem")
public class ProductItem {
    
    @XStreamAlias("id")
    @XStreamAsAttribute
    private int id;
    
    @XStreamAlias("sku")
    @XStreamAsAttribute
    private int sku;
    
    @XStreamAlias("amount")
    @XStreamAsAttribute
    private int amount;
    
    @XStreamAlias("type")
    @XStreamAsAttribute
    private ProductType type;
    
    @XStreamAlias("value")
    @XStreamAsAttribute
    private double value;
    
    @XStreamAlias("itemName")
    @XStreamAsAttribute
    private String itemName;
    
    @XStreamAlias("itemDescription")
    @XStreamAsAttribute
    private String itemDescription;

    public ProductItem() {
    }

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
        return type;
    }

    public void setProductType(ProductType productType) {
        this.type = productType;
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

    @Override
    public String toString() {
        return  "Id: " + id + "\n" +
                "SKU: " + sku + "\n" +
                "Quantidade: " + amount + "\n" +
                "Tipo: " + type + "\n" +
                "Valor: R$" + value + "\n" +
                "Nome: " + itemName + "\n" +
                "Descrição: " + itemDescription;
    }
    
    public String toStringSemQuantidade() {
        return  "Id: " + id + "\n" +
                "SKU: " + sku + "\n" +
                "Tipo: " + type + "\n" +
                "Valor: R$" + value + "\n" +
                "Nome: " + itemName + "\n" +
                "Descrição: " + itemDescription;
    }
}
