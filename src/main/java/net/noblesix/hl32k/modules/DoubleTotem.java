package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Module;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

public class DoubleTotem extends Module {
    public DoubleTotem() {
        super("DoubleTotem", Keyboard.KEY_NONE);
    }
	public static Minecraft mc = Minecraft.getMinecraft();
	@Override
    public void onUpdate() {
        if (DoubleTotem.mc.currentScreen != null && DoubleTotem.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        if (DoubleTotem.mc.player.inventory.getStackInSlot(0).getItem() == Items.TOTEM_OF_UNDYING) {
            return;
        }
        for (int i = 9; i < 35; ++i) {
            if (DoubleTotem.mc.player.inventory.getStackInSlot(i).getItem() == Items.TOTEM_OF_UNDYING) {
                DoubleTotem.mc.playerController.windowClick(DoubleTotem.mc.player.inventoryContainer.windowId, i, 0, ClickType.SWAP, (EntityPlayer)DoubleTotem.mc.player);
                break;
            }
        }
    }
}
