package cs383.team1.net;

public interface Subject {
	public void attach(Observer o);
	public void detach(Observer o);
	public void update();
}
