/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.menu;

import java.util.Scanner;
import lojadoprazer.Logout;
import lojadoprazer.company.Company;
import lojadoprazer.purchase.ApproveForPurchases;

/**
 *
 * @author danielvilha
 */
public class MenuCompany {
    
    public final static void createMenuCompany(int id) {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        
        do {
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*     MENU DO ADMINISTRADOR     *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            System.out.println("[1] LISTAR PRODUTOS");
            System.out.println("[2] LISTAR CLIENTES");
            System.out.println("[3] LISTAR FUNCIONARIOS");
            System.out.println("[4] LISTAR VENDAS");
            System.out.println("[5] APROVAR COMPRAS");
            System.out.println("[6] CADASTRAR FUNCIONARIO");
            System.out.println("[7] SAIR");
            
            System.out.print("Item selecionado: ");
            selection = scanner.nextInt();
            
            switch (selection) {
                case 1:
                    new Company().listProducts();
                    createMenuCompany(id);
                    break;
                case 2:
                    new Company().listCustomers();
                    createMenuCompany(id);
                    break;
                case 3:
                    new Company().listEmployees();
                    createMenuCompany(id);
                    break;
                case 4:
                    new Company().listPurchase();
                    createMenuCompany(id);
                    break;
                case 5:
                    new ApproveForPurchases().listApproveForPurchases(id);
                    break;
                case 6:
                    
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
