import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the command has the correct number of arguments
        if (args.length != 1) {
            sender.sendMessage("Usage: /help <buy/sell>");
            return true;
        }

        String subcommand = args[0].toLowerCase();

        // Provide help information based on the subcommand
        switch (subcommand) {
            case "buy":
                sender.sendMessage("=== Buying Help ===");
                sender.sendMessage("To buy items from the shop, use:");
                sender.sendMessage("/shop buy <item>");
                sender.sendMessage("Ensure that you are using the correct item ID, such as: \"dirt_block\" and ensure you have enough money to purchase said item(s).");
                break;
            case "sell":
                sender.sendMessage("=== Selling Help ===");
                sender.sendMessage("To sell items to the shop, use:");
                sender.sendMessage("/shop sell <item>");
                sender.sendMessage("Ensure that you have the item(s) in your hand when you use the sell command, and make sure that you want to sell EVERYTHING in your hand.");
                break;
            default:
                sender.sendMessage("Unknown subcommand. Usage: /help <buy/sell>");
                break;
        }

        return true;
    }
}
