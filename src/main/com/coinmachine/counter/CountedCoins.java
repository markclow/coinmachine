package com.coinmachine.counter;

import com.coinmachine.Coin;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by marcusclow on 7/30/18.
 */
public class CountedCoins {

    private HashMap<Coin, Integer> counts;

    public CountedCoins(){
        counts = new HashMap<>();
    }

    public void add(Coin coin, int count){
        counts.put(coin, count);
    }

    public Iterator<Coin> getCoins(){
        return counts.keySet().iterator();
    }

    public int getCountFor(Coin coin){
        return counts.containsKey(coin) ? counts.get(coin) : 0;
    }

    @Override
    public String toString() {
        return "Quarters:" + getCountFor(Coin.QUARTER) + " : " +
                "Dimes:" + getCountFor(Coin.DIME) + " : " +
                "Nikels:" + getCountFor(Coin.NICKEL) + " : " +
                "Pennies:" + getCountFor(Coin.PENNY);
    }


}
