package com.ocado.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SubsetGeneratorImpl implements SubsetGenerator {
    private List<HashSet<String>> allSubsets;

    public SubsetGeneratorImpl() {
        this.allSubsets = new ArrayList<>();
    }

    public List<HashSet<String>> generateAllSubsets(List<String> list) {
        this.allSubsets = new ArrayList<>();
        this.generateSubsets(new HashSet<>(), list, 0);
        return this.allSubsets;
    }

    private void generateSubsets(HashSet<String> chosenElems, List<String> list, int index) {
        if (index >= list.size()) {
            this.allSubsets.add(chosenElems);
            return;
        }
        this.generateSubsets(new HashSet<>(chosenElems), list, index + 1);
        chosenElems.add(list.get(index));
        this.generateSubsets(new HashSet<>(chosenElems), list, index + 1);
    }
}
