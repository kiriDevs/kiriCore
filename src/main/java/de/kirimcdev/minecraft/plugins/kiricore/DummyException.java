package de.kirimcdev.minecraft.plugins.kiricore;

import de.kirimcdev.minecraft.plugins.kiricore.main.Vars;

public class DummyException extends Exception {
    @Override
    public void printStackTrace() {
        Vars.console.sendMessage("");
        Vars.console.sendMessage("§cWell, this is awkward.");
        Vars.console.sendMessage("§cOne of your plugins using kiriCore as a library have printed a dummyException.");
        Vars.console.sendMessage("§cThis is an Exception which isn't really an exception. It's complicated.");
        Vars.console.sendMessage("§cIf you have any way of finding out which plugin did this, please do it.");
        Vars.console.sendMessage("§cTell the plugin author about this, as this isn't supposed to happen.");
        Vars.console.sendMessage("§cThe plugin shouldn't have crashed though, so everything should still work.");
        Vars.console.sendMessage("§cThanks for reporting this to the plugin author, NOT to the author of kiriCore!");
        Vars.console.sendMessage("");
    }
}
