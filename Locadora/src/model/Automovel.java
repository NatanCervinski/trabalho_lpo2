package model;

public class Automovel extends Veiculo {
    private ModeloAutomovel modelo;

    public Automovel(Marca marca, Estado estado, Categoria categoria, double valorDeCompra, String placa, int ano, ModeloAutomovel modelo) {
        super(marca, estado, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }

    public ModeloAutomovel getModelo() {
        return modelo;
    }

    @Override
    public double getValorDiariaLocacao() {
        switch (categoria) {
            case POPULAR:
                return 100.00;
            case INTERMEDIARIO:
                return 300.00;
            case LUXO:
                return 450.00;
            default:
                return 0;
        }
    }
}

// Crie as classes Motocicleta e Van de forma semelhante, alterando o método getValorDiariaLocacao de acordo com a tabela fornecida.
