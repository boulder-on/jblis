package jblis.types;

import jblis.constants.constdata_t;
import jpassport.Utils;
import jpassport.annotations.Array;

import java.lang.foreign.MemorySegment;

import static jblis.constants.blisEnums.BLIS_BITVAL_CONST_TYPE;
import static jblis.constants.blisHiddenConstants.BLIS_BITVAL_DENSE;
import static jblis.constants.blisHiddenConstants.BLIS_BITVAL_GENERAL;

/**
 * {@snippet lang=c :
 * struct obj_s {
 *     struct obj_s *root;
 *     dim_t off[2];
 *     dim_t dim[2];
 *     doff_t diag_off;
 *     objbits_t info;
 *     objbits_t info2;
 *     siz_t elem_size;
 *     void *buffer;
 *     inc_t rs;
 *     inc_t cs;
 *     inc_t is;
 *     atom_t scalar;
 *     dim_t m_padded;
 *     dim_t n_padded;
 *     inc_t ps;
 *     inc_t pd;
 *     dim_t m_panel;
 *     dim_t n_panel;
 * }
 * }
 */


public record obj_t(MemorySegment root,
                    @Array(length = 2) long[] off,
                    @Array(length = 2) long[] dim,
                    long diag_off,
                    int info,
                    int info2,
                    long elem_size,
                    MemorySegment buffer,
                    long rs,
                    long cs,
                    long is,
                    dcomplex scalar,
                    long ps,
                    long pd,
                    long m_panel,
                    long n_panel
                    ) {

    public static obj_t[] ptr()
    {
        return new obj_t[] {new obj_t(MemorySegment.NULL, new long[2], new long[2], 0, 0, 0, 0,
                MemorySegment.NULL, 0, 0, 0, new dcomplex(0, 0), 0, 0, 0, 0)};
    }

    public obj_t[] toptr()
    {
        return new obj_t[] {this};
    }

    public static obj_t[] ptr(obj_t v)
    {
        return new obj_t[] {v};
    }

    public static obj_t create_constant(constdata_t cdata)
    {
        return new obj_t(
                MemorySegment.NULL, new long[2], new long[] {1, 1}, 0,
                BLIS_BITVAL_CONST_TYPE | BLIS_BITVAL_DENSE | BLIS_BITVAL_GENERAL,
                0,
                Utils.size_of(constdata_t.class),
                cdata.toNative(),
                1, 1, 1, null, 0, 0 , 0, 0);
    }


//        blis_h.C_POINTER.withName("root"),
//                MemoryLayout.sequenceLayout(2, blis_h.C_LONG).withName("off"),
//        MemoryLayout.sequenceLayout(2, blis_h.C_LONG).withName("dim"),
//        blis_h.C_LONG.withName("diag_off"),
//                blis_h.C_INT.withName("info"),
//                blis_h.C_INT.withName("info2"),
//                blis_h.C_LONG.withName("elem_size"),
//                blis_h.C_POINTER.withName("buffer"),
//                blis_h.C_LONG.withName("rs"),
//                blis_h.C_LONG.withName("cs"),
//                blis_h.C_LONG.withName("is"),
//                dcomplex.layout().withName("scalar"),
//        blis_h.C_LONG.withName("m_padded"),
//                blis_h.C_LONG.withName("n_padded"),
//                blis_h.C_LONG.withName("ps"),
//                blis_h.C_LONG.withName("pd"),
//                blis_h.C_LONG.withName("m_panel"),
//                blis_h.C_LONG.withName("n_panel")

}
