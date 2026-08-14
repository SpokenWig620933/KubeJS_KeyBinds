package net.aura_development.kubejs_keybinds.kubejs.wrapper;

import dev.latvian.mods.kubejs.plugin.builtin.wrapper.GLFWInputWrapper;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class KeyBindWrapper {
    
    @Info("Gets whether Options already exists")
    public static boolean isOptionsSaved() {
        return Minecraft.getInstance().options.getFile().exists();
    }
    
    @NotNull
    @Info("Gets a list of all KeyMappings")
    public static List<KeyMapping> getKeyMappings() {
        return new ArrayList<>(KeyMapping.ALL.values());
    }
    
    @NotNull
    @Info("Gets a list of available KeyMappings")
    public static List<KeyMapping> getAvailableKeyMappings() {
        return new ArrayList<>(Arrays.asList(Minecraft.getInstance().options.keyMappings));
    }
    
    @Nullable
    @Info("Gets a KeyMapping from a name")
    public static KeyMapping getKeyMapping(@NotNull String name) {
        return KeyMapping.ALL.getOrDefault(name, null);
    }
    
    @NotNull
    @Info("Gets a list of the name of all KeyMappings")
    public static List<String> getKeyMappingNames() {
        return new ArrayList<>(KeyMapping.ALL.keySet());
    }
    
    @NotNull
    @Info("Gets a list of the category of all KeyMappings")
    public static List<String> getKeyMappingCategories() {
        List<String> categories = new ArrayList<>();
        
        for(KeyMapping keyMapping : KeyMapping.ALL.values()) {
            if(!categories.contains(keyMapping.getCategory())) {
                categories.add(keyMapping.getCategory());
            }
        }
        
        return categories;
    }
    
    @NotNull
    @Info("Get a map of Key Name to Key Code")
    public static Map<String, Integer> getKeys() {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        
        GLFWInputWrapper.MAP.get().forEach((key, keyCode) -> {
            if(key.startsWith("KEY") || key.startsWith("MOUSE")) {
                map.put(key, keyCode);
            }
        });
        
        return map;
    }
    
    @NotNull
    @Info("Gets a list of Key name")
    public static List<String> getKeyNames() {
        return new ArrayList<>(getKeys().keySet());
    }
    
    @NotNull
    @Info("Gets a list of Key modifier")
    public static List<String> getKeyModifiers() {
        return new ArrayList<>(Arrays.stream(KeyModifier.values()).map(Enum::name).toList());
    }
}