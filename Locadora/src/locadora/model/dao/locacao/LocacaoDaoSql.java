package locadora.model.dao.locacao;

import locadora.model.dao.ConnectionFactory;
import locadora.model.locacao.Locacao;
import locadora.model.cliente.Cliente;
import locadora.model.veiculo.Veiculo;
import locadora.model.dao.cliente.ClienteDaoSql;
import locadora.model.dao.veiculo.VeiculoDaoSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import locadora.model.locacao.Locacao;

public class LocacaoDaoSql implements LocacaoDao {

    private final String insert = "INSERT INTO locacao (dias, valor, data, cliente_id, veiculo_id) VALUES (?,?,?,?,?)";
    private final String selectAll = "SELECT * FROM locacao";
    private final String selectById = "SELECT * FROM locacao WHERE id = ?";
    private final String update = "UPDATE locacao SET dias=?, valor=?, data=?, cliente_id=?, veiculo_id=? WHERE id=?";
    private final String delete = "DELETE FROM locacao WHERE id=?";
    private final String deleteAll = "DELETE FROM locacao";
    private final String ressetAI = "ALTER TABLE locacao AUTO_INCREMENT =1;";
    
    private static LocacaoDaoSql dao;

    private LocacaoDaoSql() {}

    public static LocacaoDaoSql getLocacaoDaoSql(){
        if(dao==null)
            return dao = new LocacaoDaoSql();
        else
            return dao;
    }

    private Date convertCalendarToDate(Calendar calendar) {
        return new Date(calendar.getTimeInMillis());
    }
    
    public void add(Locacao locacao) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtAdiciona = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            stmtAdiciona.setInt(1, locacao.getDias());
            stmtAdiciona.setDouble(2, locacao.getValor());
            stmtAdiciona.setDate(3, convertCalendarToDate(locacao.getData()));
            stmtAdiciona.setLong(4, locacao.getCliente().getId());
            stmtAdiciona.setLong(5, locacao.getVeiculo().getId());
            stmtAdiciona.execute();

            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            locacao.setId(id);

            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    public List<Locacao> getAll() throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(selectAll);
             ResultSet rs = stmtLista.executeQuery()) {

            List<Locacao> locacoes = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                int dias = rs.getInt("dias");
                double valor = rs.getDouble("valor");
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));
                long clienteId = rs.getLong("cliente_id");
                long veiculoId = rs.getLong("veiculo_id");

                Cliente cliente = ClienteDaoSql.getClienteDaoSql().getById(clienteId);
                Veiculo veiculo = VeiculoDaoSql.getVeiculoDaoSql().getById(veiculoId);

                Locacao locacao = new Locacao(id, dias, valor, data, cliente, veiculo);
                locacoes.add(locacao);
            }

            return locacoes;
        }
    }

    public Locacao getById(long id) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtLista = connection.prepareStatement(selectById)) {
            stmtLista.setLong(1, id);
            try (ResultSet rs = stmtLista.executeQuery()) {
                if (rs.next()) {
                    int dias = rs.getInt("dias");
                    double valor = rs.getDouble("valor");
                    Calendar data = Calendar.getInstance();
                    data.setTime(rs.getDate("data"));
                    long clienteId = rs.getLong("cliente_id");
                    long veiculoId = rs.getLong("veiculo_id");

                    Cliente cliente = ClienteDaoSql.getClienteDaoSql().getById(clienteId);
                    Veiculo veiculo = VeiculoDaoSql.getVeiculoDaoSql().getById(veiculoId);

                    return new Locacao(id, dias, valor, data, cliente, veiculo);
                } else {
                    throw new SQLException("Locação não encontrada com id=" + id);
                }
            }
        }
    }

    public void update(Locacao locacao) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtAtualiza = connection.prepareStatement(update)) {
            stmtAtualiza.setInt(1, locacao.getDias());
            stmtAtualiza.setDouble(2, locacao.getValor());
            stmtAtualiza.setDate(3, convertCalendarToDate(locacao.getData()));
            stmtAtualiza.setLong(4, locacao.getCliente().getId());
            stmtAtualiza.setLong(5, locacao.getVeiculo().getId());
            stmtAtualiza.setLong(6, locacao.getId());
            stmtAtualiza.executeUpdate();
        }
    }

    public void delete(Locacao locacao) throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(delete)) {
            stmtExcluir.setLong(1, locacao.getId());
            stmtExcluir.executeUpdate();
        }
    }

    public void deleteAll() throws Exception {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmtExcluir = connection.prepareStatement(deleteAll);
             PreparedStatement stmtResetAI = connection.prepareStatement(ressetAI)) {
            stmtExcluir.executeUpdate();
            stmtResetAI.executeUpdate();
        }
    }
}
