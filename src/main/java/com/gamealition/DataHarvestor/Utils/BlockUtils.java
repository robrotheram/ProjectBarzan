package com.gamealition.DataHarvestor.Utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.*;

/**
 * Created by robert on 04/01/2017.
 */
public class BlockUtils {
    private static String otherID;
    public static String getFullName(Block block){
        switch (block.getType()){
            case LEAVES:
                return Material.LEAVES.name()+"_"+((Leaves)block.getState().getData()).getSpecies().name();
            case GRASS:
                return Material.GRASS.name()+"_"+block.getBiome().name();
            case WOOL:
                return Material.WOOL.name()+"_COLORED_"+((Wool)block.getState().getData()).getColor().name();
            case STAINED_CLAY:
                return Material.STAINED_CLAY.name()+"_STAINED_"+((Colorable)block.getState().getData()).getColor().name();
            case STAINED_GLASS:
                return Material.GLASS.name()+"_"+((Colorable)block.getState().getData()).getColor().name();
            case STAINED_GLASS_PANE:
                return Material.GLASS.name()+"_PANE_TOP_"+((Colorable)block.getState().getData()).getColor().name();
            case SANDSTONE:
                return Material.SANDSTONE.name()+"_"+((Sandstone)block.getState().getData()).getType().name();
            case SMOOTH_BRICK:
                otherID = "";
                switch (block.getState().getRawData()){
                    case 0: otherID=""; break;
                    case 1: otherID="MOSSY"; break;
                    case 2: otherID="CRACKED"; break;
                    case 3: otherID="CARVED"; break;
                }
                return Material.SMOOTH_BRICK.name()+"_"+otherID;
            case RED_SANDSTONE:
                otherID = "";
                switch (block.getState().getRawData()){
                    case 0: otherID="NORMAL"; break;
                    case 1: otherID="CARVED"; break;
                    case 2: otherID="SMOOTH"; break;
                }
                return Material.RED_SANDSTONE.name()+"_"+otherID;
            case STONE:
                otherID = "";
                switch (block.getState().getRawData()){
                    case 0: otherID=""; break;
                    case 1: otherID="GRANITE"; break;
                    case 2: otherID="GRANITE_SMOOTH"; break;
                    case 3: otherID="DIORITE"; break;
                    case 4: otherID="DIORITE_SMOOTH"; break;
                    case 5: otherID="ANDESITE"; break;
                    case 6: otherID="ANDESITE_SMOOTH"; break;
                }
                return Material.STONE.name()+"_"+otherID;
            case DIRT:
                otherID = "";
                switch (block.getState().getRawData()){
                    case 0: otherID="DIRT"; break;
                    case 1: otherID="DIRT_COARSE"; break;
                    case 2: otherID="DIRT_PODZOL_TOP"; break;
                }
                return otherID;
            default:
                return block.getType().name();
        }


    }

}
