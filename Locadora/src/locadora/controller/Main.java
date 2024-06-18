/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.controller;

import locadora.model.dao.ClienteDao;
import locadora.model.dao.DaoFactory;
import locadora.model.dao.DaoType;
import locadora.view.JanelaClienteView;

/**
 *
 * @author rafae
 */
public class Main {
    public static void main(String[] args){
        JanelaClienteView view = new JanelaClienteView();
        ClienteDao modelDao = DaoFactory.getClienteDao(DaoType.SQL);
        ClienteController controller = new ClienteController(view,modelDao);
        
    }
    
}
