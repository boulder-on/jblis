package jblis.types;

import jpassport.enums.EnumInt;

import static jblis.constants.blisHiddenConstants.*;

public enum struc_t  implements EnumInt {
    BLIS_GENERAL(BLIS_BITVAL_GENERAL),
    BLIS_HERMITIAN(BLIS_BITVAL_HERMITIAN),
    BLIS_SYMMETRIC(BLIS_BITVAL_SYMMETRIC),
    BLIS_TRIANGULAR(BLIS_BITVAL_TRIANGULAR);

    private final int value;

    struc_t(int v)
    {
        value = v;
    }

    @Override
    public int getCValue() {
        return value;
    }

}
