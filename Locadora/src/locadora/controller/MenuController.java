package locadora.controller;

import locadora.view.JanelaClienteView;
import locadora.controller.ClienteController;
import locadora.model.dao.ClienteDao;
import locadora.model.dao.DaoFactory;
import locadora.model.dao.DaoType;

public class MenuController {
    public void abrirJanelaCliente() {
        // Inicializar JanelaClienteView, ClienteDao e ClienteController
        JanelaClienteView view = new JanelaClienteView();
        ClienteDao modelDao = DaoFactory.getClienteDao(DaoType.SQL);
        ClienteController controller = new ClienteController(view, modelDao);

        // Configurar e exibir a JanelaClienteView
        view.setVisible(true);
        view.pack();
        view.setLocationRelativeTo(null); // Centraliza a janela na tela
    }
}
