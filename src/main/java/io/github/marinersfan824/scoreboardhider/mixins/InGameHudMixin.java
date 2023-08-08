package io.github.marinersfan824.scoreboardhider.mixins;

import io.github.marinersfan824.scoreboardhider.ScoreboardHider;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "renderScoreboardSidebar", at = @At("HEAD"), cancellable = true)
    private void cancelSidebar(DrawContext context, ScoreboardObjective objective, CallbackInfo ci) {
        if (ScoreboardHider.disableSidebar) {
            ci.cancel();
        }
    }
    @Inject(method = "setTitle", at = @At("HEAD"), cancellable = true)
    private void cancelTitle(Text title, CallbackInfo ci) {
        if (ScoreboardHider.disableTitle) {
            ci.cancel();
        }
    }
}
