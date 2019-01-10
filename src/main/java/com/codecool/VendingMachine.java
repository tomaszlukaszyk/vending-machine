package com.codecool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    private Bank bank;
    private ProductDispenser productDispenser;

    private List<Coin> acceptedCoins = new ArrayList<>();
    private List<Coin> coinReturn = new ArrayList<>();
    private float clientFunds = 0f;

    public VendingMachine(Bank bank, ProductDispenser productDispenser) {
        this.bank = bank;
        this.productDispenser = productDispenser;
    }

    public void acceptCoin(Coin coin) {
        if (bank.isCoinValid(coin)) {
            acceptedCoins.add(coin);
            clientFunds += bank.getValueOfCoin(coin);
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
