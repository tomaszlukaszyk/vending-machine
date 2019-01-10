package com.codecool;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private final Map<Coin, Float> validCoins;

    private Map<Coin, Integer> availableCoins = new HashMap<>();

    public Bank() {
        validCoins = new HashMap<>();
        validCoins.put(Coin.NICKEL, 0.05f);
        validCoins.put(Coin.DIME, 0.1f);
        validCoins.put(Coin.QUARTER, 0.25f);

        validCoins.keySet().forEach(coin -> {
            availableCoins.put(coin, 0);
        });
    }

    public boolean isCoinValid(Coin coin) {
        return validCoins.keySet().contains(coin);
    }

    public float getValueOfCoin(Coin coin) {
        return validCoins.get(coin);
    }

    public Map<Coin, Integer> getAvailableCoins() {
        return availableCoins;
    }

    public void stockUp(Coin coin, int amount) {

        if (!isCoinValid(coin)) {
            return;
        }

        availableCoins.put(coin, amount);
    }
}
