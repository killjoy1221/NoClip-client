package mnm.mods.noclip.client.mixin;

import mnm.mods.noclip.client.LiteModNoClip;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    @Redirect(
            method = "renderWorldPass(IFJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/entity/EntityPlayerSP;isSpectator()Z"))
    private boolean fixSpectator(EntityPlayerSP player) {
        // fixes the world being culled while noclipping underground
        return player.isSpectator() || LiteModNoClip.instance().isNoclipping();
    }
}
