package de.mickyjou.plugins.anticheat.hacklistener;

import de.mickyjou.plugins.anticheat.events.PlayerHackEvent;
import de.mickyjou.plugins.anticheat.utils.HackModuleType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

/**
 * Created by Mickyjou on 10.01.2017.
 */
public class NoFallListener implements Listener {

    @EventHandler
    public void onPlayerNoFall(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();

        Vector vec = to.toVector();
        double distance = vec.distance(from.toVector());


        /**
         * Gets the block under the player
         */

        Block block = to.clone().subtract(0, 1, 0).getBlock();


        /**
         * If the distance is 0, the Player won't get
         * any fall damage
         */


        if (distance == 0) return;

        /**
         * If the Player isn't in survival mode,
         * he won't get any fall damage
         */

        if (p.getGameMode() != GameMode.SURVIVAL) return;

        /**
         * If the Player is inside a vehicle,
         * the player won't get any fall damage
         */

        if (p.isInsideVehicle()) return;

        /**
         * If the Block under the Player is liquid,
         * the Player won't get any fall damage
         */
        if (block.isLiquid()) return;


        /**
         * The Block under the Player can't be air,
         * because otherwise he wouldn't get any fall damage
         */
        if (block.getType() == Material.AIR) return;

        /**
         * If the distance the player is falling is under 0.79,
         * the player wouldn't get fall damage anyway
         */
        if (p.getFallDistance() == 0 && distance > 0.79 &&
                from.getBlockY() > to.getBlockY()) {
            //Player is hacking


            Bukkit.getPluginManager().callEvent(new PlayerHackEvent(p, HackModuleType.NOFALL));
        }
    }
}
