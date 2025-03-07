package ch.endte.syncmatica.mixin;

import ch.endte.syncmatica.Syncmatica;
import ch.endte.syncmatica.network.ChannelManager;
import ch.endte.syncmatica.network.SyncmaticaPayload;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CustomPayloadC2SPacket.class)
public class MixinCustomPayloadC2SPacket {
    @Inject(method = "readPayload", at = @At(value = "HEAD"), cancellable = true)
    private static void readPayload(Identifier id, PacketByteBuf buf, CallbackInfoReturnable<CustomPayload> cir) {
        if (id.getNamespace().equals(Syncmatica.MOD_ID) || id.equals(ChannelManager.MINECRAFT_REGISTER)) {
            cir.setReturnValue(new SyncmaticaPayload(id, buf));
        }
    }
}
