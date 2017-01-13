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
 * Created by Mickyjou on 10.01.2017.
 */
public class FlightListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location to = e.getTo();
        Location from = e.getFrom();

        Vector vec = to.toVector();
        double distance = vec.distance(from.toVector());

        /**
         * If the Player is allowed to fly,
         * a flight-listener is useless :D
         */

        if (p.getAllowFlight() == true ||
                p.getGameMode() != GameMode.SURVIVAL ) return;

        /**
         * If the Player is riding on a Vehicle, his speed values could be over the
         * maximum of the reachable values without Vehicles.
         */
        if (p.isInsideVehicle() == true) return;


        /**
         * If the block under the player isn't air,
         * the player isn't flying.
         */

        if ( from.subtract(0,1,0).getBlock().getType() != Material.AIR ) {
            return;
        }


        /**
         * Hack-clients are setting the fall distance to 0.0F,
         * so it's easier to get if player is hacking
         */
        if ((p.getFallDistance() == 0.0F) &&
                (p.getLocation().getBlock().getRelative(BlockFace.UP).getType().equals(Material.AIR))) {
            /**
             * Hack-Clients are setting the speed above 0.60D,
             * so it's easier to get if player is hacking
             *
             * If the player is on ground, he can't fly.
             */
            if (distance > 0.60D) {
                if (p.isOnGround()) {
                    return;
                }
                //player is hacking
                Bukkit.getPluginManager().callEvent(new PlayerHackEvent(p, HackModuleType.FLIGHT));
            }

        }

    }
}
