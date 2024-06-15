package dao;

import java.sql.*;
import java.util.*;
import model.Cliente;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() {
        // Configure a conexão com o banco de dados
    }

    public void addCliente(Cliente cliente) {
        // Implementar o método para adicionar cliente ao banco de dados
    }

    public void updateCliente(Cliente cliente) {
        // Implementar o método para atualizar cliente no banco de dados
    }

    public void deleteCliente(int id) {
        // Implementar o método para deletar cliente do banco de dados
    }

    public List<Cliente> getAllClientes() {
        // Implementar o método para retornar todos os clientes do banco de dados
        return new ArrayList<>();
    }
}

// Crie outras classes DAO de forma semelhante para veículos e locações.
