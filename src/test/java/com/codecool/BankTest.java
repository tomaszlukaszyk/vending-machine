package com.codecool;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    void testMakeChangeReturnsCorrect() {
        bank.stockUp(Coin.NICKEL, 10);
        bank.stockUp(Coin.DIME, 10);
        bank.stockUp(Coin.QUARTER, 10);
        List<Coin> expected = Arrays.asList(Coin.QUARTER, Coin.QUARTER, Coin.DIME, Coin.NICKEL);

        List<Coin> actual = bank.makeChange(0.65f);

        assertIterableEquals(expected, actual);
    }
}