import java.util.ArrayList;

public class Pokemon {

    // Basic information about a Pokemon
    public String name;
    public Type type;
    public int maxHp;
    public int currentHp;

    // List of attacks the Pokemon has
    public ArrayList<Attack> attacks = new ArrayList<>();

    // Empty constructor used when loading JSON
    public Pokemon() {
    }

    // Creates a new Pokemon
    public Pokemon(String name, Type type, int maxHp) {
        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }
}