/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.util.Set;

/**
 * @author Brian Chu and Garrick Ablett
 *
 *  A version of LootList that enforces non-duplicate items by using a Set.
 *  
 *  TODO: needs a method that increments a throw-out counter when a duplicate is
 *  found. 
 *
 */
@Deprecated
public class LootListNoDuplicates extends LootList {

    private Set<LootItem> loot;
}
