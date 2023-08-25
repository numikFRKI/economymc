import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private EconomyManager economyManager;

    @Override
    public void onEnable() {
        getLogger().info("Juice-Technology Economy Plugin has been enabled!");

        // Initialize your economy manager
        economyManager = new EconomyManager();

        // Register command handlers
        getCommand("balance").setExecutor(new BalanceCommand(economyManager));
        getCommand("pay").setExecutor(new PayCommand(economyManager)); 

        // Register event listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(economyManager), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Juice-Technology Economy Plugin has been disabled!");

        // Save any necessary data or perform cleanup here
    }
}
