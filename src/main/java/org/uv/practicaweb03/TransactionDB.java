
package org.uv.practicaweb03;

import java.sql.Connection;

public abstract class TransactionDB<T, I> {
    
    protected T pojo;
    protected I id;
    public TransactionDB(T pojo, I id){
        this.pojo=pojo;
        this.id=id;
    }
    
    public abstract boolean execute(Connection con);
    
    
}
