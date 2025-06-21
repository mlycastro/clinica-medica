package clinica.dao;

import clinica.model.Convenio;
import clinica.util.ConexaoUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConvenioDAO {

    public void inserir(Convenio convenio) {
        String sql = "INSERT INTO convenios (nome_empresa, cnpj, telefone) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, convenio.getNomeEmpresa());
            stmt.setString(2, convenio.getCnpj());
            stmt.setString(3, convenio.getTelefone());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir convênio: " + ex.getMessage(), ex);
        }
    }

    public void atualizar(Convenio convenio) {
        String sql = "UPDATE convenios SET nome_empresa = ?, cnpj = ?, telefone = ? WHERE id = ?";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, convenio.getNomeEmpresa());
            stmt.setString(2, convenio.getCnpj());
            stmt.setString(3, convenio.getTelefone());
            stmt.setInt(4, convenio.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar convênio: " + ex.getMessage(), ex);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM convenios WHERE id = ?";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir convênio: " + ex.getMessage(), ex);
        }
    }

    public Convenio buscarPorId(int id) {
        String sql = "SELECT * FROM convenios WHERE id = ?";

        try (Connection conn = ConexaoUtil.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Convenio(
                        rs.getInt("id"),
                        rs.getString("nome_empresa"),
                        rs.getString("cnpj"),
                        rs.getString("telefone")
                    );
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar convênio por ID: " + ex.getMessage(), ex);
        }

        return null;
    }

    public List<Convenio> buscarTodos() {
        List<Convenio> lista = new ArrayList<>();
        String sql = "SELECT * FROM convenios";

        try (Connection conn = ConexaoUtil.obterConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Convenio convenio = new Convenio(
                    rs.getInt("id"),
                    rs.getString("nome_empresa"),
                    rs.getString("cnpj"),
                    rs.getString("telefone")
                );
                lista.add(convenio);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar convênios: " + ex.getMessage(), ex);
        }

        return lista;
    }
    
    public List<Convenio> buscarPorNomeEmpresa(String nomeEmpresa) {
    List<Convenio> lista = new ArrayList<>();

    String sql = "SELECT * FROM convenios WHERE nome_empresa LIKE ?";

    try (Connection conn = ConexaoUtil.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + nomeEmpresa + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Convenio convenio = new Convenio();
            convenio.setId(rs.getInt("id"));
            convenio.setNomeEmpresa(rs.getString("nome_empresa"));
            convenio.setCnpj(rs.getString("cnpj"));
            convenio.setTelefone(rs.getString("telefone"));

            lista.add(convenio);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}
}

