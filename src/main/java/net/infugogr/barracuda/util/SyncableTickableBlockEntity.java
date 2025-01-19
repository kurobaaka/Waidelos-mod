package net.infugogr.barracuda.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public interface SyncableTickableBlockEntity extends TickableBlockEntity {
    List<SyncableStorage> getSyncableStorages();

    void onTick();

    @Override
    default void tick() {
        onTick();
        getSyncableStorages().forEach(SyncableStorage::sync);

        if(this instanceof UpdatableBlockEntity updatableBlockEntity) {
            updatableBlockEntity.endTick();
        }
    }


    void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf);

    void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup);

    void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup);

    NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup);
}
