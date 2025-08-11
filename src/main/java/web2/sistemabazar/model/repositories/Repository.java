package web2.sistemabazar.model.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository <KEY,CLASS>{

    public void create(CLASS c)throws ClassNotFoundException, SQLException;
    public void update(CLASS c)throws ClassNotFoundException, SQLException;
    public CLASS read(KEY k)throws ClassNotFoundException, SQLException;
    public void delete(CLASS c)throws ClassNotFoundException, SQLException;
    public List<CLASS> readAll()throws ClassNotFoundException, SQLException;

}
