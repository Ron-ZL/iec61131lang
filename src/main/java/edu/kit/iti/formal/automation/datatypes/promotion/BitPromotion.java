package edu.kit.iti.formal.automation.datatypes.promotion;

import edu.kit.iti.formal.automation.datatypes.Any;
import edu.kit.iti.formal.automation.datatypes.AnyBit;

/**
 * Created by weigl on 24.11.16.
 */
public class BitPromotion implements TypePromotion {
    public static final TypePromotion INSTANCE = new BitPromotion();

    @Override
    public Any getPromotion(Any a, Any b) {
        try {
            return promote((AnyBit) a, (AnyBit) b);
        } catch (ClassCastException e) {
            return null;
        }
    }

    private Any promote(AnyBit a, AnyBit b) {
        if(a.getBitLength()>= b.getBitLength())
            return a;
        else
            return b;
    }
}