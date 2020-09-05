package de.kirimcdev.minecraft.plugins.kiricore.main;

import de.kirimcdev.minecraft.plugins.kiricore.DummyException;
import de.kirimcdev.minecraft.plugins.kiricore.commands.*;
import de.kirimcdev.minecraft.plugins.kiricore.listeners.LISTplayerChat;
import de.kirimcdev.minecraft.plugins.kiricore.listeners.LISTplayerLeave;
import de.kirimcdev.minecraft.plugins.kiricore.listeners.LISTplayerMove;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({"unused", "RedundantSuppression"}) // Main was reported "unused", the suppressor "redundant"
public class Main extends JavaPlugin {

    private boolean successfulInit = true;

    @Override
    public void onEnable() {
        initPlugin();
        if (successfulInit) {
            String successMessage = Methods.genPrefix("kiriCore", Vars.console, "a", "2");
            successMessage += "The Plugin got successfully enabled.";
            Vars.console.sendMessage(successMessage);
        }

        String essentialsMessage;
        if (Vars.essentialsExists) {
            essentialsMessage = Methods.genPrefix("kiriCore", Vars.console, "a", "2");
            essentialsMessage += "Essentials was detected. Vanishing of players will be considered.";
        } else {
            essentialsMessage = Methods.genPrefix("kiriCore", Vars.console, "a", "c");
            essentialsMessage += "Essentials wasn't detected. [This is not critical, the plugin will run normally]";
        }
        Vars.console.sendMessage(essentialsMessage);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        String successMessage = Methods.genPrefix("kiriCore", Vars.console, "a", "2");
        successMessage += "The Plugin got successfully disabled.";
        Vars.console.sendMessage(successMessage);
        super.onDisable();
    }

    private void initPlugin() {
        commandInit();
        listenerInit();
        configInit();
    }

    private void commandInit() {
        try {
            getCommand("afk").setExecutor(new CMDafk());
            getCommand("afklist").setExecutor(new CMDafkList());
            getCommand("isafk").setExecutor(new CMDisAfk());
            getCommand("clearchat").setExecutor(new CMDclearChat());
            getCommand("exit").setExecutor(new CMDexit());
        } catch (Exception excep) {
            Methods.logError("kiriCore", "init.command", excep);
            successfulInit = false;
        }
    }

    private void listenerInit() {
        try {
            Vars.pluginMngr.registerEvents(new LISTplayerChat(), this);
            Vars.pluginMngr.registerEvents(new LISTplayerMove(), this);
            Vars.pluginMngr.registerEvents(new LISTplayerLeave(), this);
        } catch (Exception excep) {
            Methods.logError("kiriCore", "init.listener", excep);
            successfulInit = false;
        }
    }

    private void configInit() {
        try {
            String consoleMsg = Methods.genPrefix("kiriCore", Vars.console, "a", "a");
            consoleMsg += "Config-Functionality is not available (THIS IS NOT AN ERROR)";
            Vars.console.sendMessage(consoleMsg);
        } catch (Exception excep) {
            Methods.logError("kiriCore", "init.config", new DummyException());
            successfulInit = false;
        }
    }

}
