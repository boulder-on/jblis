package jblis;


import jblis.types.dcomplex;
import jblis.types.scomplex;

public interface blisHelpers
{
    static double[] ptr(double v){ return new double[]{v};}
    static float[] ptr(float v){ return new float[]{v};}
    static long[] ptr(long v){ return new long[]{v};}
    static dcomplex[] ptr(dcomplex d){return new dcomplex[]{d};}
    static scomplex[] ptr(scomplex d){return new scomplex[]{d};}

    static dcomplex[] ptr(double real, double imag)
    {
        return new dcomplex[] {new dcomplex(real, imag)};
    }

    static scomplex[] ptr(float real, float imag)
    {
        return new scomplex[] {new scomplex(real, imag)};
    }

    static double[] mallocd(long count) {return new double[(int)count];}
    static float[] mallocs(long count) {return new float[(int)count];}

    static dcomplex[] mallocdc(long count)
    {
        var ret = new dcomplex[(int)count];
        for (int n = 0; n < count; ++n)
            ret[n] = new dcomplex(0, 0);
        return ret;
    }

    static scomplex[] mallocsc(long count)
    {
        var ret = new scomplex[(int)count];
        for (int n = 0; n < count; ++n)
            ret[n] = new scomplex(0, 0);
        return ret;
    }
}
