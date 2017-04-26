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
import lojadoprazer.dto.Employee;
import lojadoprazer.dto.Employees;
import lojadoprazer.enums.EmployeeType;
import lojadoprazer.menu.MenuEmployee;

/**
 *
 * @author danielvilha
 */
public class EmployeeController {
    
    public Employee getEmployeeById(int id) {
        Employee employee = new Employee();
        
        try {
            Employees employeeList = (Employees) Util.getEmployees();
            
            for (Employee emp : employeeList.getEmployees()) {
                if (emp.getId() == id) {
                    employee = emp;
                    employee.setUser(UserController.getUserById(employee.getId()));
                }
            }
            
            return employee;
        } catch (Exception ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int createUser(Employee employee) {
        try {
            Employees employeeList = Util.getEmployees();
            employee.setId(new UserController().createUser(employee.getUser()));
            
            employeeList.getEmployees().add(employee);
            
            File xmlFile = new File("./src/lojadoprazer/xml/employee.xml");
            XStream xStream = new XStream();
            OutputStream outputStream = new FileOutputStream(xmlFile);
            Writer writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
            xStream.toXML(employeeList.getEmployees(), writer);
            
            return employee.getId();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public void printSalario(int id) {
        Employee emp = getEmployeeById(id);
        
        if (emp != null) {
            System.out.println("Seu salário é de R$" + emp.getSalario());
        }
    }
    
    public void requestPurchase(int id) {
        new CompanyController().printListProducts();
        
        Scanner scanner = new Scanner(System.in);
        int selection = -1;
        ArrayList<Integer> productList = new ArrayList<>();
        
        do {
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*        SOLICITAR COMPRAS      *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            System.out.println("[0] PARA SAIR");
            
            System.out.print("ID do produto que deseja comprar: ");
            selection = scanner.nextInt();
            switch (selection) {
                case 0:
                    addRequestPurchase(productList);
                    new MenuEmployee().createMenu(id);
                    break;
                default:
                    productList.add(selection);
                    break;
            }
            
            
        } while (selection != 0);
    }
    
    private void addRequestPurchase(ArrayList<Integer> productList) {
        try {
            ApproveForPurchases approveList = Util.getApproveForPurchases();
            
            for (int item : productList) {
                if (approveList == null || approveList.getApproveForPurchases() == null || approveList.getApproveForPurchases().isEmpty()) {
                    approveList = new ApproveForPurchases();
                }
                
                approveList.getApproveForPurchases().add(new ApproveForPurchase(item));
            }
            
            File xmlFile = new File("./src/lojadoprazer/xml/approveForPurchases.xml");
            XStream xStream = new XStream();
            OutputStream outputStream = new FileOutputStream(xmlFile);
            Writer writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
            
            xStream.toXML(approveList, writer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Produtos selecionados com sucesso.");
    }
    
    public void printEmployeeList() {
        try {
            Employees employeeList = Util.getEmployees();

            for (Employee emp : employeeList.getEmployees()) {
                emp.setUser(UserController.getUserById(emp.getId()));
                emp.toString();
                System.out.println();
            }
        } catch (Exception e) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public EmployeeType toEmployeeType(String eType) {
        if (eType.equals("funcionario")) {
            return EmployeeType.funcionario;
        } else if (eType.equals("gerente")) {
            return EmployeeType.gerente;
        } else {
            return EmployeeType.diretor;
        }
    }
}
