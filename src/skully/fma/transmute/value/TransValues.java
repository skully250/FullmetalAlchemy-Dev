package skully.fma.transmute.value;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import skully.fma.energy.FMAPower;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


public class TransValues {

	private static List<Block> blocks = new ArrayList<Block>();

	// private static List DTrans = new Block.blocksList[5];

	public static Block[] block = Block.blocksList;

	public static int getRandomID() {

		return getRandomBlock().blockID;
	}

	/**
	 * Only called if the pSTone is active
	 *
	 */
	public static Block getRandomBlock() {

		for(Block block : Block.blocksList) {
			if(block != null)
			{
				boolean hasTile = block.hasTileEntity(0);

				if (block.blockID != 0 && !(hasTile) && block.blockID == Block.dirt.blockID) {
					//this.add(DTrans);
				}
				else if(block.blockID != 0 && !(hasTile)) {
					Material mat = block.blockMaterial != null  ? block.blockMaterial : Material.air;

					if(mat.isSolid()) {
						if (FMAPower.transEnergy > 0){
							FMAPower.modifyEnergy(-5);
							blocks.add(block);
						}
					}
				}
			}

		}

		Random rand = new Random();

		int random = rand.nextInt(blocks.size());

		Block block = blocks.get(random);

		return block;
	}
}