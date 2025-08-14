package web2.sistemabazar.model.repositories;

import web2.sistemabazar.model.classes.Lote;
import web2.sistemabazar.model.classes.OrgaoDonatario;
import web2.sistemabazar.model.classes.OrgaoFiscalizador;

import java.sql.SQLException;
import java.util.List;

public class RepositoryFacade {

    private static RepositoryFacade instance;
    private OrgaoDonatarioRepository orgaoDonatarioRepository;
    private OrgaoFiscalizadorRepository orgaoFiscalizadorRepository;
    private LoteRepository loteRepository;

    private RepositoryFacade() {
        this.orgaoDonatarioRepository = new OrgaoDonatarioRepository();
        this.orgaoFiscalizadorRepository = new OrgaoFiscalizadorRepository();
        this.loteRepository = new LoteRepository();
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


    // ============= CRUD - Órgão Fiscalizador =============
    public void createOrgaoFiscalizador(OrgaoFiscalizador o) throws ClassNotFoundException, SQLException {
        orgaoFiscalizadorRepository.create(o);
    }

    public void updateOrgaoFiscalizador(OrgaoFiscalizador o) throws ClassNotFoundException, SQLException {
        orgaoFiscalizadorRepository.update(o);
    }

    public OrgaoFiscalizador readOrgaoFiscalizador(int id) throws ClassNotFoundException, SQLException {
        return orgaoFiscalizadorRepository.read(id);
    }

    public void deleteOrgaoFiscalizador(OrgaoFiscalizador o) throws ClassNotFoundException, SQLException {
        orgaoFiscalizadorRepository.delete(o);
    }

    public List<OrgaoFiscalizador> readAllOrgaoFiscalizadores() throws ClassNotFoundException, SQLException {
        return orgaoFiscalizadorRepository.readAll();
    }

    // ============= CRUD - Lote =============
    public void createLote(Lote l) throws ClassNotFoundException, SQLException {
        loteRepository.create(l);
    }

    public void updateLote(Lote l) throws ClassNotFoundException, SQLException {
        loteRepository.update(l);
    }

    public Lote readLote(int id) throws ClassNotFoundException, SQLException {
        return loteRepository.read(id);
    }

    public void deleteLote(Lote l) throws ClassNotFoundException, SQLException {
        loteRepository.delete(l);
    }

    public List<Lote> readAllLotes() throws ClassNotFoundException, SQLException {
        return loteRepository.readAll();
    }

    public List<Lote> buscarLotePorDonatarioEFiscalizador(Integer cd, Integer cf) throws ClassNotFoundException, SQLException {
        return loteRepository.buscarLotePorDonatarioEFiscalizador(cd, cf);
    }

}
