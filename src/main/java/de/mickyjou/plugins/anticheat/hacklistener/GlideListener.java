package de.mickyjou.plugins.anticheat.hacklistener;

import de.mickyjou.plugins.anticheat.events.PlayerHackEvent;
import de.mickyjou.plugins.anticheat.utils.HackModuleType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mickyjou on 10.01.2017.
 */
public class GlideListener implements Listener {


    /**
     * this are the speed-values hacker clients are using for gliding
     */
    private Double[] hackFallSpeed = {-0.125D, -0.1256D};

    @EventHandler
    public void onPlayerGlide(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        /**
         * the speed the Player is moving up and down
         */
        double fallSpeed = e.getTo().getY() - e.getFrom().getY();

        /**
         * Gets the block under the player
         */
        Block b = e.getTo().clone().subtract(0, 1, 1).getBlock();

        /**
         * Checks if the fallspeed from the Player
         * is the same like a hack client is using
         */
        for (double speed : hackFallSpeed) {
            if (fallSpeed == speed && b.getType() == Material.AIR) {
                //Player is hacking
                Bukkit.getPluginManager().callEvent(new PlayerHackEvent(p, HackModuleType.GLIDE));


            }
        }
    }
}
