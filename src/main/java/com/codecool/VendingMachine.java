package com.codecool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachine {

    private Bank bank;
    private ProductDispenser productDispenser;

    private List<Coin> acceptedCoins = new ArrayList<>();
    private List<Coin> coinReturn = new ArrayList<>();
    private BigDecimal clientFunds = new BigDecimal("0.00");
    private List<Product> boughtProducts = new ArrayList<>();

    public VendingMachine(Bank bank, ProductDispenser productDispenser) {
        this.bank = bank;
        this.productDispenser = productDispenser;
    }

    public void acceptCoin(Coin coin) {
        if (bank.isCoinValid(coin)) {
            acceptedCoins.add(coin);
            clientFunds = clientFunds.add(bank.getValueOfCoin(coin));
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

    public BigDecimal getClientFunds() {
        return clientFunds;
    }

    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public List<Product> getAvailableProducts() {
        List<Product> availableProducts = new ArrayList<>(productDispenser.getAvailableProducts());
        Collections.sort(availableProducts);
        return availableProducts;
    }

    public boolean canAffordProduct(Product product) {
        return clientFunds.floatValue() >= product.getPrice().floatValue();
    }

    public boolean isProductInStock(Product product) {
        return productDispenser.isProductInStock(product);
    }

    public boolean canMakeChange(Product product) {
        return bank.canMakeChange(clientFunds.subtract(product.getPrice()));
    }

    public void buyProduct(Product product) {
        productDispenser.dispenseProduct(product);
        boughtProducts.add(product);
        clientFunds = clientFunds.subtract(product.getPrice());
    }

    public void putCoinsFromAcceptedToReturn() {
        coinReturn.addAll(acceptedCoins);
        removeAcceptedCoinsFromFunds();
        acceptedCoins.clear();
    }

    private void removeAcceptedCoinsFromFunds() {
        acceptedCoins.forEach(coin -> {
            clientFunds = clientFunds.subtract(bank.getValueOfCoin(coin));
        });
    }

    public void takeCoinsFromReturn() {
        coinReturn.clear();
    }
}
