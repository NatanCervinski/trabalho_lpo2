package locadora.controller;

import locadora.view.cliente.JanelaClienteView;
import locadora.view.veiculo.JanelaVeiculoView;
import locadora.view.locacao.locar.JanelaLocacaoView;
import locadora.controller.cliente.ClienteController;
import locadora.controller.locacao.DevolverController;
import locadora.controller.veiculo.VeiculoController;
import locadora.controller.locacao.LocacaoController;
import locadora.model.dao.cliente.ClienteDao;
import locadora.model.dao.veiculo.VeiculoDao;
import locadora.model.dao.DaoFactory;
import locadora.model.dao.DaoType;
import locadora.model.dao.locacao.LocacaoDao;
import locadora.view.locacao.devolver.JanelaDevolverView;

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
    
    public void abrirJanelaLocacao() {
        // Inicializar JanelaClienteView, ClienteDao e ClienteController
        JanelaLocacaoView view = new JanelaLocacaoView();
        ClienteDao clienteDao = DaoFactory.getClienteDao();
        VeiculoDao veiculoDao = DaoFactory.getVeiculoDao();
        LocacaoDao locacaoDao = DaoFactory.getLocacaoDao();
        LocacaoController controller = new LocacaoController(view, clienteDao, veiculoDao, locacaoDao);

        // Configurar e exibir a JanelaClienteView
        view.setVisible(true);
        view.pack();
        view.setLocationRelativeTo(null); // Centraliza a janela na tela
    }
    
    public void abrirJanelaDevolver(){
        JanelaDevolverView viewDevolver = new JanelaDevolverView();
        ClienteDao clienteDao = DaoFactory.getClienteDao();
        VeiculoDao veiculoDao = DaoFactory.getVeiculoDao();
        LocacaoDao locacaoDao = DaoFactory.getLocacaoDao();
        DevolverController controller = new DevolverController(viewDevolver, clienteDao, veiculoDao, locacaoDao);
        
        viewDevolver.setVisible(true);
        viewDevolver.pack();
        viewDevolver.setLocationRelativeTo(null);
    }
}
