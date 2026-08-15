package net.aura_development.kubejs_keybinds.kubejs.event;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public class KeyBindExtendedEvents {
    
    public static final EventGroup GROUP = EventGroup.of("KeyBindExtendedEvents");
    
    public static final EventHandler MODIFICATION = GROUP.client("modification", () -> KeyBindModificationEvent.class);
    public static final EventHandler CATEGORIES = GROUP.client("categories", () -> KeyBindCategoriesEvent.class);
}
