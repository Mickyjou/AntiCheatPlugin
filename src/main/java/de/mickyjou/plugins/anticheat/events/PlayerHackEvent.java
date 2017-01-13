package de.mickyjou.plugins.anticheat.events;

import de.mickyjou.plugins.anticheat.utils.HackModuleType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Mickyjou on 12.01.2017.
 */
public class PlayerHackEvent extends Event {

    Player player;
    HackModuleType hacktype;

    public static HandlerList handlers = new HandlerList();

    /**
     * This Event is called when a player is hacking
     *
     * @param p
     * @param hacktype
     */

    public PlayerHackEvent(Player p, HackModuleType hacktype) {

        this.player = p;
        this.hacktype = hacktype;
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }


    /**
     * Gets the Player who is hacking
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the Type of the hack
     *
     * @return HackModuleType
     */

    public HackModuleType getType() {
        return hacktype;
    }
}
