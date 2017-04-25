/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.menu;

import java.util.Scanner;
import lojadoprazer.Logout;
import lojadoprazer.controller.ApproveForPurchasesController;
import lojadoprazer.controller.CompanyController;

/**
 *
 * @author danielvilha
 */
public class MenuCompany implements Menu {
    
    /**
     *
     * @param id
     */
    @Override
    public void createMenu(int id) {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        
        do {
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*     MENU DO ADMINISTRADOR     *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            System.out.println("[1] LISTAR PRODUTOS"); //ok
            System.out.println("[2] LISTAR CLIENTES"); //ok
            System.out.println("[3] LISTAR FUNCIONARIOS"); //ok
            System.out.println("[4] LISTAR VENDAS"); //ok
            System.out.println("[5] APROVAR COMPRAS"); //ok
            System.out.println("[6] CADASTRAR FUNCIONARIO"); //ok
            System.out.println("[7] SAIR"); //ok
            
            System.out.print("Item selecionado: ");
            selection = scanner.nextInt();
            
            switch (selection) {
                case 1:
                    new CompanyController().printListProducts();
                    createMenu(id);
                    break;
                case 2:
                    new CompanyController().printListCustomers();
                    createMenu(id);
                    break;
                case 3:
                    new CompanyController().printListEmployees();
                    createMenu(id);
                    break;
                case 4:
                    new CompanyController().printListPurchase();
                    createMenu(id);
                    break;
                case 5:
                    new ApproveForPurchasesController().printListApproveForPurchases(id);
                    break;
                case 6:
                    new CompanyController().createEmployee(id);
                    break;
                case 7:
                    Logout.Logout();
                    break;
                default:
                    System.out.println("Item selecionado é inválido");
                    break;
            }
            
        } while (selection != 7);
    }
}
