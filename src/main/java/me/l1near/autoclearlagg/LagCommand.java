package me.l1near.autoclearlagg;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;

import java.util.Locale;

public class LagCommand extends Command {

    public LagCommand() {
        super("lag");
        this.setAliases(new String[]{"clearlag", "cl", "clag"});
        this.setPermission("admin.clearlag");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length < 1) {
            commandSender.sendMessage(Loader.getInstance().getConfig().getString("Messages.Usage"));
            return false;
        }
        switch (strings[0].toLowerCase(Locale.ROOT)) {
            case "timer":
                commandSender.sendMessage(Loader.getInstance().getConfig().getString("Messages.Time-Left message").replace("%TIME%", Loader.getInstance().translateTimer(CountdownTask.timer)));
                break;
            case "clear":
                if (!this.testPermission(commandSender)) {
                    commandSender.sendMessage(Loader.getInstance().getConfig().getString("Messages.No-Permission message"));
                    return false;
                }
                for (Level level:
                        Loader.getInstance().getServer().getLevels().values()) {
                    for (Entity entity:
                            level.getEntities()) {
                        if (!(entity instanceof Player)) entity.kill();
                    }
                }
                Loader.getInstance().getServer().broadcastMessage(Loader.getInstance().getConfig().getString("Messages.Lag-Cleared"));
                break;
        }
        return false;
    }
}
