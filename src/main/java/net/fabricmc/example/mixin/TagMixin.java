/*
 * Copyright 2021-2022 QuiltMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.example.mixin;

import loom_test.TagHooks;
import loom_test.TagInterface;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Tag.class)
public class TagMixin implements TagInterface, TagHooks {
	@Unique
	private int loom_test$replaced;

	@Override
	public boolean hasBeenReplaced() {
		return this.loom_test$replaced > 0;
	}

	@Override
	public void loom_test$setReplacementCount(int replacementCount) {
		this.loom_test$replaced = replacementCount;
	}
}
