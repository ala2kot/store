package com.test.store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class Checkout {

    public static final String APPLE = "apple";
    public static final String ORANGE = "orange";
    private final BigDecimal applePrice;
    private final BigDecimal orangePrice;

    public Checkout(BigDecimal applePrice, BigDecimal orangePrice) {
        this.applePrice = applePrice;
        this.orangePrice = orangePrice;
    }

    public BigDecimal totalCost(List<String> shoppingCart) {
        return Optional.ofNullable(shoppingCart).orElse(new ArrayList<>()).stream()
                .map(item -> mapItemToPrice(item)).reduce(new BigDecimal("0.00"), (a, b) -> a.add(b));
    }

    private BigDecimal mapItemToPrice(String item) {
        if (APPLE.equals(item.toLowerCase())) {
            return applePrice;
        }
        return orangePrice;
    }

}
