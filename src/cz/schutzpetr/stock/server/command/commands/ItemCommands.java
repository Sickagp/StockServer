package cz.schutzpetr.stock.server.command.commands;

import cz.schutzpetr.stock.core.connection.RequestResult;
import cz.schutzpetr.stock.core.expressions.WhereClause;
import cz.schutzpetr.stock.core.items.Item;
import cz.schutzpetr.stock.core.location.BaseLocation;
import cz.schutzpetr.stock.server.client.Client;
import cz.schutzpetr.stock.server.command.annotation.BaseCommand;
import cz.schutzpetr.stock.server.command.annotation.Command;
import cz.schutzpetr.stock.server.command.interfaces.CommandClass;
import cz.schutzpetr.stock.server.command.interfaces.CommandSender;
import cz.schutzpetr.stock.server.command.utils.CommandType;
import cz.schutzpetr.stock.server.data.DataManager;

import java.util.ArrayList;

/**
 * Created by Petr Schutz on 28.03.2017
 *
 * @author Petr Schutz
 * @version 1.0
 */
@BaseCommand(command = "item")
public class ItemCommands implements CommandClass {

    @Command(command = "item", aliases = "get", type = CommandType.CLIENT, min = 1, max = 1)
    public static void onGetCommand(CommandSender sender, String[] args, Object[] objects) {
        if (sender instanceof Client) {
            Client client = (Client) sender;

            if (objects == null || objects.length == 0)
                client.send(new RequestResult<>(DataManager.getItemData().getData()));
            else if (objects[0] instanceof WhereClause)
                client.send(new RequestResult<>(DataManager.getItemData().getFilteredData((WhereClause) objects[0])));
        }
    }

    @Command(command = "item", aliases = "create", type = CommandType.CLIENT, usage = "/item create %item%", min = 1, max = 1)
    public static void onCreateCommand(CommandSender sender, String[] args, Object[] objects) {
        if (sender instanceof Client) {
            Client client = (Client) sender;

            if (objects != null && objects[0] instanceof ArrayList) {
                //noinspection unchecked
                client.send(new RequestResult<>(DataManager.getItemData().insertData(((ArrayList<Item>) objects[0]))));
            }
        }
    }

    @Command(command = "item", aliases = "relocate", type = CommandType.CLIENT, usage = "/item relocate %item%", min = 1, max = 1)
    public static void onRelocateCommand(CommandSender sender, String[] args, Object[] objects) {
        if (sender instanceof Client) {
            Client client = (Client) sender;

            if (objects != null && objects[0] instanceof Item && objects[1] instanceof BaseLocation && objects[2] instanceof Integer) {
                //noinspection unchecked
                client.send(new RequestResult<>(DataManager.getItemData().relocate(((Item) objects[0]), ((BaseLocation) objects[1]), ((Integer) objects[2]))));
            }
        }
    }

    @Command(command = "item", aliases = "remove", type = CommandType.CLIENT, usage = "/location remove %location%", min = 1, max = 1)
    public static void onRemoveCommand(CommandSender sender, String[] args, Object[] objects) {
        if (sender instanceof Client) {
            Client client = (Client) sender;

            if (objects != null && objects[0] instanceof Item) {
                //noinspection unchecked
                client.send(new RequestResult<>(DataManager.getItemData().remove(((Item) objects[0]))));
            }
        }
    }

/*
    @Command(command = "item", aliases = "getall", type = CommandType.CLIENT, description = "", min = 1, max = 1)
    public static void getAll(CommandSender sender, String[] args, Object[] objects) {
        if (sender instanceof Client) {
            Client client = (Client) sender;

            //client.send(DatabaseManager.getInstance().getDatabase().getItemTable().getItems());
        }
    }

    @Command(command = "item", aliases = "get", type = CommandType.CLIENT, description = "/item get %card% %location%", min = 1, max = 1)
    public static void get(CommandSender sender, String[] args, Object[] objects) {
        if (sender instanceof Client) {
            Client client = (Client) sender;

            RequestResult<ArrayList<Item>> itemDatabaseResult;
/*
            if (objects.length == 2) {
                if (objects[0] instanceof SimpleStorageCard && objects[1] instanceof BaseLocation) {
                    ArrayList<Item> list = new ArrayList<>(1);
                    RequestResult<Item> item = DatabaseManager.getInstance().getDatabase().getItemTable().getItem((Integer) objects[0], (BaseLocation) objects[0]);
                    if (!item.isResult()) return;
                    list.add(item.getResult());
                    client.send(new RequestResult<>(item.isResult(), list));
                }
            } else {
                if (objects[0] instanceof BaseLocation) {
                    client.send(DatabaseManager.getInstance().getDatabase().getItemTable().getItems((BaseLocation) objects[0]));
                } else if (objects[0] instanceof Integer) {
                    client.send(DatabaseManager.getInstance().getDatabase().getItemTable().getItems((Integer) objects[0]));
                }
            }*/
     /*   }
    }

    @Command(command = "item", aliases = "getbysql", type = CommandType.CLIENT, description = "/item getbysql %sql%", min = 1, max = 1)
    public static void onGetBySQL(CommandSender sender, String[] args, Object[] objects) {
        if (sender instanceof Client) {
            Client client = (Client) sender;

            if (objects[0] instanceof String) {
                //todo: client.send(DatabaseManager.getInstance().getDatabase().getItemTable().ge((String) objects[0]));
            }
        }
    }

    @Command(command = "item", aliases = "create", type = CommandType.CLIENT, description = "", usage = "/item create %item%", min = 1, max = 1)
    public static void create(CommandSender sender, String[] args, Object[] objects) {
        if (sender instanceof Client) {//todo:
            Client client = (Client) sender;

            if (objects[0] instanceof Item) {
                Item item = (Item) objects[0];



               // client.send(DatabaseManager.getInstance().getDatabase().getItemTable().insertItem(item));
            }
        }
    }*/
}
