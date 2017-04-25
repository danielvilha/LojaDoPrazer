/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lojadoprazer.controller.ProductItemController;
import lojadoprazer.enums.PurchaseType;

/**
 *
 * @author danielvilha
 */
@XStreamAlias("productItem")
public class Purchase {
    
    @XStreamAlias("id")
    @XStreamAsAttribute
    private int id;
    
    @XStreamAlias("value")
    @XStreamAsAttribute
    private double value;
    
    @XStreamAlias("clientId")
    @XStreamAsAttribute
    private int clientId;
    
    @XStreamAlias("purchaseDate")
    @XStreamAsAttribute
    private String purchaseDate;
    
    @XStreamAlias("employeeId")
    @XStreamAsAttribute
    private int employeeId;
    
    @XStreamAlias("purchaseType")
    @XStreamAsAttribute
    private PurchaseType purchaseType;
    
    @XStreamAlias("productList")
    @XStreamAsAttribute
    private String productList;
    
    @XStreamOmitField
    private User client;
    
    @XStreamOmitField
    private Employee employee;

    public Purchase() {
    }

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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }
    
    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    private String toStringProductList(String products) {
        String listString = "";
        ProductsItens itens = new ProductItemController().getProductItemArray(products);
        
        for (ProductItem item : itens.getProductsItens()) {
            listString += (item.toString() + "\n");
        }
        
        return listString;
    }

    @Override
    public String toString() {
        return  "Id: " + id + "\n" +
                "Valor: R$" + value + "\n" +
                "Data: " + purchaseDate + "\n" +
                "Tipo: " + purchaseType + "\n" +
                "----- Cliente \n" +
                "Cliente: " + client.toString() + "\n" +
                "----- Funcionário \n" +
                "Funcionário: " + employee.toString() + "\n" +
                "----- Produtos \n" +
                "Lista de produtos: \n" + toStringProductList(productList);
    }
}
