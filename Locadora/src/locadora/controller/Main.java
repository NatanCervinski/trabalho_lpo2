/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.controller;

import javax.swing.JFrame;
import locadora.model.dao.cliente.ClienteDao;
import locadora.model.dao.DaoFactory;
import locadora.model.dao.DaoType;
import locadora.view.cliente.JanelaClienteView;
import locadora.view.MenuView;

/**
 *
 * @author rafae
 */
public class Main {
    public static void main(String[] args){
        // Configurar a janela principal para a MenuView
        MenuController menuController = new MenuController();

        // Criar a MenuView com a MenuController
        MenuView menuView = new MenuView(menuController);

        // Configurar a janela principal para a MenuView
        JFrame frame = new JFrame("Sistema de Locadora"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.getContentPane().add(menuView); 
        frame.pack(); 
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
        
    //   JanelaClienteView view = new JanelaClienteView();
     //   ClienteDao modelDao = DaoFactory.getClienteDao(DaoType.SQL);
      //  ClienteController controller = new ClienteController(view,modelDao);
        
        
        
    }
    
}
