/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package locadora.model.dao.veiculo;

import java.util.List;
import locadora.model.veiculo.Veiculo;
import locadora.model.dao.Dao;
import locadora.model.veiculo.Categoria;
import locadora.model.veiculo.Marca;

/**
 *
 * @author Cristhian
 */
public interface VeiculoDao  extends Dao<Veiculo>{
    public List<Veiculo> getByTipoMarcaCategoria(String tipo, Marca marca, Categoria categoria, String estado) throws Exception;
}
