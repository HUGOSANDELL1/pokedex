public class BattleStats {


    private int totalWins;
    private int totalLosses;

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
}
