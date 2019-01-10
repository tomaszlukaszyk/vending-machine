package com.codecool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachine {

    private final List<Coin> validCoins = Arrays.asList(Coin.NICKEL, Coin.DIME, Coin.QUARTER);

    private List<Coin> acceptedCoins = new ArrayList<>();
    private List<Coin> coinReturn = new ArrayList<>();

    public void acceptCoin(Coin coin) {
    }

    public List<Coin> getAcceptedCoins() {
        return acceptedCoins;
    }

    public List<Coin> getCoinReturn() {
        return coinReturn;
    }
}
