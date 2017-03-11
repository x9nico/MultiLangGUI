package fr.x9nico.multilang;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.smessie.MultiLanguage.api.Language;
import me.smessie.MultiLanguage.bukkit.AdvancedMultiLanguageAPI;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if((sender instanceof Player)){
			if(command.getName().equalsIgnoreCase("lang")){
				Player p = (Player)sender;
				if(args.length == 0){
					Menu(p);
				}
			}
		}
		return true;
	}
	
	private void Menu(Player p){
		Inventory inv = Bukkit.createInventory(null, 9, "§6Langues");
		
		ItemStack fr = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta frm = fr.getItemMeta();
		frm.setDisplayName("§aFrançais");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§e");
		lore.add("§eCliquez ici pour passer en langue française !");
		frm.setLore(lore);
		fr.setItemMeta(frm);
		p.updateInventory();
		
		inv.setItem(0, fr);
		
		
		ItemStack en = new ItemStack(Material.APPLE);
		ItemMeta enm = en.getItemMeta();
		enm.setDisplayName("§aEnglish");
		ArrayList<String> lore1 = new ArrayList<>();
		lore1.add("§e");
		lore1.add("§eClick here for switch in english's language !");
		enm.setLore(lore1);
		en.setItemMeta(enm);
		p.updateInventory();
		
		inv.setItem(2, en);
		
		p.openInventory(inv);
	}
	
	@EventHandler
	public void click(InventoryClickEvent e){
		if(e.getInventory().getName().equalsIgnoreCase("§6Langues")){
			Player p = (Player) e.getWhoClicked();
			String uuid = p.getUniqueId().toString();
			if(e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR) return;
			
			switch(e.getCurrentItem().getType()){
			
			case GOLDEN_APPLE:
				p.closeInventory();
				AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, Language.FRENCH);
				p.sendMessage("§aLa langue du serveur pour vous est désormais en §6Français §a!");
				p.sendMessage("§aNous vous recommandons de vous reconnecter au serveur afin que cela prenne effet");
				break;
				
			case APPLE:
				p.closeInventory();
				AdvancedMultiLanguageAPI.setPlayerLanguage(uuid, Language.ENGLISH);
				p.sendMessage("§aThe language on the server for you has been set in §6Englsih §a!");
				p.sendMessage("§aWe recommend that you connect to the server so that it takes effect");
				break;
			
			default:
				break;
			}
		}
	}

}
