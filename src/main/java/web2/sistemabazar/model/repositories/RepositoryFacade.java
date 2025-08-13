package web2.sistemabazar.model.repositories;

import web2.sistemabazar.model.classes.OrgaoDonatario;
import java.sql.SQLException;
import java.util.List;

public class RepositoryFacade {

    private static RepositoryFacade instance;
    private OrgaoDonatarioRepository orgaoDonatarioRepository;

    private RepositoryFacade() {
        this.orgaoDonatarioRepository = new OrgaoDonatarioRepository();
    }

    public static RepositoryFacade getCurrentInstance() {
        if (instance == null) {
            instance = new RepositoryFacade();
        }
        return instance;
    }

    // ============= CRUD - Órgão Donatário =============
    public void createOrgaoDonatario(OrgaoDonatario o) throws ClassNotFoundException, SQLException {
        orgaoDonatarioRepository.create(o);
    }

    public void updateOrgaoDonatario(OrgaoDonatario o) throws ClassNotFoundException, SQLException {
        orgaoDonatarioRepository.update(o);
    }

    public OrgaoDonatario readOrgaoDonatario(int id) throws ClassNotFoundException, SQLException {
        return orgaoDonatarioRepository.read(id);
    }

    public void deleteOrgaoDonatario(OrgaoDonatario o) throws ClassNotFoundException, SQLException {
        orgaoDonatarioRepository.delete(o);
    }

    public List<OrgaoDonatario> readAllOrgaoDonatarios() throws ClassNotFoundException, SQLException {
        return orgaoDonatarioRepository.readAll();
    }
}
