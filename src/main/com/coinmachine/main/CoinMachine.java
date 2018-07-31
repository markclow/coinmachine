package com.coinmachine.main;

import com.coinmachine.counter.CoinCounter;
import com.coinmachine.counter.CoinCounterFactory;
import com.coinmachine.counter.CountedCoins;
import com.coinmachine.counter.NotEnoughChangeException;
import com.coinmachine.inventory.CoinInventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by marcusclow on 7/30/18.
 */
public class CoinMachine {

    public static final String PROMPT_MESSAGE = "Please enter an amount in Bills that can be (1,2,5,10,20,50,100) OR 'quit'.";
    private CoinInventory coinInventory;
    private CoinCounter coinCounter;

    public CoinMachine() {
        coinInventory = new CoinInventory();
        coinCounter = CoinCounterFactory.makeCoinCounter();
    }

    public void execute() {
        try {
            BufferedReader r = new java.io.BufferedReader(new InputStreamReader(System.in));
            System.out.println(PROMPT_MESSAGE);
            String userInput = r.readLine();
            while (!userInput.startsWith("quit")) {
                processUserInput(userInput);
                System.out.println("");
                System.out.println(PROMPT_MESSAGE);
                userInput = r.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Unexpected IO Exception.");
            ex.printStackTrace();
        }
    }

    private void processUserInput(String userInput) {

        echoUserInput(userInput);

        int amount = parseUserInput(userInput);
        if (amount == -1)
            return;

        CountedCoins countedCoins;
        try {
            countedCoins = coinCounter.countCoins(coinInventory, amount);

            echoCountedCoins(countedCoins);

            subtractCountedCoinsFromInventory(countedCoins);

            echoInventory();

        }catch(NotEnoughChangeException notEnough){
            System.out.println(notEnough);
        }

    }

    private void echoUserInput(String userInput){
        System.out.printf("\nUser input: %s \n", userInput);
    }

    private int parseUserInput(String userInput){
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException pe) {
            System.out.println("Not a valid integer. Cannot convert to coins.");
            return -1;
        }
    }

    private void echoCountedCoins(CountedCoins countedCoins){
        System.out.println("Converts into the following coins: " + countedCoins);
    }

    private void subtractCountedCoinsFromInventory(CountedCoins countedCoins){
        coinInventory.subtractCountedCoinsFromInventory(countedCoins);
    }

    private void echoInventory(){
        System.out.println("Coin inventory after adjustment: " + coinInventory);
    }

}
