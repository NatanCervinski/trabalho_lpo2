package locadora.model;

import java.util.Calendar;

public abstract class Veiculo implements VeiculoI {
    private int id;
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
        this.estado = Estado.LOCADO;
        double valor = dias * getValorDiariaLocacao();
        this.locacao = new Locacao(dias, valor, data, cliente);
    }

    @Override
    public void vender() {
        this.estado = Estado.VENDIDO;
    }

    @Override
    public void devolver() {
        this.estado = Estado.DISPONIVEL;
        this.locacao = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getValorDeCompra() {
        return valorDeCompra;
    }

    public void setValorDeCompra(double valorDeCompra) {
        this.valorDeCompra = valorDeCompra;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    

    @Override
    public double getValorParaVenda() {
        int idade = Calendar.getInstance().get(Calendar.YEAR) - ano;
        double valorParaVenda = valorDeCompra - idade * 0.15 * valorDeCompra;
        return Math.max(valorParaVenda, valorDeCompra * 0.1);
    }

    @Override
    public abstract double getValorDiariaLocacao();
}
