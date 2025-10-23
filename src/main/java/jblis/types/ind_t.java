package jblis.types;

import jpassport.enums.EnumInt;

public enum ind_t  implements EnumInt {
    BLIS_1M(0),
    BLIS_NAT(1),

    BLIS_IND_FIRST(0),
    BLIS_IND_LAST(1),

    // BLIS_NUM_IND_METHODS must be last!
    BLIS_NUM_IND_METHODS(2);

    private final int value;

    ind_t(int v)
    {
        value = v;
    }


    @Override
    public int getCValue() {
        return value;
    }

}
