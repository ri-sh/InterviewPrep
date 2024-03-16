package expense.splits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import user.User;

@Getter
@Setter
public abstract class Split {
    public User user;
    public double amount;
    Split(User user) {
        this.user = user;
    }

}
