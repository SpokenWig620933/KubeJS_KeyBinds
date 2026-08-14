package net.aura_development.kubejs_keybinds.kubejs.event;

import dev.latvian.mods.kubejs.client.ClientKubeEvent;
import net.aura_development.kubejs_keybinds.event.ClientEvents;
import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KeyBindCategoriesEvent implements ClientKubeEvent {
    
    public void setDisplayIndex(@NotNull String name, int index) {
        ClientEvents.CATEGORY_SORT_ORDER.remove(name);
        ClientEvents.CATEGORY_SORT_ORDER.add(index, name);
    }
    
    public void removeCategory(@NotNull String name) {
        for(KeyMapping keyMapping : KeyMapping.ALL.values().stream().filter(keyMapping -> keyMapping.getCategory().equals(name)).toList()) {
            ClientEvents.removeKey(keyMapping);
        }
    }
    
    @NotNull
    public List<String> getCategoriesInOrder() {
        return new ArrayList<>(ClientEvents.CATEGORY_SORT_ORDER);
    }
}
