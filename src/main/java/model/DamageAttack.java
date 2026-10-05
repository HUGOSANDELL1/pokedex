package model;

public class DamageAttack extends Attack {

    public DamageAttack() {
        super();
    }

    public DamageAttack(
            String name,
            int baseDamage,
            int accuracy,
            Type type
    ) {
        super(name, baseDamage, accuracy, type);
    }

    @Override
    public void execute(Pokemon attacker, Pokemon defender, int damage) {
        defender.takeDamage(damage);
    }
}
