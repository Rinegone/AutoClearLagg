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
    }

    public static Loader getInstance() {
        return instance;
    }
}
