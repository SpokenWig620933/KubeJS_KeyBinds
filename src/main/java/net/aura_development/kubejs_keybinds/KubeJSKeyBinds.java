package net.aura_development.kubejs_keybinds;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(value = KubeJSKeyBinds.MOD_ID, dist = Dist.CLIENT)
public class KubeJSKeyBinds {

    public static final String MOD_ID = "kubejs_keybinds";
    public static final Logger LOGGER = LogUtils.getLogger();

    //private void commonSetup(FMLCommonSetupEvent event) {}
}
