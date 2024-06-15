package controller;

import dao.ClienteDAO;
import model.Cliente;
import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void addCliente(String nome, String sobrenome, String rg, String cpf, String endereco) {
        Cliente cliente = new Cliente(nome, sobrenome, rg, cpf, endereco);
        clienteDAO.addCliente(cliente);
    }

    public void updateCliente(int id, String nome, String sobrenome, String rg, String cpf, String endereco) {
        Cliente cliente = new Cliente(id, nome, sobrenome, rg, cpf, endereco);
        clienteDAO.updateCliente(cliente);
    }

    public void deleteCliente(int id) {
        clienteDAO.deleteCliente(id);
    }

    public List<Cliente> getAllClientes() {
        return clienteDAO.getAllClientes();
    }
}
