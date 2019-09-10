package haveric.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void anvilPrepare(PrepareAnvilEvent event) {
        getLogger().info("Anvil Prepare Event: ");
        AnvilInventory inventory = event.getInventory();

        ItemStack left = inventory.getItem(0);
        ItemStack right = inventory.getItem(1);
        getLogger().info("  Left: " + left + ", Right: " + right);

        if (left != null && right != null && left.getType() == Material.DIRT && right.getType() == Material.DIRT) {
            getLogger().info("  Starting                RC: " + inventory.getRepairCost() + ", Max: " + inventory.getMaximumRepairCost());

            event.setResult(new ItemStack(Material.DIRT));
            inventory.setMaximumRepairCost(100);
            inventory.setRepairCost(100);

            getLogger().info("  After Set               RC: " + inventory.getRepairCost() + ", Max: " + inventory.getMaximumRepairCost());

            HumanEntity entity = event.getView().getPlayer();
            if (entity instanceof Player) {
                Player player = (Player) entity;
                player.updateInventory();

                getLogger().info("  After Update Inv        RC: " + inventory.getRepairCost() + ", Max: " + inventory.getMaximumRepairCost());


                Bukkit.getScheduler().runTaskLater(this, () -> {
                    getLogger().info("  Before Run Task         RC: " + inventory.getRepairCost() + ", Max: " + inventory.getMaximumRepairCost());
                    inventory.setMaximumRepairCost(100);
                    inventory.setRepairCost(100);

                    getLogger().info("  After Run Task          RC: " + inventory.getRepairCost() + ", Max: " + inventory.getMaximumRepairCost());
                    player.updateInventory();

                    getLogger().info("  After Run Task + update RC: " + inventory.getRepairCost() + ", Max: " + inventory.getMaximumRepairCost());
                }, 2);
            }
        }
    }

    @EventHandler
    public void dragEvent(InventoryDragEvent event) {
        Inventory inv = event.getInventory();

        if (inv instanceof AnvilInventory) {
            getLogger().info("Drag Event: ");
            AnvilInventory inventory = (AnvilInventory) inv;
            getLogger().info("  After drag              RC: " + inventory.getRepairCost() + ", Max: " + inventory.getMaximumRepairCost());

            HumanEntity entity = event.getView().getPlayer();
            if (entity instanceof Player) {
                Player player = (Player) entity;
                player.updateInventory();

                Bukkit.getScheduler().runTaskLater(this, () -> {
                    player.updateInventory();
                }, 2);
            }
        }
    }
}
