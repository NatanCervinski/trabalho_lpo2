/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel {
    private final List<Cliente> clientes;
    private final String[] colunas = {"ID", "Nome", "Sobrenome", "RG", "CPF", "Rua", "Número", "Complemento"};

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0: return cliente.getId();
            case 1: return cliente.getNome();
            case 2: return cliente.getSobrenome();
            case 3: return cliente.getRg();
            case 4: return cliente.getCpf();
            case 5: return cliente.getEndereco().getRua();
            case 6: return cliente.getEndereco().getNumero();
            case 7: return cliente.getEndereco().getComplemento();
            default: return null;
        }
    }
}
