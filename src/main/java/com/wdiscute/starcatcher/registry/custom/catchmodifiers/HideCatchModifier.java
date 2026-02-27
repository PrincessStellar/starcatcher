package com.wdiscute.starcatcher.registry.custom.catchmodifiers;

import com.wdiscute.starcatcher.storage.FishProperties;

public class HideCatchModifier extends AbstractCatchModifier
{
    @Override
    public FishProperties overrideFpToClient(FishProperties fishProperties)
    {
        return fishProperties.withHideCatch();
    }
}
