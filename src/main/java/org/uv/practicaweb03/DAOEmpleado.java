
package org.uv.practicaweb03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmpleado implements IDAOGeneral<Empleado, Integer> {

    @Override
    public boolean guardar(Empleado pojo) {
        ConexionDB con=ConexionDB.getInstance();
        TransactionDB<Empleado, Integer> t = new TransactionDB<Empleado, Integer>(pojo, pojo.getClave()){
            @Override
            public boolean execute(Connection con) {
                try{
                    String sql ="insert into empleado(clave, nombre, direccion, telefono)" + "values (?, ?, ?, ?)";
                    PreparedStatement pst=con.prepareStatement(sql);
                    pst.setInt(1, pojo.getClave());
                    pst.setString(2, pojo.getNombre());
                    pst.setString(3, pojo.getDireccion());
                    pst.setString(4, pojo.getTelefono());

                    pst.execute();
                    return true;
                } catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                
            }    
        };
        boolean res = con.execute(t);
        if(res)
            Logger.getLogger("DAOEmpleado").log(Level.INFO, "Se guardo");
        else
            Logger.getLogger("DAOEmpleado").log(Level.INFO, "Error");
        return res;
    }

    @Override
    public boolean modificar(Empleado pojo, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Integer id) {
        ConexionDB con=ConexionDB.getInstance();
        TransactionDB<Object, Integer> t=new TransactionDB(null, id) {
            @Override
            public boolean execute(Connection con) {
                String sql="Delete * from empleado where clave=?";
                PreparedStatement pst=null;
                try{
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, (int) id);
                    return true;
                } catch(SQLException ex){
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                finally{
                    if (pst != null){
                        try{
                            pst.close();
                        }catch(SQLException ex){
                            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            
        };
        return con.execute(t);
    }

    @Override
    public Empleado buscarById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empleado> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
