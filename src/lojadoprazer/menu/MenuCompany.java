/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.menu;

import java.util.Scanner;

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
            System.out.println("[1] LISTAR ARRECADAÇÃO");
            System.out.println("[2] APROVAR COMPRAS");
            System.out.println("[3] CADASTRAR FUNCIONARIO");
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
                    
                    break;
                default:
                    System.out.println("Item selecionado é inválido");
                    break;
            }
            
        } while (selection != 4);
    }
}