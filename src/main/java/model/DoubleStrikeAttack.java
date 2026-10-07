package model;

public class DoubleStrikeAttack extends Attack {

    public DoubleStrikeAttack() {
        super();
    }

    public DoubleStrikeAttack(
            String name,
            int baseDamage,
            int accuracy,
            Type type
    ) {
        super(name, baseDamage, accuracy, type);
    }

    @Override
    public void execute(
            Pokemon attacker,
            Pokemon defender,
            int damage
    ) {
        int firstHit = damage / 2;
        int secondHit = damage - firstHit;

        defender.takeDamage(firstHit);
        defender.takeDamage(secondHit);

        System.out.println("Double strike!");
    }
}

