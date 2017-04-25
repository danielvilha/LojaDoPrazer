/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.controller;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojadoprazer.Util;
import lojadoprazer.dto.Employee;
import lojadoprazer.dto.ProductItem;
import lojadoprazer.dto.ProductsItens;
import lojadoprazer.dto.Purchase;
import lojadoprazer.dto.Purchases;
import lojadoprazer.dto.User;
import lojadoprazer.dto.Users;
import lojadoprazer.enums.EmployeeType;
import lojadoprazer.enums.TypeName;
import lojadoprazer.menu.MenuCompany;

/**
 *
 * @author danielvilha
 */
public class CompanyController {
    
    public void printListProducts() {
        try {
            ProductsItens productItemList = Util.getProductsItens();
            
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*       LISTA DE PRODUTOS       *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            
            for (ProductItem item : productItemList.getProductsItens()) {
                System.out.println(item.toString());
                System.out.println();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printListCustomers() {
        try {
            Users userList = Util.getUsersList();
            
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*       LISTA DE CLIENTES       *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            
            for (User item : userList.getUsers()) {
                if (item.getType() == 2) {
                    System.out.println(item.toString());
                    System.out.println();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printListEmployees() {
        try {
            Users userList = Util.getUsersList();
            ArrayList<Employee> employeeList = new ArrayList<>();
            
            for (User user : userList.getUsers()) {
                if (user.getType() == 1 || user.getType() == 3) {
                    employeeList.add(new EmployeeController().getEmployeeById(user.getId()));
                }
            }
            
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*     LISTA DE FUNCIONÁRIOS     *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            
            for (Employee emp : employeeList) {
                System.out.println(emp.toString());
                System.out.println();
            }
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printListPurchase() {
        try {
            Purchases purchaseList = Util.getPurchases();
            Double valueTotal = 0.0;
            
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*        LISTA DE VENDAS        *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            
            for (Purchase purchase : purchaseList.getPurchases()) {
                purchase.setClient(UserController.getUserById(purchase.getClientId()));
                purchase.setEmployee(new EmployeeController().getEmployeeById(purchase.getEmployeeId()));
                System.out.println(purchase.toString());
                valueTotal += purchase.getValue();
                System.out.println();
            }
            
            System.out.println("------- ARRECADAÇÃO TOTAL: R$" + valueTotal);
            System.out.println();
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createEmployee(int companyId) {
        Employee emp = new Employee();
        emp.setUser(new User());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Nome do Empregado: ");
            emp.getUser().setUserName(br.readLine());
            System.out.print("CPF do Empregado: ");
            emp.getUser().setCpf(br.readLine());
            System.out.print("Login do Empregado: ");
            emp.getUser().setLogin("e." + br.readLine());
            System.out.print("Senha do Empregado: ");
            emp.getUser().setPassword(br.readLine());
            System.out.print("Salário do Empregado: ");
            emp.setSalario(Double.parseDouble(br.readLine()));
            System.out.print("Matricula do Empregado: ");
            emp.setMatricula(Integer.parseInt(br.readLine()));
            
            emp.getUser().setType(1);
            emp.getUser().setTypeName(TypeName.employee);
            System.out.print("Tipo do Empregado: [1] Funcionario [2] Gerente [3] Diretor: ");
            int eType = Integer.parseInt(br.readLine());
            switch (eType) {
                case 1:
                    emp.setEmployeeType(EmployeeType.funcionario);
                    break;
                case 2:
                    emp.setEmployeeType(EmployeeType.gerente);
                    break;
                case 3:
                    emp.setEmployeeType(EmployeeType.diretor);
                    break;
            }
            
            new EmployeeController().createUser(emp);
        } 
        catch (IOException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        new MenuCompany().createMenu(companyId);
    }
}
