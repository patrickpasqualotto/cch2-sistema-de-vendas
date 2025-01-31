package tabelas;

import conexaobd.ConexaoBDPostgres;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Funcionarios {
    private long codFuncionarios;
    private String nomeFuncionarios;
    private String cpfFuncionarios;
    private String senhaFuncionarios;
    private String funcaoFuncionarios;

    public Funcionarios() {}

    public Funcionarios(long codFuncionarios, String nomeFuncionarios, String cpfFuncionarios, String senhaFuncionarios, String funcaoFuncionarios) {
        this.codFuncionarios = codFuncionarios;
        this.nomeFuncionarios = nomeFuncionarios;
        this.cpfFuncionarios = cpfFuncionarios;
        this.senhaFuncionarios = senhaFuncionarios;
        this.funcaoFuncionarios = funcaoFuncionarios;
    }

    // Getters e Setters
    public long getCodFuncionarios() { return codFuncionarios; }
    public void setCodFuncionarios(long codFuncionarios) { this.codFuncionarios = codFuncionarios; }

    public String getNomeFuncionarios() { return nomeFuncionarios; }
    public void setNomeFuncionarios(String nomeFuncionarios) { this.nomeFuncionarios = nomeFuncionarios; }

    public String getCpfFuncionarios() { return cpfFuncionarios; }
    public void setCpfFuncionarios(String cpfFuncionarios) { this.cpfFuncionarios = cpfFuncionarios; }

    public String getSenhaFuncionarios() { return senhaFuncionarios; }
    public void setSenhaFuncionarios(String senhaFuncionarios) { this.senhaFuncionarios = senhaFuncionarios; }

    public String getFuncaoFuncionarios() { return funcaoFuncionarios; }
    public void setFuncaoFuncionarios(String funcaoFuncionarios) { this.funcaoFuncionarios = funcaoFuncionarios; }

    // Método para inserir funcionário no banco

    public void inserirFuncionarios() throws SQLException {
        ConexaoBDPostgres conexao = new ConexaoBDPostgres();

        String sql = "INSERT INTO tb_funcionarios (cod_funcionario, nom_funcionario, cpf_funcionario, sen_funcionario, fun_funcionario) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexao.getConexao().prepareStatement(sql)) {
            ps.setLong(1, getCodFuncionarios());
            ps.setString(2, getNomeFuncionarios());
            ps.setString(3, getCpfFuncionarios());
            ps.setString(4, getSenhaFuncionarios());
            ps.setString(5, getFuncaoFuncionarios());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "Aviso", JOptionPane.PLAIN_MESSAGE);
        }
        conexao.disconnect();
    }

    // Método para buscar funcionário pelo código ou nome
    public ResultSet buscarFuncionario(String tipoBusca, long id, String nome) throws SQLException {
        ConexaoBDPostgres conexao = new ConexaoBDPostgres();
        String sql;

        if ("nome".equals(tipoBusca)) {
            sql = "SELECT * FROM tb_funcionarios WHERE nom_funcionario LIKE ?";
        } else {
            sql = "SELECT * FROM tb_funcionarios WHERE cod_funcionario = ?";
        }

        PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

        if ("nome".equals(tipoBusca)) {
            ps.setString(1, nome + "%");
        } else {
            ps.setLong(1, id);
        }

        return ps.executeQuery();
    }
}

