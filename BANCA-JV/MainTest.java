import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "password", 500.0, 200.0);
    }

    @Test
    void testAuthenticate() {
        assertTrue(user.authenticate("testUser", "password"));
        assertFalse(user.authenticate("testUser", "wrong"));
    }

    @Test
    void testDeposit() {
        double initialBank = user.getBankAccount();
        double initialWallet = user.getWallet();

        Scanner mockScanner = new Scanner("50");
        user.deposit(mockScanner);

        assertEquals(initialBank + 50, user.getBankAccount());
        assertEquals(initialWallet - 50, user.getWallet());
    }

    @Test
    void testWithdraw() {
        double initialBank = user.getBankAccount();
        double initialWallet = user.getWallet();

        Scanner mockScanner = new Scanner("100");
        user.withdraw(mockScanner);

        assertEquals(initialBank - 100, user.getBankAccount());
        assertEquals(initialWallet + 100, user.getWallet());
    }

    @Test
    void testCreateInvestment() {
        Scanner scanner = new Scanner("100\n12\n5");
        user.createInvestment(scanner);
        assertFalse(user.getInvestments().isEmpty());
    }

    @Test
    void testAdvanceMonth() {
        double initialWallet = user.getWallet();
        user.advanceMonth();
        assertEquals(initialWallet + 100, user.getWallet());
    }
    @Test
    void testCalculateReturn() {
        Investment inv = new Investment(1000, 12, 5);
        double result = inv.calculateReturn();
        assertTrue(result > 0);
    }
/*
    @Test
    void testAdvance() {
        Investment inv = new Investment(500, 6, 3);
        inv.advance();
        inv.advance();
        assertEquals(2, inv.monthsPassed);
    }*/
}