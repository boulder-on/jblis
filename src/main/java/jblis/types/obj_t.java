package jblis.types;

import jblis.constants.constdata_t;
import jpassport.Utils;
import jpassport.annotations.Array;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.Random;

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
                    long m_padded,
                    long n_padded,
                    long ps,
                    long pd,
                    long m_panel,
                    long n_panel
                    ) {

    public double[] getdarray()
    {
        if (MemorySegment.NULL.equals(buffer))
            return null;

        var data = buffer.reinterpret(elem_size * dim[0] * dim[1] * 3);
        return data.toArray(ValueLayout.JAVA_DOUBLE);
    }

    public float[] getsarray()
    {
        if (MemorySegment.NULL.equals(buffer))
            return null;

        var data = buffer.reinterpret(elem_size);
        return data.toArray(ValueLayout.JAVA_FLOAT);
    }

    public static obj_t[] ptr()
    {
        return new obj_t[] {new obj_t(MemorySegment.NULL, new long[2], new long[2], 0, 0, 0, 0,
                MemorySegment.NULL, 0, 0, 0, new dcomplex(0, 0), 0, 0, 0, 0, 0 ,0)};
    }

//    public static obj_t[] ptrR()
//    {
//        var r = new Random();
//        return new obj_t[] {new obj_t(MemorySegment.NULL,
//                new long[] {r.nextLong(), r.nextLong()},
//                new long[] {r.nextLong(), r.nextLong()},
//                r.nextLong(),
//                r.nextInt(),
//                r.nextInt(),
//                r.nextLong(),
//                MemorySegment.NULL,
//                r.nextLong(),
//                r.nextLong(), r.nextLong(),
//                new dcomplex(r.nextDouble(), r.nextDouble()),
//                r.nextLong(), r.nextLong(), r.nextLong(), r.nextLong(), r.nextLong() ,r.nextLong())};
//    }

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
                constdata_t.StructLayout.byteSize(),
                cdata.toNative(),
                1, 1, 1, null, 0, 0 , 0, 0, 0, 0);
    }

    public static obj_t updateInfo(obj_t orig, int newInfo)
    {
        return new obj_t(orig.root, orig.off, orig.dim, orig.diag_off, newInfo,
                orig.info2, orig.elem_size, orig.buffer, orig.rs, orig.cs, orig.is,
                orig.scalar, orig.m_padded, orig.n_padded, orig.ps, orig.pd, orig.m_panel, orig.n_panel);
    }

    public static obj_t updateOffset(obj_t orig, long newOffset)
    {
        return new obj_t(orig.root, orig.off, orig.dim, newOffset, orig.info,
                orig.info2, orig.elem_size, orig.buffer, orig.rs, orig.cs, orig.is,
                orig.scalar,  orig.m_padded, orig.n_padded, orig.ps, orig.pd, orig.m_panel, orig.n_panel);
    }
}
