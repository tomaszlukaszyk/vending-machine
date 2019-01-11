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
        List<Coin> change = new ArrayList<>();
        Map<Coin, Integer> neededCoins = getNeededCoins(amount);

        neededCoins.keySet().forEach(coin -> {

            for (int i = 0; i < neededCoins.get(coin); i++) {
                change.add(coin);
            }
        });

        removeNeededCoinsFromAvailable(neededCoins);

        return change;
    }

    public Map<Coin, Integer> getNeededCoins(float amount) {
        List<Map.Entry<Coin, Float>> denominations = new DenominationsSorter().getSortedDenominations(validCoins);
        Map<Coin, Integer> neededCoins = new HashMap<>();

        for (Map.Entry<Coin, Float> denomination: denominations) {

            int count = (int)(amount / denomination.getValue());
            addToNeeded(neededCoins, denomination.getKey(), count);
            amount = amount % denomination.getValue();
            amount = Math.round(amount * 100.0f) / 100.0f;
        }

        return neededCoins;
    }

    private class DenominationsSorter {

        List<Map.Entry<Coin, Float>> getSortedDenominations(Map<Coin, Float> map) {

            List<Map.Entry<Coin, Float>> sortedEntries = new ArrayList<>(map.entrySet());
            sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

            return sortedEntries;
        }
    }

    private void addToNeeded(Map<Coin, Integer> neededCoins, Coin coin, int count) {

        for (int i = 0; i < count; i++) {

            if (!neededCoins.containsKey(coin)) {

                neededCoins.put(coin, 1);
            } else {

                int amount = neededCoins.get(coin);
                neededCoins.put(coin, ++amount);
            }
        }
    }

    private void removeNeededCoinsFromAvailable(Map<Coin, Integer> neededCoins) {

        neededCoins.keySet().forEach(coin -> {

            int amount = availableCoins.get(coin);
            amount -= neededCoins.get(coin);
            availableCoins.put(coin, amount);
        });
    }
}
