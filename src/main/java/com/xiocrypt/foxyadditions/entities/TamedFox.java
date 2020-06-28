package com.xiocrypt.foxyadditions.entities;

import com.xiocrypt.foxyadditions.FoxyAdditionsMod;
import com.xiocrypt.foxyadditions.util.registries.EntityReg;
import com.xiocrypt.foxyadditions.util.registries.ItemReg;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class TamedFox extends AnimalEntity {

    private static final DataParameter<Integer> TAMEDFOX_TYPE = EntityDataManager.createKey(TamedFox.class, DataSerializers.VARINT);

    public TamedFox(EntityType<? extends TamedFox> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(TAMEDFOX_TYPE, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this,2.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this,1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this,1.1D,Ingredient.fromItems(ItemReg.GOLDEN_SWEET_BERRIES.get()),false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this,1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this,1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3F);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    public void writeAdditional(@Nonnull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("Type", this.getVariantType().getName());
    }

    public void readAdditional(@Nonnull CompoundNBT compound) {
        super.readAdditional(compound);
        this.setVariantType(TamedFox.Type.getTypeByName(compound.getString("Type")));
    }

    @Nonnull
    public TamedFox.Type getVariantType() {
        return TamedFox.Type.getTypeByIndex(this.dataManager.get(TAMEDFOX_TYPE));
    }

    private void setVariantType(TamedFox.Type typeIn) {
        this.dataManager.set(TAMEDFOX_TYPE, typeIn.getIndex());
    }

    @Override
    public TamedFox createChild(@Nonnull AgeableEntity ageable) {
        TamedFox entity = new TamedFox(EntityReg.TAMEDFOX.get(), this.world);
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)), SpawnReason.BREEDING,null,null);
        entity.setVariantType(this.rand.nextBoolean() ? this.getVariantType() : ((TamedFox)ageable).getVariantType());
        return entity;
    }

    @Override
    public ILivingEntityData onInitialSpawn(@Nonnull IWorld worldIn, @Nonnull DifficultyInstance difficultyIn, @Nonnull SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        ILivingEntityData livingEntity = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if(this.isChild()){
            FoxyAdditionsMod.LOGGER.info("Set Growing Age");
            this.setGrowingAge(-30000);
        }
        TamedFox.Type foxentity$type = TamedFox.Type.getTypeByIndex(this.rand.nextInt(7));
        if (spawnDataIn instanceof TamedFox.FoxData) {
            foxentity$type = ((FoxData)spawnDataIn).type;
        }

        this.setVariantType(foxentity$type);
        return livingEntity;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == ItemReg.GOLDEN_SWEET_BERRIES.get();
    }

    @Override
    protected void consumeItemFromStack( @Nonnull PlayerEntity player, @Nonnull ItemStack stack) {
        if (this.isBreedingItem(stack)) {
            this.playSound(this.getEatSound(stack), 1.0F, 1.0F);
        }
        super.consumeItemFromStack(player, stack);
    }

    @Override
    protected float getStandingEyeHeight( @Nonnull Pose poseIn, @Nonnull EntitySize sizeIn) {
        return this.isChild() ? sizeIn.height * 0.85F : 0.4F;
    }

    @Override
    public void livingTick() {
        super.livingTick();
    }

    public static class FoxData extends AgeableEntity.AgeableData {
        public final TamedFox.Type type;

        public FoxData(TamedFox.Type typeIn) {
            this.func_226259_a_(false);
            this.type = typeIn;
        }
    }

    //Type
    public enum Type {
        RED(0,"red"),
        SNOW(1,"snow"),
        GREY(2,"grey"),
        BAT(3,"bat"),
        SILVER(4,"silver"),
        FENNEC(5,"fennec"),
        MARBLE(6,"marble");

        private static final TamedFox.Type[] TYPES_BY_INDEX = Arrays.stream(values()).sorted(Comparator.comparingInt(TamedFox.Type::getIndex)).toArray(Type[]::new);
        private static final Map<String, TamedFox.Type> TYPES_BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(TamedFox.Type::getName, (newName) -> newName));

        private String name;
        private int index;

        Type(int index,String name){
            this.name = name;
            this.index = index;
        }




        public String getName(){ return this.name; }
        public int getIndex() {
            return this.index;
        }

        public static TamedFox.Type getTypeByName(String nameIn) {
            return TYPES_BY_NAME.getOrDefault(nameIn, RED);
        }
        public static TamedFox.Type getTypeByIndex(int indexIn) {
            if (indexIn < 0 || indexIn > 7) {
                indexIn = 0;
            }

            return TYPES_BY_INDEX[indexIn];
        }
    }

    //Custom Goals

    //Overridden Goals
}
