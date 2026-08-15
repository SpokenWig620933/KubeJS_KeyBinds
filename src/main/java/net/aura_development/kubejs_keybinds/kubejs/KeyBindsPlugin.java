package net.aura_development.kubejs_keybinds.kubejs;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.aura_development.kubejs_keybinds.kubejs.event.KeyBindExtendedEvents;
import net.aura_development.kubejs_keybinds.kubejs.wrapper.KeyBindWrapper;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.jetbrains.annotations.NotNull;

public class KeyBindsPlugin implements KubeJSPlugin {
    
    @Override
    public void registerEvents(@NotNull EventGroupRegistry registry) {
        registry.register(KeyBindExtendedEvents.GROUP);
    }
    
    @Override
    public void registerBindings(@NotNull BindingRegistry bindings) {
        if(bindings.type().isClient()) {
            bindings.add("KeyBind", KeyBindWrapper.class);
            bindings.add("KeyConflictContext", KeyConflictContext.class);
        }
    }
}