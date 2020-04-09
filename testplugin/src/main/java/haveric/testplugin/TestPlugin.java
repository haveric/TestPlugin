package haveric.testplugin;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        ShapelessRecipe testRecipe = new ShapelessRecipe(new NamespacedKey(this, "test"), new ItemStack(Material.STONE));
        testRecipe.addIngredient(Material.DIRT);
        testRecipe.addIngredient(Material.STONE);

        Bukkit.addRecipe(testRecipe);
    }
}
