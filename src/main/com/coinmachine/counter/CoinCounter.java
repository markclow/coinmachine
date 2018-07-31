package com.coinmachine.counter;

import com.coinmachine.inventory.CoinInventory;

/**
 * Created by marcusclow on 7/30/18.
 */
public interface CoinCounter {

    // Machine should return the least amount of coins possible;
    CountedCoins countCoins(CoinInventory coinInventory, int amount) throws NotEnoughChangeException;
}
