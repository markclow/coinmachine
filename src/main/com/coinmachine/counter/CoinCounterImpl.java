package com.coinmachine.counter;

import com.coinmachine.Coin;
import com.coinmachine.inventory.CoinInventory;

import java.math.BigDecimal;

/**
 * Created by marcusclow on 7/30/18.
 */
public class CoinCounterImpl implements CoinCounter{

    @Override
    public CountedCoins countCoins(CoinInventory coinInventory, int amount) throws NotEnoughChangeException {
        CountedCoins countedCoins = new CountedCoins();

        BigDecimal balance = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);

        for (Coin coin : Coin.values()) {

            int inventoryForCoin = coinInventory.getInventoryFor(coin);

            int count = countCoin(coin, inventoryForCoin, balance);

            if (count > 0){
                countedCoins.add(coin, count);

                BigDecimal coinAmount = new BigDecimal(count).multiply(coin.getVal());
                balance = balance.subtract(coinAmount);
            }

        }

        if (balance.compareTo(BigDecimal.ZERO) > 0){
            throw new NotEnoughChangeException();
        }
        return countedCoins;
    }

    // Machine should return the least amount of coins possible;
    private int countCoin(Coin coin, int coinInventory, BigDecimal balance)
            throws NotEnoughChangeException{

        BigDecimal desiredQuantity = balance.divide(coin.getVal()).setScale(0, BigDecimal.ROUND_DOWN);
        return Math.min(desiredQuantity.intValue(), coinInventory);
    }
}
