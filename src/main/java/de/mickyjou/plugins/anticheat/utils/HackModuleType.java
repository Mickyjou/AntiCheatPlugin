package de.mickyjou.plugins.anticheat.utils;

/**
 * Created by Mickyjou on 10.01.2017.
 */
public enum HackModuleType {
    GLIDE("GLIDE"),
    FLIGHT("FLIGHT"),
    KILLAURA("KILLAURA"),
    NOSLOWDOWN("NOSLOWDOWN"),
    NOFALL("NOFALL"),
    SPEED("SPEED"),
    WATERWALK("WATERWALK");

    private String name;

    /**
     * This is the list of all hack-types
     * the plugin is supporting to detect
     * @param name
     */

    private HackModuleType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

}
