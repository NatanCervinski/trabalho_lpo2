package locadora;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class CadastroCliente {

    public static void main(String[] args) {
        // Escolhe o tipo de persistência
        ClienteDao dao = escolhePersistencia();
        menuPrincipal(dao);
    }

    private static ClienteDao escolhePersistencia() {
        while (true) {
            try {
                System.out.println("Escolha qual o tipo de persistência (Banco de dados SQL ou Em Memória) <ENTER>. Digite 1, 2 ou 3: ");
                System.out.println("  1 - Banco de dados SQL");
                System.out.println("  2 - Em memória");
                System.out.println("  3 - Sair");
                Scanner sc = new Scanner(System.in, "ISO-8859-1");
                String opcao = sc.nextLine();
                switch (opcao) {
                    case "1":
                        return DaoFactory.getClienteDao(DaoType.SQL);
                    case "2":
                        return DaoFactory.getClienteDao(DaoType.INMEMORY);
                    case "3":
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Ocorreu alguma falha na inicialização do modelo de persistência: ");
                ex.printStackTrace(System.out);
            }
        }
    }

    private static void menuPrincipal(ClienteDao dao) {
        while (true) {
            try {
                System.out.println("Escolha uma das opções e tecle <ENTER>: ");
                System.out.println("  1 - Listar Clientes");
                System.out.println("  2 - Cadastrar Cliente");
                System.out.println("  3 - Atualizar Cliente");
                System.out.println("  4 - Excluir Cliente");
                System.out.println("  5 - Sair");
                Scanner sc = new Scanner(System.in, "ISO-8859-1");
                String opcao = sc.nextLine();
                switch (opcao) {
                    case "1":
                        listarClientes(dao);
                        break;
                    case "2":
                        cadastrarCliente(dao);
                        break;
                    case "3":
                        atualizarCliente(dao);
                        break;
                    case "4":
                        excluirCliente(dao);
                        break;
                    case "5":
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Falha na operação: ");
                ex.printStackTrace(System.out);
            }
        }
    }

    private static void listarClientes(ClienteDao dao) {
        try {
            List<Cliente> clientes = dao.getAll();
            ClienteTableModel model = new ClienteTableModel(clientes);

            JTable tabelaClientes = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(tabelaClientes);

            JFrame frame = new JFrame("Lista de Clientes");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void cadastrarCliente(ClienteDao dao) {
        try {
            Scanner sc = new Scanner(System.in, "ISO-8859-1");
            System.out.println("Digite o nome:");
            String nome = sc.nextLine();
            System.out.println("Digite o sobrenome:");
            String sobrenome = sc.nextLine();
            System.out.println("Digite o RG:");
            String rg = sc.nextLine();
            System.out.println("Digite o CPF:");
            String cpf = sc.nextLine();
            System.out.println("Digite a rua:");
            String rua = sc.nextLine();
            System.out.println("Digite o número:");
            String numero = sc.nextLine();
            System.out.println("Digite o complemento:");
            String complemento = sc.nextLine();

            Endereco endereco = new Endereco(rua, numero, complemento);
            Cliente cliente = new Cliente(0, nome, sobrenome, rg, cpf, endereco);

            dao.add(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void atualizarCliente(ClienteDao dao) {
        try {
            Scanner sc = new Scanner(System.in, "ISO-8859-1");
            System.out.println("Digite o ID do cliente a ser atualizado:");
            long id = Long.parseLong(sc.nextLine());

            Cliente cliente = dao.getById(id);

            System.out.println("Digite o novo nome (atual: " + cliente.getNome() + "):");
            String nome = sc.nextLine();
            System.out.println("Digite o novo sobrenome (atual: " + cliente.getSobrenome() + "):");
            String sobrenome = sc.nextLine();
            System.out.println("Digite o novo RG (atual: " + cliente.getRg() + "):");
            String rg = sc.nextLine();
            System.out.println("Digite o novo CPF (atual: " + cliente.getCpf() + "):");
            String cpf = sc.nextLine();
            System.out.println("Digite a nova rua (atual: " + cliente.getEndereco().getRua() + "):");
            String rua = sc.nextLine();
            System.out.println("Digite o novo número (atual: " + cliente.getEndereco().getNumero() + "):");
            String numero = sc.nextLine();
            System.out.println("Digite o novo complemento (atual: " + cliente.getEndereco().getComplemento() + "):");
            String complemento = sc.nextLine();

            Endereco endereco = new Endereco(rua, numero, complemento);
            cliente.setNome(nome);
            cliente.setSobrenome(sobrenome);
            cliente.setRg(rg);
            cliente.setCpf(cpf);
            cliente.setEndereco(endereco);

            dao.update(cliente);
            System.out.println("Cliente atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void excluirCliente(ClienteDao dao) {
        try {
            Scanner sc = new Scanner(System.in, "ISO-8859-1");
            System.out.println("Digite o ID do cliente a ser excluído:");
            long id = Long.parseLong(sc.nextLine());

            Cliente cliente = dao.getById(id);
            dao.delete(cliente);
            System.out.println("Cliente excluído com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
