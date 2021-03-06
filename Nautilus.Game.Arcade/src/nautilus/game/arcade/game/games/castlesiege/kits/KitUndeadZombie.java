package nautilus.game.arcade.game.games.castlesiege.kits;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import mineplex.core.disguise.disguises.DisguiseZombie;
import mineplex.core.itemstack.ItemStackFactory;
import nautilus.game.arcade.ArcadeManager;
import nautilus.game.arcade.kit.Kit;
import nautilus.game.arcade.kit.KitAvailability;
import nautilus.game.arcade.kit.Perk;
import nautilus.game.arcade.kit.perks.*;

public class KitUndeadZombie extends KitUndead
{
	public KitUndeadZombie(ArcadeManager manager)
	{
		super(manager, "Undead Zombie", KitAvailability.Gem, 5000,

				new String[] 
						{
				"Regenerates rapidly"
						}, 

						new Perk[] 
								{
				new PerkRegeneration(2)
								}, 
								EntityType.ZOMBIE,
								new ItemStack(Material.STONE_AXE));

	}

	@Override 
	public void GiveItems(Player player)
	{
		player.getInventory().addItem(ItemStackFactory.Instance.CreateStack(Material.STONE_AXE));
		
		DisguiseZombie disguise = new DisguiseZombie(player);
		
		if (Manager.GetGame().GetTeam(player) != null)
		{
			disguise.setName(Manager.GetGame().GetTeam(player).GetColor() + player.getName());
			disguise.setCustomNameVisible(true);
		}
		
		Manager.GetDisguise().disguise(disguise);
	}

	@EventHandler
	public void PickupArrow(PlayerPickupItemEvent event)
	{
		if (!HasKit(event.getPlayer()))
			return;
		
		if (event.getItem().getItemStack().getType() == Material.ARROW)
			event.setCancelled(true);
	}
}
