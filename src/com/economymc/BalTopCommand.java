import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaltopCommand implements CommandExecutor {

    private EconomyManager economyManager;

    public BalanceCommand(EconomyManager economyManager) {
        this.economyManager = economyManager;
    }



