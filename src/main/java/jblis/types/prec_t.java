package jblis.types;

import jpassport.enums.EnumInt;

import static jblis.constants.blisHiddenConstants.BLIS_BITVAL_DOUBLE_PREC;
import static jblis.constants.blisHiddenConstants.BLIS_BITVAL_SINGLE_PREC;

public enum prec_t  implements EnumInt {
    BLIS_SINGLE_PREC(BLIS_BITVAL_SINGLE_PREC),
    BLIS_DOUBLE_PREC(BLIS_BITVAL_DOUBLE_PREC);

    private final int value;

    prec_t(int v)
    {
        value = v;
    }


    @Override
    public int getCValue() {
        return value;
    }

}
