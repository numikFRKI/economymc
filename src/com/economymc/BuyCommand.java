import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BuyCommand implements CommandExecutor {

    private EconomyManager economyManager;

    public BuyCommand(EconomyManager economyManager) {
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

        // Check if the command has the correct number of arguments (2)
        if (args.length != 2) {
            sender.sendMessage("Usage: /buy <item_id> <amount>");
            return true;
        }

        String itemID = args[0].toLowerCase(); // The item ID to buy

        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("Invalid amount. Please use a number.");
            return true;
        }

        // Implement logic to check if the item is available in the shop
        // Check if the player has enough money to purchase the items

        // Assuming you have checked and validated the purchase, add the item(s) to the player's inventory
        ItemStack itemToGive = new ItemStack(/* Set the item type based on itemID */);
        itemToGive.setAmount(amount);
        player.getInventory().addItem(itemToGive);

        // Deduct the cost from the player's balance
        double totalPrice = /* Calculate the total price based on item price and amount */;
        economyManager.withdraw(player, totalPrice);

        // Send a message indicating the purchase
        String pluralS = (amount > 1) ? "s" : "";
        sender.sendMessage("Bought " + amount + " " + itemID + pluralS);

        return true;
    }
}
