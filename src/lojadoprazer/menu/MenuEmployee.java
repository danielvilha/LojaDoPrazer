/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.menu;

import java.util.Scanner;
import lojadoprazer.Logout;
import lojadoprazer.controller.ClientController;
import lojadoprazer.controller.EmployeeController;

/**
 *
 * @author danielvilha
 */
public class MenuEmployee implements Menu {
    
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
            System.out.println("*       MENU DO FUNCIONÁRIO     *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            System.out.println("[1] VISUALIZAR SALARIO"); //ok
            System.out.println("[2] SOLICITAR COMPRAS"); //ok
            System.out.println("[3] CADASTRAR CLIENTES"); //ok
            System.out.println("[4] SAIR"); //ok
            
            System.out.print("Item selecionado: ");
            selection = scanner.nextInt();
            
            switch (selection) {
                case 1:
                    new EmployeeController().printSalario(id);
                    createMenu(id);
                    break;
                case 2:
                    new EmployeeController().requestPurchase(id);
                    break;
                case 3:
                    new ClientController().createUser();
                    System.out.print("Cliente Cadastrado com sucesso!");
                    createMenu(id);
                    break;
                case 4:
                    Logout.Logout();
                    break;
                default:
                    System.out.println("Item selecionado é inválido");
                    break;
            }
            
        } while (selection != 4);
    }
    
}
