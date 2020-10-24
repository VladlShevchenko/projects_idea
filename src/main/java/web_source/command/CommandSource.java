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
           /* commands.put("noCommand", new NoCommand());
            commands.put("viewSettings", new ViewSettingsCommand());
            commands.put("updateSettings", new UpdateSettingsCommand());

            // client commands
            commands.put("listMenu", new ListMenuCommand());

            // admin commands
            commands.put("listOrders", new ListOrdersCommand());*/

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


