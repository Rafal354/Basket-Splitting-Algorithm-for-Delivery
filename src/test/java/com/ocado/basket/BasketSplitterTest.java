package com.ocado.basket;

import com.ocado.model.Product;
import com.ocado.reader.JsonReaderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class BasketSplitterTest {

    private static final String ROOT_PATH = "src/test/resources/";
    private static final String CONFIG_FILE_NAME = "config.json";
    private static final String BASKET_2_FILE_NAME = "basket-2.json";
    private static final String BASKET_7_FILE_NAME = "basket-7.json";
    private static final String BASKET_8_FILE_NAME = "basket-8.json";

    private BasketSplitter basketSplitter;

    @BeforeEach
    void setUp() {
        this.basketSplitter = new BasketSplitter(ROOT_PATH + CONFIG_FILE_NAME);
    }

    @Test
    void testSplitFromSecondExample() {
        // Given
        List<String> items = new JsonReaderImpl().readBasket(ROOT_PATH + BASKET_2_FILE_NAME);
        Map<String, List<String>> expectedDeliveryProductMap = this.getDeliveryProductMapFromSecondExample();

        // When
        Map<String, List<String>> actualDeliveryProductMap = this.basketSplitter.split(items);

        // Then
        Assertions.assertEquals(expectedDeliveryProductMap, actualDeliveryProductMap);
    }

    @Test
    void testEmptyBasketHandling() {
        // Given
        List<String> items = Collections.emptyList();
        Map<String, List<String>> expectedDeliveryProductMap = new HashMap<>();

        // When
        Map<String, List<String>> actualDeliveryProductMap = this.basketSplitter.split(items);

        // Then
        Assertions.assertEquals(expectedDeliveryProductMap, actualDeliveryProductMap);
    }

    @Test
    void testOneCourierShouldDeliverAllProducts() {
        // Given
        String deliveryMethod = Dictionary.COURIER;
        List<String> items = this.getAllProductsConnectedToOneDeliveryMethod(deliveryMethod);
        Map<String, List<String>> expectedDeliveryProductMap = new HashMap<>();
        expectedDeliveryProductMap.put(deliveryMethod, new ArrayList<>(items));

        // When
        Map<String, List<String>> actualDeliveryProductMap = this.basketSplitter.split(items);

        // Then
        Assertions.assertEquals(expectedDeliveryProductMap, actualDeliveryProductMap);
    }

    @Test
    void testDeliveryOfAllProducts() {
        // Given
        List<String> items = this.getAllProducts();
        Map<String, List<String>> expectedDeliveryProductMap = getExpectedDeliveryProductMapOfAllProducts();

        // When
        Map<String, List<String>> actualDeliveryProductMap = this.basketSplitter.split(new ArrayList<>(items));

        // Then
        Assertions.assertEquals(expectedDeliveryProductMap, actualDeliveryProductMap);
    }

    @Test
    void testAllProductsDeliverByOneDeliveryMethod() {
        // Given
        List<String> items = new JsonReaderImpl().readBasket(ROOT_PATH + BASKET_7_FILE_NAME);
        Map<String, List<String>> expectedDeliveryProductMap = this.getDeliveryProductMapOneDeliveryMethod();

        // When
        Map<String, List<String>> actualDeliveryProductMap = this.basketSplitter.split(items);

        // Then
        Assertions.assertEquals(expectedDeliveryProductMap, actualDeliveryProductMap);
    }

    @Test
    void testEachProductShouldBeDeliverByDifferentDeliveryMethod() {
        // Given
        List<String> items = new JsonReaderImpl().readBasket(ROOT_PATH + BASKET_8_FILE_NAME);
        Map<String, List<String>> expectedDeliveryProductMap = new HashMap<>();
        expectedDeliveryProductMap.put(Dictionary.PICK_UP_POINT, Collections.singletonList(Dictionary.CHICKEN_PHYLLO));
        expectedDeliveryProductMap.put(Dictionary.SAME_DAY_DELIVERY, Collections.singletonList(Dictionary.DRIED_PEACH));
        expectedDeliveryProductMap.put(Dictionary.MAILBOX_DELIVERY, Collections.singletonList(Dictionary.CHEESE_SHEEP_MILK));

        // When
        Map<String, List<String>> actualDeliveryProductMap = this.basketSplitter.split(items);

        // Then
        Assertions.assertEquals(expectedDeliveryProductMap, actualDeliveryProductMap);
    }

    private List<String> getAllProductsConnectedToOneDeliveryMethod(String deliveryMethod) {
        return basketSplitter.getProductMap()
                .values()
                .stream()
                .filter(product -> product.getDeliveryMethods().contains(deliveryMethod))
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    private List<String> getAllProducts() {
        return basketSplitter.getProductMap()
                .values()
                .stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    private Map<String, List<String>> getExpectedDeliveryProductMapOfAllProducts() {
        Map<String, List<String>> deliveryMethods = new HashMap<>();
        Dictionary dictionary = new Dictionary();

        deliveryMethods.put(Dictionary.PICK_UP_POINT, dictionary.getPickUpPointList());
        deliveryMethods.put(Dictionary.IN_STORE_PICK_UP, dictionary.getInStorePickUpList());
        deliveryMethods.put(Dictionary.SAME_DAY_DELIVERY, dictionary.getSameDayDeliveryList());
        deliveryMethods.put(Dictionary.COURIER, dictionary.getCourierList());
        deliveryMethods.put(Dictionary.MAILBOX_DELIVERY, dictionary.getMailboxDeliveryList());
        deliveryMethods.put(Dictionary.NEXT_DAY_SHIPPING, dictionary.getNextDayShippingList());

        return deliveryMethods;
    }

    private Map<String, List<String>> getDeliveryProductMapFromSecondExample() {
        Map<String, List<String>> expectedDeliveryProductMap = new HashMap<>();
        expectedDeliveryProductMap.put(Dictionary.SAME_DAY_DELIVERY, Arrays.asList(Dictionary.SAUCE_MINT,
                Dictionary.NUMI_TEA, Dictionary.GARLIC_PEELED));
        expectedDeliveryProductMap.put(Dictionary.COURIER, Collections.singletonList(Dictionary.CAKE_CHEESECAKE));
        expectedDeliveryProductMap.put(Dictionary.EXPRESS_COLLECTION, Arrays.asList(Dictionary.FOND_CHOCOLATE,
                Dictionary.CHOCOLATE_UNSWEETENED, Dictionary.NUT_ALMOND, Dictionary.HAGGIS, Dictionary.MUSHROOM_FROZEN,
                Dictionary.LONGAN, Dictionary.BAG_CLEAR_10LB, Dictionary.NANTUCKET_POMEGRANATE,
                Dictionary.PUREE_STRAWBERRY, Dictionary.APPLES_SPARTAN, Dictionary.CABBAGE_NAPPA,
                Dictionary.BAGEL_WHITE_SESAME, Dictionary.TEA_GREEN_APPLE));
        return expectedDeliveryProductMap;
    }

    private Map<String, List<String>> getDeliveryProductMapOneDeliveryMethod() {
        Map<String, List<String>> expectedDeliveryProductMap = new HashMap<>();
        expectedDeliveryProductMap.put(Dictionary.MAILBOX_DELIVERY, Arrays.asList(Dictionary.BREAD_FLAT_BREAD,
                Dictionary.SAUCE_MINT, Dictionary.BEER_MUSKOKA_CREAM_ALE, Dictionary.SPINACH_FROZEN,
                Dictionary.CHEESE_MIX, Dictionary.CARBONATED_WATER_RASPBERRY, Dictionary.NANTUCKET_APPLE_JUICE,
                Dictionary.LONGAN, Dictionary.OXTAIL_CUT, Dictionary.CHEESE_SHEEP_MILK));
        return expectedDeliveryProductMap;
    }
}
