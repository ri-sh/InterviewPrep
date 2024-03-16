package expense.splits;

import lombok.Getter;
import user.User;

@Getter
public class PercentageSplit extends Split{
    double percentage;
    public PercentageSplit(User user, double percentage) {
        super(user);
        this.percentage = percentage;
    }
}
