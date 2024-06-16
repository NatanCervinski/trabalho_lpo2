package locadora.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import locadora.model.Automovel;
import locadora.model.Categoria;
import locadora.model.Estado;
import locadora.model.Marca;
import locadora.model.ModeloAutomovel;
import locadora.model.ModeloMotocicleta;
import locadora.model.ModeloVan;
import locadora.model.Motocicleta;
import locadora.model.Van;
import locadora.model.Veiculo;
import locadora.model.dao.ConnectionFactory;
import locadora.model.dao.VeiculoDao;
import locadora.model.dao.VeiculoDaoSql;

public class CadastroVeiculo extends JFrame {
    private JComboBox<Marca> marcaComboBox;
    private JComboBox<Estado> estadoComboBox;
    private JComboBox<Categoria> categoriaComboBox;
    private JComboBox<String> tipoComboBox;
    private JComboBox<ModeloAutomovel> modeloAutomovelComboBox;
    private JComboBox<ModeloMotocicleta> modeloMotocicletaComboBox;
    private JComboBox<ModeloVan> modeloVanComboBox;
    private JTextField valorDeCompraField;
    private JTextField placaField;
    private JTextField anoField;
    private JButton saveButton;

    private VeiculoDao veiculoDao;

    public CadastroVeiculo(Connection connection) {
        veiculoDao = new VeiculoDaoSql(connection);
        setTitle("Cadastro de Veículo");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 2));

        // Marca
        panel.add(new JLabel("Marca:"));
        marcaComboBox = new JComboBox<>(Marca.values());
        panel.add(marcaComboBox);

        // Estado
        panel.add(new JLabel("Estado:"));
        estadoComboBox = new JComboBox<>(Estado.values());
        panel.add(estadoComboBox);

        // Categoria
        panel.add(new JLabel("Categoria:"));
        categoriaComboBox = new JComboBox<>(Categoria.values());
        panel.add(categoriaComboBox);

        // Tipo
        panel.add(new JLabel("Tipo:"));
        tipoComboBox = new JComboBox<>(new String[]{"Automovel", "Motocicleta", "Van"});
        panel.add(tipoComboBox);

        // Modelo
        panel.add(new JLabel("Modelo:"));
        modeloAutomovelComboBox = new JComboBox<>(ModeloAutomovel.values());
        modeloMotocicletaComboBox = new JComboBox<>(ModeloMotocicleta.values());
        modeloVanComboBox = new JComboBox<>(ModeloVan.values());
        panel.add(modeloAutomovelComboBox);
        panel.add(modeloMotocicletaComboBox);
        panel.add(modeloVanComboBox);

        // Hide model combo boxes initially
        modeloAutomovelComboBox.setVisible(false);
        modeloMotocicletaComboBox.setVisible(false);
        modeloVanComboBox.setVisible(false);

        // Valor de Compra
        panel.add(new JLabel("Valor de Compra:"));
        valorDeCompraField = new JTextField();
        panel.add(valorDeCompraField);

        // Placa
        panel.add(new JLabel("Placa:"));
        placaField = new JTextField();
        panel.add(placaField);

        // Ano
        panel.add(new JLabel("Ano:"));
        anoField = new JTextField();
        panel.add(anoField);

        // Save Button
        saveButton = new JButton("Salvar");
        panel.add(saveButton);

        add(panel);

        tipoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) tipoComboBox.getSelectedItem();
                switch (selectedType) {
                    case "Automovel":
                        modeloAutomovelComboBox.setVisible(true);
                        modeloMotocicletaComboBox.setVisible(false);
                        modeloVanComboBox.setVisible(false);
                        break;
                    case "Motocicleta":
                        modeloAutomovelComboBox.setVisible(false);
                        modeloMotocicletaComboBox.setVisible(true);
                        modeloVanComboBox.setVisible(false);
                        break;
                    case "Van":
                        modeloAutomovelComboBox.setVisible(false);
                        modeloMotocicletaComboBox.setVisible(false);
                        modeloVanComboBox.setVisible(true);
                        break;
                }
                revalidate();
                repaint();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Marca marca = (Marca) marcaComboBox.getSelectedItem();
                    Estado estado = (Estado) estadoComboBox.getSelectedItem();
                    Categoria categoria = (Categoria) categoriaComboBox.getSelectedItem();
                    double valorDeCompra = Double.parseDouble(valorDeCompraField.getText());
                    String placa = placaField.getText();
                    int ano = Integer.parseInt(anoField.getText());
                    Veiculo veiculo = null;

                    String tipo = (String) tipoComboBox.getSelectedItem();
                    switch (tipo) {
                        case "Automovel":
                            ModeloAutomovel modeloAutomovel = (ModeloAutomovel) modeloAutomovelComboBox.getSelectedItem();
                            veiculo = new Automovel(marca, estado, categoria, valorDeCompra, placa, ano, modeloAutomovel);
                            break;
                        case "Motocicleta":
                            ModeloMotocicleta modeloMotocicleta = (ModeloMotocicleta) modeloMotocicletaComboBox.getSelectedItem();
                            veiculo = new Motocicleta(marca, estado, categoria, valorDeCompra, placa, ano, modeloMotocicleta);
                            break;
                        case "Van":
                            ModeloVan modeloVan = (ModeloVan) modeloVanComboBox.getSelectedItem();
                            veiculo = new Van(marca, estado, categoria, valorDeCompra, placa, ano, modeloVan);
                            break;
                    }

                    if (veiculo != null) {
                        veiculoDao.add(veiculo);
                        JOptionPane.showMessageDialog(CadastroVeiculo.this, "Veículo salvo com sucesso!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CadastroVeiculo.this, "Erro ao salvar veículo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection connection = ConnectionFactory.getConnection();
                    new CadastroVeiculo(connection);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
