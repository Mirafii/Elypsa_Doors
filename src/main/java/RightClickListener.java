import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class RightClickListener implements Listener {

    private Random rand;

    public RightClickListener() {

        rand = new Random();

    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e) {

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player p = e.getPlayer();
            Block b = e.getClickedBlock();
            if (b != null) {
                if (isDoor(b)) {
                    //check if chest
                    Location chestLoc = b.getLocation().add(0, -2, 0);
                    if (chestLoc.getBlockY() >= 0) {
                        Block possibleChest = chestLoc.getBlock();
                        if (possibleChest.getType() == Material.CHEST) {

                            Chest chest = (Chest) possibleChest.getState();
                            Inventory i = chest.getBlockInventory();
                            HashMap<Integer, ? extends ItemStack> paperList = i.all(Material.PAPER);

                            boolean goodName = false;
                            if (!paperList.isEmpty()) {

                                Iterator<? extends ItemStack> it = paperList.values().iterator();
                                ItemStack item;
                                while (!goodName && it.hasNext()) {
                                    item = it.next();
                                    if (item.hasItemMeta() && (item.getItemMeta() != null) && item.getItemMeta().getDisplayName().equalsIgnoreCase(p.getName())) {
                                        goodName = true;
                                    }
                                }
                                if (!goodName) {
                                    //Si il y a un coffre, et que le nom n'est pas dans la liste, on cancell l'event
                                    if (p.getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK) {

                                        int alea = rand.nextInt(4);
                                        if (alea == 1) {

                                            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                                            p.sendMessage(ChatColor.BLUE + "** Votre outil se brise après une utilisation fructueuse. **");
                                        } else {
                                            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                                            p.sendMessage(ChatColor.BLUE + "** Votre outil se brise dans vos mains. **");
                                            e.setCancelled(true);

                                        }
                                    } else {
                                        e.setCancelled(true);
                                        p.sendMessage(ChatColor.BLUE + "** La porte est verrouillée. **");
                                    }
                                }


                            }
                        }

                    }
                } else if (isChest(b)) {

                    if (b.getState() instanceof Container) { //pas vraiment nécessaire mais bon

                        Inventory i = ((Container) b.getState()).getInventory();

                        HashMap<Integer, ? extends ItemStack> paperList = i.all(Material.PAPER);

                        boolean goodName = false;
                        if (!paperList.isEmpty()) {

                            Iterator<? extends ItemStack> it = paperList.values().iterator();
                            ItemStack item;
                            while (!goodName && it.hasNext()) {
                                item = it.next();
                                if (item.hasItemMeta() && (item.getItemMeta() != null) && item.getItemMeta().getDisplayName().equalsIgnoreCase(p.getName())) {
                                    goodName = true;
                                }
                            }
                            if (!goodName) {
                                if (p.getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK) {

                                    int alea = rand.nextInt(4);
                                    if (alea == 1) {

                                        p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                                        p.sendMessage(ChatColor.BLUE + "** Votre outil se brise après une utilisation fructueuse. **");
                                    } else {
                                        p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                                        p.sendMessage(ChatColor.BLUE + "** Votre outil se brise dans vos mains. **");
                                        e.setCancelled(true);

                                    }
                                } else {
                                    e.setCancelled(true);
                                    p.sendMessage(ChatColor.BLUE + "** C'est verrouillé. **");
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    private boolean isChest(Block b) {

        return b.getType() == Material.CHEST
                || b.getType() == Material.BARREL
                || b.getType() == Material.SHULKER_BOX;


    }

    private boolean isDoor(Block b) {

        return b.getType() == Material.ACACIA_DOOR
                || b.getType() == Material.BIRCH_DOOR
                || b.getType() == Material.DARK_OAK_DOOR
                || b.getType() == Material.CRIMSON_DOOR
                || b.getType() == Material.JUNGLE_DOOR
                || b.getType() == Material.OAK_DOOR
                || b.getType() == Material.SPRUCE_DOOR
                || b.getType() == Material.WARPED_DOOR;
//                || b.getType() == Material.IRON_DOOR

    }


}
