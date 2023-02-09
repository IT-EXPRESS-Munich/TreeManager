package tech.itexpress.treemanager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TreeManager extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        // Überprüfen Sie, ob der gebrochene Block Teil eines Baums ist
        if (block.getType().toString().contains("LOG")) {
            // Überprüfen Sie, ob es andere Blöcke mit dem gleichen Typ in der Nähe gibt
            for (Block relative : getRelatives(block)) {
                // Wenn ja, brechen Sie auch diesen Block ab
                relative.breakNaturally();
            }
        }
    }

    private List<Block> getRelatives(Block block) {
        List<Block> relatives = new ArrayList<>();
        // Überprüfen Sie, ob es Blöcke über, unter, links und rechts von dem Block gibt
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 20; y++) { // Hier haben wir die maximale Höhe auf 20 geändert
                for (int z = -1; z <= 1; z++) {
                    Block relative = block.getRelative(x, y, z);
                    if (relative.getType().toString().contains("LOG")) {
                        relatives.add(relative);
                    }
                }
            }
        }
        return relatives;
    }
}
