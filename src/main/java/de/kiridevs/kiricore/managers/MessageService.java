package de.kiridevs.kiricore.managers;

import de.kiridevs.kiricore.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Map;

/**
 * The MessageService class can be used to send preset messages with
 * preset prefixes more easily.
 */
@SuppressWarnings("unused")
public class MessageService {
    /**
     * The map used to save and resolve preset prefix objects with their
     * respective keys
     */
    Map<String, Prefix> prefixMap;

    /**
     * The map used to save and resolve preset messages with their
     * respective keys
     */
    Map<String, String> defaultMessages;


    /** Constructor of the MessageService class. Saves
     * @param prefixMap The map to be used when resolving prefixes.
     *                  Needs to contain keys "success", "error", or "info"
     *                  with Non-Null values.
     * @param defaultMessages The map to be used resolving default messages.
     *                        Value-Strings ("messages") can include
     *                        placeholders in the format {X}, which can be
     *                        replaced with their values when sending the
     *                        actual messages.
     *
     * @throws IllegalArgumentException Thrown when the Map passed for
     *                                  prefixMap doesn't include
     *                                  one of "success", "error" or "info"
     */
    public MessageService(
            Map<String, Prefix> prefixMap,
            Map<String, String> defaultMessages) {
        this.prefixMap = prefixMap;
        this.defaultMessages = defaultMessages;

        if (
                (prefixMap.get("success") == null) ||
                (prefixMap.get("error") == null) ||
                (prefixMap.get("info") == null)
        ) {
            throw new IllegalArgumentException("A prefix map must include" +
                    "prefixes for \"success\", \"error\" and \"info\"!");
        }
    }

    /**
     * Sends a message specified in content to the passed recipient,
     * using the required "success" prefix in the prefixMap
     *
     * @param recipient The CommandSender object to send the message to
     * @param content A String which will be sent to the recipient as a
     *                chat message.
     */
    public void sendSuccessMessage(CommandSender recipient, String content) {
        String msg;

        if (recipient instanceof Player) {
            msg = prefixMap.get("success").player;
        } else {
            msg = prefixMap.get("success").console;
        }

        msg += content;
        recipient.sendMessage(msg);
    }

    /**
     * Sends the preset message with the passed key to the passed recipient,
     * using the required "success" prefix in the prefixMap
     *
     * @param recipient The CommandSender object to send the message to
     * @param messageKey The key the preset message was specified with
     *                   in the defaultMessages
     * @param completionArgs Used to add more information to
     *                       placeholder-including preset messages. "{0}" in
     *                       the default message will be replaced with
     *                       completionArgs[0] and so on.
     *                       Can be null.
     *
     * @throws IllegalArgumentException Thrown when messageKey is not
     *                                  a valid key in the defaultMessages
     */
    public void sendSuccessMessage(
            CommandSender recipient,
            String messageKey,
            @Nullable List<String> completionArgs
    ) {
        String msg;

        if (recipient instanceof Player) {
            msg = prefixMap.get("success").player;
        } else {
            msg = prefixMap.get("success").console;
        }

        if (defaultMessages.get(messageKey) != null) {

            String preset = defaultMessages.get
                                   (messageKey); // Get preset msg
            if (completionArgs != null) {
                for (int i = 0; i < completionArgs.size(); i++) {
                    String placeholder = "{" + i + "}";
                    String value = completionArgs.get(i);
                    preset = preset.replace(placeholder, value);
                }
            }
            msg += preset; // Add edited preset to msg

        } else {
            throw new IllegalArgumentException
                    ("No predefined message with the key \"" +
                            messageKey + "\" was found!");
        }

        recipient.sendMessage(msg);
    }

    /**
     * Sends a message specified in content to the passed recipient,
     * using the required "error" prefix in the prefixMap
     *
     * @param recipient The CommandSender object to send the message to
     * @param content A String which will be sent to the recipient
     *                as a chat message.
     */
    public void sendErrorMessage(CommandSender recipient, String content) {
        String msg;

        if (recipient instanceof Player) {
            msg = prefixMap.get("error").player;
        } else {
            msg = prefixMap.get("error").console;
        }

        msg += content;
        recipient.sendMessage(msg);
    }

    /**
     * Sends the preset message with the passed key to the passed recipient,
     * using the required "error" prefix in the prefixMap
     *
     * @param recipient The CommandSender object to send the message to
     * @param messageKey The key the preset message was specified with
     *                   in the defaultMessages
     * @param completionArgs Used to add more information to
     *                       placeholder-including preset messages. "{0}" in
     *                       the default message will be replaced wit
     *                       completionArgs[0] and so on.
     *                       Can be null.
     *
     * @throws IllegalArgumentException Thrown when messageKey is not a valid
     *                                  key in the defaultMessages
     */
    public void sendErrorMessage(
            CommandSender recipient,
            String messageKey,
            @Nullable List<String> completionArgs
    ) {
        String msg;

        if (recipient instanceof Player) {
            msg = prefixMap.get("error").player;
        } else {
            msg = prefixMap.get("error").console;
        }

        if (defaultMessages.get(messageKey) != null) {

            String preset = defaultMessages.get(messageKey); // Get preset msg
            if (completionArgs != null) {
                for (int i = 0; i < completionArgs.size(); i++) {
                    String placeholder = "{" + i + "}";
                    String value = completionArgs.get(i);
                    preset = preset.replace(placeholder, value);
                }
            }
            msg += preset; // Add edited preset to msg

        } else {
            throw new IllegalArgumentException(
                    "No predefined message with the key \"" +
                            messageKey + "\" was found!");
        }

        recipient.sendMessage(msg);
    }

    /**
     * Sends a message specified in content to the passed recipient,
     * using the required "info" prefix in the prefixMap
     *
     * @param recipient The CommandSender object to send the message to
     * @param content A String which will be sent to the recipient as
     *                a chat message.
     */
    public void sendInfoMessage(CommandSender recipient, String content) {
        String msg;

        if (recipient instanceof Player) {
            msg = prefixMap.get("info").player;
        } else {
            msg = prefixMap.get("info").console;
        }

        msg += content;
        recipient.sendMessage(msg);
    }

    /**
     * Sends the preset message with the passed key to the passed recipient,
     * using the required "info" prefix in the prefixMap
     *
     * @param recipient The CommandSender object to send the message to
     * @param messageKey The key the preset message was specified with
     *                   in the defaultMessages
     * @param completionArgs Used to add more information to
     *                       placeholder-including preset messages. "{0}" in
     *                       the default message will be replaced with
     *                       completionArgs[0] and so on.
     *                       Can be null.
     *
     * @throws IllegalArgumentException Thrown when messageKey is not
     *                                  a valid key in the defaultMessages
     */
    public void sendInfoMessage(
            CommandSender recipient,
            String messageKey,
            @Nullable List<String> completionArgs
    ) {
        String msg;

        if (recipient instanceof Player) {
            msg = prefixMap.get("info").player;
        } else {
            msg = prefixMap.get("info").console;
        }

        if (defaultMessages.get(messageKey) != null) {

            String preset = defaultMessages.get(messageKey); // Get preset msg
            if (completionArgs != null) {
                for (int i = 0; i < completionArgs.size(); i++) {
                    String placeholder = "{" + i + "}";
                    String value = completionArgs.get(i);
                    preset = preset.replace(placeholder, value);
                }
            }
            msg += preset; // Add edited preset to msg

        } else {
            throw new IllegalArgumentException(
                    "No predefined message with the key \"" +
                            messageKey + "\" was found!");
        }

        recipient.sendMessage(msg);
    }

    /**
     * Sends a message to the whole server using the console version
     * of the required "info" prefix in the prefixMap
     *
     * @param content The String which will be broadcast across the server
     *                as a chat message
     */
    public void broadcastMessage(String content) {
        String msg = prefixMap.get("info").console;
        msg += content;
        Bukkit.getServer().broadcastMessage(msg);
    }

    /**
     * Sends a message to the whole server using the console version of the
     * required "info" prefix in the prefixMap
     *
     * @param messageKey The key of the message preset in defaultMessages
     * @param completionArgs Used to add more information to
     *                       placeholder-including preset messages. "{0}" in
     *                       the default message will be replaced with
     *                       completionArgs[0] and so on.
     *                       Can be null.
     *
     * @throws IllegalArgumentException Thrown when the value passed in
     *                                  messageKey is not a valid key
     *                                  in defaultMessages.
     */
    public void broadcastMessage(
            String messageKey,
            @Nullable  List<String> completionArgs
    ) {
        String msg;
        msg = prefixMap.get("info").console;

        if (defaultMessages.get(messageKey) != null) {

            String preset = defaultMessages.get(messageKey); // Get preset msg
            if (completionArgs != null) {
                for (int i = 0; i < completionArgs.size(); i++) {
                    String placeholder = "{" + i + "}";
                    String value = completionArgs.get(i);
                    preset = preset.replace(placeholder, value);
                }
            }
            msg += preset; // Add edited preset to msg

        } else {
            throw new IllegalArgumentException(
                    "No predefined message with the key \"" +
                            messageKey + "\" was found!");
        }

        Bukkit.getServer().broadcastMessage(msg);
    }

    /**
     * Sends a message to a specified recipient using a Prefix specified
     * before by passing it in the prefixMap
     *
     * @param recipient The CommandSender to send the message to
     * @param prefixKey The key of the prefix to use in prefixMap
     * @param content The String to send to the recipient as a chat message
     */
    public void sendCustomMessage(
            CommandSender recipient,
            String prefixKey,
            String content
    ) {
        if (prefixMap.get(prefixKey) != null) {
            String msg;

            if (recipient instanceof Player) {
                msg = prefixMap.get(prefixKey).player;
            } else {
                msg = prefixMap.get(prefixKey).console;
            }

            recipient.sendMessage(msg);
        } else {
            throw new IllegalArgumentException(
                    "No prefix was provided for key \"" +
                            prefixKey + "\"!");
        }
    }

    /**
     * Sends a message specified before in the defaultMessages
     * to a specified recipient using a Prefix specified before by passing it
     * in the prefixMap.
     *
     * @param recipient The CommandSender to send the message to
     * @param prefixKey The key of the prefix to use in prefixMap
     * @param messageKey The key of the message to send in defaultMessages
     * @param completionArgs Used to add more information to
     *                       placeholder-including preset messages."{0}" in
     *                       the default message will be replaced with
     *                       completionArgs[0] and so on.
     *                       Can be null.
     */
    public void sendCustomMessage(
            CommandSender recipient,
            String prefixKey,
            String messageKey,
            @Nullable List<String> completionArgs
    ) {
        if (prefixMap.get(prefixKey) != null) {
            if (defaultMessages.get(messageKey) != null) {
                String msg;

                if (recipient instanceof Player) {
                    msg = prefixMap.get(prefixKey).player;
                } else {
                    msg = prefixMap.get(prefixKey).console;
                }

                String preset = defaultMessages.get(messageKey); // Get preset
                if (completionArgs != null) {
                    for (int i = 0; i < completionArgs.size(); i++) {
                        String placeholder = "{" + i + "}";
                        String value = completionArgs.get(i);
                        preset = preset.replace(placeholder, value);
                    }
                }
                msg += preset; // Add edited prefix to msg

                recipient.sendMessage(msg); // Send msg
            } else {
                throw new IllegalArgumentException(
                        "No predefined message with the key \"" +
                                messageKey + "\" was found!");
            }
        } else {
            throw new IllegalArgumentException(
                    "No prefix was provided for key \"" +
                            prefixKey + "\"!");
        }
    }
}
