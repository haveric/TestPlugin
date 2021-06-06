package haveric.testplugin;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        ItemStack spawnerItemStack = new ItemStack(Material.SPAWNER);

        BlockStateMeta blockStateMeta = (BlockStateMeta) spawnerItemStack.getItemMeta();
        BlockState blockState = blockStateMeta.getBlockState();

        CreatureSpawner spawner = (CreatureSpawner) blockState;
        spawner.setSpawnedType(EntityType.CHICKEN);
        spawner.setMinSpawnDelay(1);
        spawner.setMaxSpawnDelay(3);
        spawner.setMaxNearbyEntities(10);
        spawner.setRequiredPlayerRange(5);
        spawner.setSpawnRange(4);
        spawner.setSpawnCount(3);

        spawner.update();
        blockStateMeta.setBlockState(spawner);
        spawnerItemStack.setItemMeta(blockStateMeta);

        ShapelessRecipe testRecipe = new ShapelessRecipe(new NamespacedKey(this, "test"), spawnerItemStack);
        testRecipe.addIngredient(Material.DIRT);
        testRecipe.addIngredient(Material.STONE);

        Bukkit.addRecipe(testRecipe);
    }
}
