package de.kiridevs.kiricore.commands;

import de.kiridevs.kiricore.managers.MessageService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CMDrename implements CommandExecutor {
    MessageService messageService;
    public CMDrename(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender cmdSender,
            @NotNull Command cmd,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!(cmdSender.hasPermission("kiri.core.rename"))) {
            ArrayList<String> completion = new ArrayList<>();
            completion.add("kiri.core.rename");
            messageService.sendErrorMessage(cmdSender, "noperm", completion);
            return true;
        }

        if (!(args.length > 0)) {
            ArrayList<String> completion = new ArrayList<>();
            completion.add("/rename <new item name>");
            messageService.sendErrorMessage(cmdSender, "badsyntax", completion);
            return true;
        }

        if (!(cmdSender instanceof Player)) {
            messageService.sendErrorMessage(cmdSender, "playersonly", null);
            return true;
        }

        // Everything OK, we can proceed
        Player player = (Player) cmdSender;
        PlayerInventory playerInv = player.getInventory();

        ItemStack heldItem = playerInv.getItemInMainHand();
        ItemMeta heldItemMeta = heldItem.getItemMeta();

        if (heldItemMeta == null) { // No item held in hand
            messageService.sendErrorMessage(cmdSender,
                                     "Please hold the item you want to " +
                                     "rename in your main hand!");
            return true;
        }

        // Assemble item name from command arguments
        StringBuilder newNameBuilder = new StringBuilder();
        for (String arg : args) {
            newNameBuilder.append(arg);
            newNameBuilder.append(" ");
        }
        String newName = newNameBuilder.toString();
        // Remove trailing space from the string
        newName = newNameBuilder.substring(0, newName.length() - 1);

        if (newName.length() >= 35) {
            messageService.sendErrorMessage(cmdSender,
                                            "This name is too long! " +
                                            "The maximum is 35 characters, " +
                                            "including spaces!");
            return true;
        }

        // Add color and italic style like the game would in the anvil
        newName = "§b" + newName;
        newName = "§o" + newName;

        heldItemMeta.setDisplayName(newName); // Update display name in ItemMeta
        heldItem.setItemMeta(heldItemMeta); // Update ItemStack
        playerInv.setItemInMainHand(heldItem); // Put ItemStack into main hand
        messageService.sendSuccessMessage(cmdSender,
                                          "Successfully changed the name " +
                                          "of the item in your hand!");

        return true;
    }
}
