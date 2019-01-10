package com.codecool;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<Coin, Integer> coins = new HashMap<>();

    public Bank() {
        coins.put(Coin.NICKEL, 0);
        coins.put(Coin.DIME, 0);
        coins.put(Coin.QUARTER, 0);
    }

    public void stockUp(Coin coin) {

    }
}
