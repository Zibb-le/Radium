package com.pepedevs.corelib.utils.configuration;

import org.bukkit.configuration.ConfigurationSection;

public enum SaveActionType {

    /** Saves the entry normally. */
    NORMAL,

    /** Saves the entry only if it have not already set. */
    NOT_SET,

    /**
     * Saves the entry only if the value already set on the {@link ConfigurationSection} is not
     * equal this.
     */
    NOT_EQUAL;
}
