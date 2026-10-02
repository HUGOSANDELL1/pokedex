public class Attack {
    // Stores information about an attack
    private String name;
    private int baseDamage;
    private int accuracy;
    private Type type;

    // Empty constructor used when loading JSON
    public Attack() {
    }

    // Creates a new attack with the values above
    public Attack(String name, int baseDamage, int accuracy, Type type) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Attack name cannot be empty.");
        }

        if (baseDamage <= 0) {
            throw new IllegalArgumentException("Base damage must be greater than 0.");
        }

        if (accuracy < 1 || accuracy > 100) {
            throw new IllegalArgumentException("Accuracy must be between 1 and 100.");
        }

        if (type == null) {
            throw new IllegalArgumentException("Attack type cannot be null.");
        }

        this.name = name;
        this.baseDamage = baseDamage;
        this.accuracy = accuracy;
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Attack name cannot be empty.");
        }

        this.name = name;
    }

    public void setBaseDamage(int baseDamage) {
        if (baseDamage <= 0) {
            throw new IllegalArgumentException("Base damage must be greater than 0.");
        }

        this.baseDamage = baseDamage;
    }

    public void setAccuracy(int accuracy) {
        if (accuracy < 1 || accuracy > 100) {
            throw new IllegalArgumentException("Accuracy must be between 1 and 100.");
        }

        this.accuracy = accuracy;
    }

    public void setType(Type type) {
        if (type == null) {
            throw new IllegalArgumentException("Attack type cannot be null.");
        }

        this.type = type;
    }

    @Override
    public String toString() {
        return name +
                " | Damage: " + baseDamage +
                " | Accuracy: " + accuracy +
                " | Type: " + type;
    }
}