package expense.splits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import user.User;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public abstract  class Expense {
    public String id;
    User paidBy;
    double amount;
    List<Split> splits;
    ExpenseType expenseType;
    public abstract boolean validate();
}
