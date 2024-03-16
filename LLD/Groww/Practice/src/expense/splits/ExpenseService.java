package expense.splits;

import user.User;

import java.util.List;
import java.util.UUID;

public class ExpenseService {
    public static Expense createExpense(ExpenseType expenseType, User paidBy, List<Split> splits, double amount) {
        switch (expenseType) {
            case EXACT:
                return new ExactExpense(UUID.randomUUID().toString(), paidBy, amount, splits);
            case PERCENTAGE:
                for (Split split : splits) {
                    PercentageSplit percentageSplit = (PercentageSplit)  split;
                    split.setAmount(amount * percentageSplit.getPercentage() / 100);
                }
                return new PercentageExpense(UUID.randomUUID().toString(), paidBy, amount, splits);
            case EQUAL:
                int totalSplits = splits.size();
                double equalSplitAmount = amount / totalSplits;
                for (Split split : splits) {
                    split.setAmount(equalSplitAmount);
                }

                splits.get(0).setAmount(equalSplitAmount + amount - totalSplits * equalSplitAmount);
                return  new ExactExpense(UUID.randomUUID().toString(), paidBy, amount, splits);
            default:
                return null;
        }
    }
}
