package net.aura_development.kubejs_keybinds.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.aura_development.kubejs_keybinds.KubeJSKeyBinds;
import net.aura_development.kubejs_keybinds.kubejs.event.KeyBindCategoriesEvent;
import net.aura_development.kubejs_keybinds.kubejs.event.KeybindExtendedEvents;
import net.aura_development.kubejs_keybinds.kubejs.event.KeybindModificationEvent;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = KubeJSKeyBinds.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    public static final List<KeyMapping> HIDE_KEYBINDS = new ArrayList<>();
    public static final List<String> CATEGORY_SORT_ORDER = new ArrayList<>();
    
    public static void removeKey(@NotNull KeyMapping keyMapping) {
        keyMapping.defaultKey = InputConstants.UNKNOWN;
        keyMapping.keyModifierDefault = KeyModifier.NONE;
        keyMapping.setKeyModifierAndCode(KeyModifier.NONE, InputConstants.UNKNOWN);
        
        HIDE_KEYBINDS.add(keyMapping);
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLoadComplete(@NotNull FMLLoadCompleteEvent event) {
        KeybindExtendedEvents.MODIFICATION.post(new KeybindModificationEvent());
        
        List<KeyMapping> keyMappings = new ArrayList<>();
        
        if(!HIDE_KEYBINDS.isEmpty()) {
            for(KeyMapping keyMapping : Minecraft.getInstance().options.keyMappings) {
                if(!HIDE_KEYBINDS.contains(keyMapping)) {
                    keyMappings.add(keyMapping);
                }
            }
            
            Minecraft.getInstance().options.keyMappings = keyMappings.toArray(new KeyMapping[0]);
        }
        
        List<Map.Entry<String, Integer>> categorySortOrder = new ArrayList<>(KeyMapping.CATEGORY_SORT_ORDER.entrySet());
        categorySortOrder.sort(Map.Entry.comparingByValue());
        
        for(Map.Entry<String, Integer> category : categorySortOrder) {
            CATEGORY_SORT_ORDER.add(category.getKey());
        }
        
        KeybindExtendedEvents.CATEGORIES.post(new KeyBindCategoriesEvent());
        
        KeyMapping.CATEGORY_SORT_ORDER.clear();
        
        for(int i = 0; i < CATEGORY_SORT_ORDER.size(); i++) {
            KeyMapping.CATEGORY_SORT_ORDER.put(CATEGORY_SORT_ORDER.get(i), i + 1);
        }
        
        if(!Minecraft.getInstance().options.getFile().exists()) {
            for(KeyMapping keyMapping : KeyMapping.ALL.values()) {
                keyMapping.setKeyModifierAndCode(keyMapping.getDefaultKeyModifier(), keyMapping.getDefaultKey());
            }
        }
    }
}