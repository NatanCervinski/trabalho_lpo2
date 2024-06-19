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
public class LocacaoController {

    private JanelaLocacaoView viewLocacao;
    private ClienteDao clienteDao;
    private VeiculoDao veiculoDao;
    private LocacaoDao locacaoDao;
    
    public LocacaoController(JanelaLocacaoView viewLocacao, ClienteDao clienteDao, VeiculoDao veiculoDao, LocacaoDao locacaoDao) {
        this.clienteDao = clienteDao; // Obtém a instância do DAO
        this.veiculoDao = veiculoDao;
        this.locacaoDao = locacaoDao;
        this.viewLocacao = viewLocacao;
        initController();
    }
    
    private void initController(){
        this.viewLocacao.setController(this);
        this.viewLocacao.initView();
    }

    public void listarClientes() {
        try {
//            view.limparClienteAtualizaralizar();
            
            String[] filtrosCliente = viewLocacao.recuperarFiltrosCliente();
            
            String nome = filtrosCliente[0];
            String sobrenome = filtrosCliente[1];
            String cpf = filtrosCliente[2];

            List<Cliente> lista = this.clienteDao.getByNomeSobrenomeCpf(nome, sobrenome, cpf);

            viewLocacao.mostrarListaClientes(lista);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void listarVeiculos() {
        try {
//            view.limparClienteAtualizaralizar();
            
            filtrosVeiculo filtrosVeiculo = viewLocacao.recuperarFiltrosVeiculo();
            
            Marca marca = filtrosVeiculo.getMarca();
            Categoria categoria = filtrosVeiculo.getCategoria();
            String tipo = filtrosVeiculo.getTipo();

            List<Veiculo> lista = this.veiculoDao.getByTipoMarcaCategoria(tipo, marca, categoria);

            viewLocacao.mostrarListaVeiculos(lista);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void locarVeiculo(){
        try{
            Veiculo veiculo = viewLocacao.recuperarVeiculoSelecionado();
            DadosLocacao dadosLocacao = viewLocacao.recuperarDadosLocacao();
            
            int diasLocacao = dadosLocacao.getDias();
            Calendar dataLocacao = dadosLocacao.getData();
            Cliente clienteLocacao = dadosLocacao.getCliente();
            
            veiculo.locar(diasLocacao, dataLocacao, clienteLocacao);
            veiculoDao.update(veiculo);
            locacaoDao.add(veiculo.getLocacao());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
