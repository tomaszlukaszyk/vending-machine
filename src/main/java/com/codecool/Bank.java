package com.codecool;

import java.math.BigDecimal;
import java.util.*;

public class Bank {

    private final Map<Coin, BigDecimal> validCoins;

    private Map<Coin, Integer> availableCoins = new HashMap<>();

    public Bank() {
        validCoins = new HashMap<>();
        validCoins.put(Coin.NICKEL, new BigDecimal("0.05"));
        validCoins.put(Coin.DIME, new BigDecimal("0.10"));
        validCoins.put(Coin.QUARTER, new BigDecimal("0.25"));

        validCoins.keySet().forEach(coin -> availableCoins.put(coin, 0));
    }

    public boolean isCoinValid(Coin coin) {
        return validCoins.keySet().contains(coin);
    }

    public BigDecimal getValueOfCoin(Coin coin) {
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

    public List<Coin> makeChange(BigDecimal amount) {
        List<Coin> change = new ArrayList<>();
        Map<Coin, Integer> neededCoins = getNeededCoins(amount);

        neededCoins.keySet().forEach(coin -> {

            for (int i = 0; i < neededCoins.get(coin); i++) {
                change.add(coin);
            }
        });

        return change;
    }

    public Map<Coin, Integer> getNeededCoins(BigDecimal amount) {
        List<Map.Entry<Coin, BigDecimal>> denominations = getSortedDenominations();
        Map<Coin, Integer> neededCoins = new HashMap<>();

        for (Map.Entry<Coin, BigDecimal> denomination: denominations) {

            int count = amount.divide(denomination.getValue(), BigDecimal.ROUND_HALF_EVEN).intValue();
            int countOfUsed = addToNeeded(neededCoins, denomination.getKey(), count);
            amount = amount.subtract(denomination.getValue().multiply(new BigDecimal(countOfUsed)));
        }

        return neededCoins;
    }

    private List<Map.Entry<Coin, BigDecimal>> getSortedDenominations() {

        List<Map.Entry<Coin, BigDecimal>> sortedEntries = new ArrayList<>(validCoins.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        return sortedEntries;
    }

    private int addToNeeded(Map<Coin, Integer> neededCoins, Coin coin, int count) {
        int countOfUsed = 0;

        for (int i = 0; i < count; i++) {

            if (availableCoins.get(coin) == 0) {
                continue;
            }

            if (!neededCoins.containsKey(coin)) {

                neededCoins.put(coin, 1);
            } else {

                int amount = neededCoins.get(coin);
                neededCoins.put(coin, ++amount);
            }

            removeCoinFromAvailable(coin);
            countOfUsed++;
        }

        return countOfUsed;
    }

    private void removeCoinFromAvailable(Coin coin) {

        int amount = availableCoins.get(coin);
        availableCoins.put(coin, --amount);
    }

    public boolean canMakeChange(BigDecimal amount) {
        Map<Coin, Integer> neededCoins = getNeededCoins(amount);
        BigDecimal sum = new BigDecimal("0.00");

        for (Map.Entry<Coin, Integer> entry: neededCoins.entrySet()) {
            Coin coin = entry.getKey();
            sum = sum.add(validCoins.get(coin).multiply(new BigDecimal(entry.getValue())));
        }

        boolean result = sum.equals(amount);
        returnNeededToAvailable(neededCoins);

        return result;
    }

    private void returnNeededToAvailable(Map<Coin, Integer> neededCoins) {

        neededCoins.forEach((coin, amount) -> {
            int availableAmount = availableCoins.get(coin);
            availableCoins.put(coin, availableAmount + amount);
        });
    }
}
