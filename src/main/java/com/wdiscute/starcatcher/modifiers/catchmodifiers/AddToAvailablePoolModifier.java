package com.wdiscute.starcatcher.modifiers.catchmodifiers;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddToAvailablePoolModifier extends AbstractCatchModifier
{
    FishProperties fp;
    ResourceLocation rl;
    int count;

    public static final MapCodec<AddToAvailablePoolModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    FishProperties.CODEC.optionalFieldOf("fish_properties", FishProperties.empty()).forGetter(o -> o.fp),
                    ResourceLocation.CODEC.optionalFieldOf("fish_properties_location", Starcatcher.MISSINGNO).forGetter(o -> o.rl),
                    Codec.INT.fieldOf("quantity_to_add").forGetter(o -> o.count),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, AddToAvailablePoolModifier::new));

    public AddToAvailablePoolModifier(FishProperties fp, ResourceLocation rl, int count, String translationOverride)
    {
        super(translationOverride);
        this.fp = fp;
        this.rl = rl;
        this.count = count;
    }

    @Override
    public List<FishProperties> modifyAvailablePool(List<FishProperties> available)
    {
        if (!rl.equals(Starcatcher.MISSINGNO))
        {
            Registry<FishProperties> registry = instance.level().registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY_KEY);
            Optional<FishProperties> optional = registry.getOptional(rl);

            if (optional.isPresent())
                return new ArrayList<>(available)
                {{
                    for (int i = 0; i < count; i++)
                    {
                        add(optional.get());
                    }
                }};
            else
                LogUtils.getLogger().warn("ResourceLocation {} is not registered and was used in add_to_available_pool catch modifier. Ignoring.", rl);
        }
        else
        {
            if (!fp.equals(FishProperties.empty()))
                return new ArrayList<>(available)
                {{
                    for (int i = 0; i < count; i++)
                    {
                        add(fp);
                    }
                }};
            else
                LogUtils.getLogger().warn("FishProperties {} is equal to default and was used in add_to_available_pool catch modifier. Ignoring.", fp);
        }

        return super.modifyAvailablePool(available);
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("add_to_available_pool");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
