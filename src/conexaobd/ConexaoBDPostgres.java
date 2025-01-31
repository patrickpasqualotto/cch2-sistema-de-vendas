package conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBDPostgres {
    private String url;
    private String usuario;
    private String senha;
    private String nomeBanco;
    private Connection conexao;
    private boolean status;

    public ConexaoBDPostgres(String usuario, String senha, String nomeBanco) {
        this.usuario = "postgres";
        this.senha = "3077";
        this.nomeBanco = "cch2teste";

        url = "jdbc:postgresql://localhost:5432/"+nomeBanco;

        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao Realizada Com Sucesso!!");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Nao foi possível conenectar com o Banco de dados!!");]
            conexao = null;
        }

    }

    public Connection getConexao() {
        return conexao;
    }

    public ConexaoBDPostgres (){

        usuario = "postgres";
        senha = "3077";
        nomeBanco = "cch2teste";
        url = "jdbc:postgresql://localhost:5432/"+nomeBanco;

        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao Realizada Com Sucesso!!");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Nao foi possível conenectar com o Banco de dados!!");
        }

    }


    public void disconnect() {
        try {
            conexao.close();
            status = false;
            System.out.println("Fechando a conexão");
        } catch (SQLException erro) {
            System.out.println("Erro no fechamento");

        }
    }
}
