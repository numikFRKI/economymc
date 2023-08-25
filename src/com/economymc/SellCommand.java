import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SellCommand implements CommandExecutor {

    private EconomyManager economyManager;

    public SellCommand(EconomyManager economyManager) {
        this.economyManager = economyManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        // Check if the command has the correct number of arguments (1)
        if (args.length != 1) {
            sender.sendMessage("Usage: /sell <item>");
            return true;
        }

        String itemName = args[0].toLowerCase(); // The item name to sell

        // Check if the player has the item in their inventory
        ItemStack itemToSell = null;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType().toString().equalsIgnoreCase(itemName)) {
                itemToSell = item;
                break;
            }
        }

        if (itemToSell == null) {
            sender.sendMessage("You do not have that item in your inventory.");
            return true;
        }

        // Determine the selling price for the item (you would need to implement this logic)
        double sellingPrice = calculateSellingPrice(itemToSell);

        // Add the selling price to the player's balance
        economyManager.deposit(player, sellingPrice);

        // Remove the sold item from the player's inventory
        player.getInventory().remove(itemToSell);

        // Inform the player about the successful sale
        sender.sendMessage("You have sold " + itemToSell.getType() + " for $" + sellingPrice + ".");

        return true;
    }

    // Implement your logic to calculate the selling price based on the item
    private double calculateSellingPrice(ItemStack item) {
        // Implement your pricing logic here
        // Example: return a fixed price or calculate based on item rarity, etc.
        return 10.0; // Replace with your logic
    }
}
