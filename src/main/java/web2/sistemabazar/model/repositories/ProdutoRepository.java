package web2.sistemabazar.model.repositories;

import web2.sistemabazar.model.classes.Lote;
import web2.sistemabazar.model.classes.Produto;
import web2.sistemabazar.util.ConnectionManager;

import java.sql.*;
import java.util.List;

public class ProdutoRepository implements Repository<Integer, Produto>{

    @Override
    public void create(Produto c) throws ClassNotFoundException, SQLException {
        String sqlInsert = "INSERT INTO produto (nome, descricao, lote) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getDescricao());
            ps.setInt(3, c.getLote().getId());

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
