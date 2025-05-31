import java.util.Objects;

public class AnimalDoZoologico {
    private int id;
    private String nome;
    private String especie;
    private int idade;
    private double peso;
    private String habitat;
    private boolean emExtincao;

    public AnimalDoZoologico(int id, String nome, String especie, int idade, double peso, String habitat, boolean emExtincao) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.peso = peso;
        this.habitat = habitat;
        this.emExtincao = emExtincao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public String getHabitat() { return habitat; }
    public void setHabitat(String habitat) { this.habitat = habitat; }

    public boolean isEmExtincao() { return emExtincao; }
    public void setEmExtincao(boolean emExtincao) { this.emExtincao = emExtincao; }

    public void validar() throws IllegalArgumentException {
        if (nome == null || nome.isEmpty() || especie == null || especie.isEmpty() || habitat == null || habitat.isEmpty()) {
            throw new IllegalArgumentException("Nome, especie e habitat nao podem estar vazios.");
        }
        if (idade < 0) {
            throw new IllegalArgumentException("Idade nao pode ser negativa.");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso deve ser maior que zero.");
        }
    }

    @Override
    public String toString() {
        String statusExtincao = emExtincao ? "extinto" : "nao extinto";
        return id + ";" + nome + ";" + especie + ";" + idade + ";" + peso + ";" + habitat + ";" + statusExtincao;
    }

    public static AnimalDoZoologico fromString(String linha) {
        String[] partes = linha.split(";");
        if (partes[0].equalsIgnoreCase("id")) return null; // ignora cabecalho
        boolean extincao = partes[6].equalsIgnoreCase("extinto");
        return new AnimalDoZoologico(
            Integer.parseInt(partes[0]),
            partes[1],
            partes[2],
            Integer.parseInt(partes[3]),
            Double.parseDouble(partes[4]),
            partes[5],
            extincao
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalDoZoologico that = (AnimalDoZoologico) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
