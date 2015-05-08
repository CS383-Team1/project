package cs383.team1.model;

import cs383.team1.input.InputManager;
import cs383.team1.model.combat.CombatManager;
import cs383.team1.model.overworld.Area;
import cs383.team1.model.overworld.AreaManager;

public interface GameManagerInterface {
	public void load();
	public void update(InputManager in);
	public void setKey(int k);
	public int getKey();
	public AreaManager areas();
	public CombatManager combat();
	public Area currentArea();
	public void addMessage(String m);
	public String getMessage(int m);
	public void removeMessage(int m);
}
