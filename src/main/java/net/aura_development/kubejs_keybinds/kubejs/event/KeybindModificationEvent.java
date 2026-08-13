package net.aura_development.kubejs_keybinds.kubejs.event;

import com.mojang.blaze3d.platform.InputConstants;
import dev.latvian.mods.kubejs.client.ClientKubeEvent;
import dev.latvian.mods.kubejs.plugin.builtin.wrapper.GLFWInputWrapper;
import dev.latvian.mods.kubejs.script.ConsoleJS;
import net.aura_development.kubejs_keybinds.event.ClientEvents;
import net.aura_development.kubejs_keybinds.kubejs.wrapper.KeyBindWrapper;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KeybindModificationEvent implements ClientKubeEvent {
    
    @Nullable
    public KeyMapping getKeyMapping(@NotNull String name) {
        KeyMapping keyMapping = KeyBindWrapper.getKeyMapping(name);
        
        if(keyMapping != null) {
            return keyMapping;
        } else {
            ConsoleJS.CLIENT.warn("KeyBind \"" + name + "\" not found!");
        }
        
        return null;
    }
    
    public void hide(@NotNull String name) {
        KeyMapping keyMapping = KeyBindWrapper.getKeyMapping(name);
        
        if(keyMapping != null) {
            ClientEvents.HIDE_KEYBINDS.add(keyMapping);
        } else {
            ConsoleJS.CLIENT.warn("KeyBind \"" + name + "\" not found!");
        }
    }
    
    public void remove(@NotNull String name) {
        KeyMapping keyMapping = KeyBindWrapper.getKeyMapping(name);
        
        if(keyMapping != null) {
            keyMapping.setKeyModifierAndCode(KeyModifier.NONE, InputConstants.UNKNOWN);
            
            ClientEvents.HIDE_KEYBINDS.add(keyMapping);
        }  else {
            ConsoleJS.CLIENT.warn("KeyBind \"" + name + "\" not found!");
        }
    }
    
    public void setCategory(@NotNull String name, @NotNull String category) {
        KeyMapping keyMapping = KeyBindWrapper.getKeyMapping(name);
        
        if(keyMapping != null) {
            keyMapping.category = category;
        } else {
            ConsoleJS.CLIENT.warn("KeyBind \"" + name + "\" not found!");
        }
    }
    
    public void setDefaultKey(@NotNull String name, @NotNull String key) {
        setDefaultKeyWithModifier(name, key, "NONE");
    }
    
    public void setDefaultKeyWithModifier(@NotNull String name, @NotNull String key, @NotNull String modifier) {
        KeyMapping keyMapping = KeyBindWrapper.getKeyMapping(name);
        InputConstants.Type inputType = key.startsWith("MOUSE") ? InputConstants.Type.MOUSE : InputConstants.Type.KEYSYM;
        
        if(keyMapping != null) {
            keyMapping.defaultKey = inputType.getOrCreate(GLFWInputWrapper.get(key.toUpperCase()));
            keyMapping.keyModifierDefault = KeyModifier.valueFromString(modifier);
        } else {
            ConsoleJS.CLIENT.warn("KeyBind \"" + name + "\" not found!");
        }
    }
    
    public void setKey(@NotNull String name, @NotNull String key) {
        setKeyWithModifier(name, key, "NONE");
    }
    
    public void setKeyWithModifier(@NotNull String name, @NotNull String key, @NotNull String modifier) {
        KeyMapping keyMapping = KeyBindWrapper.getKeyMapping(name);
        InputConstants.Type inputType = key.startsWith("MOUSE") ? InputConstants.Type.MOUSE : InputConstants.Type.KEYSYM;
        
        if(keyMapping != null) {
            keyMapping.setKeyModifierAndCode(KeyModifier.valueFromString(modifier), inputType.getOrCreate(GLFWInputWrapper.get(key.toUpperCase())));
        } else {
            ConsoleJS.CLIENT.warn("KeyBind \"" + name + "\" not found!");
        }
    }
}
