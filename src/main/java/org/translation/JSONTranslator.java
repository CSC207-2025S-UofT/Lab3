package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    // TO DO Task: pick appropriate instance variables for this class
    public static final String ALPHATHREE = "alpha3";
    private final List<String> countryCodes = new ArrayList<>();
    private final List<org.json.JSONObject> countryTranslations = new ArrayList<>();
    private final CountryCodeConverter countryCodeConverter = new CountryCodeConverter();
    private final LanguageCodeConverter languageCodeConverter = new LanguageCodeConverter();
    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */

    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader()
                    .getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            // TODO Task: use the data in the jsonArray to populate your instance variables
            //            Note: this will likely be one of the most substantial pieces of code you write in this lab.
            int i = 0;
            while (i < jsonArray.length()) {
                org.json.JSONObject country = jsonArray.getJSONObject(i);
                String alpha3 = country.getString(ALPHATHREE);
                countryCodes.add(alpha3);
                countryTranslations.add(country);
                i += 1;
            }

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        // TO DO Task: return an appropriate list of language codes,
        //            but make sure there is no aliasing to a mutable object
        for (org.json.JSONObject obj : countryTranslations) {
            if (obj.getString(ALPHATHREE).equalsIgnoreCase(country)) {
                List<String> languages = new ArrayList<>();
                for (String key : obj.keySet()) {
                    if (!"alpha2".equals(key) && !key.equals(ALPHATHREE) && !"id".equals(key)) {
                        languages.add(key);
                    }
                }
                return languages;
            }
        }
        return new ArrayList<>(countryCodes);
    }

    @Override
    public List<String> getCountries() {
        // TO DO Task: return an appropriate list of country codes,
        //            but make sure there is no aliasing to a mutable object
        return new ArrayList<>(countryCodes);
    }

    @Override
    public String translate(String country, String language) {
        // TO DO Task: complete this method using your instance variables as needed
        for (org.json.JSONObject obj : countryTranslations) {
            if (obj.getString("alpha3").equalsIgnoreCase(country)) {
                return obj.optString(language, "Translation not available");
            }
        }
        return "Country not found";

    }
}
