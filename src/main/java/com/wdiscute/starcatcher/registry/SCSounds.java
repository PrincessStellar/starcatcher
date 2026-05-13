package com.wdiscute.starcatcher.registry;

import com.wdiscute.starcatcher.Starcatcher;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface SCSounds
{
    DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Starcatcher.MOD_ID);


    Supplier<SoundEvent> KING_HEHEHA = registerSoundEvent("king_heheha");
    Supplier<SoundEvent> KING_CRY = registerSoundEvent("king_cry");
    Supplier<SoundEvent> KING_GRR = registerSoundEvent("king_grr");



    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = Starcatcher.rl(name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
