import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class BankAppTest {
    private static final String TEST_FILE = "test_users.dat";

    @BeforeEach
    void setup() {
        new File(TEST_FILE).delete();
    }

    @Test
    void testSaveAndLoadUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Mario", 1000, 500));
        Main.saveUsers(users);

        List<User> loadedUsers = Main.loadUsers();
        assertEquals(1, loadedUsers.size());
        assertEquals("Mario", loadedUsers.get(0).getName());
    }

    @Test
    void testCreateUser() {
        Scanner scanner = new Scanner("Luca\n1000\n200\n");
        User user = User.createUser(scanner);
        assertEquals("Luca", user.getName());
        assertEquals(1000, user.getBankAccount());
        assertEquals(200, user.getWallet());
    }

    @Test
    void testDepositMoney() {
        User user = new User("Marco", 500, 300);
        user.depositMoney(200);
        assertEquals(700, user.getBankAccount());
        assertEquals(100, user.getWallet());
    }

    @Test
    void testWithdrawMoney() {
        User user = new User("Giovanni", 600, 150);
        user.withdrawMoney(200);
        assertEquals(400, user.getBankAccount());
        assertEquals(350, user.getWallet());
    }

    @Test
    void testDepositWithInsufficientFunds() {
        User user = new User("Anna", 300, 50);
        user.depositMoney(100);
        assertEquals(300, user.getBankAccount());
        assertEquals(50, user.getWallet());
    }

    @Test
    void testWithdrawWithInsufficientFunds() {
        User user = new User("Sara", 100, 200);
        user.withdrawMoney(150);
        assertEquals(100, user.getBankAccount());
        assertEquals(200, user.getWallet());
    }
}
