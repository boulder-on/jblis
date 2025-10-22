package jblis.types;

import jpassport.enums.EnumInt;

import static jblis.constants.blisEnums.*;

public enum trans_t  implements EnumInt {
    BLIS_NO_TRANSPOSE(iBLIS_NO_TRANSPOSE),
    BLIS_TRANSPOSE(iBLIS_TRANSPOSE),
    BLIS_CONJ_NO_TRANSPOSE(iBLIS_CONJ_NO_TRANSPOSE),
    BLIS_CONJ_TRANSPOSE(iBLIS_CONJ_TRANSPOSE);

    trans_t(int v)
    {
        value = v;
    }

    private final int value;

    @Override
    public int getCValue() {
        return value;
    }

}
