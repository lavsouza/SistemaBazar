package web2.sistemabazar.model.repositories;

import web2.sistemabazar.model.classes.Produto;

import java.sql.SQLException;
import java.util.List;

public class ProdutoRepository implements Repository<Integer, Produto>{

    @Override
    public void create(Produto c) throws ClassNotFoundException, SQLException {

    }

    @Override
    public void update(Produto c) throws ClassNotFoundException, SQLException {

    }

    @Override
    public Produto read(Integer k) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public void delete(Produto c) throws ClassNotFoundException, SQLException {

    }

    @Override
    public List<Produto> readAll() throws ClassNotFoundException, SQLException {
        return List.of();
    }
}
