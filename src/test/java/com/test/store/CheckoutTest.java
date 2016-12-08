package com.test.store;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.test.store.Checkout.APPLE;
import static com.test.store.Checkout.ORANGE;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckoutTest {

    public static final BigDecimal APPLE_PRICE = new BigDecimal("0.60");
    public static final BigDecimal ORANGE_PRICE = new BigDecimal("0.25");

    private Checkout checkout;

    @Before
    public void init() {
        checkout = new Checkout(APPLE_PRICE, ORANGE_PRICE);
    }

    @Test
    public void shouldHandleEmptyListOrNullList() {
        //given

        //when

        //then
        assertThat(checkout.totalCost(null), is(new BigDecimal("0.00")));
        assertThat(checkout.totalCost(Arrays.asList()), is(new BigDecimal("0.00")));
    }

    @Test
    public void shouldHandleApples() {
        //given

        //when

        //then
        assertThat(checkout.totalCost(listOf(APPLE, 1)), is(APPLE_PRICE));
        assertThat(checkout.totalCost(listOf(APPLE, 10)), is(APPLE_PRICE.multiply(new BigDecimal(10))));
        assertThat(checkout.totalCost(listOf(APPLE, 5)), is(APPLE_PRICE.multiply(new BigDecimal(5))));
    }

    @Test
    public void shouldHandleOranges() {
        //given

        //when

        //then
        assertThat(checkout.totalCost(listOf(ORANGE, 1)), is(ORANGE_PRICE));
        assertThat(checkout.totalCost(listOf(ORANGE, 10)), is(ORANGE_PRICE.multiply(new BigDecimal(10))));
        assertThat(checkout.totalCost(listOf(ORANGE, 5)), is(ORANGE_PRICE.multiply(new BigDecimal(5))));
    }

    @Test
    public void shouldHandleMixOfApplesAndOranges() {
        //given

        //when

        //then
        assertThat(checkout.totalCost(Arrays.asList(APPLE, ORANGE)), is(APPLE_PRICE.add(ORANGE_PRICE)));
        assertThat(checkout.totalCost(Arrays.asList(APPLE, APPLE, APPLE, ORANGE)), is((APPLE_PRICE.multiply(new BigDecimal(3))).add(ORANGE_PRICE)));
        assertThat(checkout.totalCost(Arrays.asList(ORANGE, APPLE, ORANGE)), is(ORANGE_PRICE.multiply(new BigDecimal(2)).add(APPLE_PRICE)));
    }

    @Test
    public void shouldBeCaseInsensitive() {
        //given

        //when

        //then
        assertThat(checkout.totalCost(Arrays.asList(APPLE, ORANGE, "Apple", "oRange")), is(APPLE_PRICE.multiply(new BigDecimal(2)).add(ORANGE_PRICE.multiply(new BigDecimal(2)))));
    }

    private List<String> listOf(String item, int numberOfApples) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numberOfApples; i++) {
            result.add(item);
        }
        return result;
    }

}