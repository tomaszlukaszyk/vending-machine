package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    void init() {
        vendingMachine = new VendingMachine();
    }

    @Test
    void testShouldAcceptOneValidCoin() {
        vendingMachine.acceptCoin(Coin.NICKEL);
        List<Coin> expected = Arrays.asList(Coin.NICKEL);
        List<Coin> actual = vendingMachine.getAcceptedCoins();

        assertIterableEquals(expected, actual);
    }

}