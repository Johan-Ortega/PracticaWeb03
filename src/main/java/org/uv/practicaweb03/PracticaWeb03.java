
package org.uv.practicaweb03;

public class PracticaWeb03 {

    public static void main(String[] args) {
        Empleado emp= new Empleado();
        emp.setClave(12);
        emp.setNombre("Abel");
        emp.setDireccion("calle 2");
        emp.setTelefono("271475");
        
        DAOEmpleado dao = new DAOEmpleado();
        boolean res =dao.guardar(emp);
    }
    
}
