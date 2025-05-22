package org.translation;

import java.util.ArrayList;
import java.util.List;

// TOD Task: modify this class so that it also supports the Spanish language code "es" and
//            one more language code of your choice. Each member of your group should add
//            support for one additional langauge code on a branch; then push and create a pull request on GitHub.

// Extra Task: if your group has extra time, you can add support for another country code in this class.

/**
 * An implementation of the Translator interface which translates
 * the country code COUNTRY_CANADA to several languages.
 */
public class InLabByHandTranslator implements Translator {

    public static final String COUNTRY_CANADA = "can";

    @Override

    public List<String> getCountryLanguages(String country) {

        // TOD Checkstyle: The String COUNTRY_CANADA appears 4 times in the file.
        if (COUNTRY_CANADA.equals(country)) {
            return new ArrayList<>(List.of("de", "en", "zh", "es"));
        }
        return new ArrayList<>();
    }

    // TOD Checkstyle: Static variable definition in wrong order.

    /**
     * Returns the country abbreviations for all countries whose translations are
     * available from this Translator.
     *
     * @return list of country abbreviations for which we have translations available
     */
    @Override
    public List<String> getCountries() {
        return new ArrayList<>(List.of(COUNTRY_CANADA));
    }

    @Override

    public String translate(String country, String language) {
        /* 1️⃣ first (early-exit) return */
        if (!COUNTRY_CANADA.equals(country)) {
            return null;
        }

        /* 2️⃣ all remaining logic uses a single return */
        String result = null;
        if ("de".equals(language)) {
            result = "Kanada";
        }
        else if ("en".equals(language)) {
            result = "Canada";
        }
        else if ("zh".equals(language)) {
            result = "加拿大";
        }
        else if ("es".equals(language)) {
            result = "Canadá";
        }
        return result;
    }

}
