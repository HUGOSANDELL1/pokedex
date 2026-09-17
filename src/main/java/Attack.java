public class Attack {
    // Stores information about an attack
    public String name;
    public int baseDamage;
    public int accuracy;
    public Type type;
    // Creates a new attack with the values above
    public Attack(String name, int baseDamage, int accuracy, Type type) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.accuracy = accuracy;
        this.type = type;
    }
}