package locadora.controller.veiculo;

import locadora.model.veiculo.Veiculo;
import locadora.model.dao.ConnectionFactory;
import locadora.model.dao.veiculo.VeiculoDao;
import locadora.model.dao.veiculo.VeiculoDao;
import locadora.view.veiculo.JanelaVeiculoView;

import javax.swing.DefaultComboBoxModel;
import java.sql.Connection;

public class VeiculoController {
    private VeiculoDao veiculoDao;
    private JanelaVeiculoView view;

    public VeiculoController(JanelaVeiculoView view, VeiculoDao VeiculoDao) {
        this.veiculoDao = VeiculoDao;
        this.view = view;
        initController();
    }
    
    private void initController(){
        this.view.setController(this);
        this.view.initView();
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

    public void criarVeiculo(){
        try {
            Veiculo veiculo = view.getVeiculoFormulario();
            
            veiculoDao.add(veiculo);
        } catch (Exception ex) {
            ex.printStackTrace();
            view.apresentaErro("Erro ao criar cliente.");
        }
    }

}
