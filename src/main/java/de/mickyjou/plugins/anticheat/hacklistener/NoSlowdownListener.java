package de.mickyjou.plugins.anticheat.hacklistener;

import de.mickyjou.plugins.anticheat.events.PlayerHackEvent;
import de.mickyjou.plugins.anticheat.utils.HackModuleType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mickyjou on 10.01.2017.
 */
public class NoSlowdownListener implements Listener {
    public Set<Player> slow = new HashSet<>();

    @EventHandler
    public void onPlayerNoSlowdown(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (!p.isOnGround() || !slow.contains(p)) {
            return;
        }
        Location from = e.getFrom();
        Location to = e.getTo();


        Vector vector = to.toVector();
        double distance = vector.distance(from.toVector());

        if (to.getY() != from.getY()) return;

        /**
         * If the player isn't in survival mode,
         * he is probably able to be faster while spanning the bow
         */
        if (p.getGameMode() == GameMode.SURVIVAL) return;

        /**
         * 0.15D is the maximal speed a player can walk while spanning the bow
         */
        if (distance > 0.15D) {
            if (slow.contains(p)) {

                //player is hacking
                Bukkit.getPluginManager().callEvent(new PlayerHackEvent(p, HackModuleType.NOSLOWDOWN));

                slow.remove(p);
            }
        }
    }


    /**
     * The listeners find out if the player is spanning a bow
     */


    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        if (!(e.getEntity().getType() == EntityType.PLAYER)) return;

        if (slow.contains((Player) e.getEntity())) {
            slow.remove((Player) e.getEntity());
        }
    }

    @EventHandler
    public void onSwitch(PlayerItemHeldEvent e) {
        if (slow.contains(e.getPlayer())) {
            slow.remove(e.getPlayer());
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            if (e.getItem() != null && e.getItem().getType() != Material.AIR) {
                if (e.getItem().getType() == Material.BOW && p.getInventory().contains(Material.ARROW)) {
                    slow.add(p);
                }
            }
        }
    }
}
