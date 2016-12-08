package com.test.store;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
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
    public void shouldHandleApplesWithNewOffer() {
        //given

        //when

        //then
        assertThat(checkout.totalCost(listOf(APPLE, 1)), is(APPLE_PRICE));
        assertThat(checkout.totalCost(listOf(APPLE, 10)), is(APPLE_PRICE.multiply(new BigDecimal(5))));
        assertThat(checkout.totalCost(listOf(APPLE, 5)), is(APPLE_PRICE.multiply(new BigDecimal(3))));
    }

    @Test
    public void shouldHandleOrangesWithNewOffer() {
        //given

        //when

        //then
        assertThat(checkout.totalCost(listOf(ORANGE, 1)), is(ORANGE_PRICE));
        assertThat(checkout.totalCost(listOf(ORANGE, 10)), is(ORANGE_PRICE.multiply(new BigDecimal(7))));
        assertThat(checkout.totalCost(listOf(ORANGE, 5)), is(ORANGE_PRICE.multiply(new BigDecimal(4))));
    }

    @Test
    public void shouldHandleMixOfApplesAndOranges() {
        //given

        //when

        //then
        assertThat(checkout.totalCost(Arrays.asList(APPLE, ORANGE)), is(APPLE_PRICE.add(ORANGE_PRICE)));
        assertThat(checkout.totalCost(Arrays.asList(APPLE, APPLE, APPLE, ORANGE)), is((APPLE_PRICE.multiply(new BigDecimal(2))).add(ORANGE_PRICE)));
        assertThat(checkout.totalCost(Arrays.asList(ORANGE, APPLE, ORANGE)), is(ORANGE_PRICE.multiply(new BigDecimal(2)).add(APPLE_PRICE)));
    }

    @Test
    public void shouldBeCaseInsensitive() {
        //given

        //when

        //then
        assertThat(checkout.totalCost(Arrays.asList(APPLE, ORANGE, "Apple", "oRange")), is(APPLE_PRICE.multiply(new BigDecimal(2)).add(ORANGE_PRICE.multiply(new BigDecimal(2)))));
    }

    @Test
    public void shouldCalculateCorrectNumberOfApplesToPay() {
        //given

        //when

        //then
        assertThat(checkout.applyAppleNewOffer(1l),is(1l));
        assertThat(checkout.applyAppleNewOffer(2l),is(1l));
        assertThat(checkout.applyAppleNewOffer(3l),is(2l));
        assertThat(checkout.applyAppleNewOffer(4l),is(2l));
        assertThat(checkout.applyAppleNewOffer(5l),is(3l));
    }

    @Test
    public void shouldCalculateCorrectNumberOfOrangesToPay() {
        //given

        //when

        //then
        assertThat(checkout.applyOrangeNewOffer(1l),is(1l));
        assertThat(checkout.applyOrangeNewOffer(2l),is(2l));
        assertThat(checkout.applyOrangeNewOffer(3l),is(2l));
        assertThat(checkout.applyOrangeNewOffer(4l),is(3l));
        assertThat(checkout.applyOrangeNewOffer(5l),is(4l));
        assertThat(checkout.applyOrangeNewOffer(6l),is(4l));
    }

    private List<String> listOf(String item, int numberOfApples) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numberOfApples; i++) {
            result.add(item);
        }
        return result;
    }

}