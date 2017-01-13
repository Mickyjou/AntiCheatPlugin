package de.mickyjou.plugins.anticheat;

import de.craften.plugins.playerdatastore.api.PlayerDataStore;
import de.craften.plugins.playerdatastore.api.PlayerDataStoreService;
import de.mickyjou.plugins.anticheat.hacklistener.*;
import de.mickyjou.plugins.anticheat.listener.PlayerHackListener;
import de.mickyjou.plugins.anticheat.utils.HackModuleType;
import de.mickyjou.plugins.anticheat.utils.PlayerHackCounter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Mickyjou on 10.01.2017.
 */
public class AntiCheatPlugin extends JavaPlugin {

    public PlayerDataStoreService pds;

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
       // registerServices();

        super.onEnable();
    }

    @Override
    public void onDisable() {


        super.onDisable();
    }

    @Override
    public void onLoad() {


        super.onLoad();
    }
    /**
     * register all de.mickyjou.plugins.anticheat.commmands
     */
    public void registerCommands() {

    }

    /**
     * registers all de.mickyjou.plugins.anticheat.hacklistener and
     * de.mickyjou.plugins.anticheat.listener
     */

    public void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new GlideListener(), this);
        pm.registerEvents(new NoFallListener(), this);
        pm.registerEvents(new FlightListener(), this);
        pm.registerEvents(new NoSlowdownListener(), this);
        pm.registerEvents(new SpeedListener(), this);
        pm.registerEvents(new WaterWalkListener(), this);
        pm.registerEvents(new KillAuraListener(), this);

        pm.registerEvents(new PlayerHackListener(), this);
    }

    /**
     * registers all services the plugin is needing
     */
    public void registerServices() {
        pds = Bukkit.getServer().getServicesManager()
                .getRegistration(PlayerDataStoreService.class).getProvider();
    }

}
