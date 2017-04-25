/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lojadoprazer.menu;

import java.util.Scanner;
import lojadoprazer.Logout;
import lojadoprazer.controller.ClientController;
import lojadoprazer.controller.PurchaseController;

/**
 *
 * @author danielvilha
 */
public class MenuClient implements Menu {
    
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
                    new PurchaseController().buy(id);
                    createMenu(id);
                    break;
                case 2:
                    new ClientController().listMyPurchases(id);
                    createMenu(id);
                    break;
                case 3:
                    Logout.Logout();
                    break;
                default:
                    System.out.println("Item selecionado é inválido");
                    break;
            }
        } while (selection != 3);
    }
}
