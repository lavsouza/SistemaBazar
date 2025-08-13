package web2.sistemabazar.model.repositories;

import web2.sistemabazar.model.classes.OrgaoDonatario;
import web2.sistemabazar.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrgaoDonatarioRepository implements Repository<Integer, OrgaoDonatario> {

    @Override
    public void create(OrgaoDonatario c) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO orgao_donatario (nome, endereco, telefone, horario_funcionamento, descricao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getEndereco());
            ps.setString(3, c.getTelefone());
            ps.setString(4, c.getHorarioFuncionamento());
            ps.setString(5, c.getDescricao());

            boolean affected = ps.execute();
            if (affected) {
                throw new SQLException("Criação Orgao Donatario falhou");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(OrgaoDonatario c) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE orgao_donatario SET nome=?, endereco=?, telefone=?, horario_funcionamento=?, descricao=? WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getEndereco());
            ps.setString(3, c.getTelefone());
            ps.setString(4, c.getHorarioFuncionamento());
            ps.setString(5, c.getDescricao());
            ps.setInt(6, c.getId());

            ps.execute();
        }
    }

    @Override
    public OrgaoDonatario read(Integer k) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM orgao_donatario WHERE id=?";
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
    public void delete(OrgaoDonatario c) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM orgao_donatario WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<OrgaoDonatario> readAll() throws ClassNotFoundException, SQLException {
        List<OrgaoDonatario> lista = new ArrayList<>();
        String sql = "SELECT id, nome, endereco, telefone, horario_funcionamento, descricao FROM orgao_donatario ORDER BY nome";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(DTO(rs));
            }
        }
        return lista;
    }

    private OrgaoDonatario DTO(ResultSet rs) throws SQLException {
        OrgaoDonatario o = new OrgaoDonatario();
        o.setId(rs.getInt("id"));
        o.setNome(rs.getString("nome"));
        o.setEndereco(rs.getString("endereco"));
        o.setTelefone(rs.getString("telefone"));
        o.setHorarioFuncionamento(rs.getString("horario_funcionamento"));
        o.setDescricao(rs.getString("descricao"));
        return o;
    }
}
