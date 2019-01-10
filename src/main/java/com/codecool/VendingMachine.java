package com.codecool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachine {

    private final List<Coin> validCoins = Arrays.asList(Coin.NICKEL, Coin.DIME, Coin.QUARTER);

    private List<Coin> acceptedCoins = new ArrayList<>();
    private List<Coin> coinReturn = new ArrayList<>();
    private float clientFunds = 0f;

    public void acceptCoin(Coin coin) {
        if (validCoins.contains(coin)) {
            acceptedCoins.add(coin);
        } else {
            coinReturn.add(coin);
        }
    }

    public List<Coin> getAcceptedCoins() {
        return acceptedCoins;
    }

    public List<Coin> getCoinReturn() {
        return coinReturn;
    }

    public float getClientFunds() {
        return clientFunds;
    }
}
