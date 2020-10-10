package net.noblesix.hl32k.hyperlethal32k;

import net.noblesix.hl32k.HL32K;
import net.noblesix.hl32k.modules.api.Module;
import org.lwjgl.input.Keyboard;
import java.util.List;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;



public class Auto32kBypass extends Module {
	public Auto32kBypass() {
        super("Auto32kBypass", Keyboard.KEY_NONE);
    }
	
	public static Minecraft mc = Minecraft.getMinecraft();
	public List<Block> emptyBlocks = Arrays.asList(Blocks.AIR, Blocks.FLOWING_LAVA, Blocks.LAVA, Blocks.FLOWING_WATER, Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, Blocks.TALLGRASS, Blocks.FIRE);
	int oldslot;
    int hopperIndex;
    int shulkerIndex;
    int redstoneIndex;
    int dispenserIndex;
    int obiIndex;
    int placeTick = 1;
    BlockPos origin;
    BlockPos hopperPos;
    EnumFacing horizontalFace;

    @Override
	public void onToggle(boolean state){
		if (state){
			if (mc == null && Auto32kBypass.mc.player == null) {
				return;
			}
			this.obiIndex = -1;
			this.dispenserIndex = -1;
			this.redstoneIndex = -1;
			this.shulkerIndex = -1;
			this.hopperIndex = -1;
			this.placeTick = 1;
			if (mc != null && Auto32kBypass.mc.player != null && Auto32kBypass.mc.objectMouseOver != null) {
				if (!HL32K.lookingplace) {
						for (int cap2 = -2; cap2 <= 2; ++cap2) {
							for (int y = -1; y <= 2; ++y) {
								for (int x = -2; x <= 2; ++x) {
									if ((cap2 != 0 || y != 0 || x != 0) && (cap2 != 0 || y != 1 || x != 0) && this.emptyBlocks.contains(mc.world.getBlockState(mc.player.getPosition().add(cap2, y, x)).getBlock()) && this.emptyBlocks.contains(mc.world.getBlockState(mc.player.getPosition().add(cap2, y + 1, x)).getBlock())) {
										this.origin = new BlockPos(mc.player.getPosition().add(cap2, y-3, x));
									}
								}
							}
						}
				}else{
				this.origin = new BlockPos((double)Auto32kBypass.mc.objectMouseOver.getBlockPos().getX(), (double)Auto32kBypass.mc.objectMouseOver.getBlockPos().getY(), (double)Auto32kBypass.mc.objectMouseOver.getBlockPos().getZ());
				}
				this.horizontalFace = Auto32kBypass.mc.player.getHorizontalFacing();
				this.hopperPos = this.origin.offset(this.horizontalFace.getOpposite()).up();
			} else {
				Toggle();
			}
		}
	}

    @Override
    public void onUpdate() {
        if (mc == null && Auto32kBypass.mc.player == null) {
            return;
        }
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = (ItemStack)Minecraft.getMinecraft().player.inventory.mainInventory.get(i);
            if (itemStack.getItem().equals((Object)Item.getItemFromBlock((Block)Blocks.HOPPER))) {
                this.hopperIndex = i;
            }
            if (itemStack.getItem().equals((Object)Item.getItemFromBlock((Block)Blocks.OBSIDIAN))) {
                this.obiIndex = i;
            }
            if (itemStack.getItem() instanceof ItemShulkerBox) {
                this.shulkerIndex = i;
            }
            if (itemStack.getItem().equals((Object)Item.getItemFromBlock((Block)Blocks.REDSTONE_BLOCK))) {
                this.redstoneIndex = i;
            }
            if (!itemStack.getItem().equals((Object)Item.getItemFromBlock((Block)Blocks.DISPENSER))) continue;
            this.dispenserIndex = i;
        }
        ++this.placeTick;
        if (this.checkNulls()) {
            if (this.placeTick == 3) {
                Vec3d vec = new Vec3d((double)this.origin.getX(), (double)this.origin.getY(), (double)this.origin.getZ());
                this.changeItem(this.obiIndex);
                this.placeBlock(this.origin, EnumFacing.UP, vec);
                this.changeItem(this.dispenserIndex);
                this.placeBlock(this.origin.up(), EnumFacing.UP, vec);
                BlockPos dispenserPos = this.origin.up().up();
                this.faceBlock(dispenserPos, EnumFacing.DOWN);
                Auto32kBypass.mc.playerController.processRightClickBlock(Auto32kBypass.mc.player, Auto32kBypass.mc.world, dispenserPos, EnumFacing.UP, new Vec3d((double)dispenserPos.getX(), (double)dispenserPos.getY(), (double)dispenserPos.getZ()), EnumHand.MAIN_HAND);
                Auto32kBypass.mc.player.swingArm(EnumHand.MAIN_HAND);
                this.changeItem(this.shulkerIndex);
				this.oldslot = shulkerIndex;
                this.placeTick = 4;
            }
            if (this.placeTick == HL32K.disdelay + 4) {
                Auto32kBypass.mc.playerController.windowClick(Auto32kBypass.mc.player.openContainer.windowId, 1, Auto32kBypass.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Auto32kBypass.mc.player);
                Auto32kBypass.mc.player.closeScreen();
                Auto32kBypass.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Minecraft.getMinecraft().player, CPacketEntityAction.Action.START_SNEAKING));
                EnumFacing left = null;
                EnumFacing right = null;
                if (this.horizontalFace == EnumFacing.NORTH) {
                    left = EnumFacing.WEST;
                    right = EnumFacing.EAST;
                } else if (this.horizontalFace == EnumFacing.EAST) {
                    left = EnumFacing.NORTH;
                    right = EnumFacing.SOUTH;
                } else if (this.horizontalFace == EnumFacing.SOUTH) {
                    left = EnumFacing.EAST;
                    right = EnumFacing.WEST;
                } else if (this.horizontalFace == EnumFacing.WEST) {
                    left = EnumFacing.SOUTH;
                    right = EnumFacing.NORTH;
                }
                this.changeItem(this.redstoneIndex);
                if (left != null && right != null) {
                    BlockPos dispenserPos = this.origin.up().up();
                    if (this.isAir(dispenserPos.offset(left))) {
                        this.placeBlock(dispenserPos, left.getOpposite(), new Vec3d((double)dispenserPos.getX(), (double)dispenserPos.getY(), (double)dispenserPos.getZ()));
                    } else if (this.isAir(dispenserPos.offset(right))) {
                        this.placeBlock(dispenserPos, right.getOpposite(), new Vec3d((double)dispenserPos.getX(), (double)dispenserPos.getY(), (double)dispenserPos.getZ()));
                    }
                }
                Auto32kBypass.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Minecraft.getMinecraft().player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.placeTick = HL32K.disdelay + 4;
            }
            if (this.placeTick == HL32K.disdelay + 15) {
                this.changeItem(this.hopperIndex);
                BlockPos obiBase = this.origin.up();
                this.placeBlock(obiBase, this.horizontalFace.getOpposite(), new Vec3d((double)obiBase.getX(), (double)obiBase.getY(), (double)obiBase.getZ()));
				this.changeItem(this.oldslot);
                this.faceBlock(this.hopperPos, EnumFacing.UP);
                Auto32kBypass.mc.playerController.processRightClickBlock(Auto32kBypass.mc.player, Auto32kBypass.mc.world, this.hopperPos, EnumFacing.UP, new Vec3d((double)this.hopperPos.getX(), (double)this.hopperPos.getY(), (double)this.hopperPos.getZ()), EnumHand.MAIN_HAND);
                Auto32kBypass.mc.player.swingArm(EnumHand.MAIN_HAND);
				Auto32kModule.shouldKillaura = true;
				Toggle();
            }
			
        } else {
            Toggle();
        }
    }

    public boolean checkNulls() {
        return this.hopperIndex != -1 && this.shulkerIndex != -1 && this.redstoneIndex != -1 && this.dispenserIndex != -1 && this.obiIndex != -1 && this.origin != null && this.horizontalFace != null && this.hopperPos != null;
    }

    public boolean isAir(BlockPos pos) {
        return this.getBlock(pos) instanceof BlockAir;
    }

    public Block getBlock(BlockPos pos) {
        return Auto32kBypass.mc.world.getBlockState(pos).getBlock();
    }

    public void changeItem(int slot) {
        Auto32kBypass.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
        Auto32kBypass.mc.player.inventory.currentItem = slot;
    }

    public void placeBlock(BlockPos pos, EnumFacing facing, Vec3d vec) {
        Vec3d hitVec = new Vec3d((Vec3i)pos.offset(facing)).add(new Vec3d(facing.getDirectionVec()).scale(0.5));
        faceVectorPacketInstant(hitVec);
        Auto32kBypass.mc.playerController.processRightClickBlock(Minecraft.getMinecraft().player, Minecraft.getMinecraft().world, pos, facing, vec, EnumHand.MAIN_HAND);
        Auto32kBypass.mc.player.swingArm(EnumHand.MAIN_HAND);
    }

    public void faceBlock(BlockPos pos, EnumFacing face) {
        Vec3d hitVec = new Vec3d((Vec3i)pos.offset(face)).add(new Vec3d(face.getDirectionVec()).scale(0.5));
        faceVectorPacketInstant(hitVec);
    }
	
	public static void faceVectorPacketInstant(Vec3d hitVec) {
    }
	

}

