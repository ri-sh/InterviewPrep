import expense.splits.*;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//adding an expense
// splitting by different means
// viewing the splits and balances

public class Main {
    public static void main(String[] args) throws InterruptedException {
        User user1 = new User("u1", "user1", "user1@gmail.com", "7999056485");
        User user2 = new User("u2", "user2", "user2@gmail.com", "7999056485");
        User user3 = new User("u3", "user3", "user3@gmail.com", "7999056485");

        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addUser(user1);
        expenseManager.addUser(user2);
        expenseManager.addUser(user3);

        List<Split> splits = new ArrayList<>();
        splits.add(new ExactSplit(user1, 100));
        splits.add(new ExactSplit(user2, 50));
        splits.add(new ExactSplit(user3, 150));

        expenseManager.addExpense(ExpenseType.EXACT, user1, 300.0, splits);

        expenseManager.showOverAllExpenses();

        System.out.println("Add settling Transaction");
        expenseManager.addTransaction(new Transaction(user2, user1, 50.0));
        expenseManager.showOverAllExpenses();


    }


}