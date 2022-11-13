package com.xgen.interview;

public class Item {
    String itemLabel;
    int quantity;

    public Item(String itemLabel, int quantity) {
        this.itemLabel = itemLabel;
        this.quantity = quantity;
    }
    public static String getDefaultItemStringFormat(Item item, Pricer pricer) throws Exception {
        return item.itemLabel + " - " + item.quantity + " - " + pricer.getPriceInEuros(pricer.getPrice(item));
    }

    public static String getItemStringFormatWithPriceFirst(Item item,  Pricer pricer) throws Exception {
        return pricer.getPriceInEuros(pricer.getPrice(item))  + " - " + item.itemLabel + " - " + item.quantity;
    }
}
