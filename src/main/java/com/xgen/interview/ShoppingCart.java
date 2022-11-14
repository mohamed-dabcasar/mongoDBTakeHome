package com.xgen.interview;

import java.util.*;

import static com.xgen.interview.Item.getDefaultItemStringFormat;
import static com.xgen.interview.Item.getItemStringFormatWithPriceFirst;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    Pricer pricer;
    List<Item> itemsInOrder = new ArrayList<>();
    Format format = Format.DEFAULT_FORMAT;
    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
        new ShoppingCart(pricer, Format.DEFAULT_FORMAT);
    }




    public ShoppingCart(Pricer pricer, Format format){
        this.format = format;
        this.pricer = pricer;
    }

    public void addItem(String itemType, int quantity) {
        if(pricer.pricingDatabase.containsKey(itemType)){
            addItem(new Item(itemType, quantity));
        }

    }

    public void addItem(Item item) {
        itemsInOrder.add(item);
    }

    public void printReceipt() {

        if(itemsInOrder.isEmpty()){
            System.out.print("There are no items in shopping card!");
            return;
        }

        String totalCostOfItems = pricer.getStringTotalPrice(itemsInOrder);
        System.out.println("Total = " + totalCostOfItems);

        for (int i = 0; i < itemsInOrder.size(); i++) {
            Item item = itemsInOrder.get(i);
            String receiptLine = null;
            try {
                if(format == Format.DEFAULT_FORMAT){
                    receiptLine = getDefaultItemStringFormat(item,pricer);
                }else if(format == Format.PRICE_FIRST_FORMAT){
                    receiptLine = getItemStringFormatWithPriceFirst(item,pricer);
                }
             
            } catch (Exception exception) {
                receiptLine = String.format(item.itemLabel + " - " + item.quantity + " - " + "ERROR: Item %s not in pricingDatabase",item.itemLabel);
            }
            if(i == itemsInOrder.size() - 1){
                System.out.print(receiptLine);
            }else{
                System.out.println(receiptLine);
            }

        }
    }
}
