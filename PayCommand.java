import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {

    private EconomyManager economyManager;

    public PayCommand(EconomyManager economyManager) {
        this.economyManager = economyManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player senderPlayer = (Player) sender;

        // Check if the command has the correct number of arguments
        if (args.length != 2) {
            senderPlayer.sendMessage("Usage: /pay <player> <amount>");
            return true;
        }

        // Parse the target player and amount from the command arguments
        String targetName = args[0];
        double amount;
        try {
            amount = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            senderPlayer.sendMessage("Invalid amount. Please use a number.");
            return true;
        }

        // Check if the target player exists and is online
        Player targetPlayer = senderPlayer.getServer().getPlayer(targetName);
        if (targetPlayer == null) {
            senderPlayer.sendMessage("Player '" + targetName + "' is not online or does not exist.");
            return true;
        }

        // Check if the sender has enough money to perform the transaction
        double senderBalance = economyManager.getBalance(senderPlayer);
        if (senderBalance < amount) {
            senderPlayer.sendMessage("You do not have enough money to send $" + amount + ".");
            return true;
        }

        // Perform the transaction
        economyManager.withdraw(senderPlayer, amount);
        economyManager.deposit(targetPlayer, amount);

        senderPlayer.sendMessage("You have paid $" + amount + " to " + targetPlayer.getName() + ".");
        targetPlayer.sendMessage(senderPlayer.getName() + " has paid you $" + amount + ".");

        return true;
    }
}
