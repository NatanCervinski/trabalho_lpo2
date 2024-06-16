/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora;


import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rafae
 */
public class CadastroCliente {
    public static void main(String[] args) {

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
            List<Cliente> lista = dao.getAll();
            System.out.println("================");
            System.out.println("id;nome;sobrenome;rg;cpf;rua;numero;complemento");
            for (Cliente cliente : lista) {
                System.out.println(cliente.getId() + ";" + cliente.getNome() + ";" + cliente.getSobrenome() + ";" + cliente.getRg() + ";" + cliente.getCpf() + ";" + cliente.getEndereco().getRua() + ";" + cliente.getEndereco().getNumero() + ";" + cliente.getEndereco().getComplemento());
            }
            System.out.println("================");
        } catch (Exception ex) {
            System.out.println("Falha na listagem de clientes: ");
            ex.printStackTrace(System.out);
        }
    }

 private static void cadastrarCliente(ClienteDao dao) {
        try {
            Scanner sc = new Scanner(System.in, "ISO-8859-1");
            System.out.println("Informe o nome do cliente:");
            String nome = sc.nextLine();
            System.out.println("Informe o sobrenome do cliente:");
            String sobrenome = sc.nextLine();
            System.out.println("Informe o RG do cliente:");
            String rg = sc.nextLine();
            System.out.println("Informe o CPF do cliente:");
            String cpf = sc.nextLine();
            System.out.println("Informe a rua do endereço do cliente:");
            String rua = sc.nextLine();
            System.out.println("Informe o número do endereço do cliente:");
            int numero = Integer.parseInt(sc.nextLine());
            System.out.println("Informe o complemento do endereço do cliente:");
            String complemento = sc.nextLine();

            Endereco endereco = new Endereco(rua, numero, complemento);
            Cliente cliente = new Cliente(0, nome, sobrenome, rg, cpf, endereco);

            dao.add(cliente);
            System.out.println("Cliente cadastrado com sucesso!");

        } catch (Exception ex) {
            System.out.println("Falha no cadastro do cliente: ");
            ex.printStackTrace(System.out);
        }
    }

    private static void atualizarCliente(ClienteDao dao) {
        try {
            Scanner sc = new Scanner(System.in, "ISO-8859-1");
            System.out.println("Informe o ID do cliente que deseja atualizar:");
            long id = Long.parseLong(sc.nextLine());

            Cliente cliente = dao.getById(id);
            if (cliente == null) {
                System.out.println("Cliente não encontrado!");
                return;
            }

            System.out.println("Informe o novo nome do cliente (deixe em branco para manter o atual):");
            String nome = sc.nextLine();
            if (!nome.isEmpty()) cliente.setNome(nome);

            System.out.println("Informe o novo sobrenome do cliente (deixe em branco para manter o atual):");
            String sobrenome = sc.nextLine();
            if (!sobrenome.isEmpty()) cliente.setSobrenome(sobrenome);

            System.out.println("Informe o novo RG do cliente (deixe em branco para manter o atual):");
            String rg = sc.nextLine();
            if (!rg.isEmpty()) cliente.setRg(rg);

            System.out.println("Informe o novo CPF do cliente (deixe em branco para manter o atual):");
            String cpf = sc.nextLine();
            if (!cpf.isEmpty()) cliente.setCpf(cpf);

            System.out.println("Informe a nova rua do endereço do cliente (deixe em branco para manter o atual):");
            String rua = sc.nextLine();
            if (!rua.isEmpty()) cliente.getEndereco().setRua(rua);

            System.out.println("Informe o novo número do endereço do cliente (deixe em branco para manter o atual):");
            String numeroStr = sc.nextLine();
            if (!numeroStr.isEmpty()) cliente.getEndereco().setNumero(Integer.parseInt(numeroStr));

            System.out.println("Informe o novo complemento do endereço do cliente (deixe em branco para manter o atual):");
            String complemento = sc.nextLine();
            if (!complemento.isEmpty()) cliente.getEndereco().setComplemento(complemento);

            dao.update(cliente);
            System.out.println("Cliente atualizado com sucesso!");

        } catch (Exception ex) {
            System.out.println("Falha na atualização do cliente: ");
            ex.printStackTrace(System.out);
        }
    }

    private static void excluirCliente(ClienteDao dao) {
        try {
            Scanner sc = new Scanner(System.in, "ISO-8859-1");
            System.out.println("Informe o ID do cliente que deseja excluir:");
            long id = Long.parseLong(sc.nextLine());

            Cliente cliente = dao.getById(id);
            if (cliente == null) {
                System.out.println("Cliente não encontrado!");
                return;
            }

            dao.delete(cliente);
            System.out.println("Cliente excluído com sucesso!");

        } catch (Exception ex) {
            System.out.println("Falha na exclusão do cliente: ");
            ex.printStackTrace(System.out);
        }
    }
}