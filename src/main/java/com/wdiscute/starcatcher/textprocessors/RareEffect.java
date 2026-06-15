package com.wdiscute.starcatcher.textprocessors;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class RareEffect
{
    public static Component process(String text)
    {
        MutableComponent component = Component.empty();

        double time = System.currentTimeMillis() * 0.0006f;

        for (int i = 0; i < text.length(); i++)
        {
            component.append(
                    Component.literal(String.valueOf(text.charAt(i)))
                            .withColor(getBlueColorForIndex(i, time))
            );
        }

        return component;
    }

    public static int getBlueColorForIndex(int seed, double time)
    {
        double wavelength = 50.0;

        double t = time + (seed / wavelength) * (2.0 * Math.PI);

        // Oscillates smoothly between 0 and 1
        double blend = (Math.sin(t) + 1.0) / 2.0;

        // Dark blue
        int r1 = 20;
        int g1 = 40;
        int b1 = 120;

        // Bright blue
        int r2 = 100;
        int g2 = 180;
        int b2 = 255;

        int r = (int)(r1 + (r2 - r1) * blend);
        int g = (int)(g1 + (g2 - g1) * blend);
        int b = (int)(b1 + (b2 - b1) * blend);

        return (255 << 24) | (r << 16) | (g << 8) | b;
    }
}
