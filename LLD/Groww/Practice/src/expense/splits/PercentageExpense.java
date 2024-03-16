package expense.splits;

import user.User;

import java.util.List;

public class PercentageExpense extends Expense{
    public PercentageExpense(String id, User paidBy, double amount, List<Split> splits) {
        super(id, paidBy, amount, splits);
    }

    @Override
    public boolean validate() {
        double sum = 0;
        for (Split split : splits) {
            if (!(split instanceof PercentageSplit)) {
                return false;
            }
            PercentageSplit percentageSplit = (PercentageSplit) split;
            sum += percentageSplit.getPercentage();
        }
        if (Math.abs(100 - sum) >= 0.001) {
            return  false;
        }
        return true;
    }
}
