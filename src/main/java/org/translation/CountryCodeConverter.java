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

                String[] comp = line.trim().split("\\s++");

                if (comp.length >= 4) {
                    String alpha2 = comp[comp.length - 3];
                    String alpha3 = comp[comp.length - 2];
                    String numeric = comp[comp.length - 1];

                    // iterate through each item until you get to code.length - 3
                    // (full name of country received)
                    StringBuilder countryrough = new StringBuilder();
                    for (int c = 0; c < (comp.length - 3); c++) {
                        countryrough.append(comp[c] + " ");
                    }

                    String country = countryrough.toString().trim();
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
                // return the 3-letter code of the key (country) if this country is in our map
                if (alpha3name.containsKey(code)) {
                    return alpha3name.get(code);
                } else {
                    return "not found";
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

        for (String alpha3: alpha3name.keySet()) {
            if (alpha3name.get(alpha3).equals(country)) {
                return alpha3;
            }
        }
        return "not found";
        }

    /**
     * Returns how many countries are included in this code converter.
     * @return how many countries are included in this code converter.
     */
    public int getNumCountries() {
        // TODO Task: update this code to use an instance variable to return the correct value
        return alpha2name.size();
        }
}
