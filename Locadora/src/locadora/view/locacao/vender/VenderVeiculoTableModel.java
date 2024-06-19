/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package locadora.view.locacao.vender;

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

public class VenderVeiculoTableModel extends AbstractTableModel{
    private String[] colunas=new String[]{"id", "Placa","Marca", "Modelo", "Ano","Preço para venda"};
    
    private List<Veiculo> lista=new ArrayList();
    
    public VenderVeiculoTableModel(List<Veiculo> lista){
        this.lista=lista;
    }

    public VenderVeiculoTableModel(){
    }
    
    public void setListaVeiculo(List<Veiculo> veiculos) {
        this.lista = veiculos;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
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
            case 5: return veiculo.getValorParaVenda();
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


//    public void setListaLocacao(List<Locacao> locacao) {
//        this.lista = locacao;
//        this.fireTableDataChanged();
//        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
//    }

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

    public Veiculo getLocacao(int linhaClicadaParaLocacao) {
        return lista.get(linhaClicadaParaLocacao);
    }

    boolean removeVeiculo(Veiculo veiculo) {
        int linha = this.lista.indexOf(veiculo);
        boolean result = this.lista.remove(veiculo);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }
   
  
}
