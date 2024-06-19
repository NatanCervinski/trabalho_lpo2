/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package locadora.view.locacao.devolver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import locadora.model.cliente.Cliente;
import locadora.model.locacao.Locacao;
import locadora.model.veiculo.Automovel;
import locadora.model.veiculo.Motocicleta;
import locadora.model.veiculo.Van;
import locadora.model.veiculo.Veiculo;

public class DevolverVeiculoTableModel extends AbstractTableModel{
    private String[] colunas=new String[]{"id", "Nome do cliente", "Placa","Marca", "Modelo", "Ano","Data locação", "Preço da diária", "Quantidade de dias locado", "Valor locação"};
    
    private List<Locacao> lista=new ArrayList();
    
    public DevolverVeiculoTableModel(List<Locacao> lista){
        this.lista=lista;
    }

    public DevolverVeiculoTableModel(){
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
        Locacao locacao = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return locacao.getId();
            case 1: return locacao.getNomeCliente();
            case 2: return locacao.getPlaca();
            case 3: return locacao.getMarca();
            case 4: return locacao.getModelo();
            case 5: return locacao.getAno();
            case 6: return locacao.getDataLocacao();
            case 7: return locacao.getPrecoDiaria();
            case 8: return locacao.getQtdDiasLocado();  
            case 9: return locacao.valorLcoado();
            default : return null;
        }
    }

    public void setListaLocacao(List<Locacao> locacao) {
        this.lista = locacao;
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

    public Locacao getVeiculo(int linha){
        return lista.get(linha);
    }
    
    public boolean removeLocacao(Locacao locacao) {
        int linha = this.lista.indexOf(locacao);
        boolean result = this.lista.remove(locacao);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }
    void removeLocacoes(List<Locacao> listaParaExcluir) {
      listaParaExcluir.forEach((locacao) -> {
            removeLocacao(locacao);
        });
    }

    public Locacao getLocacao(int linhaClicadaParaLocacao) {
        return lista.get(linhaClicadaParaLocacao);
    }
   
  
}
