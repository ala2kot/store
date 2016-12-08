package com.test.store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        final Map<String, Long> itemGroupByType = Optional.ofNullable(shoppingCart).orElse(new ArrayList<>()).stream().map(item-> item.toLowerCase()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        BigDecimal toPay = new BigDecimal("0.00");
        for (String item : itemGroupByType.keySet()) {
             toPay = toPay.add(mapItemToPrice(item, itemGroupByType.get(item)));
        }
        return toPay;
    }

    private BigDecimal mapItemToPrice(String item, long numberOfItems) {
        if (APPLE.equals(item.toLowerCase())) {
            return applePrice.multiply(new BigDecimal(applyAppleNewOffer(numberOfItems)));
        }
        return orangePrice.multiply(new BigDecimal(applyOrangeNewOffer(numberOfItems)));
    }

    long applyAppleNewOffer(long numberOfApples) {
        return numberOfApples % 2 + (int) numberOfApples / 2;
    }

    long applyOrangeNewOffer(long numberOfOranges) {
        return numberOfOranges % 3 + ((int) numberOfOranges / 3) * 2;
    }

}
