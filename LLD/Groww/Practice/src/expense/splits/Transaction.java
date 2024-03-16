package expense.splits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import user.User;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    public User paidBy;
    public User paidTo;
    Double amount;
}
