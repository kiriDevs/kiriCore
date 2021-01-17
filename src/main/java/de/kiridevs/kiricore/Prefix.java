package de.kiridevs.kiricore;

/**
 * The Prefix class can be used to easily generate standardized chat prefixes,
 * complete with colors. Each instance has a chat prefix for the console and one for the player,
 * the difference being that the player chat prefix uses §f (bold text) while the console chat prefix does not.
 */
public class Prefix {
    /**
     * The generated chat prefix WITHOUT §f (bold text) to be used for messages shown in console
     * (e.g. server broadcasts, answers to commands executed by everything except players)
     */
    public final String console;

    /**
     * The generated chat prefix WITH §f (bold text) to be used for messages sent to players
     * (and only players!)
     */
    public final String player;

    /** Constructor of the Prefix class. Generates both console and player prefixes.
     * @param service The name of the service (i.e. PlugIn) the prefix is being generated for
     *                - the part between the brackets
     * @param serviceColor The Minecraft color code to render the service (i.e. PlugIn) name in.
     *                     If multiple are used, they have to be separated with '§' signs.
     * @param textColor The Minecraft color code to render the actual chat message in.
     *                  If multiple are used, they have to be separated with '§' signs.
     */
    public Prefix(String service, String serviceColor, String textColor) {
        console = "§8[§r§" + serviceColor + service + "§r§8]§r§" + textColor + " ";
        player = "§8[§r§l§" + serviceColor + service + "§r§8]§r§" + textColor + " ";
    }
}
