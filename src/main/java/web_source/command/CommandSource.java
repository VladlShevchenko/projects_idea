package web_source.command;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;


public class CommandSource {

        private static final Logger log = Logger.getLogger(CommandSource.class);

        private static Map<String, Command> commands = new TreeMap<String, Command>();

        static {
            // common commands
            commands.put("login", new LoginCommand());
            commands.put("publication", new PublicationCommand());
            commands.put("signIn", new SignInCommand());
            commands.put("publicationView", new PublicationViewCommand());
            commands.put("search", new SearchCommand());
            commands.put("cart", new CartCommand());
            commands.put("deleteItem", new DeleteItemFromCartCommand());
            commands.put("account", new AccountCommand());
            commands.put("logout", new LogoutCommand());
            commands.put("buy", new BuyCommand());
            commands.put("deleteSubscribe", new DeleteSubscribeFromAccount());
            commands.put("viewAccounts", new ViewAccountsCommand());
            commands.put("blockUser", new BlockCommand());
            commands.put("unblockUser", new UnblockCommand());
            commands.put("sortByTopic", new SortByTopicCommand());
            commands.put("sortByParam", new SelectByParamCommand());


            log.debug("Command container was successfully initialized");
            log.trace("Number of commands --> " + commands.size());
        }

        /**
         * Returns command object with the given name.
         *
         * @param commandName
         *            Name of the command.
         * @return Command object.
         */
        public static Command get(String commandName) {
            if (commandName == null || !commands.containsKey(commandName)) {
                log.trace("Command not found, name --> " + commandName);
                return commands.get("noCommand");
            }

            return commands.get(commandName);
        }

    }


