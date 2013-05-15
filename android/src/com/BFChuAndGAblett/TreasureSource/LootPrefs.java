/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.util.Arrays;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 *         Container class that holds all of the values the user inputs for loot
 *         generation.
 * 
 */
public class LootPrefs {

    private static final Integer NUM_RESTRICTIONS = 9; // [1] = armor, ect...
    private static final Integer NUM_DISPLAY_OPTS = 3;// [ ]

    private Integer aPL; // Average Party Level
    private Integer eCR; // Encounter Challenge Rating
    private Integer enDifficulty; // Encounter Difficulty
    private Integer lootSize;
    private Integer magicLv;
    private Double resGold; // Residual Gold ammount %
    private boolean rollMundane;
    private boolean rollGoods;
    private boolean noRepeats;
    private boolean limitValByCR;
    private boolean[] itemRestrictions = new boolean[NUM_RESTRICTIONS]; //
    private boolean[] displayOpts = new boolean[NUM_DISPLAY_OPTS];

    public LootPrefs() {
        super();
        this.aPL = 1; // Default values
        this.eCR = 1;
        this.enDifficulty = 3;
        this.lootSize = 3;
        this.magicLv = 2;
        this.resGold = .1;
        this.rollMundane = true;
        this.rollGoods = true;
        this.noRepeats = true;
        this.limitValByCR = false;
        this.itemRestrictions = new boolean[NUM_RESTRICTIONS];
        this.displayOpts = new boolean[NUM_DISPLAY_OPTS];
    }

    public LootPrefs(Integer aPL, Integer eCR, Integer enDifficulty,
            Integer lootSize, Integer magicLv, Double resGold,
            boolean rollMundane, boolean rollGoods, boolean noRepeats,
            boolean limitValByCR, boolean[] itemRestrictions,
            boolean[] displayOpts) {
        super();
        this.aPL = aPL;
        this.eCR = eCR;
        this.enDifficulty = enDifficulty;
        this.lootSize = lootSize;
        this.magicLv = magicLv;
        this.resGold = resGold;
        this.rollMundane = rollMundane;
        this.rollGoods = rollGoods;
        this.noRepeats = noRepeats;
        this.limitValByCR = limitValByCR;
        this.itemRestrictions = itemRestrictions;
        this.displayOpts = displayOpts;
    }

    public Integer getAPL() {
        return aPL;
    }

    public void setAPL(Integer aPL) {
        this.aPL = aPL;
    }

    public Integer getECR() {
        return eCR;
    }

    public void setECR(Integer eCR) {
        this.eCR = eCR;
    }

    public Integer getEnDifficulty() {
        return enDifficulty;
    }

    public void setEnDifficulty(Integer enDifficulty) {
        this.enDifficulty = enDifficulty;
    }

    public Integer getLootSize() {
        return lootSize;
    }

    public void setLootSize(Integer lootSize) {
        this.lootSize = lootSize;
    }

    public Integer getMagicLv() {
        return magicLv;
    }

    public void setMagicLv(Integer magicLv) {
        this.magicLv = magicLv;
    }

    public Double getResGold() {
        return resGold;
    }

    public void setResGold(Double resGold) {
        this.resGold = resGold;
    }

    public boolean isRollMundane() {
        return rollMundane;
    }

    public void setRollMundane(boolean rollMundane) {
        this.rollMundane = rollMundane;
    }

    public boolean isRollGoods() {
        return rollGoods;
    }

    public void setRollGoods(boolean rollGoods) {
        this.rollGoods = rollGoods;
    }

    public boolean isNoRepeats() {
        return noRepeats;
    }

    public void setNoRepeats(boolean noRepeats) {
        this.noRepeats = noRepeats;
    }

    public boolean isLimitValByCR() {
        return limitValByCR;
    }

    public void setLimitValByCR(boolean limitValByCR) {
        this.limitValByCR = limitValByCR;
    }

    public boolean[] getItemRestrictions() {
        return itemRestrictions;
    }

    public void setItemRestrictions(boolean[] itemRestrictions) {
        this.itemRestrictions = itemRestrictions;
    }

    public boolean[] getDisplayOpts() {
        return displayOpts;
    }

    public void setDisplayOpts(boolean[] displayOpts) {
        this.displayOpts = displayOpts;
    }

    @Override
    public String toString() {
        return "LootPrefs [aPL=" + aPL + ", eCR=" + eCR + ", enDifficulty="
                + enDifficulty + ", lootSize=" + lootSize + ", magicLv="
                + magicLv + ", resGold=" + resGold + ", rollMundane="
                + rollMundane + ", rollGoods=" + rollGoods + ", noRepeats="
                + noRepeats + ", limitValByCR=" + limitValByCR
                + ", itemRestrictions=" + Arrays.toString(itemRestrictions)
                + ", displayOpts=" + Arrays.toString(displayOpts) + "]";
    }

}
