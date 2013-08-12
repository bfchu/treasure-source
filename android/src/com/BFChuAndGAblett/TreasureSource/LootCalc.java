/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import android.database.Cursor;
import android.util.Log;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 *         LootCalc class handles most of math. It gathers the loot tables and
 *         dice and outputs loot Items. Contains math methods for determining
 *         how many rolls happen at various stages.
 * 
 * 
 */
public class LootCalc {

    private static final String TAG = "LootCalc";
    private LootDice dice;
    private LootDB books;
    private LootPrefs prefs;

    public LootCalc() {
        super();
    }

    /**
     * @param dice
     * @param table
     */
    public LootCalc(LootDice dice, LootDB table, LootPrefs prefs) {
        super();
        this.dice = dice;
        this.books = table;
        this.prefs = prefs;
    }

    public LootCalc(LootDB books, LootPrefs prefs) {
        super();
        this.dice = new LootDice();
        this.books = books;
        this.prefs = prefs;
    }

    // Paizo math
    /**
     * COINS
     * */
    public LootItemGold rollCoins() {
        LootItemGold coins = new LootItemGold();

        String tableIndex = "APL" + prefs.getAPL() + "_Coins";
        Integer dRoll = rollPercent();

        Integer numDice = 1;
        Integer dieSize = 6;
        Integer coinQuantity = 10;
        Integer coinType = 3; // 1 = cp, 2 = sp, 3 = gp, 4 = pp.

        Cursor cursor = books.getCoinsByAPL(tableIndex, dRoll);

        numDice = cursor.getInt(3);
        dieSize = cursor.getInt(4);
        coinQuantity = cursor.getInt(5);
        coinType = cursor.getInt(6);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollCoins() Roll: " + dRoll
                    + ", got from DB: numDice: " + numDice + ", dieSize: "
                    + dieSize + ", quantity: " + coinQuantity + ", coinType: "
                    + coinType);
        }

        coins.setCoinType(coinType);
        coins.setQuantity((int) (coinQuantity * dice.roll(numDice, dieSize) * getTreasureMultiplier()));

        if (!(coins.getQuantity() > 0)) {
            coins.setName("");
        }
        return coins;
    }

    public Integer rollCoinsQuanitity(Integer numDice, Integer dieSize) {
        double coinMultiplier = getTreasureMultiplier();
        return (int) (dice.roll(numDice, dieSize) * coinMultiplier);
    }

    public double getTreasureMultiplier() {
        // Detecting for size of treasure hoard.
        switch (prefs.getLootSize()) {
        case 1:
            return 0.25;
        case 2:
            return 0.5;
        case 3:
            return 1.0;
        case 4:
            return 2.0;
        case 5:
            return 3.0;
        }
        return 1.0;
    }

    /**
     * GOODS
     * */
    public LootItemGoods rollGoods() {
        LootItemGoods goods = new LootItemGoods();

        String tableIndex = "APL" + prefs.getAPL() + "_Goods";
        Integer dRoll = rollPercent();

        // Roll on goods chart to determine if and what kind of goods
        Integer numDice = 1;
        Integer dieSize = 6;
        Integer goodsType = 1;

        Cursor cursor = books.getGoodsByAPL(tableIndex, dRoll);

        numDice = cursor.getInt(3);
        dieSize = cursor.getInt(4);
        goodsType = cursor.getInt(5);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollGoods() Roll: " + dRoll
                    + ", got from DB: numDice: " + numDice + ", dieSize: "
                    + dieSize + ", goodsType: " + goodsType);
        }

        goods.setGoodsType(goodsType);
        // Roll number of goods
        goods.setQuantity(dice.roll(numDice, dieSize));

        // Roll value per goods
        double goodsMultiplier = getTreasureMultiplier();
        goods.setgValue(rollGoodsVal(goods.getGoodsType()) * goodsMultiplier);

        if (!(goods.getQuantity() > 0)) {
            goods.setName("");
        }
        return goods;
    }

    public Double rollGoodsVal(Integer goodsType) {
        Double val = 1.0;
        int numDice = 1;
        int dieSize = 6;
        int coinVal = 10;
        int dRoll = rollPercent();

        // TODO: replace with database calls
        // Hard-coded table for Gems and Art value ranges
        if (goodsType != 2) { // Gems table
            if (rollIsBetween(dRoll, 1, 25)) {
                numDice = 4;
                dieSize = 4;
                coinVal = 1;
            } else if (rollIsBetween(dRoll, 26, 50)) {
                numDice = 2;
                dieSize = 4;
                coinVal = 10;
            } else if (rollIsBetween(dRoll, 51, 70)) {
                numDice = 4;
                dieSize = 4;
                coinVal = 10;
            } else if (rollIsBetween(dRoll, 71, 90)) {
                numDice = 2;
                dieSize = 4;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 91, 99)) {
                numDice = 4;
                dieSize = 4;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 100, 100)) {
                numDice = 2;
                dieSize = 4;
                coinVal = 1000;
            }
        } else { // Art table
            if (rollIsBetween(dRoll, 1, 10)) {
                numDice = 1;
                dieSize = 10;
                coinVal = 10;
            } else if (rollIsBetween(dRoll, 11, 25)) {
                numDice = 3;
                dieSize = 6;
                coinVal = 10;
            } else if (rollIsBetween(dRoll, 26, 40)) {
                numDice = 1;
                dieSize = 6;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 41, 50)) {
                numDice = 1;
                dieSize = 10;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 51, 60)) {
                numDice = 2;
                dieSize = 6;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 61, 70)) {
                numDice = 3;
                dieSize = 6;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 71, 80)) {
                numDice = 4;
                dieSize = 6;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 81, 85)) {
                numDice = 5;
                dieSize = 6;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 86, 90)) {
                numDice = 1;
                dieSize = 4;
                coinVal = 1000;
            } else if (rollIsBetween(dRoll, 91, 99)) {
                numDice = 1;
                dieSize = 6;
                coinVal = 1000;
            } else if (rollIsBetween(dRoll, 100, 100)) {
                numDice = 2;
                dieSize = 6;
                coinVal = 1000;
            }
        }

        val = (double) (dice.roll(numDice, dieSize) * coinVal);
        return val;
    }

    public boolean rollIsBetween(int dRoll, int low, int high) {
        if (!((dRoll < low) && (dRoll > high))) {
            return true;
        }
        return false;
    }

    /**
     * ITEMS
     * */
    public Integer getItemGrouping(Integer dRoll) {
        String tableIndex = "APL" + prefs.getAPL() + "_Items";
        Integer itemGroup = 4;

        Cursor cursor = books.getItemsByAPL(tableIndex, dRoll);

        itemGroup = cursor.getInt(5);

        return itemGroup;
    }

    public Integer getNumItems(Integer dRoll) {
        String tableIndex = "APL" + prefs.getAPL() + "_Items";
        Integer numDice = null;
        Integer dieSize = null;

        Cursor cursor = books.getItemsByAPL(tableIndex, dRoll);

        numDice = cursor.getInt(3);
        dieSize = cursor.getInt(4);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in getNumItems() Roll: " + dRoll
                    + ", got from DB: numDice: " + numDice + ", dieSize: "
                    + dieSize);
        }

        Integer numItems = 0;
        if ((numDice > 0) && (dieSize > 0)) {
            numItems = (int) (dice.roll(numDice, dieSize) * getTreasureMultiplier());
        }

        return numItems;
    }

    public LootItem rollItem(Integer rarityLevel) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollItem() rarity:" + rarityLevel);
        }
        LootItem item = new LootItem();
        // catch mundane item calls first
        if ((rarityLevel == 1) && prefs.isRollMundane()) {
            LootItem mundane = rollMundaneItem();
            item.setName(mundane.getName());
            item.setgValue(mundane.getgValue());
            item.setItemType(mundane.getItemType());
            item.setQuantity(mundane.getQuantity());
            return item;
        }

        do {
            // Roll to determine item type (ie. armor, weapon, scroll...)
            if (rarityLevel == 2) {
                do {
                    item.setItemType(rollMinorItemType());
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG,
                                "in rollItem: item type set to: "
                                        + item.getItemType());
                    }
                } while (prefs.getItemRestrictions()[item.getItemType()]);
            } else if (rarityLevel == 3) {
                do {
                    item.setItemType(rollMediumItemType());
                } while (prefs.getItemRestrictions()[item.getItemType()]);
            } else if (rarityLevel == 4) {
                do {
                    item.setItemType(rollMajorItemType());
                } while (prefs.getItemRestrictions()[item.getItemType()]);
            }

            if (BuildConfig.DEBUG) {
                Log.d(TAG, "In rollItem() rolling for: " + item.getItemType());
            }
            // TODO:
            // Roll qualities, and special abilities.
            if (item.getItemType() == 3) {
                item = rollMagicArmor(rarityLevel);
            } else if (item.getItemType() == 4) {
                item = rollMagicWeapon(rarityLevel);
            } else if (item.getItemType() == 5) {
                item = rollSpecificItem(rarityLevel, "Potions");
            } else if (item.getItemType() == 6) {
                item = rollSpecificItem(rarityLevel, "Rings");
            } else if (item.getItemType() == 7) {
                item = rollSpecificItem(rarityLevel, "Rods");
            } else if (item.getItemType() == 8) {
                item = rollScroll(rarityLevel);
            } else if (item.getItemType() == 9) {
                item = rollSpecificItem(rarityLevel, "Staves");
            } else if (item.getItemType() == 10) {
                // item = rollWand();
            } else if (item.getItemType() == 11) {
                item = rollWondrousItem(rarityLevel);
            }
            item.setrPower(rarityLevel + item.getmLevel());

        } while (prefs.isLimitValByCR()
                && (item.getgValue() > books.getEncounterValue(prefs.getAPL(),
                        1))); // TODO: figure out what needs to be true about
        // at item before it can be returned.
        return item;
    }

    public Integer rollMinorItemType() {
        // Hard-coded static table
        Integer dRoll = rollPercent();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollMinorItemType() rolled: " + dRoll);
        }
        if (dRoll < 5) {
            return 3; // Armor and Shields
        } else if (dRoll < 10) {
            return 4; // Weapons
        } else if (dRoll < 45) {
            return 5; // Potions
        } else if (dRoll < 47) {
            return 6; // Rings
        } else if (dRoll < 82) {
            return 8; // Scrolls
        } else if (dRoll < 92) {
            return 10; // Wands
        } else {
            return 11; // Wondrous Items
        }
    }

    public Integer rollMediumItemType() {
        // Hard-coded static table
        Integer dRoll = rollPercent();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollMediumItem() rolled: " + dRoll);
        }
        if (dRoll < 11) {
            return 3; // Armor and Shields
        } else if (dRoll < 21) {
            return 4; // Weapons
        } else if (dRoll < 31) {
            return 5; // Potions
        } else if (dRoll < 41) {
            return 6; // Rings
        } else if (dRoll < 51) {
            return 7; // Rods
        } else if (dRoll < 66) {
            return 8; // Scrolls
        } else if (dRoll < 69) {
            return 9; // Staves
        } else if (dRoll < 84) {
            return 10; // Wands
        } else {
            return 11; // Wondrous Items
        }
    }

    public Integer rollMajorItemType() {
        // Hard-Coded static table
        Integer dRoll = rollPercent();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollMajorItem() rolled: " + dRoll);
        }
        if (dRoll < 11) {
            return 3;
        } else if (dRoll < 21) {
            return 4;
        } else if (dRoll < 26) {
            return 5;
        } else if (dRoll < 36) {
            return 6;
        } else if (dRoll < 46) {
            return 7;
        } else if (dRoll < 56) {
            return 8;
        } else if (dRoll < 76) {
            return 9;
        } else if (dRoll < 81) {
            return 10;
        } else {
            return 11;
        }
    }

    /**
     * MUNDANE ITEMS
     * */
    public LootItem rollMundaneItem() {
        // TODO: get correct values out somehow.
        LootItem item = new LootItem();
        String mundaneType = rollMundaneType();
        Integer dRoll = rollPercent();

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollMundaneItem: rolled " + dRoll);
        }

        if (mundaneType.equals("Alchemical_item")) {
            item = books.getMundaneAlchemical(dRoll);
        } else if (mundaneType.equals("Armor")) {
            item = books.getMundaneArmor(dRoll);
        } else if (mundaneType.equals("Weapon")) {
            // TODO: make it possible to get ranged weapons
            item = books.getMundaneWeapon(dRoll);
        } else if (mundaneType.equals("Tools_and_gear")) {
            item = books.getMundaneToolsGear(dRoll);
        }

        return item;
    }

    private String rollMundaneType() {
        String type = "Weapon";
        Integer dRoll = rollPercent();

        type = books.getMundaneType(dRoll);

        return type;
    }

    /** WONDROUS ITEMS */
    private LootItem rollWondrousItem(Integer rarityLevel) {
        String wondrousType = rollWondrousType();

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollWondrousItem: Type:" + wondrousType);
        }

        LootItem item = rollSpecificItem(rarityLevel, wondrousType);

        item.setItemType(11);
        return item;
    }

    public String rollWondrousType() {
        String type = "Wondrous_";
        Integer dRoll = rollPercent();

        type += books.getWondrousType(dRoll);

        return type;
    }

    private LootItem rollSpecificItem(Integer rarityLevel, String itemType) {
        LootItem item = new LootItem();
        Integer dRoll = rollPercent();
        boolean isGreaterItem = true;
        if (dice.roll(1, 2) != 2) {
            isGreaterItem = false;
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollSpecificItem: rolled " + dRoll + " for "
                    + itemType);
        }

        if ((((itemType != "Rods") || (rarityLevel != 2)) && ((itemType != "Staves") || (rarityLevel != 2)))) {
            item = books.getSpecificItem(dRoll, itemType, isGreaterItem,
                    rarityLevel);
        }
        item.setNumRolled(dRoll);

        return item;
    }

    /** MAGIC WEAPONS */
    private LootItem rollMagicWeapon(Integer rarityLevel) {
        LootItem item = new LootItem();

        // find out if it's a specific item first.
        Integer dRoll = rollPercent();
        Integer dRoll2 = rollPercent();
        boolean isGreaterItem = true;
        if (dice.roll(1, 2) != 2) {
            isGreaterItem = false;
        }

        Integer isSpecific = 0;
        Cursor cursor = books.getEnhancement(dRoll, "Weapons", isGreaterItem,
                rarityLevel);

        isSpecific = cursor.getInt(6);
        Integer enhancement = cursor.getInt(3);
        Integer numAbilities = cursor.getInt(4);
        Integer abilityLevel = cursor.getInt(5);
        isSpecific = cursor.getInt(6);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollMagicWeapon(): rolled " + dRoll + " for rarity"
                    + rarityLevel + " and isGreaterItem: " + isGreaterItem
                    + ", isSpecific = " + isSpecific
                    + " enhancement, num, lv: " + enhancement + " "
                    + numAbilities + " " + abilityLevel);
        }

        // if its specific, return a specific item, otherwise roll abilities
        if (isSpecific > 0) {
            item = rollSpecificItem(rarityLevel, "Weapons");
        } else {
            // Is it made of special stuff?
            if (rollPercent() > 95) {
                item.setName(rollSpecialMaterial() + " ");
            }
            // is it a Weapon, Ranged Weapon, or Ammunition?
            // 1,2,3 = weapon. 4,5 = ranged weapon. 6 = ammo;
            Integer weaponRangedOrAmmo = dice.roll(1, 6);

            // What kind of weapon? get its price too
            String weaponType = rollWeaponType(weaponRangedOrAmmo, dRoll2);
            if (weaponRangedOrAmmo < 4) {
                item.setgValue(books.getItemValue("WeaponTypes", dRoll2));
            } else if (weaponRangedOrAmmo < 6) {
                item.setgValue(books.getItemValue("RangedWeaponTypes", dRoll2));
            } else {
                item.setgValue(books.getItemValue("AmmoTypes", dRoll2));
            }

            // What special abilities does it get?
            item = getWeaponSpecials(cursor, item, isGreaterItem,
                    weaponRangedOrAmmo, rarityLevel, weaponType);
        }

        item.setItemType(4);
        return item;
    }

    private String rollWeaponType(int weaponRangedOrAmmo, Integer dRoll) {
        String type = "longsword";

        if (weaponRangedOrAmmo < 4) {
            type = books.getMeleeWeaponType(dRoll);
        } else if (weaponRangedOrAmmo < 6) {
            type = books.getRangedWeaponType(dRoll);
        } else {
            type = books.getAmmoType(dRoll);
        }

        return type;
    }

    public LootItem getWeaponSpecials(Cursor cursor, LootItem item,
            boolean isGreaterItem, int weaponRangedOrAmmo, Integer rarityLevel,
            String weaponType) {
        Integer enhancement = cursor.getInt(3);
        Integer numAbilities = cursor.getInt(4);
        Integer abilityLevel = cursor.getInt(5);

        item.setmLevel(enhancement);
        if (weaponRangedOrAmmo < 4) {
            item = rollAbilities("Weapons", item, enhancement, numAbilities,
                    abilityLevel, weaponType);
        } else if (weaponRangedOrAmmo < 6) {
            item = rollAbilities("Ranged_Weapons", item, enhancement,
                    numAbilities, abilityLevel, weaponType);
        } else {
            item = rollAbilities("Ammunition", item, enhancement, numAbilities,
                    abilityLevel, weaponType);
        }

        double priceAdjust = books.getMagicPrice(item.getmLevel(), "Weapons");
        item.setgValue(item.getgValue() + priceAdjust);

        return item;
    }

    /** MAGIC ARMOR */
    private LootItem rollMagicArmor(Integer rarityLevel) {

        LootItem item = new LootItem();

        // find out if it's a specific item first.
        Integer dRoll = rollPercent();
        Integer dRoll2 = rollPercent();
        boolean isGreaterItem = true;
        if (dice.roll(1, 2) != 2) {
            isGreaterItem = false;
        }
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "In rollMagicArmor(): rolled " + dRoll + " for rarity"
                    + rarityLevel + " and isGreaterItem: " + isGreaterItem);
        }
        Integer isSpecific = 0;
        Cursor cursor = books.getEnhancement(dRoll, "Armor", isGreaterItem,
                rarityLevel);

        isSpecific = cursor.getInt(6);

        // is it armor or shield?
        Integer armorOrShield = dice.roll(1, 2);// 1 = armor, 2 = shield;

        // if its specific, return a specific item, otherwise roll abilities
        if (armorOrShield != 2) {
            if (isSpecific > 0) {
                item = rollSpecificItem(rarityLevel, "Armor");
            } else {
                // Is it made of special stuff?
                if (rollPercent() > 95) {
                    item.setName(rollSpecialMaterial() + " ");
                }

                // What kind of armor or shield? get its price too
                String armorType = rollArmorType(armorOrShield, dRoll2);
                item.setgValue(books.getItemValue("ArmorTypes", dRoll2));
                // What special abilities does it get?
                item = getArmorSpecials(cursor, item, isGreaterItem,
                        armorOrShield, rarityLevel, armorType);
            }
        } else {
            if (isSpecific > 0) {
                item = rollSpecificItem(rarityLevel, "Shields");
            } else {
                // Is it made of special stuff?
                if (rollPercent() > 95) {
                    item.setName(rollSpecialMaterial() + " ");
                }

                // What kind of armor or shield? get its price too
                String armorType = rollArmorType(armorOrShield, dRoll2);
                item.setgValue(books.getItemValue("ArmorTypes", dRoll2));
                // What special abilities does it get?
                item = getArmorSpecials(cursor, item, isGreaterItem,
                        armorOrShield, rarityLevel, armorType);
            }
        }

        item.setItemType(3);
        return item;
    }

    private LootItem getArmorSpecials(Cursor cursor, LootItem item,
            boolean isGreaterItem, Integer armorOrShield, Integer rarityLevel,
            String armorType) {
        Integer enhancement = cursor.getInt(3);
        Integer numAbilities = cursor.getInt(4);
        Integer abilityLevel = cursor.getInt(5);

        item.setmLevel(enhancement);
        if (armorOrShield != 2) {
            item = rollAbilities("Armor", item, enhancement, numAbilities,
                    abilityLevel, armorType);
        } else {
            item = rollAbilities("Shields", item, enhancement, numAbilities,
                    abilityLevel, armorType);
        }

        double priceAdjust = books.getMagicPrice(item.getmLevel(), "Armor");
        item.setgValue(item.getgValue() + priceAdjust);
        return item;
    }

    private LootItem rollAbilities(String itemsClass, LootItem item,
            Integer enhancement, Integer numAbilities, Integer abilityLevel,
            String itemsType) {
        // TODO: abilities are getting returned as null sometimes.
        String abilities = "";
        if (enhancement > 0) {
            abilities = "+" + enhancement + " ";
        }

        for (int ii = 0; ii < numAbilities; ii++) {
            Integer dRoll = rollPercent();
            Cursor abilityCursor = books.getAbilities(dRoll, itemsClass,
                    abilityLevel);
            abilities += abilityCursor.getString(3) + " ";
        }
        item.setName(item.getName() + abilities + itemsType);
        item.setmLevel(item.getmLevel() + (abilityLevel * numAbilities));

        return item;
    }

    private String rollArmorType(int armorOrShield, Integer dRoll) {
        String type = "full plate";

        if (armorOrShield != 2) {
            type = books.getArmorType(dRoll);
        } else {
            type = books.getShieldType(dRoll);
        }

        return type;
    }

    private String rollSpecialMaterial() {
        String material = "Adamantine"; // placeholder
        // TODO: find out how special materials are rolled, then put it here.
        return material;
    }

    public Integer rollPercent() {
        return dice.roll(1, 100);
    }

    @Deprecated
    public boolean isValid(LootItem item) {
        // TODO: create validity tests based on APL, CR, encounter value,
        // allowed item types, and other prefs
        boolean validity = true;
        Integer campaignSpeed = 2; // normal speed
        double goldAmmount = books.getEncounterValue(prefs.getAPL(),
                campaignSpeed);
        Integer magicLevel = prefs.getAPL() * prefs.getMagicLv();

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in isValid()");
        }

        // if ((item.getName() == "")) {
        // validity = false;
        // }

        if (item.getgValue() != 0.0) {
            if ((item.getgValue() > goldAmmount) && prefs.isLimitValByCR()) {
                return false;
            }
        }

        if (item.getmLevel() > 0) {
            if (item.getmLevel() > magicLevel) {
                return false;
            }
        }

        if (item.getItemType() != 0) {
            if (prefs.getItemRestrictions()[item.getItemType()]) {
                return false;
            }
        }

        return validity;
    }

    /**
     * SCROLLS
     * 
     * @param rarityLevel
     * @return
     */

    // TODO: Scrolls and Wands; database initialization, logic, database calls.
    private LootItem rollScroll(Integer rarityLevel) {
        LootItem item = new LootItem();

        // Which class's spell list?
        Integer rpgClass = getRPGClass();

        // What Spell level and Caster Level?
        Integer dRoll = rollPercent();
        Integer spellLv = getSpellLv(dRoll, rpgClass, rarityLevel);
        // Integer castLv = getCastLv(dRoll, rpgClass, rarityLevel);

        // What is the cost of that spell level on that list?
        // Double gValue = getScrollValue(rpgClass, spellLv);

        // What is the spell?
        // item.setName(getScrollName(rpgClass, spellLv) + " CL" + castLv);

        // build and return item.

        // item.setgValue(gValue);
        item.setItemType(8);

        return item;
    }

    private Integer getSpellLv(Integer dRoll, Integer rpgClass,
            Integer rarityLevel) {

        return null;
    }

    private Integer getRPGClass() {
        Integer dRoll = rollPercent();

        if (dRoll < 25) {
            return 1; // Cleric & Oracle
        } else if (dRoll < 53) {
            return 2; // Wizard
        } else if (dRoll < 68) {
            return 3; // Druid
        } else if (dRoll < 78) {
            return 4; // Bard
        } else if (dRoll < 93) {
            return 5; // Paladin
        } else if (dRoll < 100) {
            return 5; // Ranger
        }

        return 0; // Error code
    }

    // ////////////////////////
    // Getter/setters//
    // ////////////////////////
    /**
     * @return the dice
     */
    public LootDice getDice() {
        return dice;
    }

    /**
     * @param dice
     *            the dice to set
     */
    public void setDice(LootDice dice) {
        this.dice = dice;
    }

    /**
     * @return the books
     */
    public LootDB getBooks() {
        return books;
    }

    /**
     * @param books
     *            the books to set
     */
    public void setBooks(LootDB books) {
        this.books = books;
    }

    /**
     * @return the prefs
     */
    public LootPrefs getPrefs() {
        return prefs;
    }

    /**
     * @param prefs
     *            the prefs to set
     */
    public void setPrefs(LootPrefs prefs) {
        this.prefs = prefs;
    }

}
