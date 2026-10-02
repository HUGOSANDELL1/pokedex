import java.util.ArrayList;

public class Pokemon {

    // Basic information about a Pokemon
    private String name;
    private Type type;
    private int maxHp;
    private int currentHp;

    // List of attacks the Pokemon has
    private ArrayList<Attack> attacks = new ArrayList<>();

    // Empty constructor used when loading JSON
    public Pokemon() {
    }

    // Creates a new Pokemon
    public Pokemon(String name, Type type, int maxHp) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Pokemon name cannot be empty.");
        }

        if (type == null) {
            throw new IllegalArgumentException("Pokemon type cannot be null.");
        }

        if (maxHp <= 0) {
            throw new IllegalArgumentException("Pokemon HP must be greater than 0.");
        }

        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Pokemon name cannot be empty.");
        }


        this.name = name;
    }

    public void setType(Type type) {
        if (type == null) {
            throw new IllegalArgumentException("Pokemon type cannot be null.");
        }

        this.type = type;
    }

    public void setMaxHp(int maxHp) {
        if (maxHp <= 0) {
            throw new IllegalArgumentException("Pokemon HP must be greater than 0.");
        }

        this.maxHp = maxHp;

        if (currentHp > maxHp) {
            currentHp = maxHp;
        }
    }

    public void setCurrentHp(int currentHp) {
        if (currentHp < 0) {
            throw new IllegalArgumentException("Current HP cannot be negative.");
        }

        if (currentHp > maxHp) {
            throw new IllegalArgumentException("Current HP cannot be higher than max HP.");
        }

        this.currentHp = currentHp;
    }

    public void addAttack(Attack attack) {
        if (attack == null) {
            throw new IllegalArgumentException("Attack cannot be null.");
        }

        if (attacks.size() >= 4) {
            throw new IllegalArgumentException("A Pokemon can have maximum 4 attacks.");
        }

        attacks.add(attack);
    }

    @Override
    public String toString() {
        return name +
                " (" + type +
                ", " + currentHp +
                "/" + maxHp + " HP)";
    }
}