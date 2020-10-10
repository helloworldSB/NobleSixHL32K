package net.noblesix.hl32k.hyperlethal32k;

import com.google.common.base.Predicate;

import org.lwjgl.input.Keyboard;
import net.noblesix.hl32k.helpers.MultiColor;
import net.noblesix.hl32k.managers.GUIManager;
import net.noblesix.hl32k.HL32K;
import net.minecraft.block.BlockAir;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static net.noblesix.hl32k.HL32K.mc;
public class Auto32kModuleWWE {
	
	public String colorname;

	public BlockPos placedHopperPos;//this isent needed.. i thought i would need it, but i dident and i dident remove it //actually you reference it a dozen times
	public boolean shouldKillaura;
	public boolean hasPlacedStuff;
	public EntityPlayer entityPlayer;
	public int cpsTick;//i guess ill do it this way, not with system time

	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event) {
		if(HL32K.a32kcore == 2){
		switch (HL32K.color) {
		case 0:
			HL32K.fontcolor = 0xd1ff0000;
			colorname = "0xd1ff0000";
			break;
		case 1:
			HL32K.fontcolor = 0xd1ffb6c1;
			colorname = "0xd1ffb6c1";
			break;
		case 2:
			HL32K.fontcolor = 0xd1ffc0cb;
			colorname = "0xd1ffc0cb";
			break;
		case 3:
			HL32K.fontcolor = 0xd1dc143c;
			colorname = "0xd1dc143c";
			break;
		case 5:
			HL32K.fontcolor = 0xd1fff0f5;
			colorname = "0xd1fff0f5";
			break;
		case 6:
			HL32K.fontcolor = 0xd1db7093;
			colorname = "0xd1db7093";
			break;
		case 7:
			HL32K.fontcolor = 0xd1ff69b4;
			colorname = "0xd1ff69b4";
			break;
		case 8:
			HL32K.fontcolor = 0xd1ff1493;
			colorname = "0xd1ff1493";
			break;
		case 9:
			HL32K.fontcolor = 0xd1c71585;
			colorname = "0xd1c71585";
			break;
		case 10:
			HL32K.fontcolor = 0xd1da70d6;
			colorname = "0xd1da70d6";
			break;
		case 11:
			HL32K.fontcolor = 0xd1d8bfd8;
			colorname = "0xd1d8bfd8";
			break;
		case 12:
			HL32K.fontcolor = 0xd1dda0dd;
			colorname = "0xd1dda0dd";
			break;
		case 13:
			HL32K.fontcolor = 0xd1ee82ee;
			colorname = "0xd1ee82ee";
			break;
		case 14:
			HL32K.fontcolor = 0xd1ff00ff;
			colorname = "0xd1ff00ff";
			break;
		case 15:
			HL32K.fontcolor = 0xd1800080;
			colorname = "0xd1800080";
			break;
		case 16:
			HL32K.fontcolor = 0xd1ba55d3;
			colorname = "0xd1ba55d3";
			break;
		case 17:
			HL32K.fontcolor = 0xd19400d3;
			colorname = "0xd19400d3";
			break;
		case 18:
			HL32K.fontcolor = 0xd19370db;
			colorname = "0xd19370db";
			break;
		case 19:
			HL32K.fontcolor = 0xd17b68ee;
			colorname = "0xd17b68ee";
			break;
		case 20:
			HL32K.fontcolor = 0xd16a5acd;
			colorname = "0xd16a5acd";
			break;
		case 21:
			HL32K.fontcolor = 0xd1483D8B;
			colorname = "0xd1483D8B";
			break;
		case 22:
			HL32K.fontcolor = 0xd1E6E6FA;
			colorname = "0xd1E6E6FA";
			break;
		case 23:
			HL32K.fontcolor = 0xd1F8F8FF;
			colorname = "0xd1F8F8FF";
			break;
		case 24:
			HL32K.fontcolor = 0xd10000FF;
			colorname = "0xd10000FF";
			break;
		case 25:
			HL32K.fontcolor = 0xd10000CD;
			colorname = "0xd10000CD";
			break;
		case 26:
			HL32K.fontcolor = 0xd1191970;
			colorname = "0xd1191970";
			break;
		default:
			HL32K.fontcolor = 0xd1ff0000;
			colorname = "0xd1ff0000";
		}
		if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
			return;
		}

		HL32K.mc.fontRenderer.drawStringWithShadow("WWE's Auto 32k " + (HL32K.wweis32kEnabled ? "Enabled" : "Disabled") + "[" + HL32K.cps + "]", 2, event.getResolution().getScaledHeight() - 10, HL32K.fontcolor);
		if (HL32K.wweis32kEnabled) {
			HL32K.mc.fontRenderer.drawStringWithShadow("Kill Aura [" + HL32K.isKillauraOptionEnabled + "]", 2, event.getResolution().getScaledHeight() - 20, HL32K.fontcolor);
		}
		}
	}
	@SubscribeEvent
	public void onKey(InputUpdateEvent event) {
		if(HL32K.a32kcore == 2){
		if(GUIManager.allowguimove){
		if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat) && HL32K.mc.world != null && HL32K.mc.player != null) {
			 if (Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode())) {
				 event.getMovementInput().moveForward = 1;
			 }
			 if (Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode())) {
				 event.getMovementInput().moveForward = -1;
			 }
			 if (Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode())) {
				 event.getMovementInput().moveStrafe = 1;
			 }
			 if (Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())) {
				 event.getMovementInput().moveStrafe = -1;
			 }
			 if (Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode())) {
				 if (mc.player.isInLava() || mc.player.isInWater()) {
						final EntityPlayerSP player = mc.player;
	                    player.motionY += 0.039000000804662704;
	                }
	                else if (mc.player.onGround) {
	                    mc.player.jump();
	                }
			 }
			 if (Keyboard.isKeyDown(200)) {
	               mc.player.rotationPitch -= 5;

	            }
	            if (Keyboard.isKeyDown(208)) {
	            	mc.player.rotationPitch += 5;
	            }
	            if (Keyboard.isKeyDown(205)) {
	            	mc.player.rotationYaw += 5;
	            }
	            if (Keyboard.isKeyDown(203)) {
	            	mc.player.rotationYaw -= 5;
	            }
	            if (mc.player.rotationPitch > 90) mc.player.rotationPitch = 90;
	            if (mc.player.rotationPitch < -90) mc.player.rotationPitch = -90;
	            

		}
		}
		}
	}

	@SubscribeEvent
	public void init(TickEvent.PlayerTickEvent event) {
		if(HL32K.a32kcore == 2){

		if (HL32K.auto32kKeybind.isPressed()) {
			HL32K.wweis32kEnabled = !HL32K.wweis32kEnabled;
			if (HL32K.wweis32kEnabled) {
				this.onEnable();
			}
		}

		if (HL32K.auto32kCpsIncrementKeybind.isPressed()) {
			HL32K.cps++;
			HL32K.saveInformation();
		}

		if (HL32K.auto32kCpsdecrementKeybind.isPressed()) {
			HL32K.cps--;
			HL32K.saveInformation();
		}

		if (HL32K.auto32kToggleKillauraKeybind.isPressed()) {
			HL32K.isKillauraOptionEnabled = !HL32K.isKillauraOptionEnabled;
			HL32K.saveInformation();
		}

		if (!HL32K.wweis32kEnabled) {
			return;
		}

		int hopperIndex = -1;
		int shulkerIndex = -1;
		int enchantedSwordIndex = -1;

		for (int i = 0; i < 9; i++) {
			ItemStack itemStack = HL32K.mc.player.inventory.mainInventory.get(i);
			if (itemStack.getItem().equals(Item.getItemFromBlock(Blocks.HOPPER))) {
				hopperIndex = i;
			}

			if (itemStack.getItem() instanceof ItemShulkerBox) {
				shulkerIndex = i;
			}
			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, itemStack) >= Short.MAX_VALUE) {
				enchantedSwordIndex = i;
			}
		}

		if (!this.hasPlacedStuff && (hopperIndex == -1 || shulkerIndex == -1)) {
			HL32K.wweis32kEnabled = false;
			return;
		}

		if (enchantedSwordIndex == -1 && !hasPlacedStuff) {

			double closestBlockPosDistance = 4;//maybe?
			BlockPos closestBlockPos = null;

			Predicate<Entity> predicate = new Predicate<Entity>() {
				public boolean apply(Entity entity) {
					return !(entity instanceof EntityItem);
				}
			};

			for (BlockPos blockPos : BlockPos.getAllInBox(new BlockPos(HL32K.mc.player.posX - 3, HL32K.mc.player.posY - 1, HL32K.mc.player.posZ - 3), new BlockPos(HL32K.mc.player.posX + 3, HL32K.mc.player.posY + 2, HL32K.mc.player.posZ + 3))) {
				if (HL32K.mc.player.getDistance(blockPos.getX(), blockPos.getY(), blockPos.getZ()) < closestBlockPosDistance && HL32K.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos.add(0, 1, 0)), predicate).isEmpty() && !(HL32K.mc.world.getBlockState(blockPos.down()).getBlock() instanceof BlockAir) && HL32K.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir && HL32K.mc.world.getBlockState(blockPos.up()).getBlock() instanceof BlockAir && HL32K.mc.world.getBlockState(blockPos.up().up()).getBlock() instanceof BlockAir) {
					closestBlockPosDistance = HL32K.mc.player.getDistance(blockPos.getX(), blockPos.getY(), blockPos.getZ());
					closestBlockPos = blockPos;
				}
			}

			if (closestBlockPos != null) {
				this.placeStuff(hopperIndex, shulkerIndex, closestBlockPos.down(), EnumFacing.UP, new Vec3d(closestBlockPos.getX(), closestBlockPos.getY(), closestBlockPos.getZ()));
			}

		}

		if (enchantedSwordIndex != -1) {
			//we have a 32k
			this.shouldKillaura = true;

			//wow this was a fricking pain to figure out
			if (HL32K.mc.player.inventory.currentItem != enchantedSwordIndex) {
				HL32K.mc.player.connection.sendPacket(new CPacketHeldItemChange(enchantedSwordIndex));
				HL32K.mc.player.inventory.currentItem = enchantedSwordIndex;
				HL32K.mc.playerController.updateController();
			}

		} else {
			this.shouldKillaura = false;
		}

		if (enchantedSwordIndex == -1 && HL32K.mc.player.openContainer != null && HL32K.mc.player.openContainer instanceof ContainerHopper && HL32K.mc.player.openContainer.inventorySlots != null && !HL32K.mc.player.openContainer.inventorySlots.isEmpty()) {
			//this is very weird.. but i dont have to get the hopperInventory from GuiHopper
			for (int i = 0; i < 5; i++) {
				if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, HL32K.mc.player.openContainer.inventorySlots.get(0).inventory.getStackInSlot(i)) >= Short.MAX_VALUE) {
					enchantedSwordIndex = i;
					break;
				}
			}

			if (enchantedSwordIndex == -1) {
				return;
			}

			if (HL32K.mc.player.inventory.mainInventory.get(HL32K.mc.player.inventory.currentItem).getItem() instanceof ItemAir) {
				for (int i = 0; i < 9; i++) {
					ItemStack itemStack = HL32K.mc.player.inventory.mainInventory.get(i);
					if (itemStack.getItem() instanceof ItemAir) {
						if (HL32K.mc.player.inventory.currentItem != i) {
							HL32K.mc.player.connection.sendPacket(new CPacketHeldItemChange(i));
							HL32K.mc.player.inventory.currentItem = i;
							HL32K.mc.playerController.updateController();
						}
						break;
					}
				}
			}

			HL32K.mc.playerController.windowClick(HL32K.mc.player.openContainer.windowId, enchantedSwordIndex, HL32K.mc.player.inventory.currentItem, ClickType.SWAP, HL32K.mc.player);

		}

		if (this.shouldKillaura && HL32K.isKillauraOptionEnabled) {

			double closestEntityDistance = 8;//range

			for (Entity entity : HL32K.mc.world.loadedEntityList) {
				if (!(entity instanceof EntityPlayer) || entity instanceof EntityPlayerSP || entity.isDead) {
					continue;
				}

				if (HL32K.mc.player.getDistance(entity) < closestEntityDistance && ((EntityPlayer) entity).getHealth() > 0/*this doesnt seem to do anything */) {
					this.entityPlayer = (EntityPlayer) entity;
					closestEntityDistance = HL32K.mc.player.getDistance(entity);
				}

			}

			if (this.entityPlayer != null) {

				this.cpsTick++;

				if (this.cpsTick >= (20 / (HL32K.cps))) {

					HL32K.mc.playerController.attackEntity(HL32K.mc.player, this.entityPlayer);
					HL32K.mc.player.swingArm(EnumHand.MAIN_HAND);
					this.cpsTick = 0;
				}
			}
		}

	}
	}

	public void placeStuff(int hopperIndex, int shulkerIndex, BlockPos blockPos, EnumFacing enumFacing, Vec3d vec3d) {
		if(HL32K.a32kcore == 2){
		HL32K.mc.player.connection.sendPacket(new CPacketEntityAction(HL32K.mc.player, CPacketEntityAction.Action.START_SNEAKING));

		//place hopper
		if (HL32K.mc.world.getBlockState(blockPos.up()).getBlock() instanceof BlockAir) {//shouldent happen
			HL32K.mc.player.connection.sendPacket(new CPacketHeldItemChange(hopperIndex));
			HL32K.mc.player.inventory.currentItem = hopperIndex;
			HL32K.mc.playerController.updateController();
			HL32K.mc.playerController.processRightClickBlock(HL32K.mc.player, HL32K.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
			HL32K.mc.player.swingArm(EnumHand.MAIN_HAND);
		}
		this.placedHopperPos = blockPos.up();

		boolean placedShulker = false;

		//place shulker
		if (HL32K.mc.world.getBlockState(this.placedHopperPos).getBlock().equals(Blocks.HOPPER) && HL32K.mc.world.getBlockState(this.placedHopperPos.up()).getBlock() instanceof BlockAir) {
			HL32K.mc.player.connection.sendPacket(new CPacketHeldItemChange(shulkerIndex));
			HL32K.mc.player.inventory.currentItem = shulkerIndex;
			HL32K.mc.playerController.updateController();
			HL32K.mc.playerController.processRightClickBlock(HL32K.mc.player, HL32K.mc.world, this.placedHopperPos, EnumFacing.UP/* we are placing on the top? */, new Vec3d(this.placedHopperPos.getX(), this.placedHopperPos.getY(), this.placedHopperPos.getZ()), EnumHand.MAIN_HAND);
			HL32K.mc.player.swingArm(EnumHand.MAIN_HAND);
			placedShulker = true;
		}
		HL32K.mc.player.connection.sendPacket(new CPacketEntityAction(HL32K.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));

		if (placedShulker) {
			//open hopper
			HL32K.mc.playerController.processRightClickBlock(HL32K.mc.player, HL32K.mc.world, this.placedHopperPos, enumFacing/*....*/, new Vec3d(this.placedHopperPos.getX(), this.placedHopperPos.getY(), this.placedHopperPos.getZ()), EnumHand.MAIN_HAND);
		}
		this.hasPlacedStuff = true;
	}
	}

	public void onEnable() {
		if(HL32K.a32kcore == 2){
		this.placedHopperPos = null;
		this.shouldKillaura = false;
		this.hasPlacedStuff = false;
		}
	}

}