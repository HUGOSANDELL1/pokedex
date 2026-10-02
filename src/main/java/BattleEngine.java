import java.util.ArrayList;

public class BattleEngine {

    public static void startBattle(ArrayList<Pokemon> pokemons) {

        if (pokemons.isEmpty()) {
            System.out.println("You do not own any Pokemon.");
            return;
        }

        System.out.println("Battle started!");
    }
}
