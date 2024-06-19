/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.controller.locacao;

import java.util.Calendar;
import java.util.List;
import locadora.model.cliente.Cliente;
import locadora.model.dao.cliente.ClienteDao;
import locadora.view.cliente.JanelaClienteView;
import locadora.view.locacao.locar.JanelaLocacaoView;
import locadora.view.locacao.devolver.JanelaDevolverView;
import locadora.controller.cliente.ClienteController;
import locadora.model.dao.locacao.LocacaoDao;
import locadora.model.dao.veiculo.VeiculoDao;
import locadora.model.locacao.Locacao;
import locadora.model.veiculo.Categoria;
import locadora.model.veiculo.Marca;
import locadora.model.veiculo.Veiculo;
import locadora.view.locacao.locar.FormularioPesquisaVeiculoLocacaoView.filtrosVeiculo;
import locadora.view.locacao.locar.JanelaLocacaoView.DadosLocacao;

/**
 *
 * @author natan
 */
public class DevolverController {

    private JanelaDevolverView view;
    private ClienteDao clienteDao;
    private VeiculoDao veiculoDao;
    private LocacaoDao locacaoDao;
    
    public DevolverController(JanelaDevolverView viewDevolver, ClienteDao clienteDao, VeiculoDao veiculoDao, LocacaoDao locacaoDao) {
        this.clienteDao = clienteDao; // Obtém a instância do DAO
        this.veiculoDao = veiculoDao;
        this.locacaoDao = locacaoDao;
        this.view = viewDevolver;
        initController();
    }
    
    private void initController(){
        this.view.setController(this);
        this.view.initView();
    }

    public void listarLocacoes() {
        try{
            List<Locacao> locacoes = this.locacaoDao.getAll();
            
            view.mostrarListaLocacao(locacoes);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void devolverVeiculo() {
        try{
            
            Locacao locacao = view.recuperarLocacaoSelecionada();
            
            locacao.devolver();
            this.veiculoDao.update(locacao.getVeiculo());
            this.locacaoDao.delete(locacao);
            
            this.listarLocacoes();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
