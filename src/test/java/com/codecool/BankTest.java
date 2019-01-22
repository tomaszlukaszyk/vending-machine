package com.codecool;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

        List<Coin> actual = bank.makeChange(new BigDecimal("0.65"));

        assertTrue(expected.size() == actual.size() &&
                expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void testMakeChangeRemovesCoinsFromAvailableCoins() {
        bank.stockUp(Coin.NICKEL, 10);
        bank.stockUp(Coin.DIME, 10);
        bank.stockUp(Coin.QUARTER, 10);
        bank.makeChange(new BigDecimal("0.65"));

        assertEquals(9, bank.getAvailableCoins().get(Coin.NICKEL));
        assertEquals(9, bank.getAvailableCoins().get(Coin.DIME));
        assertEquals(8, bank.getAvailableCoins().get(Coin.QUARTER));
    }

    @Test
    void testCanMakeChangeReturnsTrueWhenEnoughCoinsProvided() {
        bank.stockUp(Coin.NICKEL, 10);
        bank.stockUp(Coin.DIME, 10);
        bank.stockUp(Coin.QUARTER, 10);

        assertTrue(bank.canMakeChange(new BigDecimal("0.65")));
        assertEquals(10, bank.getAvailableCoins().get(Coin.NICKEL));
        assertEquals(10, bank.getAvailableCoins().get(Coin.DIME));
        assertEquals(10, bank.getAvailableCoins().get(Coin.QUARTER));
    }

    @Test
    void testMakeChangeReturnsCorrectWhenHigherDenominationsNotAvailable() {
        bank.stockUp(Coin.NICKEL, 10);
        bank.stockUp(Coin.DIME, 10);
        List<Coin> expected = Arrays.asList(Coin.DIME, Coin.DIME, Coin.DIME, Coin.DIME, Coin.DIME, Coin.DIME, Coin.NICKEL);

        List<Coin> actual = bank.makeChange(new BigDecimal("0.65"));

        assertTrue(expected.size() == actual.size() &&
                expected.containsAll(actual) && actual.containsAll(expected));
    }
}