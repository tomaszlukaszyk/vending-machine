package com.codecool;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args){
        Bank bank = new Bank();
        bank.stockUp(Coin.QUARTER, 10);
        bank.stockUp(Coin.DIME, 10);
        bank.stockUp(Coin.NICKEL, 10);

        ProductDispenser productDispenser = new ProductDispenser();
        productDispenser.addProduct(new Product("cola", new BigDecimal("1.00")));
        productDispenser.addProduct(new Product("chips", new BigDecimal("0.50")));
        productDispenser.addProduct(new Product("candy", new BigDecimal("0.65")));

        productDispenser.getAvailableProducts().forEach(product -> {
            productDispenser.stockUp(product, 10);
        });

        VendingMachine vm = new VendingMachine(bank, productDispenser);

        boolean isRunning = true;
        boolean insufficientFunds = true;
        String message = "";
        List<Product> products = null;
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.println("q - quit, u - insert quarter, d - insert dime, n - insert nickel, p - insert penny, " +
                    "t - claim coins from return, r - put inserted coins in return\n");

            products = vm.getAvailableProducts();

            for (int i=0; i<products.size(); i++) {
                Product product = products.get(i);
                System.out.printf("%d. %s - %s\n", i + 1, product.getName(), product.getPrice().toString());
            }

            System.out.println("\nBalance: " + vm.getClientFunds().toString());
            System.out.println("\nCoin return: " + vm.getCoinReturn());
            System.out.println("\nBought products " + vm.getBoughtProducts());

            if (!message.isEmpty()) {
                System.out.println(message);
            }
            if (insufficientFunds) {
                System.out.print("\nINSERT COIN: ");
            }

            String input = scanner.nextLine();

            if (isInteger(input)) {
                int index = Integer.parseInt(input) - 1;

                if (index < products.size()) {
                    Product product = products.get(index);

                    if (!vm.canAffordProduct(product)) {

                        insufficientFunds = true;
                        message = "\nCannot afford product";
                    } else if (!vm.isProductInStock(product)) {

                        insufficientFunds = false;
                        message = "\nProduct not in stock";
                    } else if (!vm.canMakeChange(product)) {

                        insufficientFunds = true;
                        message = "\nExact change only";
                        vm.putCoinsFromAcceptedToReturn();
                    } else {

                        vm.buyProduct(product);
                    }
                }
            } else {

                switch (input) {
                    case "q":
                        isRunning = false;
                        break;
                    case "u":
                        vm.acceptCoin(Coin.QUARTER);
                        break;
                    case "d":
                        vm.acceptCoin(Coin.DIME);
                        break;
                    case "n":
                        vm.acceptCoin(Coin.NICKEL);
                        break;
                    case "p":
                        vm.acceptCoin(Coin.PENNY);
                        break;
                    case "t":
                        vm.takeCoinsFromReturn();
                        break;
                    case "r":
                        vm.putCoinsFromAcceptedToReturn();
                        break;
                }
            }
        }
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
