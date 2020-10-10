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

import org.lwjgl.input.Keyboard;
import com.google.common.base.Predicate;

import net.minecraft.client.gui.GuiChat;
import net.noblesix.hl32k.HL32K;
import net.noblesix.hl32k.helpers.MultiColor;
import net.noblesix.hl32k.managers.GUIManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
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
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
//import org.jibble.pircbot.*;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import static net.noblesix.hl32k.HL32K.mc;

public class Auto32kModuleBad {

	public BlockPos placedHopperPos;
	public static boolean shouldKillaura = false;
	public boolean hasPlacedStuff;
	public EntityPlayer entityPlayer;
	public int newTick;
	public String colorname;
	DecimalFormat fnum = new DecimalFormat("#.## ");
	private static final DecimalFormat df = new DecimalFormat("#.#");
	// dispenser
	private int stage = 0;
	private BlockPos placeTarget;
	private int obiSlot;
	private int dispenserSlot;
	private int shulkerSlot;
	private int redstoneSlot;
	private int hopperSlot;
	private boolean isSneaking;
	private int placetick;
	public static final List<Block> shulkerList = Arrays.asList(new Block[] { Blocks.WHITE_SHULKER_BOX,
			Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX,
			Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX,
			Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX,
			Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX });

	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event) {
		if(HL32K.a32kcore == 1){
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
		HL32K.mc.fontRenderer.drawStringWithShadow(
				"Bad Auto32K " + (HL32K.is32kEnabled ? "Enabled" : "Disabled") + (HL32K.madmode ? " =) " : " =| ")
						+ "[cps:" + HL32K.cps + "] " + "[cpt:" + HL32K.cpt + "]",
				2, event.getResolution().getScaledHeight() - 10, HL32K.fontcolor);
		if (HL32K.is32kEnabled) {
			HL32K.mc.fontRenderer.drawStringWithShadow(
					"Kill Aura [" + (HL32K.isKillauraOptionEnabled ? "Enabled" : "Disabled") + "]", 2,
					event.getResolution().getScaledHeight() - 20, HL32K.fontcolor);
			HL32K.mc.fontRenderer.drawStringWithShadow("Aura Mode:" + "[" + (HL32K.auramode ? "cps" : "cpt") + "]", 2,
					event.getResolution().getScaledHeight() - 30, HL32K.fontcolor);
			HL32K.mc.fontRenderer.drawStringWithShadow("Range:" + fnum.format(HL32K.reach) + "blocks", 2,
					event.getResolution().getScaledHeight() - 40, HL32K.fontcolor);
			HL32K.mc.fontRenderer.drawStringWithShadow("Color:" + colorname, 2,
					event.getResolution().getScaledHeight() - 50, HL32K.fontcolor);
		} else {
			HL32K.mc.fontRenderer.drawStringWithShadow("Aura mode:" + "[" + (HL32K.auramode ? "cps" : "cpt") + "]", 2,
					event.getResolution().getScaledHeight() - 20, HL32K.fontcolor);
			HL32K.mc.fontRenderer.drawStringWithShadow("Range:" + fnum.format(HL32K.reach) + "blocks", 2,
					event.getResolution().getScaledHeight() - 30, HL32K.fontcolor);
			HL32K.mc.fontRenderer.drawStringWithShadow("Color:" + colorname, 2,
					event.getResolution().getScaledHeight() - 40, HL32K.fontcolor);
		}
	}
	}


	public boolean isDouble(String s) {
		if(HL32K.a32kcore == 1){
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
		if(HL32K.a32kcore == 1){
		boolean flag = false;
		Pattern p = Pattern.compile(".*\\d+.*");
		Matcher m = p.matcher(content);
		if (m.matches()) {
			flag = true;
		}
		return flag;
		}else {
			return false;
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onChatSent(ClientChatEvent event) {
		if(HL32K.a32kcore == 1){
		if (event.getMessage().startsWith("@tp")) {
			event.setCanceled(true);
			try {
				HL32K.mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
				String input = event.getMessage().substring(3);
				String[] split = input.split(" ");
				if (isDouble(split[1])) {
					if (isDouble(split[2])) {
						if (isDouble(split[3])) {
							final double x = Double.parseDouble(split[1]);
							final double y = Double.parseDouble(split[2]);
							final double z = Double.parseDouble(split[3]);
							if (HL32K.mc.player.getRidingEntity() != null) {
								HL32K.mc.player.getRidingEntity().setPosition(x, y, z);
							} else {
								HL32K.mc.player.setPosition(x, y, z);
							}
							final DecimalFormat format = new DecimalFormat("##.##");
							drawText(TextFormatting.RED + "Take You to X: " + format.format(x) + " Y: " + format.format(y)
									+ " Z: " + format.format(z));
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
				drawText(TextFormatting.RED + "Fatal error");
			}
			event.setMessage("");
		} else if (HasDigit(event.getMessage()) && HL32K.chatdisable && !event.getMessage().startsWith("/")) {
			event.setCanceled(true);
			HL32K.mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
			event.setMessage("");
			drawText(TextFormatting.RED + "Warning you are sending numbers.Using -=disable to switch number sending Mode");
		} else if (event.getMessage().startsWith("-=disable")) {
			event.setCanceled(true);
			if (HL32K.chatdisable) {
				HL32K.chatdisable = false;
				drawText(TextFormatting.RED + "Chatmode ALL");
			} else if (!HL32K.chatdisable) {
				HL32K.chatdisable = true;
				drawText(TextFormatting.RED + "Chatmode No Number");
			}
			event.setMessage("");
		}
		}
	}

	public void drawText(String text) {
		if(HL32K.a32kcore == 1){
		HL32K.mc.ingameGUI.addChatMessage(ChatType.CHAT, new TextComponentString(text));
		}
	}

	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event) {
		if(HL32K.a32kcore == 1){
		try {
			if (shouldthrow(HL32K.mc.player.getHeldItemMainhand()) && HL32K.mc.currentScreen instanceof GuiHopper) {
				EntityItem troll = HL32K.mc.player.dropItem(true);
			}
		} catch (Exception e) {
		}
		}
	}

	@SubscribeEvent
	public void onTick(ClientTickEvent event) {
		if(HL32K.a32kcore == 1){
		if (event.phase == Phase.START && HL32K.mc.player != null && HL32K.mc.world != null) {
			if (HL32K.auto32kKeybind.isPressed()) {
				HL32K.is32kEnabled = !HL32K.is32kEnabled;
				if (HL32K.is32kEnabled) {
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

			if (HL32K.changecolor.isPressed()) {
				if (HL32K.color == 20) {
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
			
	
			if (!HL32K.is32kEnabled) {
				return;
			}

			if (mc.player != null && mc.world != null) {
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
					HL32K.is32kEnabled = false;
					return;
				}

				
				if (enchantedSwordIndex == -1 && !hasPlacedStuff) {

					double closestBlockPosDistance = 4;// maybe?
					BlockPos closestBlockPos = null;

					Predicate<Entity> predicate = new Predicate<Entity>() {
						public boolean apply(Entity entity) {
							return !(entity instanceof EntityItem);
						}
					};

					for (BlockPos blockPos : BlockPos.getAllInBox(
							new BlockPos(HL32K.mc.player.posX - 3, HL32K.mc.player.posY - 1,
									HL32K.mc.player.posZ - 3),
							new BlockPos(HL32K.mc.player.posX + 3, HL32K.mc.player.posY + 2,
									HL32K.mc.player.posZ + 3))) {
						if (HL32K.mc.player.getDistance(blockPos.getX(), blockPos.getY(),
								blockPos.getZ()) < closestBlockPosDistance
								&& HL32K.mc.world.getEntitiesWithinAABB(Entity.class,
										new AxisAlignedBB(blockPos.add(0, 1, 0)), predicate).isEmpty()
								&& !(HL32K.mc.world.getBlockState(blockPos.down()).getBlock() instanceof BlockAir)
								&& HL32K.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir
								&& HL32K.mc.world.getBlockState(blockPos.up()).getBlock() instanceof BlockAir
								&& HL32K.mc.world.getBlockState(blockPos.up().up()).getBlock() instanceof BlockAir) {
							closestBlockPosDistance = HL32K.mc.player.getDistance(blockPos.getX(), blockPos.getY(),
									blockPos.getZ());
							closestBlockPos = blockPos;
						}
					}

					if (closestBlockPos != null) {
						this.placeStuff(hopperIndex, shulkerIndex, closestBlockPos.down(), EnumFacing.UP,
								new Vec3d(closestBlockPos.getX(), closestBlockPos.getY(), closestBlockPos.getZ()));
					}

				}

				if (enchantedSwordIndex != -1) {
					// we have a 32k
					this.shouldKillaura = true;

					// wow this was a fricking pain to figure out
					if (HL32K.mc.player.inventory.currentItem != enchantedSwordIndex) {
						HL32K.mc.player.connection.sendPacket(new CPacketHeldItemChange(enchantedSwordIndex));
						HL32K.mc.player.inventory.currentItem = enchantedSwordIndex;
						HL32K.mc.playerController.updateController();
					}

				} else {
					this.shouldKillaura = false;
				}

				if (enchantedSwordIndex == -1 && HL32K.mc.player.openContainer != null
						&& HL32K.mc.player.openContainer instanceof ContainerHopper
						&& HL32K.mc.player.openContainer.inventorySlots != null
						&& !HL32K.mc.player.openContainer.inventorySlots.isEmpty()) {
					// this is very weird.. but i dont have to get the hopperInventory from
					// GuiHopper
					for (int i = 0; i < 5; i++) {
						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS,
										HL32K.mc.player.openContainer.inventorySlots.get(0).inventory
												.getStackInSlot(i)) >= Short.MAX_VALUE) {
							enchantedSwordIndex = i;
							break;
						}
					}

					if (enchantedSwordIndex == -1) {
						return;
					}

					if (HL32K.mc.player.inventory.mainInventory.get(HL32K.mc.player.inventory.currentItem)
							.getItem() instanceof ItemAir) {
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

					HL32K.mc.playerController.windowClick(HL32K.mc.player.openContainer.windowId,
							enchantedSwordIndex, HL32K.mc.player.inventory.currentItem, ClickType.SWAP,
							HL32K.mc.player);

				}

				if (this.shouldKillaura && HL32K.isKillauraOptionEnabled) {

					
					EntityPlayer target = null;
				try {
					List<EntityPlayer> players = new ArrayList<EntityPlayer>(mc.world.playerEntities);
					Iterator<EntityPlayer> var3 = (new ArrayList<EntityPlayer>(players)).iterator();
					while (var3.hasNext()) {
						EntityPlayer var4 = (EntityPlayer) var3.next();
						if (HL32K.friends.contains(var4.getName().toLowerCase())) {
							players.remove(var4);
						}
					}
					players.remove(mc.player);
					for(int k = 0 ; k < players.size() ; k++){
					if(players.get(k).isDead ||  players.get(k).getHealth() <= 0){
						players.remove(k);//our targer are alive players,by xX_NobleSix_Xx
					}
					players.sort((a, b) -> {
							return Float.compare(a.getDistance(mc.player), b.getDistance(mc.player));
					});
					if (((EntityPlayer) players.get(0)).getDistance(mc.player) <= HL32K.reach && !HL32K.madmode) {
						target = (EntityPlayer) players.get(0);
					}
					else if (HL32K.madmode) {
						target = (EntityPlayer) players.get(0);
					} 
				}
				} catch (Exception var13) {
					}

					if (target != null && !HL32K.auramode) {
						if (!HL32K.madmode) {
							double diffX = target.posX - HL32K.mc.player.posX;
							double diffY = target.posY + 1.0D
									- (HL32K.mc.player.posY + (double) HL32K.mc.player.getEyeHeight());
							double diffZ = target.posZ - HL32K.mc.player.posZ;
							double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
							float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
							float pitch = (float) (-Math.toDegrees(Math.atan2(diffY, diffXZ)));
							EntityPlayerSP var10000 = HL32K.mc.player;
							var10000.rotationYaw += MathHelper.wrapDegrees(yaw - HL32K.mc.player.rotationYaw);
							var10000 = HL32K.mc.player;
							var10000.rotationPitch += MathHelper.wrapDegrees(pitch - HL32K.mc.player.rotationPitch);
						}
						for (int i = 1; i <= HL32K.cpt; ++i) {
							HL32K.mc.playerController.attackEntity(HL32K.mc.player, target);
							HL32K.mc.player.swingArm(EnumHand.MAIN_HAND);
						}
					} else if (target != null && HL32K.auramode) {
						this.newTick++;
						if (!HL32K.madmode) {
							double diffX = target.posX - HL32K.mc.player.posX;
							double diffY = target.posY + 1.0D
									- (HL32K.mc.player.posY + (double) HL32K.mc.player.getEyeHeight());
							double diffZ = target.posZ - HL32K.mc.player.posZ;
							double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
							float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
							float pitch = (float) (-Math.toDegrees(Math.atan2(diffY, diffXZ)));
							EntityPlayerSP var10000 = HL32K.mc.player;
							var10000.rotationYaw += MathHelper.wrapDegrees(yaw - HL32K.mc.player.rotationYaw);
							var10000 = HL32K.mc.player;
							var10000.rotationPitch += MathHelper.wrapDegrees(pitch - HL32K.mc.player.rotationPitch);
						}
						if (this.newTick >= (20 / (HL32K.cps))) {
							HL32K.mc.playerController.attackEntity(HL32K.mc.player, target);
							HL32K.mc.player.swingArm(EnumHand.MAIN_HAND);
							this.newTick = 0;
						}
					}
				}
			} 
	
			if (this.stage == 1) {
			      if (!(HL32K.mc.currentScreen instanceof GuiContainer))
			        return; 
			      HL32K.mc.playerController.windowClick(HL32K.mc.player.openContainer.windowId, 1, this.shulkerSlot, ClickType.SWAP, (EntityPlayer)HL32K.mc.player);
			      HL32K.mc.player.closeScreen();
			      HL32K.mc.player.inventory.currentItem = this.redstoneSlot;
			      placeBlock(new BlockPos((Vec3i)this.placeTarget.add(0, 2, 0)), EnumFacing.DOWN);
			      this.stage = 2;
			      return;
			} 
			
			if (this.stage == 2) {
			      Block block = HL32K.mc.world.getBlockState(this.placeTarget.offset(HL32K.mc.player.getHorizontalFacing().getOpposite()).up()).getBlock();
			      if (block instanceof net.minecraft.block.BlockAir || block instanceof net.minecraft.block.BlockLiquid)
			        return; 
			      HL32K.mc.player.inventory.currentItem = this.hopperSlot;
			      placeBlock(new BlockPos((Vec3i)this.placeTarget.offset(HL32K.mc.player.getHorizontalFacing().getOpposite())), HL32K.mc.player.getHorizontalFacing());
			      HL32K.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HL32K.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
			      this.isSneaking = false;
			      HL32K.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.placeTarget.offset(HL32K.mc.player.getHorizontalFacing().getOpposite()), EnumFacing.DOWN, EnumHand.MAIN_HAND, 0.0F, 0.0F, 0.0F));
			      HL32K.mc.player.inventory.currentItem = this.shulkerSlot; 
			      this.stage = 0;
			      HL32K.is32kEnabled = false;
			      return;
			    } 
		}
		}
	}


	public boolean shouldthrow(ItemStack item) {
		if(HL32K.a32kcore == 1){
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
	}else {
			return false;
		}
	}
	
	@SubscribeEvent
	public void onKey(InputUpdateEvent event) {
		if(HL32K.a32kcore == 1){
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

	public void placeStuff(int hopperIndex, int shulkerIndex, BlockPos blockPos, EnumFacing enumFacing, Vec3d vec3d) {
		if(HL32K.a32kcore == 1){
		HL32K.mc.player.connection
				.sendPacket(new CPacketEntityAction(HL32K.mc.player, CPacketEntityAction.Action.START_SNEAKING));

		// place hopper
		if (HL32K.mc.world.getBlockState(blockPos.up()).getBlock() instanceof BlockAir) {// shouldent happen
			HL32K.mc.player.connection.sendPacket(new CPacketHeldItemChange(hopperIndex));
			HL32K.mc.player.inventory.currentItem = hopperIndex;
			HL32K.mc.playerController.updateController();
			HL32K.mc.playerController.processRightClickBlock(HL32K.mc.player, HL32K.mc.world, blockPos,
					enumFacing, vec3d, EnumHand.MAIN_HAND);
			HL32K.mc.player.swingArm(EnumHand.MAIN_HAND);
		}
		this.placedHopperPos = blockPos.up();

		boolean placedShulker = false;

		// place shulker
		if (HL32K.mc.world.getBlockState(this.placedHopperPos).getBlock().equals(Blocks.HOPPER)
				&& HL32K.mc.world.getBlockState(this.placedHopperPos.up()).getBlock() instanceof BlockAir) {
			HL32K.mc.player.connection.sendPacket(new CPacketHeldItemChange(shulkerIndex));
			HL32K.mc.player.inventory.currentItem = shulkerIndex;
			HL32K.mc.playerController.updateController();
			HL32K.mc.playerController.processRightClickBlock(HL32K.mc.player, HL32K.mc.world,
					this.placedHopperPos, EnumFacing.UP/* we are placing on the top? */,
					new Vec3d(this.placedHopperPos.getX(), this.placedHopperPos.getY(), this.placedHopperPos.getZ()),
					EnumHand.MAIN_HAND);
			HL32K.mc.player.swingArm(EnumHand.MAIN_HAND);
			placedShulker = true;
		}
		HL32K.mc.player.connection
				.sendPacket(new CPacketEntityAction(HL32K.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));

		if (placedShulker) {
			// open hopper
			HL32K.mc.playerController.processRightClickBlock(HL32K.mc.player, HL32K.mc.world,
					this.placedHopperPos, enumFacing/* .... */,
					new Vec3d(this.placedHopperPos.getX(), this.placedHopperPos.getY(), this.placedHopperPos.getZ()),
					EnumHand.MAIN_HAND);
		}
		this.hasPlacedStuff = true;
		}
	}

	public void onEnable() {
		if(HL32K.a32kcore == 1){
		this.placedHopperPos = null;
		this.shouldKillaura = false;
		this.hasPlacedStuff = false;
		}
	}

	public List getFriends() {

		try {
			List<String> friends = Files
					.readAllLines(Paths.get(HL32K.mc.mcDataDir.getPath(), "BADHL32K friends.txt")).stream()
					.map(String::toLowerCase).collect(Collectors.toList());
			return friends;
		} catch (IOException var2) {
			System.out.println("Error Reading Friends");
			return new ArrayList();
		}
		
	}
	
	private static float[] getLegitRotations(Vec3d vec) {
		
		Vec3d eyesPos = getEyesPos();
		double diffX = vec.x - eyesPos.x;
		double diffY = vec.y - eyesPos.y;
		double diffZ = vec.z - eyesPos.z;
		double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
		float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
		float pitch = (float) -Math.toDegrees(Math.atan2(diffY, diffXZ));
		return new float[] {
				HL32K.mc.player.rotationYaw + MathHelper.wrapDegrees(yaw - HL32K.mc.player.rotationYaw),
				HL32K.mc.player.rotationPitch + MathHelper.wrapDegrees(pitch - HL32K.mc.player.rotationPitch) };
		
	}
	
	public static void faceVectorPacketInstant(Vec3d vec) {
		float[] rotations = getLegitRotations(vec);
		HL32K.mc.player.connection.sendPacket(
				(Packet) new CPacketPlayer.Rotation(rotations[0], rotations[1], (HL32K.mc.player).onGround));
	}
	
	private static Vec3d getEyesPos() {
		return new Vec3d((HL32K.mc.player).posX, (HL32K.mc.player).posY + HL32K.mc.player.getEyeHeight(),
				(HL32K.mc.player).posZ);
	}
	
	private void placeBlock(BlockPos pos, EnumFacing side) {
		BlockPos neighbour = pos.offset(side);
		EnumFacing opposite = side.getOpposite();
		if (!this.isSneaking) {
			HL32K.mc.player.connection.sendPacket((Packet) new CPacketEntityAction((Entity) HL32K.mc.player,
					CPacketEntityAction.Action.START_SNEAKING));
			this.isSneaking = true;
		}
		Vec3d hitVec = (new Vec3d((Vec3i) neighbour)).addVector(0.5D, 0.5D, 0.5D)
				.add((new Vec3d(opposite.getDirectionVec())).scale(0.5D));
		faceVectorPacketInstant(hitVec);
		HL32K.mc.playerController.processRightClickBlock(HL32K.mc.player, HL32K.mc.world, neighbour, opposite,
				hitVec, EnumHand.MAIN_HAND);
		HL32K.mc.player.swingArm(EnumHand.MAIN_HAND);
	}

}
