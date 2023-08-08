package io.github.marinersfan824.scoreboardhider;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class ScoreboardHider implements ModInitializer, ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static boolean disableSidebar = true;
    public static boolean disableTitle = true;
    private static final KeyBinding disableSidebarKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Disable Sidebar",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_BACKSLASH,
            "Scoreboard Hider"
    ));
    private static final KeyBinding disableTitleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Disable Title",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_BRACKET,
            "Scoreboard Hider"
    ));
    @Override
    public void onInitialize() {
        LOGGER.info("ScoreboardHider mod initialized");
    }
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (disableSidebarKey.wasPressed()) {
                disableSidebar = !disableSidebar;
                client.player.sendMessage(Text.literal("Scoreboard " + (disableSidebar ? "disabled" : "enabled")), true);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (disableTitleKey.wasPressed()) {
                disableTitle = !disableTitle;
                client.player.sendMessage(Text.literal("Title text " + (disableTitle ? "disabled" : "enabled")), true);
            }
        });
        LOGGER.info("Scoreboard Hider keybinds initialized");
    }
}
