package com.codecool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    private Bank bank = new Bank();

    @Test
    void testStockUpCoins() {
        int expected = 6;
        bank.stockUp(Coin.NICKEL, expected);

        int actual = bank.getAvailableCoins().get(Coin.NICKEL);

        assertEquals(expected, actual);
    }
}