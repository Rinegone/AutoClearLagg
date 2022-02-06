package me.l1near.autoclearlagg;

import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;
import cn.nukkit.scheduler.Task;

import java.util.List;

public class CountdownTask extends Task {

    private int timer;

    public CountdownTask() {
        this.timer = Loader.getInstance().getConfig().getInt("Timer");
        Loader.getInstance().getServer().broadcastMessage(Loader.getInstance().getConfig().getString("Message").replace("%TIME%", translateTimer(timer)));
    }

    @Override
    public void onRun(int i) {
        timer--;
        List<Integer> ints = Loader.getInstance().getConfig().getIntegerList("Broadcasting-time");
        if (ints.contains(timer)) Loader.getInstance().getServer().broadcastMessage(Loader.getInstance().getConfig().getString("Message").replace("%TIME%", translateTimer(timer)));
        if (timer <= 0) {
            for (Level level:
                 Loader.getInstance().getServer().getLevels().values()) {
                for (Entity entity:
                     level.getEntities()) {
                    entity.kill();
                }
            }
        }
    }

    public String translateTimer(int seconds) {
        if (seconds <= 60) {
            if (seconds == 1) return seconds + " second";
            return seconds + " seconds";
        }else if (Math.round((double) seconds / 60) == 1) return Math.round((double) seconds / 60) + " minute";
        else if (Math.round((double) seconds / 60) > 1) return Math.round((double) seconds / 60) + " minutes";
        else if (Math.round((double) seconds / 360) == 1) return Math.round((double) seconds / 60) + " hour";
        else  return Math.round((double) seconds / 360) + " hours";
    }
}
