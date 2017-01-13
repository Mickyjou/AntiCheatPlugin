package de.mickyjou.plugins.anticheat.hacklistener;

import de.mickyjou.plugins.anticheat.events.PlayerHackEvent;
import de.mickyjou.plugins.anticheat.utils.HackModuleType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

/**
 * Created by Mickyjou on 12.01.2017.
 */
public class WaterWalkListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location to = e.getTo();
        Location from = e.getFrom();

        Vector vec = to.toVector();
        double distance = vec.distance(from.toVector());

        /**
         * If the Player is allowed to fly, the spead values could be over the
         * maximum of the reachable values without Flying.
         */
        if (p.getAllowFlight() == true || p.getGameMode() != GameMode.SURVIVAL) return;

        /**
         * If the Player is riding on a Vehicle, his speed values could be over the
         * maximum of the reachable values without Vehicles.
         */
        if (p.isInsideVehicle() == true) return;

        /**
         * If the Player is inside of a liquid block, the listener would
         * think that he is using waterwalk
         */
        if (p.getLocation().getBlock().isLiquid() || p.getLocation().add(0, 1, 0).getBlock().isLiquid()) return;


        /**
         * If the Player is standing on a lily pad,
         *  The Plugin would that he is using waterwalk
         */

        if(p.getLocation().getBlock().getRelative(BlockFace.SELF).getType() == Material.WATER_LILY) return;


        if(to.getBlockY() != from.getBlockY() || to.getY() < from.getY()) return;


        /**
         * If the Block under the Player is liquid,
         * the plugin detects the hack of the Player
         */
        if (distance > 0.80D && distance < 0.90D) {
            if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).isLiquid()) {
                //Player is hacking
                Bukkit.getPluginManager().callEvent(new PlayerHackEvent(p, HackModuleType.WATERWALK));
            }
        }

    }
}
