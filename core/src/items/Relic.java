package items;

public abstract class Relic extends Item
{
	protected float defenseBarrier; // size of the defense
	protected int absorbance; // how much damage that can be absorbed
	private String name;

	public Relic(int absorbance, float defenseBarrier, String name, String flavorText, int x, int y)
	{
		super(x, y, name, flavorText);
		this.absorbance = absorbance;
		this.defenseBarrier = defenseBarrier;

	}

	public Relic(int absorbance, float defenseBarrier, String name, String flavorText)
	{
		super(name, flavorText);
		this.absorbance = absorbance;
		this.defenseBarrier = defenseBarrier;
	}

	public void defend()
	{

	}
}
