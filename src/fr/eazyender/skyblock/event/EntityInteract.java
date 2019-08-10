package fr.eazyender.skyblock.event;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityInteract implements Listener{

	@EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntity() instanceof Creeper) {
            
        	event.setCancelled(true);
        	
        }
    }
	
	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent e) {
		if (e.getEntity() == null)
			return;

		if (e.getEntity().getType() == EntityType.ENDERMAN) {
				e.setCancelled(true);
			}
			return;
		}

	
	
}
