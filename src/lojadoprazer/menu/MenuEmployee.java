/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.menu;

import java.util.Scanner;
import lojadoprazer.Logout;

/**
 *
 * @author danielvilha
 */
public class MenuEmployee {
    
    public final static void createMenuEmployee(int id) {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        
        do {
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*       MENU DO FUNCIONÁRIO     *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            System.out.println("[1] VISUALIZAR SALARIO");
            System.out.println("[2] SOLICITAR COMPRAS");
            System.out.println("[3] CADASTRAR CLIENTES");
            System.out.println("[4] SAIR");
            
            System.out.print("Item selecionado: ");
            selection = scanner.nextInt();
            
            switch (selection) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
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
