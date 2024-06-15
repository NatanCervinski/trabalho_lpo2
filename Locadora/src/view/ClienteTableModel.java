package view;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes;
    private String[] columnNames = {"ID", "Nome", "Sobrenome", "RG", "CPF", "Endereço"};

    public ClienteTableModel() {
        this.clientes = new ArrayList<>();
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cliente.getId();
            case 1:
                return cliente.getNome();
            case 2:
                return cliente.getSobrenome();
            case 3:
                return cliente.getRg();
            case 4:
                return cliente.getCpf();
            case 5:
                return cliente.getEndereco();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
