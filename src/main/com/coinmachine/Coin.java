package com.coinmachine;

import java.math.BigDecimal;

/**
 * Created by marcusclow on 7/30/18.
 */
public enum Coin {

    QUARTER(0.25),
    DIME(0.1),
    NICKEL(0.05),
    PENNY(0.01);

    private final BigDecimal val;

    Coin(double val) {
        this.val = new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getVal() {
        return val;
    }
}
