package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.BattleStats;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BattleStatsFileManager {

    private static final Path FILE_PATH =
            Path.of("battle-stats.json");

    public static void saveBattleStats(BattleStats battleStats) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.enable(
                    SerializationFeature.INDENT_OUTPUT
            );

            mapper.writeValue(
                    FILE_PATH.toFile(),
                    battleStats
            );

            System.out.println("Battle stats saved.");

        } catch (IOException e) {
            System.out.println(
                    "Could not save battle stats."
            );
        }
    }

    public static BattleStats loadBattleStats() {

        if (!Files.exists(FILE_PATH)) {
            return new BattleStats();
        }

        try {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(
                    FILE_PATH.toFile(),
                    BattleStats.class
            );

        } catch (IOException e) {
            System.out.println(
                    "Could not load battle stats. Starting with empty stats."
            );

            return new BattleStats();
        }
    }
}
