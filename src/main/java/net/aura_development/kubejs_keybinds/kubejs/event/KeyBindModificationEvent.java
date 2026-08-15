package net.aura_development.kubejs_keybinds.kubejs.event;

import com.mojang.blaze3d.platform.InputConstants;
import dev.latvian.mods.kubejs.client.ClientKubeEvent;
import dev.latvian.mods.kubejs.plugin.builtin.wrapper.GLFWInputWrapper;
import dev.latvian.mods.kubejs.script.ConsoleJS;
import net.aura_development.kubejs_keybinds.event.ClientEvents;
import net.aura_development.kubejs_keybinds.kubejs.wrapper.KeyBindWrapper;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.IKeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;

public class KeyBindModificationEvent implements ClientKubeEvent {
    
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
            ClientEvents.removeKey(keyMapping);
        }  else {
            ConsoleJS.CLIENT.warn("KeyBind \"" + name + "\" not found!");
        }
    }
    
    public void resetKey(@NotNull String name) {
        KeyMapping keyMapping = KeyBindWrapper.getKeyMapping(name);
        
        if(keyMapping != null) {
            keyMapping.setKeyModifierAndCode(keyMapping.getDefaultKeyModifier(), keyMapping.getDefaultKey());
        } else {
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
            keyMapping.setKeyModifierAndCode(keyMapping.getDefaultKeyModifier(), keyMapping.getDefaultKey());
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
    
    @Nullable
    public IKeyConflictContext getKeyConflictContext(@NotNull String name) {
        KeyMapping keyMapping = KeyBindWrapper.getKeyMapping(name);
        
        if(keyMapping != null) {
            return keyMapping.getKeyConflictContext();
        } else {
            ConsoleJS.CLIENT.warn("KeyBind \"" + name + "\" not found!");
        }
        
        return null;
    }
    
    public void setKeyConflictContext(@NotNull String name, @NotNull IKeyConflictContext conflictContext) {
        KeyMapping keyMapping = KeyBindWrapper.getKeyMapping(name);
        
        if(keyMapping != null) {
            keyMapping.setKeyConflictContext(conflictContext);
        } else {
            ConsoleJS.CLIENT.warn("KeyBind \"" + name + "\" not found!");
        }
    }
    
    public KeyConflictContextBuilder getKeyConflictContextBuilder() {
        return new KeyConflictContextBuilder();
    }
    
    public static class KeyConflictContextBuilder {
        
        public Supplier<Boolean> isActive = () -> true;
        public Function<IKeyConflictContext, Boolean> conflicts = keyConflictContext -> true;
        
        public void setActive(@NotNull Supplier<Boolean> isActive) {
            this.isActive = isActive;
        }
        
        public void setConflicts(@NotNull Function<IKeyConflictContext, Boolean> conflicts) {
            this.conflicts = conflicts;
        }
        
        @NotNull
        public IKeyConflictContext build() {
            return new IKeyConflictContext() {
                
                @Override
                public boolean isActive() {
                    return isActive.get();
                }
                
                @Override
                public boolean conflicts(@NotNull IKeyConflictContext keyConflictContext) {
                    return conflicts.apply(keyConflictContext);
                }
            };
        }
    }
}
