package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting country codes to their names.
 */
public class CountryCodeConverter {

    // TODO Task: pick appropriate instance variable(s) to store the data necessary for this class
    private Map<String, String> alpha2name;
    private Map<String, String> alpha3name;
    private Map<String, String> numericname;
    /**
     * Default constructor which will load the country codes from "country-codes.txt"
     * in the resources folder.
     */
    public CountryCodeConverter() {
        this("country-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the country code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public CountryCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            // TODO Task: use lines to populate the instance variable(s)
            // iterate through each line after the header, split the line into separate words
            // contained in a list, and map each keyword to its corresponding value for the relevant
            // hashmap

            alpha2name = new HashMap<>();
            alpha3name = new HashMap<>();
            numericname = new HashMap<>();

            for (String line : lines) {
                if (line.startsWith("Country")) continue;

                String[] code = line.trim().split(" ");

                if (code.length >= 4) {
                    String country = code[0];
                    String alpha2 = code[1];
                    String alpha3 = code[2];
                    String numeric = code[3];

                    alpha2name.put(alpha2, country);
                    alpha3name.put(alpha3, country);
                    numericname.put(numeric, country);
                }
            }

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

            /**
             * Returns the name of the country for the given country code.
             * @param code the 3-letter code of the country
             * @return the name of the country corresponding to the code
             */
            public String fromCountryCode (String code) {

                // TODO Task: update this code to use an instance variable to return the correct value
                // return the value of the key (country) depending on how long the key is (2 v 3)

                if (code.length() == 2) {
                    return alpha2name.get(code);
                } else if (code.length() == 3) {
                    return alpha3name.get(code);
                } else {
                    String error = new String("error");
                    return error;
                }
            }

    /**
     * Returns the code of the country for the given country name.
     * @param country the name of the country
     * @return the 3-letter code of the country
     */
    public String fromCountry(String country) {
        // TODO Task: update this code to use an instance variable to return the correct value
        // iterate through each key and record the key being accessed. check
        // to see if its value matches country. if yes, return the key.

        return country;
    }

    /**
     * Returns how many countries are included in this code converter.
     * @return how many countries are included in this code converter.
     */
    public int getNumCountries() {
        // TODO Task: update this code to use an instance variable to return the correct value
        return 0;
    }
}
