package locadora.model.locacao;

import locadora.model.cliente.Cliente;
import java.util.Calendar;
import locadora.model.veiculo.Automovel;
import locadora.model.veiculo.Marca;
import locadora.model.veiculo.Motocicleta;
import locadora.model.veiculo.Van;
import locadora.model.veiculo.Veiculo;

public class Locacao {
    private long id;
    private int dias;
    private double valor;
    private Calendar data;
    private Cliente cliente;
    private Veiculo veiculo;

    public Locacao(long id, int dias, double valor, Calendar data, Cliente cliente, Veiculo veiculo) {
        this.id = id;
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }
    
    public long getId(){
        return id;
    }

    public int getDias() {
        return dias;
    }

    public double getValor() {
        return valor;
    }

    public Calendar getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public Veiculo getVeiculo(){
        return veiculo;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return this.cliente.getNome();
    }

    public String getPlaca() {
        return this.veiculo.getPlaca();
    }

    public Marca getMarca() {
        return this.veiculo.getMarca();
    }

    public int getAno() {
        return this.veiculo.getAno();
    }

    public String getModelo() {
        return getModelo(this.veiculo);
    }

    public Calendar getDataLocacao() {
        return this.getData();
    }

    public double getPrecoDiaria() {
        return getPrecoDiaria(this.veiculo);
    }

    public int getQtdDiasLocado() {
        return this.getDias();
    }

    public double valorLcoado() {
        return this.getValor();
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

    public void devolver() {
        this.veiculo.devolver();
    }
}
