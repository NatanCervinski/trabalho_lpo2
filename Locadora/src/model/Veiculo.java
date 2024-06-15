package model;

public abstract class Veiculo implements VeiculoI {
    protected Marca marca;
    protected Estado estado;
    protected Categoria categoria;
    protected double valorDeCompra;
    protected String placa;
    protected int ano;
    protected Locacao locacao;

    // Construtor
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
        this.locacao = new Locacao(dias, getValorDiariaLocacao() * dias, data, cliente);
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
        return Math.max(valorParaVenda, valorDeCompra * 0.1);
    }

    // Método abstrato para o valor da diária
    @Override
    public abstract double getValorDiariaLocacao();
}
