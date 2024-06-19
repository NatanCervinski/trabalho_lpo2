/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.model.dao;

import locadora.model.dao.veiculo.VeiculoDao;
import locadora.model.dao.veiculo.VeiculoDaoSql;
import locadora.model.dao.cliente.ClienteDaoSql;
import locadora.model.dao.cliente.ClienteDao;
import locadora.model.dao.locacao.LocacaoDao;
import locadora.model.dao.locacao.LocacaoDaoSql;




/**
 *
 * @author rafae
 */
public class DaoFactory {
    private DaoFactory(){
    }
    /*
    public static UniversidadeDao getUniversidadeDao(DaoType type){
        switch(type){
            case SQL : 
                return UniversidadeDaoSql.getUniversidadeDaoSql();
            default:
                throw new RuntimeException("Tipo não existe:"+type);
        }
    }
    
    public static DepartamentoDao getDepartamentoDao(DaoType type){
        switch(type){
            case SQL : 
                return DepartamentoDaoSql.getDepartamentoDaoSql();
            default:
                throw new RuntimeException("Tipo não existe:"+type);
        }
    } */
   
    public static ClienteDao getClienteDao(){
        return ClienteDaoSql.getClienteDaoSql();
    }
    
    public static VeiculoDao getVeiculoDao(){
        return VeiculoDaoSql.getVeiculoDaoSql();
    }
    
    public static LocacaoDao getLocacaoDao(){
        return LocacaoDaoSql.getLocacaoDaoSql();
    }
    /*
     public static DisciplinaDao getDisciplinaDao(DaoType type){
        switch(type){
            case SQL : 
                return DisciplinaDaoSql.getDisciplinaDaoSql();
            default:
                throw new RuntimeException("Tipo não existe:"+type);
        } 
    }*/
    
}
