package net.momirealms.craftengine.bukkit.util;

import net.momirealms.craftengine.core.plugin.CraftEngine;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.Contract;

import java.lang.reflect.InvocationTargetException;

public class ItemUtils {

    @Contract("null -> true")
    public static boolean isEmpty(final ItemStack item) {
        if (item == null) return true;
        if (item.getType() == Material.AIR) return true;
        return item.getAmount() == 0;
    }

    // 1.21.4+
    public static void setItem(PlayerInventory inventory, int slot, ItemStack itemStack) {
        try {
            Object nmsInventory = Reflections.method$CraftInventoryPlayer$getInventory.invoke(inventory);
            Object nmsInventory$items = Reflections.field$Inventory$items.get(nmsInventory);
            Object nmsItemStack = Reflections.method$CraftItemStack$asNMSMirror.invoke(null, itemStack);
            Reflections.method$NonNullList$set.invoke(nmsInventory$items, slot, nmsItemStack);
        } catch (InvocationTargetException | IllegalAccessException e) {
            CraftEngine.instance().logger().warn("Failed to set item", e);
        }
    }
}
