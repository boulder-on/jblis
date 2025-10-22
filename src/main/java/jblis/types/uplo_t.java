package jblis.types;

import jpassport.enums.EnumInt;

import static jblis.constants.blisEnums.*;

public enum uplo_t implements EnumInt {
    BLIS_LOWER(iBLIS_LOWER),
    BLIS_UPPER(iBLIS_UPPER),
    BLIS_DENSE(iBLIS_DENSE);

    uplo_t(int v)
    {
        value = v;
    }

    private final int value;

    @Override
    public int getCValue() {
        return value;
    }
}
