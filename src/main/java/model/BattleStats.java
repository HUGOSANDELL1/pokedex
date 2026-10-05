package model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
public class BattleStats {


    private int totalWins;
    private int totalLosses;
    private Map<String, Integer> pokemonUsage = new HashMap<>();

    private Map<String, Integer> attackUsage = new HashMap<>();

    @JsonProperty("pokemonWins")
    private Map<String, Integer> pokemonWins = new HashMap<>();
    @JsonProperty("pokemonLosses")
    private Map<String, Integer> pokemonLosses = new HashMap<>();

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

    @JsonIgnore
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

    @JsonIgnore
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

    public void addPokemonWin(String pokemonName) {
        pokemonWins.put(
                pokemonName,
                pokemonWins.getOrDefault(pokemonName, 0) + 1
        );
    }

    public void addPokemonLoss(String pokemonName) {
        pokemonLosses.put(
                pokemonName,
                pokemonLosses.getOrDefault(pokemonName, 0) + 1
        );
    }

    public int getPokemonWins(String pokemonName) {
        return pokemonWins.getOrDefault(pokemonName, 0);
    }

    public int getPokemonLosses(String pokemonName) {
        return pokemonLosses.getOrDefault(pokemonName, 0);
    }

    public double getPokemonWinRatio(String pokemonName) {
        int wins = getPokemonWins(pokemonName);
        int losses = getPokemonLosses(pokemonName);

        int totalBattles = wins + losses;

        if (totalBattles == 0) {
            return 0;
        }

        return (wins * 100.0) / totalBattles;
    }
}
