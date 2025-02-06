package net.infugogr.barracuda.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.util.SyncableStorage;
import net.infugogr.barracuda.util.SyncableTickableBlockEntity;
import net.infugogr.barracuda.util.UpdatableBlockEntity;
import net.infugogr.barracuda.util.energy.EnergySpreader;
import net.infugogr.barracuda.util.energy.SyncingEnergyStorage;
import net.infugogr.barracuda.util.energy.WrappedEnergyStorage;
import net.infugogr.barracuda.util.inventory.SyncingSimpleInventory;
import net.infugogr.barracuda.util.inventory.WrappedInventoryStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.List;

public class TeleporterBlockEntity extends UpdatableBlockEntity implements SyncableTickableBlockEntity, EnergySpreader, ExtendedScreenHandlerFactory, GeoBlockEntity {
    public static final Text TITLE = Barracuda.containerTitle("teleporter");
    private final WrappedEnergyStorage energyStorage = new WrappedEnergyStorage();
    private final WrappedInventoryStorage<SimpleInventory> inventoryStorage = new WrappedInventoryStorage<>();
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public TeleporterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.TELEPORTER, pos, state);

        this.energyStorage.addStorage(new SyncingEnergyStorage(this, 10000000, 10000, 0));
        this.inventoryStorage.addInventory(new SyncingSimpleInventory(this, 4));
    }



    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return TITLE;
    }
    @Override
    public List<SyncableStorage> getSyncableStorages() {
        var energy = (SyncingEnergyStorage) this.energyStorage.getStorage(null);
        var inventory = (SyncingSimpleInventory) this.inventoryStorage.getInventory(0);
        assert inventory != null;
        return List.of(energy, inventory);
    }
    @Override
    public void onTick() {
    }
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return null;//new TeleporterScreenHandler(syncId, playerInventory, this);
    }
    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.put("EnergyStorage", this.energyStorage.writeNbt());
        nbt.put("Inventory", this.inventoryStorage.writeNbt());
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.energyStorage.readNbt(nbt.getList("EnergyStorage", NbtElement.COMPOUND_TYPE));
        this.inventoryStorage.readNbt(nbt.getList("Inventory", NbtElement.COMPOUND_TYPE));
    }
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public boolean isValid(ItemStack itemStack, int slot) {
        return slot == 0;
    }
    public EnergyStorage getEnergyProvider(Direction direction) {
        return this.energyStorage.getStorage(direction);
    }
    public InventoryStorage getInventoryProvider(Direction direction) {
        return this.inventoryStorage.getStorage(direction);
    }
    public SimpleEnergyStorage getEnergyStorage() {
        return this.energyStorage.getStorage(null);
    }
    public WrappedInventoryStorage<SimpleInventory> getWrappedInventoryStorage() {
        return this.inventoryStorage;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("teleport", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
