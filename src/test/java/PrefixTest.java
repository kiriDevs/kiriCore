import de.kiridevs.kiricore.Prefix;
import org.junit.jupiter.api.Test;

public class PrefixTest {
    Prefix checkingPrefix;
    String consoleExpectation;
    String playerExpectation;

    boolean checkPrefix() {
        boolean consoleOkay = checkingPrefix.console.equals(consoleExpectation);
        boolean playerOkay = checkingPrefix.player.equals(playerExpectation);

        return consoleOkay && playerOkay;
    }

    @Test
    void testPrefix() {
        consoleExpectation = "§8[§r§2success§r§8]§r§a ";
        playerExpectation = "§8[§r§l§2success§r§8]§r§a ";
        checkingPrefix = new Prefix("success", "2", "a");
        assert checkPrefix();
        System.out.println("Successfully tested Prefix (1/3)!");

        consoleExpectation = "§8[§r§aerrorMaker§r§8]§r§4 ";
        playerExpectation = "§8[§r§l§aerrorMaker§r§8]§r§4 ";
        checkingPrefix = new Prefix("errorMaker", "a", "4");
        assert checkPrefix();
        System.out.println("Successfully tested Prefix (2/3)!");

        consoleExpectation = "§8[§r§9secretMessageLol§r§8]§r§k ";
        playerExpectation = "§8[§r§l§9secretMessageLol§r§8]§r§k ";
        checkingPrefix = new Prefix("secretMessageLol", "9", "k");
        assert checkPrefix();
        System.out.println("Successfully tested Prefix (3/3)!");
    }

}
