package jblis.oapi;

import jblis.constants.constdata_t;
import jblis.types.num_t;
import jblis.types.obj_t;
import jblis.types.rntm_t;
import jblis.types.timpl_t;
import jpassport.Passport;
import jpassport.annotations.RefArg;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

import static jblis.constants.blisHiddenConstants.BLIS_DATATYPE_BITS;
import static jblis.types.mdim_t.BLIS_M;
import static jblis.types.mdim_t.BLIS_N;
import static jblis.types.obj_t.create_constant;

public interface blis_oapi extends Passport {

    static long dim_t(long value)
    {
        return value;
    }
    static long inc_t(long value)
    {
        return value;
    }
    static long doff_t(long value)
    {
        return value;
    }
    static MemorySegment toVoidPtr(Arena a, double[] d)
    {
        return a.allocateFrom(ValueLayout.JAVA_DOUBLE, d);
    }

    static MemorySegment toVoidPtr(Arena a, float[] d)
    {
        return a.allocateFrom(ValueLayout.JAVA_FLOAT, d);
    }

    // TODO, these are broken. The conversion to native memory is not correct. Why?
    public static obj_t BLIS_TWO = create_constant(constdata_t.init_const(2.0));
    public static obj_t BLIS_ONE = create_constant(constdata_t.init_const(1.0));
    public static obj_t BLIS_ZERO = create_constant(constdata_t.init_const(0.0));
    public static obj_t BLIS_MINUS_ONE = create_constant(constdata_t.init_const(-1.0));
    public static obj_t BLIS_MINUS_TWO = create_constant(constdata_t.init_const(-2.0));
    public static obj_t BLIS_ONE_I = create_constant(constdata_t.init_const_ri(0.0, 1.0));
    public static obj_t BLIS_MINUS_ONE_I = create_constant(constdata_t.init_const_ri(0.0, -1.0));
    public static obj_t BLIS_NAN = create_constant(constdata_t.init_const_ri(Double.NaN, Double.NaN));


    void bli_obj_create(num_t dt, long m, long n, long rs, long cs, @RefArg obj_t[] obj);
    void bli_obj_free(obj_t []obj);

    //------Level 1----------
    void bli_addv(
       obj_t[]  x,
       obj_t[]  y);
    void bli_amaxv(
       obj_t[]  x,
       obj_t[]  index);
    void bli_axpyv(
       obj_t[]  alpha,
       obj_t[]  x,
       obj_t[]  y);
    void bli_axpbyv(
       obj_t[]  alpha,
       obj_t[]  x,
       obj_t[]  beta,
       obj_t[]  y);
    void bli_copyv(
       obj_t[]  x,
       obj_t[]  y);
    void bli_dotv(
       obj_t[]  x,
       obj_t[]  y,
       obj_t[]  rho);
    void bli_dotxv(
       obj_t[]  alpha,
       obj_t[]  x,
       obj_t[]  y,
       obj_t[]  beta,
       obj_t[]  rho);
    void bli_invertv(obj_t[]  x);
    void bli_invscalv(
       obj_t[]  alpha,
       obj_t[]  x);
    void bli_scalv(
       obj_t[]  alpha,
       obj_t[]  x);
    void bli_scal2v(
       obj_t[]  alpha,
       obj_t[]  x,
       obj_t[]  y);
    void bli_setv(
       obj_t[]  alpha,
       obj_t[]  x);
    void bli_setrv(
        obj_t[]  alpha,
       obj_t[]  x);
    void bli_setiv(
       obj_t[]  alpha,
       obj_t[]  x);
    void bli_subv(
       obj_t[]  x,
       obj_t[]  y);
    void bli_swapv(
       obj_t[]  x,
       obj_t[]  y);
    void bli_xpbyv(
       obj_t[]  x,
       obj_t[]  beta,
       obj_t[]  y);
    //------Level 1----------

    //------Level 1d----------
    void bli_addd(
       obj_t[]  a,
       obj_t[]  b);
    void bli_axpyd(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b);
    void bli_copyd(
       obj_t[]  a,
       obj_t[]  b);
    void bli_invertd(obj_t[]  a);
    void bli_invscald(
       obj_t[]  alpha,
       obj_t[]  a);
    void bli_scald(
       obj_t[]  alpha,
       obj_t[]  a);
    void bli_scal2d(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b);
    void bli_setd(
       obj_t[]  alpha,
       obj_t[]  a);
    void bli_setid(
       obj_t[]  alpha,
       obj_t[]  a);
    void bli_shiftd(
       obj_t[]  alpha,
       obj_t[]  a);
    void bli_subd(
       obj_t[]  a,
       obj_t[]  b);
    void bli_xpbyd(
       obj_t[]  a,
       obj_t[]  beta,
       obj_t[]  b);
    //------Level 1d----------


    void bli_obj_create_1x1(num_t dt, @RefArg obj_t[] obj);
    void bli_obj_create_1x1_with_attached_buffer(num_t dt, MemorySegment p, @RefArg obj_t[] obj);
    default void bli_obj_create_1x1_with_attached_buffer(num_t dt, Arena a, double[] p, @RefArg obj_t[] obj)
    {
        bli_obj_create_1x1_with_attached_buffer(dt, toVoidPtr(a, p), obj);
    }
    void bli_obj_scalar_init_detached(num_t dt, @RefArg obj_t []beta);
    void bli_setsc(double zeta_r, double zeta_i, obj_t []chi);
    void bli_copysc(obj_t[] chi, obj_t[] psi);
    void bli_addsc(obj_t[] chi, obj_t[] psi);
    void bli_subsc(obj_t[]chi, obj_t[]psi);
    void bli_divsc(obj_t[]chi, obj_t[]psi);
    void bli_sqrtsc(obj_t[]chi, obj_t[]psi);
    void bli_normfsc(obj_t[]chi, obj_t[]absq);
    void bli_invertsc(obj_t[]chi, obj_t[]psi);

    String bli_dt_string(num_t dt);
    long bli_dt_size(num_t dt);
    void bli_randm(obj_t []x);
    void bli_randv(obj_t[] x);
    void bli_printm(String s1, obj_t []x, String format, String s2);
    void bli_obj_create_without_buffer(num_t dt, long m, long n, @RefArg obj_t[]obj);
    void bli_obj_attach_buffer(MemorySegment p, long rs, long cs, long is, @RefArg obj_t[]obj);
    default void bli_obj_attach_buffer(Arena a, double[] p, long rs, long cs, long is, obj_t[]obj)
    {
        bli_obj_attach_buffer(toVoidPtr(a, p), rs, cs, is, obj);
    }
    void bli_obj_create_with_attached_buffer(num_t dt, long m, long n, MemorySegment p, long rs, long cs,  @RefArg obj_t[]obj);
    default void bli_obj_create_with_attached_buffer(num_t dt, long m, long n, Arena a, double[] p, long rs, long cs,  @RefArg obj_t[]obj)
    {
        bli_obj_create_with_attached_buffer(dt, m, n, toVoidPtr(a, p), rs, cs, obj);
    }

    int bli_getijm(long i, long j, obj_t[] b, @RefArg double[] ar, @RefArg double[] ai);
    int bli_setijm(double ar, double ai, long i, long j, obj_t[] b);
    void bli_acquire_mpart(long i, long j, long m, long n, obj_t[] obj, @RefArg obj_t[] sub_obj);
    void bli_setm(obj_t[] alpha, obj_t[] x);

    void bli_init( );
    void bli_finalize(  );
    void bli_thread_set_num_threads(long value);
    long bli_thread_get_num_threads();
    void bli_thread_set_ways(long jc, long pc, long ic, long jr, long ir);
    void bli_thread_set_thread_impl(timpl_t ti);
    void bli_rntm_init_from_global(@RefArg rntm_t[] rntm);
    void bli_rntm_set_num_threads(long nt, @RefArg rntm_t[] rntm);
    void bli_rntm_set_ways(long jc, long pc, long ic, long jr, long ir, @RefArg rntm_t[] rntm);

    default num_t bli_obj_dt( obj_t[] obj )
    {
        return num_t.fromValue( obj[0].info() & BLIS_DATATYPE_BITS );
    }

    default long bli_obj_length( obj_t[] obj )
    {
        return ( obj[0].dim()[ BLIS_M.ordinal() ] );
    }

    default long bli_obj_width( obj_t[] obj )
    {
        return ( obj[0].dim()[ BLIS_N.ordinal() ] );
    }

    default long bli_obj_row_stride( obj_t[] obj )
    {
        return ( obj[0].rs() );
    }

    default long bli_obj_col_stride( obj_t[] obj )
    {
        return ( obj[0].cs() );
    }


}
