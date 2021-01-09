package de.kiridevs.kiricore.managers;

import de.kiridevs.kiricore.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class MessageService {
    Map<String, Prefix> prefixMap;
    Map<String, String> defaultMessages;

    public MessageService(Map<String, Prefix> prefixMap, Map<String, String> defaultMessages) {
        this.prefixMap = prefixMap;
        this.defaultMessages = defaultMessages;

        if ((prefixMap.get("success") == null) || (prefixMap.get("error") == null) || (prefixMap.get("info") == null)) {
            throw new IllegalArgumentException("A prefix map must include prefixes for \"success\", \"error\" and \"info\"!");
        }
    }

    public void sendSuccessMessage(CommandSender recipient, String content) {
        String msg;

        if (recipient instanceof Player) { msg = prefixMap.get("success").player; }
        else { msg = prefixMap.get("success").console; }

        msg += content;
        recipient.sendMessage(msg);
    }

    public void sendSuccessMessage(CommandSender recipient, String messageKey, @Nullable List<String> completionArgs) {
        String msg;

        if (recipient instanceof Player) { msg = prefixMap.get("success").player; }
        else { msg = prefixMap.get("success").console; }

        if (defaultMessages.get(messageKey) != null) {

            String preset = defaultMessages.get(messageKey); // Get preset message
            if (completionArgs != null) {
                for (int i = 0; i < completionArgs.size(); i++) {
                    preset = preset.replace("{" + i + "}", completionArgs.get(i)); // iteratively replace all placeholders with the respective content
                }
            }
            msg += preset; // Append latest modified preset message / pure preset message (if no completionArgs were passed)

        } else {
            throw new IllegalArgumentException("No predefined message with the key \"" + messageKey + "\" was found!");
        }

        recipient.sendMessage(msg);
    }

    public void sendErrorMessage(CommandSender recipient, String content) {
        String msg;

        if (recipient instanceof Player) { msg = prefixMap.get("error").player; }
        else { msg = prefixMap.get("error").console; }

        msg += content;
        recipient.sendMessage(msg);
    }

    public void sendErrorMessage(CommandSender recipient, String messageKey, @Nullable List<String> completionArgs) {
        String msg;

        if (recipient instanceof Player) { msg = prefixMap.get("error").player; }
        else { msg = prefixMap.get("error").console; }

        if (defaultMessages.get(messageKey) != null) {

            String preset = defaultMessages.get(messageKey); // Get preset message
            if (completionArgs != null) {
                for (int i = 0; i < completionArgs.size(); i++) {
                    preset = preset.replace("{" + i + "}", completionArgs.get(i)); // iteratively replace all placeholders with the respective content
                }
            }
            msg += preset; // Append latest modified preset message / pure preset message (if no completionArgs were passed)

        } else {
            throw new IllegalArgumentException("No predefined message with the key \"" + messageKey + "\" was found!");
        }

        recipient.sendMessage(msg);
    }

    public void sendInfoMessage(CommandSender recipient, String content) {
        String msg;

        if (recipient instanceof Player) { msg = prefixMap.get("info").player; }
        else { msg = prefixMap.get("info").console; }

        msg += content;
        recipient.sendMessage(msg);
    }

    public void sendInfoMessage(CommandSender recipient, String messageKey, @Nullable List<String> completionArgs) {
        String msg;

        if (recipient instanceof Player) { msg = prefixMap.get("info").player; }
        else { msg = prefixMap.get("info").console; }

        if (defaultMessages.get(messageKey) != null) {

            String preset = defaultMessages.get(messageKey); // Get preset message
            if (completionArgs != null) {
                for (int i = 0; i < completionArgs.size(); i++) {
                    preset = preset.replace("{" + i + "}", completionArgs.get(i)); // iteratively replace all placeholders with the respective content
                }
            }
            msg += preset; // Append latest modified preset message / pure preset message (if no completionArgs were passed)

        } else {
            throw new IllegalArgumentException("No predefined message with the key \"" + messageKey + "\" was found!");
        }

        recipient.sendMessage(msg);
    }

    public void broadcastMessage(String content) {
        String msg = prefixMap.get("info").console;
        msg += content;
        Bukkit.getServer().broadcastMessage(msg);
    }

    public void broadcastMessage(String messageKey, @Nullable  List<String> completionArgs) {
        String msg;
        msg = prefixMap.get("info").console;

        if (defaultMessages.get(messageKey) != null) {

            String preset = defaultMessages.get(messageKey); // Get preset message
            if (completionArgs != null) {
                for (int i = 0; i < completionArgs.size(); i++) {
                    preset = preset.replace("{" + i + "}", completionArgs.get(i)); // iteratively replace all placeholders with the respective content
                }
            }
            msg += preset; // Append latest modified preset message / pure preset message (if no completionArgs were passed)

        } else {
            throw new IllegalArgumentException("No predefined message with the key \"" + messageKey + "\" was found!");
        }

        Bukkit.getServer().broadcastMessage(msg);
    }

    public void sendCustomMessage(CommandSender recipient, String prefixKey, String content) {
        if (prefixMap.get(prefixKey) != null) {
            String msg;

            if (recipient instanceof Player) { msg = prefixMap.get(prefixKey).player; }
            else { msg = prefixMap.get(prefixKey).console; }

            recipient.sendMessage(msg);
        } else {
            throw new IllegalArgumentException("No prefix was provided for key \"" + prefixKey + "\"!");
        }
    }

    public void sendCustomMessage(CommandSender recipient, String prefixKey, String messageKey, @Nullable List<String> completionArgs) {
        if (prefixMap.get(prefixKey) != null) {
            if (defaultMessages.get(messageKey) != null) {
                String msg;

                if (recipient instanceof Player) { msg = prefixMap.get(prefixKey).player; }
                else { msg = prefixMap.get(prefixKey).console; }

                String preset = defaultMessages.get(messageKey); // Get the message preset to modify afterwards
                if (completionArgs != null) {
                    for (int i = 0; i < completionArgs.size(); i++) {
                        preset = preset.replace("{" + i + "}", completionArgs.get(i)); // Iteratively replace all placeholders with the respective content
                    }
                }
                msg += preset; // Append latest modified preset message / pure preset message (if no completionArgs were passed)

                recipient.sendMessage(msg); // Send the message
            } else {
                throw new IllegalArgumentException("No predefined message with the key \"" + messageKey + "\" was found!");
            }
        } else {
            throw new IllegalArgumentException("No prefix was provided for key \"" + prefixKey + "\"!");
        }
    }
}
