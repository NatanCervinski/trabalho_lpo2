/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package locadora.model.dao.cliente;

import java.util.List;
import locadora.model.cliente.Cliente;
import locadora.model.dao.Dao;

/**
 *
 * @author rafae
 */
public interface ClienteDao extends Dao<Cliente>{
  //  public List<Disciplina> getDisciplinasByProfessor(Cliente professor) throws Exception;    
    public void delete(List<Cliente> lista) throws Exception; 
}
