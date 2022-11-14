package com.xgen.interview;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PricerTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void canGetSingleItemPrice() throws Exception {
        Pricer pricer = new Pricer();

        int expectedPrice = 100;
        int actualPrice = pricer.getPriceSingleItem("apple");

        assertEquals(expectedPrice,actualPrice);
    }

    @Test
    public void throwsExceptionIfItemDoesNotExist() throws Exception {
        Pricer pricer = new Pricer();

        exception.expect(Exception.class);
        pricer.getPriceSingleItem("orange");
    }

    @Test
    public void canGetTotalItemPrice() throws Exception {
        Pricer pricer = new Pricer();

        Item item = new Item("apple",2);

        int expectedPrice = 200;
        int actualPrice = pricer.getPrice(item);

        assertEquals(expectedPrice, actualPrice);
    }


    @Test
    public void canGetCostInEuros(){
        Pricer pricer = new Pricer();

        int costInCents = 1006;

        String expectedPrice = "10.06" + pricer.EURO;
        String actualPrice = pricer.getPriceInEuros(costInCents);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void canGetTotalCostGivenAListOfItems(){
        Pricer pricer = new Pricer();

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("apple",2));
        itemList.add(new Item("banana",3));

        String expectedPrice = "8.00" + pricer.EURO;
        String actualPrice = pricer.getStringTotalPrice(itemList);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void canGetTotalCostEvenWhenItemIsNotInHashMap(){
        Pricer pricer = new Pricer();

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("apple",2));
        itemList.add(new Item("banana",3));
        itemList.add(new Item("orange",3));


        String expectedPrice = "8.00" + pricer.EURO;
        String actualPrice = pricer.getStringTotalPrice(itemList);
        System.out.println(actualPrice + "->" + expectedPrice);
        assertEquals(expectedPrice, actualPrice);
    }

}
