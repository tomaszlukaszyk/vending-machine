package com.codecool;

import java.util.*;

public class Bank {

    private final Map<Coin, Float> validCoins;

    private Map<Coin, Integer> availableCoins = new HashMap<>();

    public Bank() {
        validCoins = new HashMap<>();
        validCoins.put(Coin.NICKEL, 0.05f);
        validCoins.put(Coin.DIME, 0.1f);
        validCoins.put(Coin.QUARTER, 0.25f);

        validCoins.keySet().forEach(coin -> availableCoins.put(coin, 0));
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

    public List<Coin> makeChange(float amount) {
        List<Map.Entry<Coin, Float>> denominations = new DenominationsSorter().getSortedDenominations(validCoins);
        List<Coin> change = new ArrayList<>();

        for (Map.Entry<Coin, Float> denomination: denominations) {

            int count = (int)(amount / denomination.getValue());
            addToChange(change, denomination, count);
            amount = amount % denomination.getValue();
            amount = Math.round(amount * 100.0f) / 100.0f;
        }

        return change;
    }

    private class DenominationsSorter {

        List<Map.Entry<Coin, Float>> getSortedDenominations(Map<Coin, Float> map) {

            List<Map.Entry<Coin, Float>> sortedEntries = new ArrayList<>(map.entrySet());
            sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

            return sortedEntries;
        }
    }

    private void addToChange(List<Coin> change, Map.Entry<Coin, Float> denomination, int count) {

        for (int i = 0; i < count; i++) {
            change.add(denomination.getKey());
        }
    }
}
