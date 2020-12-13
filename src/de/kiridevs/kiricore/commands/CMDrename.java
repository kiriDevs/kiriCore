package de.kiridevs.kiricore.commands;

import de.kiridevs.kiricore.main.Main;
import de.kiridevs.kiricore.main.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class CMDrename implements CommandExecutor {
    @Override
    public boolean onCommand(@Nonnull CommandSender cmdSender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if (!(cmdSender.hasPermission("kiri.core.rename"))) {
            cmdSender.sendMessage(Messages.noPerm(cmdSender, "kiri.core.rename"));
            return true;
        }

        if (!(args.length > 0)) {
            cmdSender.sendMessage(Messages.badSyntax(cmdSender, "/rename <new item name>"));
            return true;
        }

        if (!(cmdSender instanceof Player)) {
            cmdSender.sendMessage(Messages.playersOnly());
            return true;
        }
        // Everything OK, we can proceed
        Player player = (Player) cmdSender;
        PlayerInventory playerInv = player.getInventory();

        ItemStack heldItem = playerInv.getItemInMainHand();
        ItemMeta heldItemMeta = heldItem.getItemMeta();

        if (heldItemMeta == null) { // No item held in hand
            cmdSender.sendMessage(Main.errorPrefix.player + "Please hold the item you want to rename in your main hand!");
            return true;
        }

        // Assemble item name from command arguments
        StringBuilder newNameBuilder = new StringBuilder();
        for (String arg : args) {
            newNameBuilder.append(arg);
            newNameBuilder.append(" ");
        }
        String newName = newNameBuilder.toString();
        newName = newNameBuilder.substring(0, newName.length() - 1); // Remove trailing space from the string

        if (newName.length() > 35) {
            cmdSender.sendMessage(Main.errorPrefix.player + "This name is too long! The maximum is 35 characters, including spaces!");
            return true;
        }

        newName = "§b" + newName; // Add the color code the game would add for anvil-renamed items
        newName = "§o" + newName; // Make the new name cursive like the game would with anvil-renamed items

        heldItemMeta.setDisplayName(newName); // Update display name in ItemMeta
        heldItem.setItemMeta(heldItemMeta); // Update ItemStack
        playerInv.setItemInMainHand(heldItem); // Put ItemStack into Player's main hand

        return true;
    }
}
