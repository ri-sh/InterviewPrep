package expense.splits;

import user.User;

public class EqualSplit extends Split{
    public EqualSplit(User user, double amount) {
        super(user);
        this.amount  = amount;
    }
}
