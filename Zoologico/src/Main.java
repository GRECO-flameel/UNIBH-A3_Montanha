import java.io.*;
import java.util.*;

public class Main {
    private static final String ARQUIVO = "animais.txt";
    private static List<AnimalDoZoologico> animais = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarArquivo();
        int opcao;
        do {
            System.out.println("\n1. Criar novo registro");
            System.out.println("2. Listar todos os registros");
            System.out.println("3. Buscar registro por ID");
            System.out.println("4. Editar registro");
            System.out.println("5. Excluir registro");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1 -> criar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> editar();
                case 5 -> excluir();
                case 6 -> salvarArquivo();
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 6);
    }

    private static void criar() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (buscarPorId(id) != null) {
                System.out.println("ID ja existente.");
                return;
            }
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Especie: ");
            String especie = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = Integer.parseInt(scanner.nextLine());
            System.out.print("Peso: ");
            double peso = Double.parseDouble(scanner.nextLine());
            System.out.print("Habitat: ");
            String habitat = scanner.nextLine();
            System.out.print("Em extincao (sim/nao): ");
            boolean emExtincao = Boolean.parseBoolean(scanner.nextLine());

            AnimalDoZoologico animal = new AnimalDoZoologico(id, nome, especie, idade, peso, habitat, emExtincao);
            animal.validar();
            animais.add(animal);
            salvarArquivo();
            System.out.println("Animal cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listar() {
        if (animais.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            animais.forEach(System.out::println);
        }
    }

    private static void buscar() {
        System.out.print("ID do animal: ");
        int id = Integer.parseInt(scanner.nextLine());
        AnimalDoZoologico animal = buscarPorId(id);
        if (animal != null) System.out.println(animal);
        else System.out.println("Animal nao encontrado.");
    }

    private static void editar() {
        System.out.print("ID do animal a editar: ");
        int id = Integer.parseInt(scanner.nextLine());
        AnimalDoZoologico animal = buscarPorId(id);
        if (animal == null) {
            System.out.println("Animal nao encontrado.");
            return;
        }
        try {
            System.out.print("Novo nome: ");
            animal.setNome(scanner.nextLine());
            System.out.print("Nova especie: ");
            animal.setEspecie(scanner.nextLine());
            System.out.print("Nova idade: ");
            animal.setIdade(Integer.parseInt(scanner.nextLine()));
            System.out.print("Novo peso: ");
            animal.setPeso(Double.parseDouble(scanner.nextLine()));
            System.out.print("Novo habitat: ");
            animal.setHabitat(scanner.nextLine());
            System.out.print("Em extincao (sim/nao): ");
            animal.setEmExtincao(Boolean.parseBoolean(scanner.nextLine()));
            animal.validar();
            salvarArquivo();
            System.out.println("Animal atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void excluir() {
        System.out.print("ID do animal a excluir: ");
        int id = Integer.parseInt(scanner.nextLine());
        AnimalDoZoologico animal = buscarPorId(id);
        if (animal != null) {
            animais.remove(animal);
            salvarArquivo();
            System.out.println("Animal excluido com sucesso.");
        } else {
            System.out.println("Animal nao encontrado.");
        }
    }

    private static AnimalDoZoologico buscarPorId(int id) {
        return animais.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    private static void carregarArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                animais.add(AnimalDoZoologico.fromString(linha));
            }
        } catch (IOException e) {
            System.out.println("Arquivo nao encontrado, iniciando lista vazia.");
        }
    }

    private static void salvarArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (AnimalDoZoologico animal : animais) {
                bw.write(animal.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
