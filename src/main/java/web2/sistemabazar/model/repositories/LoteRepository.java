package web2.sistemabazar.model.repositories;

import web2.sistemabazar.model.classes.Lote;
import web2.sistemabazar.model.classes.OrgaoDonatario;
import web2.sistemabazar.model.classes.OrgaoFiscalizador;
import web2.sistemabazar.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoteRepository implements Repository<Integer, Lote> {

    @Override
    public void create(Lote l) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO lote (data_entrega, observacao, cod_donatario, cod_fiscal) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, java.sql.Date.valueOf(l.getDataEntrega()));
            ps.setString(2, l.getObservacao());
            ps.setInt(3, l.getIdOrgaoDonatario());
            ps.setInt(4, l.getIdOrgaoFiscalizador());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Criação de Lote falhou, nenhuma linha afetada.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    l.setId(rs.getInt(1));
                }
            }
        }
    }


    @Override
    public void update(Lote l) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE lote SET data_entrega=?, observacao=?, cod_donatario=?, cod_fiscal=? WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(l.getDataEntrega()));
            ps.setString(2, l.getObservacao());
            ps.setInt(3, l.getIdOrgaoDonatario());
            ps.setInt(4, l.getIdOrgaoFiscalizador());
            ps.setInt(5, l.getId());

            ps.execute();
        }
    }

    @Override
    public Lote read(Integer k) throws ClassNotFoundException, SQLException {
        String sql = """
        SELECT 
            l.id AS lote_id,
            l.data_entrega,
            l.observacao,
            od.id AS od_id,
            od.nome AS od_nome,
            ofi.id AS of_id,
            ofi.nome AS of_nome
        FROM lote l
        JOIN orgao_donatario od ON l.cod_donatario = od.id
        JOIN orgao_fiscalizador ofi ON l.cod_fiscal = ofi.id
        WHERE l.id = ?
    """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, k);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return DTO(rs);
                }
                return null;
            }
        }
    }

    @Override
    public void delete(Lote l) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM lote WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, l.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Lote> readAll() throws ClassNotFoundException, SQLException {
        List<Lote> lista = new ArrayList<>();
        String sql = """
        SELECT 
            l.id AS lote_id,
            l.data_entrega,
            l.observacao,
            od.id AS od_id,
            od.nome AS od_nome,
            ofi.id AS of_id,
            ofi.nome AS of_nome
        FROM lote l
        JOIN orgao_donatario od ON l.cod_donatario = od.id
        JOIN orgao_fiscalizador ofi ON l.cod_fiscal = ofi.id
        ORDER BY l.id
    """;
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(DTO(rs));
            }
        }
        return lista;
    }

    public List<Lote> buscarLotePorDonatarioEFiscalizador(Integer cd, Integer cf)
            throws ClassNotFoundException, SQLException {

        List<Lote> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
        SELECT
            l.id AS lote_id,
            l.data_entrega,
            l.observacao,
            od.id AS od_id,
            od.nome AS od_nome,
            ofi.id AS of_id,
            ofi.nome AS of_nome
        FROM lote l
        JOIN orgao_donatario od ON l.cod_donatario = od.id
        JOIN orgao_fiscalizador ofi ON l.cod_fiscal = ofi.id
        WHERE 1=1
    """);

        if (cd != null) {
            sql.append(" AND l.cod_donatario = ?");
        }
        if (cf != null) {
            sql.append(" AND l.cod_fiscal = ?");
        }

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (cd != null) {
                ps.setInt(paramIndex++, cd);
            }
            if (cf != null) {
                ps.setInt(paramIndex++, cf);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(DTO(rs));
                }
            }
        }
        return lista;
    }

    private Lote DTO(ResultSet rs) throws SQLException {
        Lote l = new Lote();
        l.setId(rs.getInt("lote_id"));
        l.setDataEntrega(rs.getDate("data_entrega").toLocalDate());
        l.setObservacao(rs.getString("observacao"));
        l.setIdOrgaoDonatario(rs.getInt("od_id"));
        l.setIdOrgaoFiscalizador(rs.getInt("of_id"));
        l.setOrgaoDonatarioNome(rs.getString("od_nome"));
        l.setOrgaoFiscalizadorNome(rs.getString("of_nome"));

        return l;
    }

}

