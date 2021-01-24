package de.kiridevs.kiricore.annotations;

public @interface PublicAPI {
    /*
        This @interface is doing nothing (as you can see).

        It can be used as an Entry Point for "unused"-inspection in IntelliJ
        This prevents IntelliJ from showing "unused" warnings,
          while not suppressing compiler warning.
        This behaviour makes sure VSCode doesn't throw warnings about
          a compiler warning being suppressed.
     */
}
