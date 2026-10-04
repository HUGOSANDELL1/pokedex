import java.util.HashMap;
import java.util.Map;
public class BattleStats {


    private int totalWins;
    private int totalLosses;
    private Map<String, Integer> pokemonUsage = new HashMap<>();

    private Map<String, Integer> attackUsage = new HashMap<>();

    public BattleStats() {
        this.totalWins = 0;
        this.totalLosses = 0;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public void addWin() {
        totalWins++;
    }

    public void addLoss() {
        totalLosses++;
    }

    public Map<String, Integer> getPokemonUsage() {
        return pokemonUsage;
    }

    public Map<String, Integer> getAttackUsage() {
        return attackUsage;
    }

    public void recordPokemonUse(String pokemonName) {
        pokemonUsage.put(
                pokemonName,
                pokemonUsage.getOrDefault(pokemonName, 0) + 1
        );
    }
    public void recordAttackUse(String attackName) {
        attackUsage.put(
                attackName,
                attackUsage.getOrDefault(attackName, 0) + 1
        );
    }

    public String getMostUsedPokemon() {
        String mostUsed = "None";
        int highestUsage = 0;

        for (Map.Entry<String, Integer> entry : pokemonUsage.entrySet()) {
            if (entry.getValue() > highestUsage) {
                highestUsage = entry.getValue();
                mostUsed = entry.getKey();
            }
        }

        return mostUsed;
    }

    public String getMostUsedAttack() {
        String mostUsed = "None";
        int highestUsage = 0;

        for (Map.Entry<String, Integer> entry : attackUsage.entrySet()) {
            if (entry.getValue() > highestUsage) {
                highestUsage = entry.getValue();
                mostUsed = entry.getKey();
            }
        }

        return mostUsed;
    }
}
