package jblis.types;

import jpassport.enums.EnumInt;

import static jblis.constants.blisEnums.iBLIS_CONJUGATE;
import static jblis.constants.blisEnums.iBLIS_NO_CONJUGATE;

public enum conj_t implements EnumInt {
    BLIS_NO_CONJUGATE(iBLIS_NO_CONJUGATE),
    BLIS_CONJUGATE(iBLIS_CONJUGATE);

    conj_t(int v)
    {
        value = v;
    }

    private final int value;

    @Override
    public int getCValue() {
        return value;
    }
}
