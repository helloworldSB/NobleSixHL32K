package net.noblesix.hl32k.hyperlethal32k;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;
import net.noblesix.hl32k.HL32K;
import com.google.common.base.Predicate;

import net.noblesix.hl32k.helpers.MultiColor;
import net.noblesix.hl32k.managers.GUIManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
//import org.jibble.pircbot.*;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

import static net.noblesix.hl32k.HL32K.mc;

public class Auto32kModuleGOD {

	public BlockPos placedHopperPos;
	public static boolean shouldKillaura = false;
	public boolean hasPlacedStuff;
	private MultiColor multiColor;
	public EntityPlayer entityPlayer;
	public int newTick;
	public String colorname;
	DecimalFormat fnum = new DecimalFormat("#.## ");
	private static final DecimalFormat df = new DecimalFormat("#.#");
	// dispenser
	private boolean multi = true;
	private int stage = 0;
	private BlockPos placeTarget;
	private int obiSlot;
	private int dispenserSlot;
	private int shulkerSlot;
	private int redstoneSlot;
	private int hopperSlot;
	private boolean isSneaking;
	private int placetick;
	public RayTraceResult objectMouseOver;
	public List<Block> emptyBlocks = Arrays.asList(Blocks.AIR, Blocks.FLOWING_LAVA, Blocks.LAVA, Blocks.FLOWING_WATER, Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, Blocks.TALLGRASS, Blocks.FIRE);
	public static final List<Block> shulkerList = Arrays.asList(new Block[] { Blocks.WHITE_SHULKER_BOX,
			Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX,
			Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX,
			Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX,
			Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX });

	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event) {
		if(HL32K.a32kcore == 3){
		if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
			return;
		}
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
		if(!HL32K.RGB) {
			mc.fontRenderer.drawStringWithShadow("Auto32K" + (HL32K.madmode ? " =) " : " =| ") + "[cps:" + HL32K.cps + "] " + "[cpt:" + HL32K.cpt + "]", 2, event.getResolution().getScaledHeight() - 10, HL32K.fontcolor);
			mc.fontRenderer.drawStringWithShadow("KillAura [" + (HL32K.isKillauraOptionEnabled ? "Enabled" : "Disabled") + "]", 2, event.getResolution().getScaledHeight() - 20, HL32K.fontcolor);
			mc.fontRenderer.drawStringWithShadow("Aura Mode:" + "[" + (HL32K.auramode ? "cps" : "cpt") + "]", 2, event.getResolution().getScaledHeight() - 30, HL32K.fontcolor);
			mc.fontRenderer.drawStringWithShadow("Reach:" + fnum.format(HL32K.reach) + "Blocks", 2, event.getResolution().getScaledHeight() - 40, HL32K.fontcolor);
			mc.fontRenderer.drawStringWithShadow("Color:" + colorname, 2, event.getResolution().getScaledHeight() - 50, HL32K.fontcolor);
		}else{
			mc.fontRenderer.drawStringWithShadow("Auto32K" + (HL32K.madmode ? " =) " : " =| ") + "[cps:" + HL32K.cps + "] " + "[cpt:" + HL32K.cpt + "]", 2, event.getResolution().getScaledHeight() - 10, (0xff << 24) | ((HL32K.red & 0xff) << 16) | ((HL32K.green & 0xff) << 8) | (HL32K.blue & 0xff));
			mc.fontRenderer.drawStringWithShadow("KillAura [" + (HL32K.isKillauraOptionEnabled ? "Enabled" : "Disabled") + "]", 2, event.getResolution().getScaledHeight() - 20, (0xff << 24) | ((HL32K.red & 0xff) << 16) | ((HL32K.green & 0xff) << 8) | (HL32K.blue & 0xff));
			mc.fontRenderer.drawStringWithShadow("Aura Mode:" + "[" + (HL32K.auramode ? "cps" : "cpt") + "]", 2, event.getResolution().getScaledHeight() - 30, (0xff << 24) | ((HL32K.red & 0xff) << 16) | ((HL32K.green & 0xff) << 8) | (HL32K.blue & 0xff));
			mc.fontRenderer.drawStringWithShadow("Reach:" + fnum.format(HL32K.reach) + "Blocks", 2, event.getResolution().getScaledHeight() - 40, (0xff << 24) | ((HL32K.red & 0xff) << 16) | ((HL32K.green & 0xff) << 8) | (HL32K.blue & 0xff));
			mc.fontRenderer.drawStringWithShadow("Color:" + colorname, 2, event.getResolution().getScaledHeight() - 50, (0xff << 24) | ((HL32K.red & 0xff) << 16) | ((HL32K.green & 0xff) << 8) | (HL32K.blue & 0xff));

		}
	}
	}

	/*
	 * @SubscribeEvent public void onjoin(PlayerEvent.PlayerLoggedInEvent event){
	 * mt.start(); }
	 */
	public boolean isDouble(String s) {
		if(HL32K.a32kcore == 3){
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		} else {
			return false;
		}
	}

	public boolean HasDigit(String content) {
		if(HL32K.a32kcore == 3){
		boolean flag = false;
		Pattern p = Pattern.compile(".*\\d+.*");
		Matcher m = p.matcher(content);
		if (m.matches()) {
			flag = true;
		}
		return flag;
		} else{
			return false;
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onChatSent(ClientChatEvent event) {
		if(HL32K.a32kcore == 3){
		if (event.getMessage().startsWith("@tp")) {
			event.setCanceled(true);
			try {
				mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
				String input = event.getMessage().substring(3);
				String[] split = input.split(" ");
				if (isDouble(split[1])) {
					if (isDouble(split[2])) {
						if (isDouble(split[3])) {
							final double x = Double.parseDouble(split[1]);
							final double y = Double.parseDouble(split[2]);
							final double z = Double.parseDouble(split[3]);
							if (mc.player.getRidingEntity() != null) {
								mc.player.getRidingEntity().setPosition(x, y, z);
							} else {
								mc.player.setPosition(x, y, z);
							}
							final DecimalFormat format = new DecimalFormat("##.##");
							drawText(TextFormatting.RED + "Take You to X: " + format.format(x) + " Y: " + format.format(y) + " Z: " + format.format(z));
						} else {
							drawText(TextFormatting.RED + "Illegal Number " + " " + split[3]);
						}
					} else {
						drawText(TextFormatting.RED + "Illegal Number " + " " + split[2]);
					}
				} else {
					drawText(TextFormatting.RED + "Illegal Number " + " " + split[1]);
				}
			} catch (Exception e) {
				e.printStackTrace();
				drawText(TextFormatting.RED + "Fatal Error");
			}
			event.setMessage("");
		} else if (event.getMessage().startsWith("@rgb") || event.getMessage().startsWith("@RGB")){
			event.setCanceled(true);
			try {
				mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
				String input = event.getMessage().substring(4);
				String[] split = input.split(" ");
				if (isDouble(split[1])) {
					if (isDouble(split[2])) {
						if (isDouble(split[3])) {
							HL32K.RGB = true;
							HL32K.red = Integer.parseInt(split[1]);
							HL32K.green = Integer.parseInt(split[2]);
							HL32K.blue = Integer.parseInt(split[3]);
						} else {
							HL32K.RGB = false;
						}
					} else {
						HL32K.RGB = false;
					}
				} else {
					HL32K.RGB = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				HL32K.RGB = false;
			}
		} else if (HasDigit(event.getMessage()) && HL32K.chatdisable && !event.getMessage().startsWith("/")) {
			event.setCanceled(true);
			mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
			event.setMessage("");
			drawText(TextFormatting.RED + "Warning you are sending numbers.Using -=disable to switch number sending Mode");
		} else if (event.getMessage().startsWith("-=disable")) {
			event.setCanceled(true);
			if (HL32K.chatdisable) {
				HL32K.chatdisable = false;
				HL32K.saveInformation();
				drawText(TextFormatting.RED + "Toggle Chat Switched off");
			} else if (!HL32K.chatdisable) {
				HL32K.chatdisable = true;
				HL32K.saveInformation();
				drawText(TextFormatting.RED + "Toggle Chat Switched on");
			}
			event.setMessage("");
		}
	}
	}

	public void drawText(String text) {
		if(HL32K.a32kcore == 3){
		mc.ingameGUI.addChatMessage(ChatType.CHAT, new TextComponentString(text));
		}
	}
	
	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event) {
		if(HL32K.a32kcore == 3){
		try {
				if (mc.currentScreen instanceof GuiHopper) {
					if (shouldthrow(mc.player.getHeldItemMainhand()) && mc.currentScreen instanceof GuiHopper) {
						mc.player.dropItem(true);
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
	
	@SubscribeEvent
	public void onKey(InputUpdateEvent event) {
		if(HL32K.a32kcore == 3){
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
	public void onTick(ClientTickEvent event) {
		if(HL32K.a32kcore == 3){
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

		if (HL32K.changecolor.isPressed()) {
			if (HL32K.color == 26) {
				HL32K.color = 0;
			} else {
				HL32K.color += 1;
			}
			HL32K.saveInformation();
		}

		if (HL32K.reachplus.isPressed()) {
			HL32K.reach += 0.01F;
			HL32K.saveInformation();
		}

		if (HL32K.reachminus.isPressed()) {
			HL32K.reach -= 0.01F;
			HL32K.saveInformation();
		}

		if (HL32K.cptplus.isPressed()) {
			HL32K.cpt++;
			HL32K.saveInformation();
		}

		if (HL32K.cptminus.isPressed()) {
			HL32K.cpt--;
			HL32K.saveInformation();
		}

		if (HL32K.auraswitch.isPressed()) {
			HL32K.auramode = !HL32K.auramode;
			HL32K.saveInformation();
		}

		if (HL32K.switch32k.isPressed()) {
			HL32K.dispensermode = !HL32K.dispensermode;
			HL32K.saveInformation();
		}

		if (HL32K.switchmad.isPressed()) {
			HL32K.madmode = !HL32K.madmode;
			HL32K.saveInformation();
		}
		
		if (HL32K.switchplace.isPressed()) {
			HL32K.lookingplace = !HL32K.lookingplace;
			mc.ingameGUI.addChatMessage(ChatType.GAME_INFO, new TextComponentString(TextFormatting.YELLOW+"[HL32K]32k place mode:"+(HL32K.lookingplace ? "Looking" : "Auto")));
			HL32K.saveInformation();
		}
		

		if (mc.player != null && mc.world != null) {
			int hopperIndex = -1;
			int shulkerIndex = -1;
			int enchantedSwordIndex = -1;
			for (int i = 0; i < 9; i++) {
				ItemStack itemStack = mc.player.inventory.mainInventory.get(i);
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
			if (HL32K.auto32kKeybind.isPressed()) {
		
				if ((hopperIndex == -1 || shulkerIndex == -1)) {
					return;
				}
		

				if (enchantedSwordIndex == -1) {
					if (HL32K.lookingplace) {
						RayTraceResult ray = mc.player.rayTrace(4.25D, mc.getRenderPartialTicks());
                        if (this.emptyBlocks.contains(mc.world.getBlockState(ray.getBlockPos()).getBlock())) {
                           return;
                        }

                        this.placeBlock(ray.getBlockPos().up(), hopperIndex);
                        this.placeBlock(ray.getBlockPos().up(2), shulkerIndex);
                        this.openBlock(ray.getBlockPos().up());
                        this.placedHopperPos = ray.getBlockPos().up();
					} else {
						auto: 
						for (int cap2 = -2; cap2 <= 2; ++cap2) {
							for (int y = -1; y <= 2; ++y) {
								for (int x = -2; x <= 2; ++x) {
									if ((cap2 != 0 || y != 0 || x != 0) && (cap2 != 0 || y != 1 || x != 0) && this.emptyBlocks.contains(mc.world.getBlockState(mc.player.getPosition().add(cap2, y, x)).getBlock()) && this.emptyBlocks.contains(mc.world.getBlockState(mc.player.getPosition().add(cap2, y + 1, x)).getBlock())) {
										this.placeBlock(mc.player.getPosition().add(cap2, y, x), hopperIndex);
										this.placeBlock(mc.player.getPosition().add(cap2, y + 1, x), shulkerIndex);
										this.openBlock(mc.player.getPosition().add(cap2, y, x));
										this.placedHopperPos = mc.player.getPosition().add(cap2, y, x);
										break auto;
									}
								}
							}
						}
					}
				}
			}
			if (enchantedSwordIndex != -1) {
				// we have a 32k
				this.shouldKillaura = true;
	
				// wow this was a fricking pain to figure out
				if (mc.player.inventory.currentItem != enchantedSwordIndex) {
					mc.player.connection.sendPacket(new CPacketHeldItemChange(enchantedSwordIndex));
					mc.player.inventory.currentItem = enchantedSwordIndex;
					mc.playerController.updateController();
				}
	
			} else {
				this.shouldKillaura = false;
			}
	
			if (enchantedSwordIndex == -1 && mc.player.openContainer != null && mc.player.openContainer instanceof ContainerHopper && mc.player.openContainer.inventorySlots != null && !mc.player.openContainer.inventorySlots.isEmpty()) {
				// this is very weird.. but i dont have to get the hopperInventory from
				// GuiHopper
				for (int i = 0; i < 5; i++) {
					if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, mc.player.openContainer.inventorySlots.get(0).inventory.getStackInSlot(i)) >= Short.MAX_VALUE) {
						enchantedSwordIndex = i;
						break;
					}
				}

				if (enchantedSwordIndex == -1) {
					return;
				}
	
				if (mc.player.inventory.mainInventory.get(mc.player.inventory.currentItem).getItem() instanceof ItemAir) {
					for (int i = 0; i < 9; i++) {
						ItemStack get = mc.player.inventory.mainInventory.get(i);
						if (get.getItem() instanceof ItemAir) {
							if (mc.player.inventory.currentItem != i) {
								mc.player.connection.sendPacket(new CPacketHeldItemChange(i));
								mc.player.inventory.currentItem = i;
								mc.playerController.updateController();
							}
							break;
						}
					}
				}
	
				mc.playerController.windowClick(mc.player.openContainer.windowId, enchantedSwordIndex, mc.player.inventory.currentItem, ClickType.SWAP, mc.player);
			}
	
			if (HL32K.isKillauraOptionEnabled && shouldKillaura) {
				try{
				if (!HL32K.auramode) {
					for (int i = 0; i <= HL32K.cpt; ++i) {
						for (Entity target : mc.world.playerEntities) {
							if (target.isDead)
								continue;
							if (target == mc.player)
								continue;
							if (mc.player.getDistance(target) > HL32K.reach)
								continue;
							if (((EntityLivingBase) target).getHealth() <= 0)
								continue;
							attack(target);
							if (!multi) return;
						}
					}
				}
				else if (HL32K.auramode){
					this.newTick++;
					if (this.newTick >= (20 / (HL32K.cps))) {
						for (int i = 0; i <= HL32K.cpt; ++i) {
							for (Entity target2 : mc.world.playerEntities) {
								if (target2.isDead)
									continue;
								if (target2 == mc.player)
									continue;
								if (mc.player.getDistance(target2) > HL32K.reach)
									continue;
								if (((EntityLivingBase) target2).getHealth() <= 0)
									continue;
								attack(target2);
								if (!multi) return;
							}
						}
						this.newTick = 0;
					}
				}
				}catch (Exception var13) {
				}
			}
		}

		}
	}
	

	
	
	private void attack(Entity e) {
        mc.playerController.attackEntity(mc.player, e);
        mc.player.swingArm(EnumHand.MAIN_HAND);
    }
	
	public boolean placeBlock(BlockPos pos, int slot) {
		if(HL32K.a32kcore == 3){
	      if (!this.emptyBlocks.contains(mc.world.getBlockState(pos).getBlock())) {
	         return false;
	      } else {
	         if (slot != mc.player.inventory.currentItem) {
	            mc.player.inventory.currentItem = slot;
	         }

	         EnumFacing[] var3 = EnumFacing.values();
	         int var4 = var3.length;

	         for(int var5 = 0; var5 < var4; ++var5) {
	            EnumFacing f = var3[var5];
	            Block neighborBlock = mc.world.getBlockState(pos.offset(f)).getBlock();
	            if (!this.emptyBlocks.contains(neighborBlock)) {
	               mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));
	               mc.playerController.processRightClickBlock(mc.player, mc.world, pos.offset(f), f.getOpposite(), new Vec3d(pos), EnumHand.MAIN_HAND);
	               mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
	               return true;
	            }
	         }

	         return false;
	      }
	   }
	   else {
		   return false;
	   }
	}

	public void openBlock(BlockPos pos) {
		   if(HL32K.a32kcore == 3){
	      EnumFacing[] var2 = EnumFacing.values();
	      int var3 = var2.length;

	      for(int var4 = 0; var4 < var3; ++var4) {
	         EnumFacing f = var2[var4];
	         Block neighborBlock = mc.world.getBlockState(pos.offset(f)).getBlock();
	         if (this.emptyBlocks.contains(neighborBlock)) {
	            mc.playerController.processRightClickBlock(mc.player, mc.world, pos, f.getOpposite(), new Vec3d(pos), EnumHand.MAIN_HAND);
	            return;
	         }
	      }

	   }
	}
	
	public boolean shouldthrow(ItemStack item) {
		if(HL32K.a32kcore == 3){
		if (item == null)
			return false;
		if (item.getTagCompound() == null)
			return false;
		if (item.getEnchantmentTagList().getTagType() == 0)
			return false;
		NBTTagList enchants = (NBTTagList) item.getTagCompound().getTag("ench");
		int i = 0;
		while (i < enchants.tagCount()) {
			NBTTagCompound enchant = enchants.getCompoundTagAt(i);
			if (enchant.getInteger("id") == 16) {
				int lvl = enchant.getInteger("lvl");
				if (lvl < 32767)
					return true;
			}
			i++;
		}
		return false;
	}
	else {
		return false;
	}
	}

	public void placeStuff(int hopperIndex, int shulkerIndex, BlockPos blockPos, EnumFacing enumFacing, Vec3d vec3d) {
		if(HL32K.a32kcore == 3){
		mc.player.connection
				.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));

		// place hopper
		if (mc.world.getBlockState(blockPos.up()).getBlock() instanceof BlockAir) {// shouldent happen
			mc.player.connection.sendPacket(new CPacketHeldItemChange(hopperIndex));
			mc.player.inventory.currentItem = hopperIndex;
			mc.playerController.updateController();
			mc.playerController.processRightClickBlock(mc.player, mc.world, blockPos,
					enumFacing, vec3d, EnumHand.MAIN_HAND);
			mc.player.swingArm(EnumHand.MAIN_HAND);
		}
		this.placedHopperPos = blockPos.up();

		boolean placedShulker = false;

		// place shulker
		if (mc.world.getBlockState(this.placedHopperPos).getBlock().equals(Blocks.HOPPER) && mc.world.getBlockState(this.placedHopperPos.up()).getBlock() instanceof BlockAir) {
			mc.player.connection.sendPacket(new CPacketHeldItemChange(shulkerIndex));
			mc.player.inventory.currentItem = shulkerIndex;
			mc.playerController.updateController();
			mc.playerController.processRightClickBlock(mc.player, mc.world, this.placedHopperPos, EnumFacing.UP/* we are placing on the top? */, new Vec3d(this.placedHopperPos.getX(), this.placedHopperPos.getY(), this.placedHopperPos.getZ()), EnumHand.MAIN_HAND);
			mc.player.swingArm(EnumHand.MAIN_HAND);
			placedShulker = true;
		}
		mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));

		if (placedShulker) {
			// open hopper
			mc.playerController.processRightClickBlock(mc.player, mc.world, this.placedHopperPos, enumFacing, new Vec3d(this.placedHopperPos.getX(), this.placedHopperPos.getY(), this.placedHopperPos.getZ()), EnumHand.MAIN_HAND);
		}
		this.hasPlacedStuff = true;
		
	}
	}

	public void onEnable() {
		this.placedHopperPos = null;
		this.shouldKillaura = false;
		this.hasPlacedStuff = false;
	}



}
