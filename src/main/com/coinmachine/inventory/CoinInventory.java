package com.coinmachine.inventory;

import com.coinmachine.Coin;
import com.coinmachine.counter.CountedCoins;

import java.util.HashMap;
import java.util.Iterator;

import static java.util.Objects.requireNonNull;

/**
 * Created by marcusclow on 7/30/18.
 */
public class CoinInventory {

    private HashMap<Coin, Integer> inventory;

    public CoinInventory(){

        inventory = new HashMap<>();

        // Start with 100 coins of each.
        inventory.put(Coin.QUARTER, 100);
        inventory.put(Coin.DIME, 100);
        inventory.put(Coin.NICKEL, 100);
        inventory.put(Coin.PENNY, 100);
    }

    public int getInventoryFor(Coin coin){
        requireNonNull(coin, "Coin cannot be null.");
        Integer inventory = this.inventory.get(coin);
        requireNonNull(inventory, "Coin: " + coin + " - should be in inventory even if 0.");
        return inventory;
    }

    public void subtractCountedCoinsFromInventory(CountedCoins countedCoins){
        Iterator<Coin> iterator = countedCoins.getCoins();
        while (iterator.hasNext()){
            Coin coin = iterator.next();
            int count = countedCoins.getCountFor(coin);
            int currentInventory = getInventoryFor(coin);
            inventory.put(coin, currentInventory - count);
        }
    }

    @Override
    public String toString() {
        return "Quarters:" + getInventoryFor(Coin.QUARTER) + " : " +
                "Dimes:" + getInventoryFor(Coin.DIME) + " : " +
                "Nikels:" + getInventoryFor(Coin.NICKEL) + " : " +
                "Pennies:" + getInventoryFor(Coin.PENNY);
    }
}
