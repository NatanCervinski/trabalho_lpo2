package locadora.controller;

import locadora.view.cliente.JanelaClienteView;
import locadora.view.veiculo.JanelaVeiculoView;
import locadora.controller.cliente.ClienteController;
import locadora.controller.veiculo.VeiculoController;
import locadora.model.dao.cliente.ClienteDao;
import locadora.model.dao.veiculo.VeiculoDao;
import locadora.model.dao.DaoFactory;
import locadora.model.dao.DaoType;

public class MenuController {
    public void abrirJanelaCliente() {
        // Inicializar JanelaClienteView, ClienteDao e ClienteController
        JanelaClienteView view = new JanelaClienteView();
        ClienteDao modelDao = DaoFactory.getClienteDao();
        ClienteController controller = new ClienteController(view, modelDao);

        // Configurar e exibir a JanelaClienteView
        view.setVisible(true);
        view.pack();
        view.setLocationRelativeTo(null); // Centraliza a janela na tela
    }
    
    public void abrirJanelaVeiculo() {
        // Inicializar JanelaClienteView, ClienteDao e ClienteController
        JanelaVeiculoView view = new JanelaVeiculoView();
        VeiculoDao modelDao = DaoFactory.getVeiculoDao();
        VeiculoController controller = new VeiculoController(view, modelDao);

        // Configurar e exibir a JanelaClienteView
        view.setVisible(true);
        view.pack();
        view.setLocationRelativeTo(null); // Centraliza a janela na tela
    }
}
