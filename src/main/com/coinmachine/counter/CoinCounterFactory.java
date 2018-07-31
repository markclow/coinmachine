package com.coinmachine.counter;

/**
 * Created by marcusclow on 7/30/18.
 */
public class CoinCounterFactory {

    public static CoinCounter makeCoinCounter(){
        return new CoinCounterImpl();
    }
}
