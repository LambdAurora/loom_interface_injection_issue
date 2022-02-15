package net.fabricmc.example.mixin;

import com.google.gson.JsonObject;
import loom_test.TagBuilderInterface;
import loom_test.TagHooks;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Tag.Builder.class)
public abstract class TagBuilderMixin implements TagBuilderInterface {
	@Final
	@Shadow
	private List<Tag.TrackedEntry> entries;

	@Unique
	private int loom_test$replacementCount;

	@ModifyArg(
			method = "build",
			at = @At(
					value = "INVOKE",
					target = "Lcom/mojang/datafixers/util/Either;right(Ljava/lang/Object;)Lcom/mojang/datafixers/util/Either;",
					remap = false
			)
	)
	private Object onBuild(Object tag) {
		((TagHooks) tag).loom_test$setReplacementCount(this.loom_test$replacementCount);
		return tag;
	}

	@Inject(method = "read", at = @At(value = "INVOKE", target = "Ljava/util/List;clear()V"))
	public void onFromJsonClear(JsonObject json, String packName, CallbackInfoReturnable<Tag.Builder> info) {
		this.loom_test$replacementCount++;
	}

	@Override
	@SuppressWarnings("ConstantConditions")
	public Tag.Builder clearEntries() {
		this.entries.clear();
		this.loom_test$replacementCount++;
		return (Tag.Builder) (Object) this;
	}
}
