package locadora.model.dao.veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import locadora.model.veiculo.Automovel;
import locadora.model.veiculo.Categoria;
import locadora.model.cliente.Estado;
import locadora.model.veiculo.Marca;
import locadora.model.veiculo.ModeloAutomovel;
import locadora.model.veiculo.ModeloMotocicleta;
import locadora.model.veiculo.ModeloVan;
import locadora.model.veiculo.Motocicleta;
import locadora.model.veiculo.Van;
import locadora.model.veiculo.Veiculo;
import locadora.model.dao.ConnectionFactory;

public class VeiculoDaoSql implements VeiculoDao {
    private ConnectionFactory connectionFactory;
    
    private final String insert = "INSERT INTO veiculo (marca, estado, categoria, valorDeCompra, placa, ano, tipo, modeloAutomovel, modeloMotocicleta, modeloVan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    
    private Connection connection;

    public VeiculoDaoSql(Connection connection) {
        this.connection = connection;
    }
    
    private static VeiculoDaoSql dao;
    
    private VeiculoDaoSql(){
    }
    
    public static VeiculoDaoSql getVeiculoDaoSql(){
        if(dao==null)
            return dao = new VeiculoDaoSql();
        else
            return dao;
    } 
    
    public VeiculoDaoSql(ConnectionFactory conFactory) {
        this.connectionFactory = conFactory;
    }

    @Override
    public void add(Veiculo veiculo) throws Exception {
        
        try (Connection connection=ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            )
        {
            stmt.setString(1, veiculo.getMarca().name());
            stmt.setString(2, veiculo.getEstado().name());
            stmt.setString(3, veiculo.getCategoria().name());
            stmt.setDouble(4, veiculo.getValorDeCompra());
            stmt.setString(5, veiculo.getPlaca());
            stmt.setInt(6, veiculo.getAno());
            stmt.setString(7, veiculo.getClass().getSimpleName());
            if (veiculo instanceof Automovel) {
                stmt.setString(8, ((Automovel) veiculo).getModelo().name());
                stmt.setNull(9, Types.VARCHAR);
                stmt.setNull(10, Types.VARCHAR);
            } else if (veiculo instanceof Motocicleta) {
                stmt.setNull(8, Types.VARCHAR);
                stmt.setString(9, ((Motocicleta) veiculo).getModelo().name());
                stmt.setNull(10, Types.VARCHAR);
            } else if (veiculo instanceof Van) {
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.VARCHAR);
                stmt.setString(10, ((Van) veiculo).getModelo().name());
            }
            stmt.execute();
            
        }
    }

    @Override
    public List<Veiculo> getAll() throws Exception {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Marca marca = Marca.valueOf(rs.getString("marca"));
                Estado estado = Estado.valueOf(rs.getString("estado"));
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
                double valorDeCompra = rs.getDouble("valorDeCompra");
                String placa = rs.getString("placa");
                int ano = rs.getInt("ano");
                String tipo = rs.getString("tipo");
                Veiculo veiculo = null;
                switch (tipo) {
                    case "Automovel":
                        ModeloAutomovel modeloAutomovel = ModeloAutomovel.valueOf(rs.getString("modeloAutomovel"));
                        veiculo = new Automovel(marca, estado, categoria, valorDeCompra, placa, ano, modeloAutomovel);
                        break;
                    case "Motocicleta":
                        ModeloMotocicleta modeloMotocicleta = ModeloMotocicleta.valueOf(rs.getString("modeloMotocicleta"));
                        veiculo = new Motocicleta(marca, estado, categoria, valorDeCompra, placa, ano, modeloMotocicleta);
                        break;
                    case "Van":
                        ModeloVan modeloVan = ModeloVan.valueOf(rs.getString("modeloVan"));
                        veiculo = new Van(marca, estado, categoria, valorDeCompra, placa, ano, modeloVan);
                        break;
                }
                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }

    @Override
    public Veiculo getById(long id) throws Exception {
        String sql = "SELECT * FROM veiculo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Marca marca = Marca.valueOf(rs.getString("marca"));
                    Estado estado = Estado.valueOf(rs.getString("estado"));
                    Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
                    double valorDeCompra = rs.getDouble("valorDeCompra");
                    String placa = rs.getString("placa");
                    int ano = rs.getInt("ano");
                    String tipo = rs.getString("tipo");
                    Veiculo veiculo = null;
                    switch (tipo) {
                        case "Automovel":
                            ModeloAutomovel modeloAutomovel = ModeloAutomovel.valueOf(rs.getString("modeloAutomovel"));
                            veiculo = new Automovel(marca, estado, categoria, valorDeCompra, placa, ano, modeloAutomovel);
                            break;
                        case "Motocicleta":
                            ModeloMotocicleta modeloMotocicleta = ModeloMotocicleta.valueOf(rs.getString("modeloMotocicleta"));
                            veiculo = new Motocicleta(marca, estado, categoria, valorDeCompra, placa, ano, modeloMotocicleta);
                            break;
                        case "Van":
                            ModeloVan modeloVan = ModeloVan.valueOf(rs.getString("modeloVan"));
                            veiculo = new Van(marca, estado, categoria, valorDeCompra, placa, ano, modeloVan);
                            break;
                    }
                    return veiculo;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Veiculo veiculo) throws Exception {
        String sql = "UPDATE veiculo SET marca = ?, estado = ?, categoria = ?, valorDeCompra = ?, placa = ?, ano = ?, tipo = ?, modeloAutomovel = ?, modeloMotocicleta = ?, modeloVan = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getMarca().name());
            stmt.setString(2, veiculo.getEstado().name());
            stmt.setString(3, veiculo.getCategoria().name());
            stmt.setDouble(4, veiculo.getValorDeCompra());
            stmt.setString(5, veiculo.getPlaca());
            stmt.setInt(6, veiculo.getAno());
            stmt.setString(7, veiculo.getClass().getSimpleName());
            if (veiculo instanceof Automovel) {
                stmt.setString(8, ((Automovel) veiculo).getModelo().name());
                stmt.setNull(9, Types.VARCHAR);
                stmt.setNull(10, Types.VARCHAR);
            } else if (veiculo instanceof Motocicleta) {
                stmt.setNull(8, Types.VARCHAR);
                stmt.setString(9, ((Motocicleta) veiculo).getModelo().name());
                stmt.setNull(10, Types.VARCHAR);
            } else if (veiculo instanceof Van) {
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.VARCHAR);
                stmt.setString(10, ((Van) veiculo).getModelo().name());
            }
            stmt.setLong(11, veiculo.getId()); // Assuming getId() method exists in Veiculo class to get the id of the vehicle
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Veiculo veiculo) throws Exception {
        String sql = "DELETE FROM veiculo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, veiculo.getId()); // Assuming getId() method exists in Veiculo class to get the id of the vehicle
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteAll() throws Exception {
        String sql = "DELETE FROM veiculo";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
