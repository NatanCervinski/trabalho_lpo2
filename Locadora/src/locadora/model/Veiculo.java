package locadora.model;

import java.util.Calendar;

public abstract class Veiculo implements VeiculoI {
    private Marca marca;
    private Estado estado;
    private Locacao locacao;
    private Categoria categoria;
    private double valorDeCompra;
    private String placa;
    private int ano;

    public Veiculo(Marca marca, Estado estado, Categoria categoria, double valorDeCompra, String placa, int ano) {
        this.marca = marca;
        this.estado = estado;
        this.categoria = categoria;
        this.valorDeCompra = valorDeCompra;
        this.placa = placa;
        this.ano = ano;
        this.locacao = null;
    }

    @Override
    public void locar(int dias, Calendar data, Cliente cliente) {
        if (estado == Estado.DISPONIVEL) {
            double valor = getValorDiariaLocacao() * dias;
            locacao = new Locacao(dias, valor, data, cliente);
            estado = Estado.LOCADO;
        }
    }

    @Override
    public void vender() {
        estado = Estado.VENDIDO;
        locacao = null;
    }

    @Override
    public void devolver() {
        estado = Estado.DISPONIVEL;
        locacao = null;
    }

    @Override
    public Estado getEstado() {
        return estado;
    }

    @Override
    public Marca getMarca() {
        return marca;
    }

    @Override
    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public Locacao getLocacao() {
        return locacao;
    }

    @Override
    public String getPlaca() {
        return placa;
    }

    @Override
    public int getAno() {
        return ano;
    }

    @Override
    public double getValorParaVenda() {
        int idade = Calendar.getInstance().get(Calendar.YEAR) - ano;
        double valorParaVenda = valorDeCompra - idade * 0.15 * valorDeCompra;
        if (valorParaVenda < valorDeCompra * 0.1) {
            valorParaVenda = valorDeCompra * 0.1;
        }
        return valorParaVenda;
    }

    @Override
    public abstract double getValorDiariaLocacao();
}
