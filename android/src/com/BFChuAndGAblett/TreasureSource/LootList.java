/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.util.TreeMap;

/**
 * @author Brian Chu and Garrick Ablett
 *
 *
 *  Container that holds all of the loot items rolled by the calculator
 *
 */
@Deprecated
public class LootList {

    private TreeMap<Integer, LootItem> loot;
    private Integer LootListSize;

    public LootList() {
        super();
        this.loot = new TreeMap<Integer, LootItem>();
        LootListSize = 0;
    }

    /**
     * @param loot
     * @param lootListSize
     */
    public LootList(TreeMap<Integer, LootItem> loot, Integer lootListSize) {
        super();
        this.loot = loot;
        LootListSize = lootListSize;
    }

    // Getters and Setters
    /**
     * @return the loot
     */
    public TreeMap<Integer, LootItem> getLoot() {
        return loot;
    }

    /**
     * @param loot the loot to set
     */
    public void setLoot(TreeMap<Integer, LootItem> loot) {
        this.loot = loot;
    }

    /**
     * @return the lootListSize
     */
    public Integer getLootListSize() {
        return LootListSize;
    }

    /**
     * @param lootListSize the lootListSize to set
     */
    public void setLootListSize(Integer lootListSize) {
        LootListSize = lootListSize;
    }

}
