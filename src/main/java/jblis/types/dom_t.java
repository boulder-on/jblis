package jblis.types;

import jpassport.enums.EnumInt;

import static jblis.constants.blisEnums.BLIS_BITVAL_COMPLEX;
import static jblis.constants.blisEnums.BLIS_BITVAL_REAL;

public enum dom_t implements EnumInt {
    BLIS_REAL(BLIS_BITVAL_REAL),
    BLIS_COMPLEX(BLIS_BITVAL_COMPLEX);

    private final int value;

    dom_t(int v)
    {
        value = v;
    }


    @Override
    public int getCValue() {
        return value;
    }
}
