package de.mickyjou.plugins.anticheat.hacklistener;

import de.mickyjou.plugins.anticheat.events.PlayerHackEvent;
import de.mickyjou.plugins.anticheat.utils.HackModuleType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mickyjou on 12.01.2017.
 */
public class SpeedListener implements Listener {

    public Map<Player, List<Double>> average = new HashMap<>();

    @EventHandler
    public void onPlayerSpeed(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location to = e.getTo();
        Location from = e.getFrom();
        Vector vector = to.toVector().setY(0);
        double distance = vector.distance(from.toVector().setY(0));

        /**
         * If the Player is allowed to fly, the spead values could be over the
         * maximum of the reachable values without Flying.
         */
        if (p.getGameMode() != GameMode.SURVIVAL || p.getAllowFlight() == true) return;

        /**
         * If the Player is riding on a Vehicle, his speed values could be over the
         * maximum of the reachable values without Vehicles.
         */
        if (p.isInsideVehicle() == true) return;


        /**
         * If the Player is just moving his Cursor on his screen, he is activating the PlayerMoveEvent, so the value is 0.
         * But the value 0 don't shows the speed of the Player moving at x/z direction which is indeed to
         * detect the Speed-hack.
         */
        if (distance != 0.0D) addSpeedValue(p, distance);


        /**
         * The average speed of a non-cheating Player can't be over 0.50.
         * So if the value is above 0.50, the Player is using a SpeedHack.
         */
        if (getAverageSpeed(p) >= 0.50) {
            //Player is hacking
            Bukkit.getPluginManager().callEvent(new PlayerHackEvent(p, HackModuleType.SPEED));
        }

    }

    /**
     * Adds the double to the list of the Player's speed-values
     *
     * @param p
     * @param value
     */

    public void addSpeedValue(Player p, double value) {
        List<Double> speeds = getPlayerSpeedValues(p);

        if (speeds.size() == 50) {
            List<Double> newSpeeds = new ArrayList<>();
            for (int i = 40; i < 50; i++) {
                newSpeeds.add(speeds.get(i));
            }
            average.put(p, newSpeeds);
        } else {
            speeds.add(value);
            average.put(p, speeds);
        }
    }

    /**
     * Returns the List of the Player's speed-values
     *
     * @param player
     * @return List<Double>
     */

    private List<Double> getPlayerSpeedValues(Player player) {
        return average.get(player) != null ? average.get(player) : new ArrayList<Double>();
    }

    /**
     * Returns the average speed from the Values of @getPlayerSpeedValues.
     *
     * @param p
     * @return double
     */

    private double getAverageSpeed(Player p) {
        double total = 0;

        List<Double> speeds = getPlayerSpeedValues(p);
        int size = speeds.size();
        for (Double values : speeds) {
            total += values;
        }
        total = total / size;
        return total;
    }
}
