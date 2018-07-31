package com.coinmachine;

import com.coinmachine.counter.CoinCounter;
import com.coinmachine.counter.CoinCounterImpl;
import com.coinmachine.counter.CountedCoins;
import com.coinmachine.counter.NotEnoughChangeException;
import com.coinmachine.inventory.CoinInventory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by marcusclow on 7/30/18.
 */
public class CoinCounterTests {

    private CoinInventory coinInventory;
    private CoinCounter coinCounter;

    @Before
    public void setup(){
        System.out.println("setup");
        this.coinInventory = new CoinInventory();
        this.coinCounter = new CoinCounterImpl();
    }

    @Test
    public void fullInventoryJustEnough(){
        try {
            CountedCoins countedCoins = coinCounter.countCoins(coinInventory, 41);
            assertEquals(countedCoins.getCountFor(Coin.QUARTER), 100);
            assertEquals(countedCoins.getCountFor(Coin.DIME), 100);
            assertEquals(countedCoins.getCountFor(Coin.NICKEL), 100);
            assertEquals(countedCoins.getCountFor(Coin.PENNY), 100);
        }catch (NotEnoughChangeException ex){
            fail("Should have had enough change.");
        }
    }

    @Test
    public void fullInventoryNotEnough(){ //TODO Use expected exception annotation
        try {
            CountedCoins countedCoins = coinCounter.countCoins(coinInventory, 42);
            fail("Should not have had enough change.");
        }catch (NotEnoughChangeException ex){
        }
    }

    @Test
    public void fullInventoryShouldReturnSixteenQuartersForFourDollars(){
        try {
            CountedCoins countedCoins = coinCounter.countCoins(coinInventory, 3);
            assertEquals(countedCoins.getCountFor(Coin.QUARTER), 12);
            assertEquals(countedCoins.getCountFor(Coin.DIME), 0);
            assertEquals(countedCoins.getCountFor(Coin.NICKEL), 0);
            assertEquals(countedCoins.getCountFor(Coin.PENNY), 0);
        }catch (NotEnoughChangeException ex){
            fail("Should have had enough change.");
        }
    }

    @Test
    public void inventoryMinus99QuartersForFourDollars(){
        try {
            CountedCoins ninetyNineQuarters = new CountedCoins();
            ninetyNineQuarters.add(Coin.QUARTER, 99);
            coinInventory.subtractCountedCoinsFromInventory(ninetyNineQuarters);

            CountedCoins countedCoins = coinCounter.countCoins(coinInventory, 4);
            assertEquals(countedCoins.getCountFor(Coin.QUARTER), 1);
            assertEquals(countedCoins.getCountFor(Coin.DIME), 37);
            assertEquals(countedCoins.getCountFor(Coin.NICKEL), 1);
            assertEquals(countedCoins.getCountFor(Coin.PENNY), 0);

        }catch (NotEnoughChangeException ex){
            fail("Should have had enough change.");
        }
    }

    @Before
    public void teardown(){
        this.coinCounter = null;
        this.coinInventory = null;
    }

}
