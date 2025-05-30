package org.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program.
 * Complete the code according to the "to do" notes.<br/>
 * The system will:<br/>
 * - prompt the user to pick a country name from a list<br/>
 * - prompt the user to pick the language they want it translated to from a list<br/>
 * - output the translation<br/>
 * - at any time, the user can type quit to quit the program<br/>
 */
public class Main {
    public static final String QUIT = "quit";
    private static final CountryCodeConverter COUNTRY_CODE_CONVERTER = new CountryCodeConverter();
    private static final LanguageCodeConverter LANGUAGE_CODE_CONVERTER = new LanguageCodeConverter();

    /**
     * This is the main entry point of our Translation System!<br/>
     * A class implementing the Translator interface is created and passed into a call to runProgram.
     * @param args not used by the program
     */
    public static void main(String[] args) {

        // TO DO Task: once you finish the JSONTranslator,
        //            you can use it here instead of the InLabByHandTranslator
        //            to try out the whole program!
        // Translator translator = new JSONTranslator(null);
        Translator translator = new JSONTranslator();

        runProgram(translator);
    }

    /**
     * This is the method which we will use to test your overall program, since
     * it allows us to pass in whatever translator object that we want!
     * See the class Javadoc for a summary of what the program will do.
     * @param translator the Translator implementation to use in the program
     */
    public static void runProgram(Translator translator) {
        while (true) {
            String country = promptForCountry(translator);
            if (country.equals(QUIT)) {
                break;
            }
            // TO DO Task: Once you switch promptForCountry so that it returns the country
            //            name rather than the 3-letter country code, you will need to
            //            convert it back to its 3-letter country code when calling promptForLanguage

            String language = promptForLanguage(translator, country);
            if (language.equals(QUIT)) {
                break;
            }
            // TO DO Task: Once you switch promptForLanguage so that it returns the language
            //            name rather than the 2-letter language code, you will need to
            //            convert it back to its 2-letter language code when calling translate.
            //            Note: you should use the actual names in the message printed below though,
            //            since the user will see the displayed message.

            String alpha3 = COUNTRY_CODE_CONVERTER.fromCountry(country);
            String langCode = LANGUAGE_CODE_CONVERTER.fromLanguage(language);

            String translated = translator.translate(alpha3, langCode);

            System.out.println(country + " in " + language + " is " + translator.translate(country, language));
            System.out.println("Press enter to continue or quit to exit.");
            Scanner s = new Scanner(System.in);
            String textTyped = s.nextLine();

            if (textTyped.equalsIgnoreCase(QUIT)) {
                break;
            }
        }
    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForCountry(Translator translator) {
        List<String> countries = translator.getCountries();
        List<String> countryNames = new ArrayList<>();

        // TO DO Task: replace the following println call, sort the countries alphabetically,
        //            and print them out; one per line
        //      hint: class Collections provides a static sort method
        // TO DO Task: convert the country codes to the actual country names before sorting

        for (String code : countries) {
            countryNames.add(COUNTRY_CODE_CONVERTER.fromCountryCode(code));
        }

        Collections.sort(countryNames);
        for (String name : countryNames) {
            System.out.println(name);
        }

        System.out.println("select a country from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();

    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForLanguage(Translator translator, String country) {
        String countryCode = COUNTRY_CODE_CONVERTER.fromCountry(country);
        List<String> languageCodes = translator.getCountryLanguages(countryCode);
        List<String> languageNames = new ArrayList<>();

        for (String code : languageCodes) {
            languageNames.add(LANGUAGE_CODE_CONVERTER.fromLanguageCode(code));
        }

        Collections.sort(languageNames);
        for (String name : languageNames) {
            System.out.println(name);
        }
        // TO DO Task: replace the line below so that we sort the
        //  languages alphabetically and print them out; one per line
        // TO DO Task: convert the language codes to the actual language names before sorting

        System.out.println("select a language from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
