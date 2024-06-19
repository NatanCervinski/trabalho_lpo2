/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.model.dao.cliente;

import locadora.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import locadora.model.cliente.Cliente;
import locadora.model.dao.cliente.ClienteDao;
import locadora.model.cliente.Endereco;
import locadora.model.dao.ConnectionFactory;

/**
 *
 * @author rafae
 */
public class ClienteDaoSql implements ClienteDao{
    private final String insert = "INSERT INTO cliente "
            + "(nome, sobrenome, rg, cpf) VALUES (?,?,?,?)";
    private final String insertEndereco = "INSERT INTO endereco "
            + "(rua, numero, complemento, cliente_id) VALUES (?,?,?,?)";
private final String selectAll = 
        "SELECT " +
        "cliente.id AS cliente_id, " +
        "cliente.nome, " +
        "cliente.sobrenome, " +
        "cliente.rg, " +
        "cliente.cpf, " +
        "endereco.rua, " +
        "endereco.numero, " +
        "endereco.complemento " +
        "FROM cliente " +
        "JOIN endereco ON cliente.id = endereco.cliente_id";
    private final String selectById = 
    "SELECT " +
    "cliente.id AS cliente_id, " +
    "cliente.nome, " +
    "cliente.sobrenome, " +
    "cliente.rg, " +
    "cliente.cpf, " +
    "endereco.rua, " +
    "endereco.numero, " +
    "endereco.complemento " +
    "FROM cliente " +
    "INNER JOIN endereco ON cliente.id = endereco.cliente_id " +
    "WHERE cliente.id = ?";
    private final String update = "UPDATE cliente "
            + "SET nome=?,sobrenome=?,rg=?,cpf=? WHERE id=?";
    private final String updateEndereco = "UPDATE enderecos "
            + "SET rua=?,numero=?,complemento=? WHERE cliente_id=?";    
    private final String delete = "DELETE FROM cliente WHERE id=?";
    private final String deleteAll = "DELETE FROM cliente";
    private final String selectByNomeSobrenomeCpf = "SELECT*FROM cliente INNER JOIN endereco ON cliente.id = endereco.cliente_id WHERE 1=1 ";
    private final String ressetAI = "ALTER TABLE cliente AUTO_INCREMENT =1;";
    private static ClienteDaoSql dao;
    private ClienteDaoSql(){
    }
    public static ClienteDaoSql getClienteDaoSql(){
        if(dao==null)
            return dao = new ClienteDaoSql();
        else
            return dao;
    }    


    @Override
public void add(Cliente cliente) throws Exception {
    try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement stmtAdicionaCliente = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement stmtAdicionaEndereco = connection.prepareStatement(insertEndereco);
    ) {
        // seta os valores
        connection.setAutoCommit(false);
        stmtAdicionaCliente.setString(1, cliente.getNome());
        stmtAdicionaCliente.setString(2, cliente.getSobrenome());
        stmtAdicionaCliente.setString(3, cliente.getRg());
        stmtAdicionaCliente.setString(4, cliente.getCpf());
        // executa
        stmtAdicionaCliente.execute();
        //Seta o id do cliente e insere endereco
        ResultSet rs = stmtAdicionaCliente.getGeneratedKeys();
        rs.next();
        long i = rs.getLong(1);
        cliente.setId(i);
        stmtAdicionaEndereco.setString(1, cliente.getEndereco().getRua());
        stmtAdicionaEndereco.setString(2, cliente.getEndereco().getNumero());
        stmtAdicionaEndereco.setString(3, cliente.getEndereco().getComplemento());
        stmtAdicionaEndereco.setLong(4, i);
        stmtAdicionaEndereco.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    } 
}

    @Override
    public List<Cliente> getAll() throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(selectAll);
             ResultSet rs = stmtLista.executeQuery()) {
            
            List<Cliente> clientes = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("cliente_id");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String rg = rs.getString("rg");
                String cpf = rs.getString("cpf");
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");
                String complemento = rs.getString("complemento");

                Endereco endereco = new Endereco(rua, numero, complemento);
                Cliente cliente = new Cliente(id, nome, sobrenome, rg, cpf, endereco);

                clientes.add(cliente);
            }
            
            return clientes;
        }
    }
    
    public List<Cliente> getByNomeSobrenomeCpf(String filtroNome, String filtroSobrenome, String filtroCpf) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();) {
            StringBuilder sql = new StringBuilder(selectByNomeSobrenomeCpf);

            boolean filtrarNome = filtroNome != null && !filtroNome.isEmpty();
            boolean filtrarSobrenome = filtroSobrenome != null && !filtroSobrenome.isEmpty();
            boolean filtrarCpf = filtroCpf != null && !filtroCpf.isEmpty();

            // Adiciona condições à consulta SQL
            if (filtrarNome) {
                sql.append(" AND nome LIKE ?");
            }
            if (filtrarSobrenome) {
                sql.append(" AND sobrenome LIKE ?");
            }
            if (filtrarCpf) {
                sql.append(" AND cpf = ?");
            }
            PreparedStatement stmtLista = connection.prepareStatement(sql.toString());
            
            int paramIndex = 1;

            if (filtrarNome){
                stmtLista.setString(paramIndex++, "%" + filtroNome + "%");
            }
            if (filtrarSobrenome){
                stmtLista.setString(paramIndex++, "%" + filtroSobrenome + "%");
            }
            if (filtrarCpf){
                stmtLista.setString(paramIndex++, filtroCpf);
            }

            try (ResultSet rs = stmtLista.executeQuery()) {

                List<Cliente> clientes = new ArrayList<>();
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    String sobrenome = rs.getString("sobrenome");
                    String rg = rs.getString("rg");
                    String cpf = rs.getString("cpf");
                    String rua = rs.getString("rua");
                    String numero = rs.getString("numero");
                    String complemento = rs.getString("complemento");

                    Endereco endereco = new Endereco(rua, numero, complemento);
                    Cliente cliente = new Cliente(id, nome, sobrenome, rg, cpf, endereco);

                    clientes.add(cliente);
                }

                return clientes;
            }
        }
    }
    
    @Override
    public Cliente getById(long id) throws Exception{
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(selectById);
            ){
            stmtLista.setLong(1, id);
            try (ResultSet rs = stmtLista.executeQuery()) {
                if (rs.next()) {
                    String nome= rs.getString("nome");
                    String sobrenome= rs.getString("sobrenome");
                    String rg= rs.getString("rg");
                    String cpf= rs.getString("cpf");
                    Endereco endereco = new Endereco(rs.getString("rua"),rs.getString("numero"),rs.getString("complemento"));
           

                    // adicionando o objeto à lista
                    return new Cliente(id,nome,sobrenome,rg,cpf,endereco);
                } else {
                    throw new SQLException("Cliente não encontrado com id=" + id);
                }
            }
        } 
    }
    @Override
    public void update(Cliente cliente) throws Exception{
        try(    Connection connection=ConnectionFactory.getConnection();
                PreparedStatement stmtAtualiza = connection.prepareStatement(update);
                ){

            stmtAtualiza.setString(1, cliente.getNome());
            stmtAtualiza.setString(2, cliente.getSobrenome());
            stmtAtualiza.setString(3, cliente.getRg());
            stmtAtualiza.setString(4, cliente.getCpf());
            stmtAtualiza.setLong(5, cliente.getId());
            stmtAtualiza.executeUpdate();
        } 
    }
   @Override
    public void delete(Cliente cliente) throws Exception {
        String deleteEndereco = "DELETE FROM endereco WHERE cliente_id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtExcluirEndereco = connection.prepareStatement(deleteEndereco);
            PreparedStatement stmtExcluirCliente = connection.prepareStatement(delete);
        ) {
        connection.setAutoCommit(false);
        
      
        stmtExcluirEndereco.setLong(1, cliente.getId());
        stmtExcluirEndereco.executeUpdate();

        stmtExcluirCliente.setLong(1, cliente.getId());
        stmtExcluirCliente.executeUpdate();

        connection.commit();
        connection.setAutoCommit(true);
        cliente.setId(-1);
    }
    
}
    @Override
    public void delete(List<Cliente> clientes) throws Exception {
        for(Cliente cliente:clientes){
            delete(cliente);
        }
    }
    
    @Override
    public void deleteAll() throws Exception {
        
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(deleteAll);
             PreparedStatement stmtResetAI = connection.prepareStatement(ressetAI);
            ){
            stmtExcluir.executeUpdate();
            stmtResetAI.executeUpdate();
        }
    } 
    
}
