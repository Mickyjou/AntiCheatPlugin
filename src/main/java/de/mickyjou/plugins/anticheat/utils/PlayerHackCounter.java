package de.mickyjou.plugins.anticheat.utils;

import de.craften.plugins.playerdatastore.api.PlayerDataStore;
import de.craften.plugins.playerdatastore.api.PlayerDataStoreService;
import de.mickyjou.plugins.anticheat.AntiCheatPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Mickyjou on 10.01.2017.
 */
public class PlayerHackCounter {

    private UUID uuid;

    public PlayerHackCounter(Player player) {
        this.uuid = player.getUniqueId();
    }

    public PlayerHackCounter(OfflinePlayer player) {
        this.uuid = player.getUniqueId();
    }

    public Integer getHackCounter(HackModuleType hacktype) {
        String result = getPlayerStore().get(hacktype.toString());
        if (result != null) return Integer.valueOf(getPlayerStore().get(hacktype.toString()));
        return null;
    }

    public void addHackCounter(HackModuleType hacktype) {
        if (getHackCounter(hacktype) == null) {
            getPlayerStore().put(hacktype.toString(), "0");
            return;
        }
        getPlayerStore().put(hacktype.toString(), String.valueOf(getHackCounter(hacktype) + 1));
    }

    private PlayerDataStore getPlayerStore() {
        return AntiCheatPlugin.getPlugin(AntiCheatPlugin.class).pds.getStore(uuid);
    }
}
