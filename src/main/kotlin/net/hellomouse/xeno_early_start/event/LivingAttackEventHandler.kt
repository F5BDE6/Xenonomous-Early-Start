package net.hellomouse.xeno_early_start.event

import net.hellomouse.xeno_early_start.ProgressionMod
import net.minecraft.entity.LivingEntity
import net.minecraftforge.event.entity.living.LivingAttackEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@Suppress("UNUSED_PARAMETER")
@EventBusSubscriber(modid = ProgressionMod.MODID, bus = EventBusSubscriber.Bus.FORGE)
object LivingAttackEventHandler {
    @SubscribeEvent
    fun onAttackEvent(event: LivingAttackEvent) {
        val attacked = event.entity
        val attacker = event.source.attacker
        if (attacker != null) {
            val f = attacker.world.getLocalDifficulty(attacker.blockPos).localDifficulty
            if ((attacker is LivingEntity && attacker.mainHandStack.isEmpty) || (attacker !is LivingEntity)) {
                if (attacker.isOnFire() && attacker.world.getRandom()
                        .nextFloat() < f * 0.3f
                ) {
                    attacked.setOnFireFor(2 * f.toInt())
                }
            }
        }

    }
}