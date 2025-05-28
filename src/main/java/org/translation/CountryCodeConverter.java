package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the service of converting country codes to their names.
 */
public class CountryCodeConverter {

    private List<List<String>> countries = new ArrayList<>();
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
            List<String> linne = lines.subList(1, lines.size());
            for (String line : linne) {
                String[] parts = line.split("\\t");
                String name = parts[0];
                String code = parts[3];
                countries.add(List.of(name, parts[1], parts[2], code));
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
    public String fromCountryCode(String code) {
        for (List<String> country : countries) {
            if (country.get(2).equalsIgnoreCase(code)) {
                return country.get(0);
            }
        }
        return code;
    }

    /**
     * Returns the code of the country for the given country name.
     * @param country the name of the country
     * @return the 3-letter code of the country
     */
    public String fromCountry(String country) {
        for (List<String> countryInfo : countries) {
            if (countryInfo.get(0).equals(country)) {
                return countryInfo.get(3);
            }
        }
        return country;
    }

    /**
     * Returns how many countries are included in this code converter.
     * @return how many countries are included in this code converter.
     */
    public int getNumCountries() {
        return countries.size();
    }
}
