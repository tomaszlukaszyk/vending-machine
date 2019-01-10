package com.codecool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<Coin, Integer> coins = new HashMap<>();
    private List<Coin> availableCoins = Arrays.asList(Coin.NICKEL, Coin.DIME, Coin.QUARTER);

    public Bank() {
        availableCoins.forEach(coin -> {
            coins.put(coin, 0);
        });
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }

    public void stockUp(Coin coin, int amount) {

        if (!availableCoins.contains(coin)) {
            return;
        }

        coins.put(coin, amount);
    }
}
