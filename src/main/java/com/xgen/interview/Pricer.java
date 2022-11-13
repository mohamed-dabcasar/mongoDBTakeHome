package com.xgen.interview;

import java.util.HashMap;
import java.util.List;


/**
 * A stub implementation - for this exercise, you may disregard that this is incomplete.
 */
public class Pricer {
    HashMap<String, Integer> pricingDatabase = new HashMap<>(); // stub
    final String EURO = "\u20ac";

    public Pricer() {
        pricingDatabase.put("apple", 100);
        pricingDatabase.put("banana", 200);
    }

    /**
     * Returns the price of the item passed, in Euro-cent. Eg. if an item costs €1, this will return 100
     * If itemType is an unknown string, store policy is that the item is free.
     */
    public Integer getPriceSingleItem(String itemLabel) throws Exception {
        if (!pricingDatabase.containsKey(itemLabel)) {
            String message = String.format("ItemType%s not found in pricing database", itemLabel);
            throw new Exception(message);
        }
        return pricingDatabase.get(itemLabel);
    }

    public Integer getPrice(Item item) throws Exception {
        return getPriceSingleItem(item.itemLabel) * item.quantity;
    }

    public String getPriceInEuros(Integer totalPriceInCents){
        int wholeNumberPart = totalPriceInCents / 100;
        int decimalPart = totalPriceInCents % 100;

        String decimalPartString = Integer.toString(decimalPart);
        String wholeNumberPartString =Integer.toString(wholeNumberPart);

        if(decimalPart < 10){
            decimalPartString = "0" + decimalPartString;
        }

        return wholeNumberPartString + "." + decimalPartString + EURO;
    }

    public String getStringTotalPrice(List<Item> itemsInOrder) {
        Integer totalPriceInCents = 0;

        for(Item item : itemsInOrder){
            try {
                totalPriceInCents += getPrice(item);
            } catch (Exception exception) {
                String message = String.format("Cannot add cost of item %s to sum because it not is database", item.itemLabel);
                System.out.println(message);
            }
        }

        return getPriceInEuros(totalPriceInCents);
    }
}