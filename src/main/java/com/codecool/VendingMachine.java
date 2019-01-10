package com.codecool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    private final Map<Coin, Float> validCoins;

    private List<Coin> acceptedCoins = new ArrayList<>();
    private List<Coin> coinReturn = new ArrayList<>();
    private float clientFunds = 0f;

    public VendingMachine() {
        validCoins = new HashMap<>();
        validCoins.put(Coin.NICKEL, 0.05f);
        validCoins.put(Coin.DIME, 0.1f);
        validCoins.put(Coin.QUARTER, 0.25f);
    }

    public void acceptCoin(Coin coin) {
        if (validCoins.containsKey(coin)) {
            acceptedCoins.add(coin);
            clientFunds += validCoins.get(coin);
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
