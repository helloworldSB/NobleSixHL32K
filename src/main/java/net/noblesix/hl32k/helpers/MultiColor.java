package net.noblesix.hl32k.helpers;

import net.noblesix.hl32k.HL32K;

import java.awt.*;

public class MultiColor {
    public int red;
    public int green;
    public int blue;

    private int rgb;

    public MultiColor(Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();

        this.rgb = color.getRGB();
    }

    public static MultiColor getRainbow() {
        MultiColor the_rainbow = new MultiColor(HL32K.client.moduleManager.getModule("Rainbow").getState() ? Color.getHSBColor((float)(System.currentTimeMillis() % 7500L) / 7500f, 0.8f, 0.8f) : new Color(255, 255, 255));

        return the_rainbow;
    }

    public int getRGB() {
        return rgb;
    }
}
