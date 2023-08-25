import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class EconomyManager {
    private Map<Player, Double> balances = new HashMap<>();

    public void initializePlayer(Player player) {
        balances.put(player, 0.0);
    }

    public double getBalance(Player player) {
        return balances.getOrDefault(player, 0.0);
    }

    public void deposit(Player player, double amount) {
        double currentBalance = getBalance(player);
        balances.put(player, currentBalance + amount);
    }

    public boolean withdraw(Player player, double amount) {
        double currentBalance = getBalance(player);
        if (currentBalance >= amount) {
            balances.put(player, currentBalance - amount);
            return true;
        }
        return false;
    }
}
