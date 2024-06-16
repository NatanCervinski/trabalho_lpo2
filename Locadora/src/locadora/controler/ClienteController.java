package locadora.controler;

import java.util.List;
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
    
    public void listarClientes(JTable tabelaCliente) {
        try {
            // Limpa a tabela antes de adicionar os novos dados
            DefaultTableModel model = (DefaultTableModel) tabelaCliente.getModel();
            model.setRowCount(0);
            
            // Obtém a lista de clientes do banco de dados
            List<Cliente> clientes = clienteDao.getAll();
            
            // Adiciona os clientes à tabela
            for (Cliente cliente : clientes) {
                model.addRow(new Object[] {
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getSobrenome(),
                    cliente.getRg(),
                    cliente.getCpf(),
                    cliente.getEndereco().getRua(),
                    cliente.getEndereco().getNumero(),
                    cliente.getEndereco().getComplemento()
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // Tratar exceções conforme necessário
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
