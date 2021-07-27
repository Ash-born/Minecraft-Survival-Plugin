package fr.minecraft.survival.plugin.utils;

import org.bukkit.scheduler.BukkitRunnable;

public class BidTimer extends BukkitRunnable {
    // counter and time in seconds
    int counter = 0;
    int time;

    public BidTimer(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        if (counter < time) {
            counter++;
        } else {
            cancel();
        }

    }

    public int getTimeLeft() {
        return time - counter;
    }

}
