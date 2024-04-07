package com.ocado.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {
    private String name;
    private HashSet<String> deliveryMethods;
}
