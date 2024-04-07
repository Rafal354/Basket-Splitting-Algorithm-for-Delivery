package com.ocado.basket;

import com.ocado.generator.SubsetGenerator;
import com.ocado.generator.SubsetGeneratorImpl;
import com.ocado.model.Product;
import com.ocado.reader.JsonReader;
import com.ocado.reader.JsonReaderImpl;
import lombok.Getter;

import java.util.*;

@Getter
public class BasketSplitter {
    private final Map<String, Product> productMap;
    private final List<String> deliveryMethods;

    public BasketSplitter(String absolutePathToConfigFile) {
        this.productMap = this.initializeProductMap(absolutePathToConfigFile);
        this.deliveryMethods = this.initializeDeliveryList();
    }

    public Map<String, List<String>> split(List<String> items) {
        SubsetGenerator subsetGenerator = new SubsetGeneratorImpl();
        List<HashSet<String>> deliverySubsets = subsetGenerator.generateAllSubsets(this.deliveryMethods);
        deliverySubsets.sort(Comparator.comparingInt(HashSet::size));
        return this.findSolution(deliverySubsets, items);
    }

    private Map<String, List<String>> findSolution(List<HashSet<String>> deliverySubsets, List<String> items) {

        if (items.isEmpty()) {
            return new HashMap<>();
        }

        int minNumberOfDeliveries = this.deliveryMethods.size();
        int maxProductsNumberDelivery = 0;
        List<String> bestDeliveriesSubset = new ArrayList<>();
        String bestDelivery = "";

        for (HashSet<String> availableDeliveries : deliverySubsets) {
            if (availableDeliveries.size() > minNumberOfDeliveries) { // If we have found a smaller number of suppliers, we can stop searching
                break;
            }
            int canDeliver = 0;

            for (String item : items) {
                canDeliver = 0;
                for (String delivery : availableDeliveries) {
                    if (productMap.get(item).getDeliveryMethods().contains(delivery)) {
                        canDeliver = 1;
                        break;
                    }
                }
                if (canDeliver == 0) { // If a product cannot be delivered by any supplier, we search for another subset
                    break;
                }
            }
            if (canDeliver == 1) { // If suppliers can deliver all products, we can check the second condition
                for (String delivery : availableDeliveries) { // We are searching for the largest group of products that one delivery can deliver
                    int productNumberDelivery = 0;
                    for (String item : items) {
                        if (productMap.get(item).getDeliveryMethods().contains(delivery)) {
                            productNumberDelivery += 1;
                        }
                    }
                    if (productNumberDelivery > maxProductsNumberDelivery) {
                        maxProductsNumberDelivery = productNumberDelivery;
                        minNumberOfDeliveries = availableDeliveries.size();
                        bestDeliveriesSubset = availableDeliveries.stream().toList();
                        bestDelivery = delivery;
                    }
                }
            }
        }

        List<String> bestDeliveriesList = new ArrayList<>(bestDeliveriesSubset);
        bestDeliveriesList.remove(bestDelivery);   // We're moving the best supplier to the beginning
        bestDeliveriesList.addFirst(bestDelivery); // to ensure we have the largest possible group of products

        Map<String, List<String>> deliveryProductMap = new HashMap<>(); // The final stage - creating the map returned in the output
        List<String> itemsToDeliver = new ArrayList<>(items);
        for (String delivery : bestDeliveriesList) {
            List<String> deliveryProductsList = new ArrayList<>();
            for (String item : itemsToDeliver) {
                if (productMap.get(item).getDeliveryMethods().contains(delivery)) {
                    deliveryProductsList.add(item);
                }
            }
            deliveryProductMap.put(delivery, deliveryProductsList);
            itemsToDeliver.removeAll(deliveryProductsList);
        }

        return deliveryProductMap;
    }

    private Map<String, Product> initializeProductMap(String path) {
        JsonReader jsonReader = new JsonReaderImpl();
        return jsonReader.readConfiguration(path);
    }

    private List<String> initializeDeliveryList() {
        Set<String> supplierSet = new HashSet<>();
        for (Product product : this.productMap.values()) {
            supplierSet.addAll(product.getDeliveryMethods());
        }
        return supplierSet.stream().sorted().toList();
    }
}
