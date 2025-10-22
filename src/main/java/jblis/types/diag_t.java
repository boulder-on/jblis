package jblis.types;

import jpassport.enums.EnumInt;

import static jblis.constants.blisEnums.iBLIS_NONUNIT_DIAG;
import static jblis.constants.blisEnums.iBLIS_UNIT_DIAG;

public enum diag_t implements EnumInt {
    BLIS_NONUNIT_DIAG(iBLIS_NONUNIT_DIAG),
    BLIS_UNIT_DIAG(iBLIS_UNIT_DIAG);

    diag_t(int v)
    {
        value = v;
    }

    private final int value;

    @Override
    public int getCValue() {
        return value;
    }
}
