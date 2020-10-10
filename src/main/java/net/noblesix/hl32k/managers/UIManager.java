package net.noblesix.hl32k.managers;

import net.noblesix.hl32k.HL32K;
import net.noblesix.hl32k.helpers.MultiColor;
import net.noblesix.hl32k.helpers.UIPosition;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.Random;

public class UIManager {
    private final Random random = new Random();
    private Minecraft minecraft;
    public UIPosition uiPosition;

    private MultiColor multiColor;

    public UIPosition.POSITION_ON_SCREEN position_on_screen = UIPosition.POSITION_ON_SCREEN.TOP_LEFT;
    public float scaleFactor = 1.5f;

    public UIManager() {
        minecraft = Minecraft.getMinecraft();
        uiPosition = new UIPosition();
    }

    public void Draw() {
		if(HL32K.allowHUD){
        multiColor = MultiColor.getRainbow();
        uiPosition.positionText(position_on_screen, "Hyper Lethal Auto 32K", 2, 2, scaleFactor);
        uiPosition.GLScale(scaleFactor);
        minecraft.fontRenderer.drawStringWithShadow("Hyper Lethal Auto 32K", uiPosition.x_position, uiPosition.y_position, multiColor.getRGB());
        uiPosition.GLScale(1 / scaleFactor);
		}
    }
}