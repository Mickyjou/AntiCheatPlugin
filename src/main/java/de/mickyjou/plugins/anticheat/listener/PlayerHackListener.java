package de.mickyjou.plugins.anticheat.listener;

import de.mickyjou.plugins.anticheat.events.PlayerHackEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Mickyjou on 11.01.2017.
 */
public class PlayerHackListener implements Listener {
    @EventHandler
    public void onPlayerHack(PlayerHackEvent e){
        e.getPlayer().sendMessage(e.getType().getName());
    }
}
