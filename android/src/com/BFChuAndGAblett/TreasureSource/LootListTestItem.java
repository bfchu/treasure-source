package com.BFChuAndGAblett.TreasureSource;

public class LootListTestItem extends LootItem {
    private int id;
    private int dLow;
    private int dHigh;

    /**
     * @param id
     * @param dLow
     * @param dHigh
     */
    public LootListTestItem(int id, int dLow, int dHigh) {
        super();
        this.id = id;
        this.dLow = dLow;
        this.dHigh = dHigh;
    }

    public LootListTestItem(int id, int dLow, int dHigh, String name,
            double gValue) {
        super();
        this.id = id;
        this.dLow = dLow;
        this.dHigh = dHigh;
        this.setName(name);
        this.setgValue(gValue);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the dLow
     */
    public int getdLow() {
        return dLow;
    }

    /**
     * @param dLow the dLow to set
     */
    public void setdLow(int dLow) {
        this.dLow = dLow;
    }

    /**
     * @return the dHigh
     */
    public int getdHigh() {
        return dHigh;
    }

    /**
     * @param dHigh the dHigh to set
     */
    public void setdHigh(int dHigh) {
        this.dHigh = dHigh;
    }

}
