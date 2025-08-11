package web2.sistemabazar.model.repositories;

import web2.sistemabazar.model.classes.OrgaoDonatario;

import java.sql.SQLException;
import java.util.List;

public class OrgaoDonatarioRepository implements Repository<Integer, OrgaoDonatario>{

    @Override
    public void create(OrgaoDonatario c) throws ClassNotFoundException, SQLException {

    }

    @Override
    public void update(OrgaoDonatario c) throws ClassNotFoundException, SQLException {

    }

    @Override
    public OrgaoDonatario read(Integer k) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public void delete(OrgaoDonatario c) throws ClassNotFoundException, SQLException {

    }

    @Override
    public List<OrgaoDonatario> readAll() throws ClassNotFoundException, SQLException {
        return List.of();
    }
}
