package com.coinmachine.counter;

/**
 * Created by marcusclow on 7/30/18.
 */
public class NotEnoughChangeException extends Exception {

    public NotEnoughChangeException(){
        super("There are not enough coins in inventory to change this amount.");
    }
}
