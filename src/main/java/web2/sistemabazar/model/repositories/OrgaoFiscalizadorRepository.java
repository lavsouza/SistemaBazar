package web2.sistemabazar.model.repositories;

import web2.sistemabazar.model.classes.OrgaoFiscalizador;

import java.sql.SQLException;
import java.util.List;

public class OrgaoFiscalizadorRepository implements Repository<Integer, OrgaoFiscalizador>{

    @Override
    public void create(OrgaoFiscalizador c) throws ClassNotFoundException, SQLException {

    }

    @Override
    public void update(OrgaoFiscalizador c) throws ClassNotFoundException, SQLException {

    }

    @Override
    public OrgaoFiscalizador read(Integer k) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public void delete(OrgaoFiscalizador c) throws ClassNotFoundException, SQLException {

    }

    @Override
    public List<OrgaoFiscalizador> readAll() throws ClassNotFoundException, SQLException {
        return List.of();
    }
}
