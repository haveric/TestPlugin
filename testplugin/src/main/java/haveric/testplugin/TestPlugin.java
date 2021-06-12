package haveric.testplugin;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.BundleMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TestPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        ItemStack bundleSet = new ItemStack(Material.BUNDLE);
        ItemStack bundleAdd = new ItemStack(Material.BUNDLE);

        List<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(Material.DIRT, 32));
        items.add(new ItemStack(Material.STONE, 32));

        BundleMeta setMeta = (BundleMeta) bundleSet.getItemMeta();
        setMeta.setItems(items);
        bundleSet.setItemMeta(setMeta);

        BundleMeta addMeta = (BundleMeta) bundleAdd.getItemMeta();
        for (ItemStack item : items) {
            addMeta.addItem(item);
        }
        bundleAdd.setItemMeta(addMeta);

        ShapelessRecipe testSetRecipe = new ShapelessRecipe(new NamespacedKey(this, "testset"), bundleSet);
        ShapelessRecipe testAddRecipe = new ShapelessRecipe(new NamespacedKey(this, "testadd"), bundleAdd);

        testSetRecipe.addIngredient(Material.DIRT);
        testAddRecipe.addIngredient(Material.STONE);

        Bukkit.addRecipe(testSetRecipe);
        Bukkit.addRecipe(testAddRecipe);
    }
}
