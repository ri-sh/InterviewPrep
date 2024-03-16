package expense.splits;

import user.User;

import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(String id, User paidBy, double amount, List<Split> splits) {
        super(id, paidBy, amount, splits);
    }

    @Override
    public boolean validate() {
        for (Split split : splits) {
            if ( !(split instanceof ExactSplit)) {
                return false;
            }
        }
        double amount = splits.get(0).getAmount();
        for(Split split : splits) {
            if ( (split.getAmount() - amount) > 0.001) {
                return false;
            }
        }
        return true;
    }
}
