/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.controller.veiculo;

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
import locadora.view.locacao.vender.FormularioPesquisaVeiculoVendaView;
import locadora.view.locacao.vender.JanelaVenderVeiculoView;

/**
 *
 * @author natan
 */
public class VenderController {

    private JanelaVenderVeiculoView view;
    private ClienteDao clienteDao;
    private VeiculoDao veiculoDao;
    private LocacaoDao locacaoDao;
    
    public VenderController(JanelaVenderVeiculoView viewVender, ClienteDao clienteDao, VeiculoDao veiculoDao, LocacaoDao locacaoDao) {
        this.clienteDao = clienteDao; // Obtém a instância do DAO
        this.veiculoDao = veiculoDao;
        this.locacaoDao = locacaoDao;
        this.view = viewVender;
        initController();
    }
    
    private void initController(){
        this.view.setController(this);
        this.view.initView();
    }


    public void listarVeiculos() {
        try {
//            view.limparClienteAtualizaralizar();
            
            FormularioPesquisaVeiculoVendaView.filtrosVeiculo filtrosVeiculo = view.recuperarFiltrosVeiculo();
            
            Marca marca = filtrosVeiculo.getMarca();
            Categoria categoria = filtrosVeiculo.getCategoria();
            String tipo = filtrosVeiculo.getTipo();

            List<Veiculo> lista = this.veiculoDao.getByTipoMarcaCategoria(tipo, marca, categoria, "DISPONIVEL");

            view.mostrarListaVeiculos(lista);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void venderVeiculo() {
        try {
//            view.limparClienteAtualizaralizar();
            Veiculo veiculo = view.recuperarVeiculoSelecionado();
            veiculo.vender();
            
            this.veiculoDao.update(veiculo);
            

            view.excluirVeiculoTabela(veiculo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
