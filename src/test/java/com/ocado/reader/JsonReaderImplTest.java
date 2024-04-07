package com.ocado.reader;

import com.ocado.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class JsonReaderImplTest {

    private static final String CONFIGURATION_PATH = "src/test/resources/config-1.json";
    private static final String BASKET_PATH = "src/test/resources/basket-1.json";
    private static final String PRODUCT_1_NAME = "product 1";
    private static final String PRODUCT_2_NAME = "product 2";
    private static final String PRODUCT_3_NAME = "product 3";
    private static final String DELIVERY_METHOD_1 = "DeliveryMethod1";
    private static final String DELIVERY_METHOD_2 = "DeliveryMethod2";
    private static final String DELIVERY_METHOD_3 = "DeliveryMethod3";

    private JsonReaderImpl jsonReaderImpl;

    @BeforeEach
    void setUp() {
        jsonReaderImpl = new JsonReaderImpl();
    }

    @Test
    void testReadConfiguration() {
        // Given
        Map<String, Product> expectedConfiguration = this.createConfigurationMap();

        // When
        Map<String, Product> actualConfiguration = jsonReaderImpl.readConfiguration(CONFIGURATION_PATH);

        // Then
        Assertions.assertEquals(expectedConfiguration, actualConfiguration);
    }

    @Test
    void testReadBasket() {
        // Given
        List<String> expectedProducts = Arrays.asList(PRODUCT_1_NAME, PRODUCT_2_NAME, PRODUCT_3_NAME);

        // When
        List<String> actualProducts = jsonReaderImpl.readBasket(BASKET_PATH);

        // Then
        Assertions.assertEquals(expectedProducts, actualProducts);
    }

    private Map<String, Product> createConfigurationMap() {

        Map<String, Product> expectedConfiguration = new HashMap<>();

        Product product1 = new Product(PRODUCT_1_NAME, new HashSet<>(Set.of(DELIVERY_METHOD_1, DELIVERY_METHOD_2)));
        Product product2 = new Product(PRODUCT_2_NAME, new HashSet<>(Set.of(DELIVERY_METHOD_2, DELIVERY_METHOD_3)));

        expectedConfiguration.put(PRODUCT_1_NAME, product1);
        expectedConfiguration.put(PRODUCT_2_NAME, product2);

        return expectedConfiguration;
    }
}
