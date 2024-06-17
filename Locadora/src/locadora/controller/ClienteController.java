package locadora.controller;

import java.util.List;
import java.util.Collections;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import locadora.model.Cliente;
import locadora.model.Endereco;
import locadora.model.dao.ClienteDaoSql;

public class ClienteController {
    
    private final ClienteDaoSql clienteDao;
    
    public ClienteController() {
        this.clienteDao = ClienteDaoSql.getClienteDaoSql(); // Obtém a instância do DAO
    }
    
    public void criarCliente(String nome, String sobrenome, String rg, String cpf, String rua, String numero, String complemento) {
        try {
            // Cria o objeto Cliente com os dados informados
            Cliente cliente = new Cliente(0, nome, sobrenome, rg, cpf, new Endereco(rua, numero, complemento));
            
            // Chama o método do DAO para adicionar o cliente ao banco de dados
            clienteDao.add(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            // Tratar exceções conforme necessário
        }
    }
    
    public List<Cliente> listarClientes() {
        try {
            List<Cliente> clientes = clienteDao.getAll();
            
            return clientes;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList(); // Retorna uma lista vazia
        }
    }
    
    public void excluirCliente(long id) {
        try {
            // Cria um objeto Cliente apenas com o ID para passar ao método de exclusão
            Cliente cliente = new Cliente(id, "", "", "", "", null);
            
            // Chama o método do DAO para excluir o cliente do banco de dados
            clienteDao.delete(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            // Tratar exceções conforme necessário
        }
    }
    
    public void atualizarCliente(Cliente cliente) {
        try {
            // Chama o método do DAO para atualizar os dados do cliente no banco de dados
            clienteDao.update(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
            // Tratar exceções conforme necessário
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
