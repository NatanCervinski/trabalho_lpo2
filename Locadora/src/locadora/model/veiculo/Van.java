package locadora.model.veiculo;

import locadora.model.cliente.Estado;



public class Van extends Veiculo {
    private ModeloVan modelo;

    public Van(long id, Marca marca, Estado estado, Categoria categoria, double valorDeCompra, String placa, int ano, ModeloVan modelo) {
        super(id, marca, estado, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }

    public ModeloVan getModelo() {
        return modelo;
    }

    @Override
    public double getValorDiariaLocacao() {
        switch (getCategoria()) {
            case POPULAR:
                return 200.0;
            case INTERMEDIARIO:
                return 400.0;
            case LUXO:
                return 600.0;
            default:
                return 0.0;
        }
    }
}
