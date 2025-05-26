package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    private final List<String> countryCodes = new ArrayList<>();
    private final List<String> languageCodes;
    private final List<JSONObject> countryList = new ArrayList<>();

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

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            String alpha3 = "alpha3";
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject country = jsonArray.getJSONObject(index);
                countryList.add(country);
                countryCodes.add(country.getString(alpha3));
            }

            JSONObject firstCountry = jsonArray.getJSONObject(0);
            Set<String> keys = firstCountry.keySet();
            languageCodes = new ArrayList<>(keys);
            languageCodes.remove("id");
            languageCodes.remove("alpha2");
            languageCodes.remove(alpha3);
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        return new ArrayList<>(languageCodes);
    }

    @Override
    public List<String> getCountries() {
        return new ArrayList<>(countryCodes);
    }

    @Override
    public String translate(String country, String language) {
        for (JSONObject countryObject : countryList) {
            if (countryObject.getString("alpha3").equals(country)) {
                return countryObject.getString(language);
            }
        }
        return null;
    }
}
