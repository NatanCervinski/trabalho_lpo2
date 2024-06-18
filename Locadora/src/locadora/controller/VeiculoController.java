package locadora.controller;

import locadora.model.Veiculo;
import locadora.model.dao.ConnectionFactory;
import locadora.model.dao.VeiculoDaoSql;

import javax.swing.DefaultComboBoxModel;
import java.sql.Connection;

public class VeiculoController {
    private VeiculoDaoSql veiculoDao;

    public VeiculoController() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            veiculoDao = new VeiculoDaoSql(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DefaultComboBoxModel<String> getModelOptions(String tipo) {
        switch (tipo) {
            case "Automovel":
                return new DefaultComboBoxModel<>(new String[] { "Gol", "Celta", "Palio", "Fiesta", "Civic", "Corolla" });
            case "Motocicleta":
                return new DefaultComboBoxModel<>(new String[] { "CG125", "CBR500", "Ninja300", "XJ6" });
            case "Van":
                return new DefaultComboBoxModel<>(new String[] { "Kombi", "Sprinter", "Ducato" });
            default:
                return new DefaultComboBoxModel<>(new String[] {});
        }
    }

    public void addVeiculo(Veiculo veiculo) throws Exception {
        veiculoDao.add(veiculo);
    }
}
