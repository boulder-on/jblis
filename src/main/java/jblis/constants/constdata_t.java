package jblis.constants;

import jblis.types.dcomplex;
import jblis.types.scomplex;
import jpassport.Utils;

import java.lang.foreign.Arena;
import java.lang.foreign.GroupLayout;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;

import static java.lang.foreign.MemoryLayout.PathElement.groupElement;
import static java.lang.foreign.ValueLayout.*;

public record constdata_t(float s, double d, scomplex c, dcomplex z, long i) {

    public static final GroupLayout StructLayout = Utils.makeStruct(
            JAVA_FLOAT.withName("s"),
            JAVA_DOUBLE.withName("d"),
            JAVA_FLOAT.withName("cr"),
            JAVA_FLOAT.withName("ci"),
            JAVA_DOUBLE.withName("zr"),
            JAVA_DOUBLE.withName("zi"),
            JAVA_LONG.withName("i")
            );

    private static final long[] Offsets = new long[] {
            StructLayout.byteOffset(groupElement("s")),
            StructLayout.byteOffset(groupElement("d")),
            StructLayout.byteOffset(groupElement("cr")),
            StructLayout.byteOffset(groupElement("ci")),
            StructLayout.byteOffset(groupElement("zr")),
            StructLayout.byteOffset(groupElement("zi")),
            StructLayout.byteOffset(groupElement("i")),
    };

    public MemorySegment toNative()
    {
        Arena a = Arena.ofAuto();
        long size = StructLayout.byteSize();
        var mem = a.allocate(size);
        mem.set(JAVA_FLOAT, Offsets[0], s);
        mem.set(JAVA_DOUBLE, Offsets[1], d);
        mem.set(JAVA_FLOAT, Offsets[2], c.real());
        mem.set(JAVA_FLOAT, Offsets[3], c.imag());
        mem.set(JAVA_DOUBLE, Offsets[4], z.real());
        mem.set(JAVA_DOUBLE, Offsets[5], z.imag());
        mem.set(JAVA_LONG, Offsets[6], i);

        return mem;
    }

    public static constdata_t init_const(double v)
    {
        return new constdata_t((float)v, v, new scomplex((float)v, 0), new dcomplex(v, 0), (long)v);
    }

    public static constdata_t init_const_ri(double r, double i)
    {
        return new constdata_t((float)r, r, new scomplex((float)r, (float)i), new dcomplex(r, i), (int)r);
    }

}
