package com.ocado.reader;

import com.ocado.model.Product;

import java.util.List;
import java.util.Map;

/**
 * An interface for reading JSON data.
 */
public interface JsonReader {

    /**
     * Reads the configuration file containing product information.
     *
     * @param path The path to the configuration file.
     *
     * @return A map containing product names mapped to their corresponding Product objects.
     */
    Map<String, Product> readConfiguration(String path);

    /**
     * Reads the basket file containing a list of product names.
     *
     * @param path The path to the json file describing basket.
     *
     * @return A list of product names.
     */
    List<String> readBasket(String path);
}
