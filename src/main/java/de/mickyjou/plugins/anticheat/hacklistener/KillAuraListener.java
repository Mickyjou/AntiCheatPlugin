package de.mickyjou.plugins.anticheat.hacklistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Mickyjou on 13.01.2017.
 */
public class KillAuraListener implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player) && !(e.getDamager() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        Player damager = (Player) e.getDamager();

    }
}
