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
public class MenuClient {
    
    public final static void createMenuClient(int id) {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        
        do {
            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*          MENU CLIENTE         *");
            System.out.println("*                               *");
            System.out.println("*********************************");
            System.out.println("[1] COMPRAR");
            System.out.println("[2] VISUALIZAR COMPRAS");
            System.out.println("[3] SAIR");
            
            System.out.print("Item selecionado: ");
            selection = scanner.nextInt();
            
            switch (selection) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                default:
                    System.out.println("Item selecionado é inválido");
                    break;
            }
            
        } while (selection != 3);
    }
    
}
