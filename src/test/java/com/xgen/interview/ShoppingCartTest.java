package com.xgen.interview;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class ShoppingCartTest {

    static final String EURO = "\u20ac";

    @Test
    public void canAddAnItem() {
        ShoppingCart shoppingCart = new ShoppingCart(new Pricer());

        shoppingCart.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        shoppingCart.printReceipt();

        assertEquals(String.format("Total = 1.00%s%napple - 1 - 1.00%s",EURO,EURO), myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart shoppingCart = new ShoppingCart(new Pricer());

        shoppingCart.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        shoppingCart.printReceipt();

        assertEquals(String.format("Total = 2.00%s%napple - 2 - 2.00%s",EURO,EURO), myOut.toString());
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCart shoppingCart = new ShoppingCart(new Pricer());

        shoppingCart.addItem("apple", 2);
        shoppingCart.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        shoppingCart.printReceipt();

        assertEquals(String.format("Total = 4.00%s%napple - 2 - 2.00%s%nbanana - 1 - 2.00%s",EURO,EURO,EURO), myOut.toString());
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart shoppingCart = new ShoppingCart(new Pricer());

        shoppingCart.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        shoppingCart.printReceipt();

        assertEquals("There are no items in shopping card!", myOut.toString());
    }


    @Test
    public void canUsePriceFirstFormat() {
        ShoppingCart shoppingCart = new ShoppingCart(new Pricer(),Format.PRICE_FIRST_FORMAT);

        shoppingCart.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        shoppingCart.printReceipt();
        assertEquals(String.format("Total = 1.00%s\r\n" +
                "1.00%s - apple - 1",EURO,EURO), myOut.toString());

    }

    @Test
    public void canHandleEmptyListOfItems() {
        ShoppingCart shoppingCart = new ShoppingCart(new Pricer(),Format.PRICE_FIRST_FORMAT);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        shoppingCart.printReceipt();
        assertEquals("There are no items in shopping card!", myOut.toString());
    }

}


