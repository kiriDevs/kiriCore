package de.kirimcdev.minecraft.plugins.kiricore.main;

import de.kirimcdev.minecraft.plugins.kiricore.DummyException;
import de.kirimcdev.minecraft.plugins.kiricore.commands.CMDafk;
import de.kirimcdev.minecraft.plugins.kiricore.commands.CMDafkList;
import de.kirimcdev.minecraft.plugins.kiricore.commands.CMDclearChat;
import de.kirimcdev.minecraft.plugins.kiricore.commands.CMDisAfk;
import de.kirimcdev.minecraft.plugins.kiricore.listeners.LISTplayerChat;
import de.kirimcdev.minecraft.plugins.kiricore.listeners.LISTplayerLeave;
import de.kirimcdev.minecraft.plugins.kiricore.listeners.LISTplayerMove;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {

    private boolean successfulInit = true;

    @Override
    public void onEnable() {
        initPlugin();
        if (successfulInit) {
            String successMessage = Methods.genPrefix("kiriCore", Vars.console, "a", "2");
            successMessage += "I had a perfect startup without any errors!";
            Vars.console.sendMessage(successMessage);
        }

        super.onEnable();
    }

    @Override
    public void onDisable() {
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
