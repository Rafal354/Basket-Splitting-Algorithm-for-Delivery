package com.ocado.generator;

import java.util.HashSet;
import java.util.List;

/**
 * An interface for generating subsets.
 */
public interface SubsetGenerator {

    /**
     * Generates all possible subsets of a given list of elements.
     *
     * @param list The list of elements for which subsets are to be generated.
     *
     * @return A list containing all possible subsets of the input list.
     */
    List<HashSet<String>> generateAllSubsets(List<String> list);
}
