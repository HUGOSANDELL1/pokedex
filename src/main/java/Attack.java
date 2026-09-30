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
        this.name = name;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setType(Type type) {
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