package web2.sistemabazar.model.repositories;

import web2.sistemabazar.model.classes.Lote;
import web2.sistemabazar.model.classes.Produto;
import web2.sistemabazar.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository implements Repository<Integer, Produto>{

    @Override
    public void create(Produto c) throws ClassNotFoundException, SQLException {
        String sqlInsert = "INSERT INTO produto (nome, descricao, lote) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getDescricao());
            ps.setInt(3, c.getIdLote());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Criação de Produto falhou, nenhuma linha afetada.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setCodigo(rs.getInt(1)); // ID gerado para Produto
                }
            }
        }
    }

    @Override
    public void update(Produto c) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE produto SET nome=?, descricao=?, lote=? WHERE codigo=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getDescricao());
            ps.setInt(3, c.getIdLote());
            ps.setInt(4, c.getCodigo());

            ps.execute();
        }
    }

    @Override
    public Produto read(Integer k) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM produto p JOIN lote l ON l.id = p.lote WHERE p.codigo = ?";
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

    public List<Produto> buscarProdutoPorLote(Integer l) throws ClassNotFoundException, SQLException {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto p WHERE p.lote = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, l);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(DTO(rs));
                }
            }
        }
        return lista;
    }

    @Override
    public void delete(Produto c) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM produto WHERE codigo=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getCodigo());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Produto> readAll() throws ClassNotFoundException, SQLException {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto p JOIN lote l ON l.id = p.lote ORDER BY p.lote";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(DTO(rs));
            }
        }
        return lista;
    }

    private Produto DTO(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setCodigo(rs.getInt("codigo"));
        p.setNome(rs.getString("nome"));
        p.setDescricao(rs.getString("descricao"));
        p.setIdLote(rs.getInt("lote"));

        return p;
    }
}
