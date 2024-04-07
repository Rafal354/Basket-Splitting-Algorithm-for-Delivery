package com.ocado.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SubsetGeneratorImplTest {

    private static final String SUPPLIER_A = "A";
    private static final String SUPPLIER_B = "B";
    private static final String SUPPLIER_C = "C";

    SubsetGeneratorImpl subsetGenerator;

    @BeforeEach
    void setUp() {
        subsetGenerator = new SubsetGeneratorImpl();
    }

    @Test
    void testGenerateAllSubsets() {
        // Given
        List<String> supplierList = Arrays.asList(SUPPLIER_A, SUPPLIER_B, SUPPLIER_C);
        List<HashSet<String>> expectedSubsets = getAllSubsets();

        // When
        List<HashSet<String>> actualSubsets = subsetGenerator.generateAllSubsets(supplierList);

        // Then
        Assertions.assertTrue(actualSubsets.containsAll(expectedSubsets));
    }

    @Test
    void testGenerateAllSubsetsForEmptyList() {
        // Given
        List<String> emptySupplierList = new ArrayList<>();
        List<HashSet<String>> expectedSubsets = new ArrayList<>(Collections.singletonList(new HashSet<>()));

        // When
        List<HashSet<String>> actualSubsets = subsetGenerator.generateAllSubsets(emptySupplierList);

        // Then
        Assertions.assertEquals(expectedSubsets, actualSubsets);
    }

    private List<HashSet<String>> getAllSubsets() {
        List<HashSet<String>> subsets = new ArrayList<>();

        subsets.add(new HashSet<>());

        subsets.add(new HashSet<>(Collections.singletonList(SUPPLIER_A)));
        subsets.add(new HashSet<>(Collections.singletonList(SUPPLIER_B)));
        subsets.add(new HashSet<>(Collections.singletonList(SUPPLIER_C)));

        subsets.add(new HashSet<>(Arrays.asList(SUPPLIER_A, SUPPLIER_B)));
        subsets.add(new HashSet<>(Arrays.asList(SUPPLIER_A, SUPPLIER_C)));
        subsets.add(new HashSet<>(Arrays.asList(SUPPLIER_B, SUPPLIER_C)));

        subsets.add(new HashSet<>(Arrays.asList(SUPPLIER_A, SUPPLIER_B, SUPPLIER_C)));

        return subsets;
    }
}
