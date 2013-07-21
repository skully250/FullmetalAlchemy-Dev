package mods.fullmetalalchemy.core.renderer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKunaiEnder extends Render
{
    private Item field_94151_a;
    private int field_94150_f;

    public RenderKunaiEnder(Item par1, int par2)
    {
        this.field_94151_a = par1;
        this.field_94150_f = par2;
    }

    public RenderKunaiEnder(Item par1)
    {
        this(par1, 0);
    }

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
		
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return null;
	}
}
