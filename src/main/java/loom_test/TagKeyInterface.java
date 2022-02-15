package loom_test;

import net.fabricmc.example.mixin.TagKeyAccessor;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public interface TagKeyInterface<T> {
	default TagType type() {
		return TagType.NORMAL;
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	static <T> TagKey<T> create(RegistryKey<? extends Registry<T>> registry, Identifier id, TagType type) {
		var key = new TagKey<>(registry, id);
		((TagKeyHooks) (Object) key).loom_test$setType(type);
		return (TagKey<T>) TagKeyAccessor.getInterner().intern(key);
	}
}
