/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package locadora.view.locacao.locar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import locadora.model.cliente.Cliente;
import locadora.model.veiculo.Automovel;
import locadora.model.veiculo.Motocicleta;
import locadora.model.veiculo.Van;
import locadora.model.veiculo.Veiculo;

public class LocacaoVeiculoTableModel extends AbstractTableModel{
    private String[] colunas=new String[]{"id","Placa","Marca", "Modelo", "Ano","Preço da diária"};
    
    private List<Veiculo> lista=new ArrayList();
    
    public LocacaoVeiculoTableModel(List<Veiculo> lista){
        this.lista=lista;
    }

    public LocacaoVeiculoTableModel(){
    }

    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
        /*if(column==0)
            return true;
        return false;*/
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veiculo veiculo = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return veiculo.getId();
            case 1: return veiculo.getPlaca();
            case 2: return veiculo.getMarca();
            case 3: return getModelo(veiculo);
            case 4: return veiculo.getAno();
            case 5: return getPrecoDiaria(veiculo);            
            default : return null;
        }
    }
    
    private String getModelo(Veiculo veiculo) {
        if (veiculo instanceof Automovel) {
            return ((Automovel) veiculo).getModelo().toString();
        } else if (veiculo instanceof Van) {
            return ((Van) veiculo).getModelo().toString();
        } else if (veiculo instanceof Motocicleta) {
            return ((Motocicleta) veiculo).getModelo().toString();
        }
        return "";
    }

    private double getPrecoDiaria(Veiculo veiculo) {
        if (veiculo instanceof Automovel) {
            return ((Automovel) veiculo).getValorDiariaLocacao();
        } else if (veiculo instanceof Van) {
            return ((Van) veiculo).getValorDiariaLocacao();
        } else if (veiculo instanceof Motocicleta) {
            return ((Motocicleta) veiculo).getValorDiariaLocacao();
        }
        return 0.0;
    }

    public void setListaVeiculo(List<Veiculo> veiculos) {
        this.lista = veiculos;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
    }

    public void limpaTabela() {
        int indice = lista.size()-1;
        if(indice<0)
            indice=0;
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0,indice);//update JTable
    }

    public Veiculo getVeiculo(int linha){
        return lista.get(linha);
    }
  
}
