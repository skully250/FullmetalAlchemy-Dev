package mods.fullmetalalchemy.item;


public class ItemKunai extends ItemFMA {

	@SuppressWarnings("unused")
	private int weaponDamage;

	public ItemKunai(int par1) {
		super(par1);
        this.maxStackSize = 5;
        this.weaponDamage = 7;
	}
}
