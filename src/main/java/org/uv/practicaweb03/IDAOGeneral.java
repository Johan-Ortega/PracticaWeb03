
package org.uv.practicaweb03;

import java.util.List;

public interface IDAOGeneral <T, I>{
    public boolean guardar(T pojo);
    public boolean modificar(T pojo, I id);
    public boolean eliminar(I id);
    public T buscarById(I id);
    public List<T> buscarTodos();
}
