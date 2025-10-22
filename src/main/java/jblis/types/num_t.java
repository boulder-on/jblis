package jblis.types;

import jpassport.enums.EnumInt;

import static jblis.constants.blisEnums.*;

public enum num_t implements EnumInt {
    BLIS_FLOAT (BLIS_BITVAL_FLOAT_TYPE),
    BLIS_DOUBLE(BLIS_BITVAL_DOUBLE_TYPE),
    BLIS_SCOMPLEX(BLIS_BITVAL_SCOMPLEX_TYPE),
    BLIS_DCOMPLEX(BLIS_BITVAL_DCOMPLEX_TYPE),
    BLIS_INT(BLIS_BITVAL_INT_TYPE),
    BLIS_CONSTANT(BLIS_BITVAL_CONST_TYPE),
    BLIS_DT_LO(BLIS_FLOAT.getCValue()),
    BLIS_DT_HI(BLIS_DCOMPLEX.getCValue());

    private final int value;

    num_t(int v)
    {
        value = v;
    }

    @Override
    public int getCValue() {
        return value;
    }

    public static num_t fromValue(int v)
    {
        for (num_t t : num_t.values())
        {
            if (t.getCValue() == v)
                return t;
        }
        return null;
    }

}
