package net.fabricmc.example.mixin;

import loom_test.type_erasure.Event;
import loom_test.type_erasure.RegistryEventStorage;
import loom_test.type_erasure.RegistryEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Registry.class)
public class RegistryMixin<V> implements RegistryEventStorage<V> {
	@Override
	public Event<RegistryEvents.EntryAdded<V>> getEntryAddedEvent() {
		return new Event<>(new Identifier("test"));
	}
}
