package locadora.controller;

import java.util.List;
import java.util.Collections;
import locadora.model.Cliente;
import locadora.model.dao.ClienteDao;
import locadora.view.cliente.JanelaClienteView;


public class ClienteController {
    private JanelaClienteView view;
    private ClienteDao clienteDao;
    
    public ClienteController(JanelaClienteView view, ClienteDao ClienteDao) {
        this.clienteDao = ClienteDao; // Obtém a instância do DAO
        this.view = view;
        initController();
    }
    
    private void initController(){
        this.view.setController(this);
        this.view.initView();
    }
    
    public void criarCliente() {
        try {
            Cliente cliente = view.getClienteFormulario();
            
            clienteDao.add(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            view.apresentaErro("Erro ao criar cliente.");

        }
    }
    
    public void listarClientes() {
        try {
            view.limparClienteAtualizar();
            
            List<Cliente> lista = this.clienteDao.getAll();
            
            view.mostrarListaClientes(lista);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void excluirCliente() {
        try {            
            List<Cliente> listaParaExcluir = view.getClientesParaExcluir();

            clienteDao.delete(listaParaExcluir);
            view.excluirClientesView(listaParaExcluir);
        } catch (Exception ex) {
            ex.printStackTrace();
            view.apresentaErro("Erro ao excluir clientes.");

        }
    }
    
    public void atualizarCliente() {
        try {
            Cliente cliente = view.getClienteParaAtualizar();
            if(cliente==null){
                view.apresentaInfo("Selecione um contato na tabela para atualizar.");
                return;
            }
            clienteDao.update(cliente);
            view.atualizarCliente(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            view.apresentaErro("Erro ao atualizar cliente.");
        }
    }
    
    public Cliente buscarClientePorId(long id) {
        try {
            return clienteDao.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
