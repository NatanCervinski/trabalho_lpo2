package locadora;




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
        switch (getCategoria()) {
            case POPULAR:
                return 100.0;
            case INTERMEDIARIO:
                return 300.0;
            case LUXO:
                return 450.0;
            default:
                return 0.0;
        }
    }
}
