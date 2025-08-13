package web2.sistemabazar.model.repositories;

import web2.sistemabazar.model.classes.OrgaoFiscalizador;
import web2.sistemabazar.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrgaoFiscalizadorRepository implements Repository<Integer, OrgaoFiscalizador>{

    @Override
    public void create(OrgaoFiscalizador c) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO orgao_fiscalizador (nome, descricao) VALUES (?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getDescricao());

            boolean affected = ps.execute();
            if (affected) {
                throw new SQLException("Criação Orgao fiscalizador falhou");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(OrgaoFiscalizador c) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE orgao_fiscalizador SET nome=?, descricao=? WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getDescricao());
            ps.setInt(3, c.getId());

            ps.execute();
        }
    }

    @Override
    public OrgaoFiscalizador read(Integer k) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM orgao_fiscalizador WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, k);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return DTO(rs);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void delete(OrgaoFiscalizador c) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM orgao_fiscalizador WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<OrgaoFiscalizador> readAll() throws ClassNotFoundException, SQLException {
        List<OrgaoFiscalizador> lista = new ArrayList<>();
        String sql = "SELECT * FROM orgao_fiscalizador ORDER BY nome";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(DTO(rs));
            }
        }
        return lista;
    }

    private OrgaoFiscalizador DTO(ResultSet rs) throws SQLException {
        OrgaoFiscalizador o = new OrgaoFiscalizador();
        o.setId(rs.getInt("id"));
        o.setNome(rs.getString("nome"));
        o.setDescricao(rs.getString("descricao"));
        return o;
    }
}
