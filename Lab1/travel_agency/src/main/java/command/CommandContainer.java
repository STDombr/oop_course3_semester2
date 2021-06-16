package command;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("/login", new LoginCommand());
        commands.put("/logout", new LogoutCommand());
        commands.put("/registration", new RegistrationCommand());
        commands.put("/list", new ListCommand());
        commands.put("/admin", new AdminCommand());
        commands.put("/admin/update", new AdminUpdateCommand());
        commands.put("/order", new OrderCommand());
        commands.put("/orderList", new OrderListCommand());
        commands.put("/order/submit", new OrderSubmitCommand());
        commands.put("/balance", new BalanceCommand());
        commands.put("/balance/topup", new TopUpCommand());
    }

    public static Command get(String commandName) {
        String name = Optional.of(commandName.replace("/travel_agency/", "/")).orElse("/exception");
        return commands.get(name);
    }
}
