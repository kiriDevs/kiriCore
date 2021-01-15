import de.kiridevs.kiricore.Prefix;
import org.junit.jupiter.api.Test;

public class PrefixTest {

    boolean check(String value, String expectation) {
        return value.equals(expectation);
    }

    boolean checkPrefix(Prefix resultPrefix, String consoleExpectation, String playerExpectation) {
        boolean consoleMeets = check(resultPrefix.console, consoleExpectation);
        boolean playerMeets = check(resultPrefix.player, playerExpectation);

        return consoleMeets && playerMeets;
    }

    @Test
    void testPrefix() {
        // Declare expectations
        final String SUCCESS_PREFIX_CONSOLE_EXPECTATION = "§8[§r§2kiriCore§r§8]§r§a ";
        final String SUCCESS_PREFIX_PLAYER_EXPECTATION = "§8[§r§l§2kiriCore§r§8]§r§a ";

        // Testing logic
        Prefix successPrefix = new Prefix("kiriCore", "2", "a");

        // Assertions
        assert checkPrefix(successPrefix, SUCCESS_PREFIX_CONSOLE_EXPECTATION, SUCCESS_PREFIX_PLAYER_EXPECTATION);
    }

}
