package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ClienteController;

public class ClienteView extends JFrame {
    private ClienteController controller;
    private JTextField nomeField, sobrenomeField, rgField, cpfField, enderecoField;
    private JTable clienteTable;
    private ClienteTableModel clienteTableModel;

    public ClienteView() {
        controller = new ClienteController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Manutenção de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Sobrenome:"));
        sobrenomeField = new JTextField();
        formPanel.add(sobrenomeField);

        formPanel.add(new JLabel("RG:"));
        rgField = new JTextField();
        formPanel.add(rgField);

        formPanel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        formPanel.add(cpfField);

        formPanel.add(new JLabel("Endereço:"));
        enderecoField = new JTextField();
        formPanel.add(enderecoField);

        add(formPanel, BorderLayout.NORTH);

        clienteTableModel = new ClienteTableModel();
        clienteTable = new JTable(clienteTableModel);
        add(new JScrollPane(clienteTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.addCliente(nomeField.getText(), sobrenomeField.getText(), rgField.getText(), cpfField.getText(), enderecoField.getText());
                refreshTable();
            }
        });
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clienteTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) clienteTableModel.getValueAt(selectedRow, 0);
                    controller.updateCliente(id, nomeField.getText(), sobrenomeField.getText(), rgField.getText(), cpfField.getText(), enderecoField.getText());
                    refreshTable();
                }
            }
        });
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Excluir");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clienteTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) clienteTableModel.getValueAt(selectedRow, 0);
                    controller.deleteCliente(id);
                    refreshTable();
                }
            }
        });
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshTable() {
        clienteTableModel.setClientes(controller.getAllClientes());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClienteView().setVisible(true);
            }
        });
    }
}
