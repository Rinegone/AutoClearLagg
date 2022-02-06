package me.l1near.autoclearlagg;

import cn.nukkit.plugin.PluginBase;

public class Loader extends PluginBase {

    private static Loader instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        System.out.println("AutoClearLagg by L1near is now enabled!");
        Loader.getInstance().getServer().getScheduler().scheduleRepeatingTask(new CountdownTask(), 20);
        Loader.getInstance().getServer().getCommandMap().register("clear-lag", new LagCommand());
    }

    public static Loader getInstance() {
        return instance;
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
