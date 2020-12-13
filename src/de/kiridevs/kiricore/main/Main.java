package de.kiridevs.kiricore.main;

import de.kiridevs.kiricore.Prefix;
import de.kiridevs.kiricore.commands.CMDAfk;
import de.kiridevs.kiricore.commands.CMDIsAfk;
import de.kiridevs.kiricore.commands.CMDrename;
import de.kiridevs.kiricore.listeners.LISTonPlayerChangeAfkStatus;
import de.kiridevs.kiricore.listeners.LISTonPlayerLeave;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused") // Class is used by Spigot
public class Main extends JavaPlugin {
    public static Server server = Bukkit.getServer();
    public static ConsoleCommandSender console = server.getConsoleSender();

    public static Prefix successPrefix = new Prefix("kiriCore", "2", "a");
    public static Prefix errorPrefix = new Prefix("kiriCore", "2", "c");
    public static Prefix broadcastPrefix = new Prefix("kiriCore", "2", "b");

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        // Command registration
        getCommand("afk").setExecutor(new CMDAfk());
        getCommand("isafk").setExecutor(new CMDIsAfk());
        getCommand("rename").setExecutor(new CMDrename());

        // Listener registration
        Bukkit.getPluginManager().registerEvents(new LISTonPlayerLeave(), this);
        Bukkit.getPluginManager().registerEvents(new LISTonPlayerChangeAfkStatus(), this);
    }

}
