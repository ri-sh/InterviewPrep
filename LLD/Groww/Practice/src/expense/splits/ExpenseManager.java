package expense.splits;

import user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    List<Expense> expenses;
    Map<String, User> users;
    Map<String, Map<String, Double>> balancesMap;
    List<Transaction> transactions;

    public ExpenseManager(){
        this.expenses = new ArrayList<>();
        this.users = new HashMap<>();
        this.balancesMap = new HashMap<>();
        transactions = new ArrayList<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
        balancesMap.put(user.getId(), new HashMap<>());
    }

    public void addExpense(ExpenseType expenseType, User paidBy, double amount, List<Split> splits){
        Expense expense = ExpenseService.createExpense(expenseType, paidBy, splits, amount);
        expenses.add(expense);
        for (Split split : splits) {
            String paidToUserId = split.getUser().getId();
            addAmountInBalanceSheet(paidBy.getId(), paidToUserId, split.getAmount());
        }
    }

    private void addAmountInBalanceSheet(String paidBy, String paidToUserId, double amount) {
        Map<String, Double>  balances =  balancesMap.get(paidBy);
        if (!balances.containsKey(paidToUserId)) {
            balances.put(paidToUserId, 0.0);
        }
        balances.put(paidToUserId, balances.get(paidToUserId) + amount);

        balances = balancesMap.get(paidToUserId);
        if (!balances.containsKey(paidBy)) {
            balances.put(paidBy, 0.0);
        }
        balances.put(paidBy, balances.get(paidBy) - amount);
    }

    public void addTransaction(Transaction transaction) {
        addAmountInBalanceSheet(transaction.getPaidBy().getId(), transaction.getPaidTo().getId(), transaction.getAmount());
    }

    public void showBalanceForUserId(String userId){
        for (Map.Entry<String, Double> entry : balancesMap.get(userId).entrySet()) {
            if (entry.getValue() == 0)
                continue;
            printToOutput(userId, entry.getKey(), entry.getValue());
        }
    }

    public void showOverAllExpenses(){
        for (Map.Entry<String, Map<String, Double>> allBalances : balancesMap.entrySet()) {
            for (Map.Entry<String, Double> entry : allBalances.getValue().entrySet()) {
                if (entry.getValue() > 0.0) {
                    printToOutput(allBalances.getKey(), entry.getKey(),  entry.getValue());
                }
            }
        }
    }

    public void printToOutput(String user1, String user2, double amount) {
        String firstUserName = users.get(user1).getName();
        String secondUserName = users.get(user2).getName();

        if (amount < 0) {
            System.out.println(firstUserName + " owes " + secondUserName + " " +  amount);
        }else {
            System.out.println(secondUserName + " owes " + firstUserName + " " +  amount);
        }
    }
}

//
//Users
//        id, name, email, phone
//
//Expenses
//        id , paidyByUserId, amount, expenseType;
//        1     1             100
//
//splits
//id expenseId userId amount ;
//1 1                 25
//2 1                 25
//3 1                     25
//4 1                     25
//
//Transactions
//paidByUserId paidToUserId amount timestamp


