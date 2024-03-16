package expense.splits;

import user.User;

import java.util.List;

public class EqualExpense extends Expense{
    public EqualExpense(String id, User paidBy, double amount, List<Split> splits) {
        super(id, paidBy, amount, splits);
    }

    @Override
    public boolean validate() {
        for (Split split : splits) {
            if ( !(split instanceof EqualSplit)) {
                return false;
            }
        }
        return  true;
    }
}
