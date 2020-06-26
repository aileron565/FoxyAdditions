package com.xiocrypt.foxyadditions.entities;

import com.xiocrypt.foxyadditions.FoxyAdditionsMod;
import com.xiocrypt.foxyadditions.util.RegistryHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TamedFox extends AnimalEntity {

    private int tamedFoxTimer;

    public TamedFox(EntityType<? extends TamedFox> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this,1D));//2.2D
        this.goalSelector.addGoal(2, new BreedGoal(this,1.0D));
        //this.goalSelector.addGoal(3, new TemptGoal(this,1.1D, Ingredient.fromItems(Items.SWEET_BERRIES),false));
        this.goalSelector.addGoal(3, new TemptGoal(this,1.1D,Ingredient.fromItems(RegistryHandler.GOLDEN_SWEET_BERRIES.get()),false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this,1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this,1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.3F);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    public TamedFox createChild(AgeableEntity ageable) {
        TamedFox entity = new TamedFox(RegistryHandler.TAMEDFOX.get(), this.world);
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)), SpawnReason.BREEDING,(ILivingEntityData)null,(CompoundNBT) null);
        return entity;
    }

    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        ILivingEntityData livingEntity = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if(reason == SpawnReason.BREEDING){
            FoxyAdditionsMod.LOGGER.info("Set Growing Age");
            this.setGrowingAge(-30000);
        }
        return livingEntity;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == RegistryHandler.GOLDEN_SWEET_BERRIES.get();
    }

    @Override
    protected void consumeItemFromStack(PlayerEntity player, ItemStack stack) {
        if (this.isBreedingItem(stack)) {
            //this.playSound(this.getEatSound(stack), 1.0F, 1.0F);
        }
        super.consumeItemFromStack(player, stack);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? sizeIn.height * 0.85F : 0.4F;
    }

    @Override
    public void livingTick() {
        if(this.world.isRemote){
            this.tamedFoxTimer = Math.max(0,this.tamedFoxTimer-1);
        }
        super.livingTick();
    }
}
