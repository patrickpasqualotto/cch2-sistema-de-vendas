import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import tabelas.Funcionarios; // Certifique-se de que o pacote está correto
import conexaobd.ConexaoBDPostgres;


public static void main(String[] args) {
    ConexaoBDPostgres conexao = new ConexaoBDPostgres("postgres", "3077", "cch2teste");

        if (conexao.getConexao() != null) {
        System.out.println("Teste de conexão: SUCESSO!");
        } else {
        System.out.println("Teste de conexão: FALHOU!");
        }

        conexao.disconnect(); // Fechar a conexão após o teste

    Funcionarios funcionario = new Funcionarios();

        while (true) {
            String[] opcoes = {"Inserir Funcionário", "Buscar Funcionário", "Sair"};
            int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha == 0) { // Inserir Funcionário
                try {
                    String nome = JOptionPane.showInputDialog("Digite o nome do Funcionário:");
                    String cpf = JOptionPane.showInputDialog("Digite o CPF do Funcionário:");
                    String senha = JOptionPane.showInputDialog("Digite a Senha do Funcionário:");
                    String funcao = JOptionPane.showInputDialog("Digite a Função do Funcionário:");

                    funcionario.setNomeFuncionarios(nome);
                    funcionario.setCpfFuncionarios(cpf);
                    funcionario.setSenhaFuncionarios(senha);
                    funcionario.setFuncaoFuncionarios(funcao);

                    // Aqui você deve definir um código único para o funcionário
                    funcionario.setCodFuncionarios(System.currentTimeMillis()); // Exemplo simples

                    funcionario.inserirFuncionarios();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir funcionário: " + e.getMessage());
                }
            } else if (escolha == 1) { // Buscar Funcionário
                String tipoBusca = JOptionPane.showInputDialog("Buscar por (nome/código):");
                if ("nome".equalsIgnoreCase(tipoBusca)) {
                    String nome = JOptionPane.showInputDialog("Digite o nome do Funcionário:");
                    try {
                        ResultSet rs = funcionario.buscarFuncionario("nome", 0, nome);
                        while (rs.next()) {
                            System.out.println("Código: " + rs.getLong("cod_funcionario"));
                            System.out.println("Nome: " + rs.getString("nom_funcionario"));
                            System.out.println("CPF: " + rs.getString("cpf_funcionario"));
                            System.out.println("Função: " + rs.getString("fun_funcionario"));
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao buscar funcionário: " + e.getMessage());
                    }
                } else if ("código".equalsIgnoreCase(tipoBusca)) {
                    long id = Long.parseLong(JOptionPane.showInputDialog("Digite o código do Funcionário:"));
                    try {
                        ResultSet rs = funcionario.buscarFuncionario("codigo", id, null);
                        if (rs.next()) {
                            System.out.println("Código: " + rs.getLong("cod_funcionario"));
                            System.out.println("Nome: " + rs.getString("nom_funcionario"));
                            System.out.println("CPF: " + rs.getString("cpf_funcionario"));
                            System.out.println("Função: " + rs.getString("fun_funcionario"));
                        } else {
                            JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao buscar funcionário: " + e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Tipo de busca inválido.");
                }
            } else if (escolha == 2) { // Sair
                break;
            }
        }

        conexao.disconnect();
    }