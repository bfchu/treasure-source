/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian
 * 
 */
public class LootTable {
    public static final Integer TABLE_SIZE = 101;

    private String[] table;

    public LootTable() {
        super();
        this.table = new String[TABLE_SIZE];
    }

    public LootTable(String[] table) {
        super();
        this.table = table;
    }

    public String[] getTable() {
        return table;
    }

    /**
     * This should never be used in the program
     * 
     * @param table
     */
    public void setTable(String[] table) {
        this.table = table;
    }

}
