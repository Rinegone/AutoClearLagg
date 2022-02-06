package me.l1near.autoclearlagg;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;
import cn.nukkit.scheduler.Task;

import java.util.List;

public class CountdownTask extends Task {

    public static int timer;

    public CountdownTask() {
        timer = Loader.getInstance().getConfig().getInt("Timer");
        Loader.getInstance().getServer().broadcastMessage(Loader.getInstance().getConfig().getString("Messages.Broadcast").replace("%TIME%", Loader.getInstance().translateTimer(timer)));
    }

    @Override
    public void onRun(int i) {
        timer--;
        List<Integer> ints = Loader.getInstance().getConfig().getIntegerList("Broadcasting-time");
        if (ints.contains(timer)) Loader.getInstance().getServer().broadcastMessage(Loader.getInstance().getConfig().getString("Messages.Broadcast").replace("%TIME%", Loader.getInstance().translateTimer(timer)));
        if (timer <= 0) {
            for (Level level:
                 Loader.getInstance().getServer().getLevels().values()) {
                for (Entity entity:
                     level.getEntities()) {
                    if (entity instanceof Player) return;
                    entity.kill();
                }
            }
            timer = Loader.getInstance().getConfig().getInt("Timer");
            Loader.getInstance().getServer().broadcastMessage(Loader.getInstance().getConfig().getString("Messages.Lag-Cleared"));
            Loader.getInstance().getServer().broadcastMessage(Loader.getInstance().getConfig().getString("Messages.Broadcast").replace("%TIME%", Loader.getInstance().translateTimer(timer)));
        }
    }
}
