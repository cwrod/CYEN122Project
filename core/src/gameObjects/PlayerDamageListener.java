package gameObjects;

import gameObjects.PlayerObject.PlayerActions;

public interface PlayerDamageListener
{
	public void damageTaken(EnemyObject source, float damage);
}
