package jblis.oapi;

import jblis.constants.constdata_t;
import jblis.types.*;
import jpassport.Passport;
import jpassport.annotations.RefArg;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

import static jblis.constants.blisHiddenConstants.*;
import static jblis.types.conj_t.BLIS_CONJUGATE;
import static jblis.types.mdim_t.BLIS_M;
import static jblis.types.mdim_t.BLIS_N;
import static jblis.types.obj_t.create_constant;
import static jblis.types.trans_t.BLIS_TRANSPOSE;

public interface blis_oapi extends Passport, blis_query {

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

    obj_t BLIS_TWO = create_constant(constdata_t.init_const(2.0));
    obj_t BLIS_ONE = create_constant(constdata_t.init_const(1.0));
    obj_t BLIS_ZERO = create_constant(constdata_t.init_const(0.0));
    obj_t BLIS_MINUS_ONE = create_constant(constdata_t.init_const(-1.0));
    obj_t BLIS_MINUS_TWO = create_constant(constdata_t.init_const(-2.0));
    obj_t BLIS_ONE_I = create_constant(constdata_t.init_const_ri(0.0, 1.0));
    obj_t BLIS_MINUS_ONE_I = create_constant(constdata_t.init_const_ri(0.0, -1.0));
    obj_t BLIS_NAN = create_constant(constdata_t.init_const_ri(Double.NaN, Double.NaN));


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

    //------Level 1m----------
    void bli_addm(
       obj_t[]  a,
       obj_t[]  b);
    void bli_axpym(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b);
    void bli_copym(
       obj_t[]  a,
       obj_t[]  b);
    void bli_invscalm(
       obj_t[]  alpha,
       obj_t[]  a);
    void bli_scalm(
       obj_t[]  alpha,
       obj_t[]  a);
    void bli_scal2m(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b);
    void bli_setm(
       obj_t[]  alpha,
       obj_t[]  a);
    void bli_setrm(
       obj_t[]  alpha,
       obj_t[]  a);
    void bli_setim(
       obj_t[]  alpha,
       obj_t[]  a);
    void bli_subm(
       obj_t[]  a,
       obj_t[]  b);

    //------Level 1m----------

    //------Level 1f----------
    void bli_axpy2v
    (
       obj_t[]  alphax,
       obj_t[]  alphay,
       obj_t[]  x,
       obj_t[]  y,
       obj_t[]  z
    );
    void bli_dotaxpyv
            (
       obj_t[]  alpha,
       obj_t[]  xt,
       obj_t[]  x,
       obj_t[]  y,
       obj_t[]  rho,
       obj_t[]  z
            );
    void bli_axpyf
            (
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  x,
       obj_t[]  y
            );
    void bli_dotxf
            (
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  x,
       obj_t[]  beta,
       obj_t[]  y
            );
    void bli_dotxaxpyf
            (
       obj_t[]  alpha,
       obj_t[]  at,
       obj_t[]  a,
       obj_t[]  w,
       obj_t[]  x,
       obj_t[]  beta,
       obj_t[]  y,
       obj_t[]  z
            );
    //------Level 1f----------

    //------Level 2----------
    void bli_gemv(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  x,
       obj_t[]  beta,
       obj_t[]  y);
    void bli_ger(
       obj_t[]  alpha,
       obj_t[]  x,
       obj_t[]  y,
       obj_t[]  a);
    void bli_hemv(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  x,
       obj_t[]  beta,
       obj_t[]  y);
    void bli_her(
       obj_t[]  alpha,
       obj_t[]  x,
       obj_t[]  a);
    void bli_her2(
       obj_t[]  alpha,
       obj_t[]  x,
       obj_t[]  y,
       obj_t[]  a);
    void bli_symv(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  x,
       obj_t[]  beta,
       obj_t[]  y);
    void bli_syr(
       obj_t[]  alpha,
       obj_t[]  x,
       obj_t[]  a);
    void bli_syr2(
       obj_t[]  alpha,
       obj_t[]  x,
       obj_t[]  y,
       obj_t[]  a);
    void bli_trmv(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  x);
    void bli_trsv(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  y);
    //------Level 2----------

    //------Level 3----------
    void bli_gemm
    (
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b,
       obj_t[]  beta,
       obj_t[]  c);
    void bli_gemmt(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b,
       obj_t[]  beta,
       obj_t[]  c);
    void bli_hemm(side_t  sidea,
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b,
       obj_t[]  beta,
       obj_t[]  c);
    void bli_herk(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  beta,
       obj_t[]  c);
    void bli_her2k(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b,
       obj_t[]  beta,
       obj_t[]  c);
    void bli_symm(side_t  sidea,
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b,
       obj_t[]  beta,
       obj_t[]  c);
    void bli_syrk(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  beta,
       obj_t[]  c);
    void bli_syr2k(
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b,
       obj_t[]  beta,
       obj_t[]  c);
    void bli_trmm(side_t  sidea,
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b);
    void bli_trmm3(side_t sidea,
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b,
       obj_t[]  beta,
       obj_t[]  c);
    void bli_trsm(side_t  sidea,
       obj_t[]  alpha,
       obj_t[]  a,
       obj_t[]  b);
    //------Level 3----------

    //------Utility----------
    void bli_asumv(
       obj_t[]  x,
       obj_t[]  asum);
    void bli_norm1m(
       obj_t[]  a,
       obj_t[]  norm);
    void bli_normfm(
       obj_t[]  a,
       obj_t[]  norm);
    void bli_normim(
       obj_t[]  a,
       obj_t[]  norm);
    void bli_norm1v(
       obj_t[]  x,
       obj_t[]  norm);
    void bli_normfv(
       obj_t[]  x,
       obj_t[]  norm);
    void bli_normiv(
       obj_t[]  x,
       obj_t[]  norm);
    void bli_mkherm(
       obj_t[]  a);
    void bli_mksymm(
       obj_t[]  a);
    void bli_mktrim(
       obj_t[]  a);
    void bli_printv(
       String   s1,
       obj_t[]  x,
       String   format,
       String   s2);
    void bli_printm(
       String   s1,
       obj_t[]  a,
       String   format,
       String   s2);
    void bli_randv(
       obj_t[]  x);
    void bli_randm(
       obj_t[]  a);
    void bli_sumsqv(
       obj_t[]  x,
       obj_t[]  scale,
       obj_t[]  sumsq);
    void bli_getsc(
       obj_t[]   chi,
       @RefArg double[]  zeta_r,
       @RefArg double[]  zeta_i);
    err_t bli_getijv(
                    long    i,
       obj_t[]   x,
                    @RefArg double[]  ar,
                    @RefArg double[]  ai);

    err_t bli_getijm(
                    long    i,
                    long    j,
       obj_t[]   b,
                    @RefArg double[]  ar,
                    @RefArg double[]  ai);
    void bli_setsc(
                    double  zeta_r,
                    double  zeta_i,
       obj_t[]  chi);
    err_t bli_setijv(
                    double  ar,
                    double  ai,
                    long   i,
       obj_t[]  x);
    err_t bli_setijm(
                    double  ar,
                    double  ai,
                    long   i,
                    long   j,
       obj_t[]  b);

    void bli_eqsc(
       obj_t[]  chi,
       obj_t[]  psi,
       @RefArg boolean[]   is_eq);
    void bli_eqv(
       obj_t[]  x,
       obj_t[]  y,
       @RefArg boolean[]   is_eq);
    void bli_eqm(
       obj_t[]  a,
       obj_t[]  b,
       @RefArg boolean[]   is_eq);
    //------Utility----------

    void bli_projm(obj_t[] a, obj_t[] b);
    void bli_castm(obj_t[] a, obj_t[] b);
    void bli_obj_create_1x1(num_t dt, @RefArg obj_t[] obj);
    void bli_obj_create_1x1_with_attached_buffer(num_t dt, MemorySegment p, @RefArg obj_t[] obj);
    default void bli_obj_create_1x1_with_attached_buffer(num_t dt, Arena a, double[] p, @RefArg obj_t[] obj)
    {
        bli_obj_create_1x1_with_attached_buffer(dt, toVoidPtr(a, p), obj);
    }

    //todo: when used in level0 test, this does not appear to work
    void bli_obj_scalar_init_detached(num_t dt, @RefArg obj_t []beta);
//    void bli_setsc(double zeta_r, double zeta_i, obj_t []chi);
    void bli_copysc(obj_t[] chi, obj_t[] psi);
    void bli_addsc(obj_t[] chi, obj_t[] psi);
    void bli_subsc(obj_t[]chi, obj_t[]psi);
    void bli_divsc(obj_t[]chi, obj_t[]psi);
    void bli_sqrtsc(obj_t[]chi, obj_t[]psi);
    void bli_normfsc(obj_t[]chi, obj_t[]absq);
    void bli_invertsc(obj_t[]chi, obj_t[]psi);

    String bli_dt_string(num_t dt);
    long bli_dt_size(num_t dt);
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

    void bli_acquire_mpart(long i, long j, long m, long n, obj_t[] obj, @RefArg obj_t[] sub_obj);

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

    default void bli_obj_toggle_trans( obj_t[] obj )
    {
        bli_obj_apply_trans( BLIS_TRANSPOSE, obj );
    }

    default void bli_obj_toggle_conj( obj_t[] obj )
    {
        bli_obj_apply_conj( BLIS_CONJUGATE, obj );
    }

    default void bli_obj_apply_trans( trans_t trans, obj_t[] obj )
    {
        obj[0] = obj_t.updateInfo(obj[0], obj[0].info() ^ trans.getCValue() );
    }

    default void bli_obj_apply_conj( conj_t conj, obj_t[] obj )
    {
        obj[0] = obj_t.updateInfo(obj[0], obj[0].info() ^ conj.getCValue() );
    }

    default void bli_obj_set_struc( struc_t struc, obj_t[] obj )
    {
        obj[0] = obj_t.updateInfo(obj[0], (obj[0].info() & ~BLIS_STRUC_BITS ) | struc.getCValue() );
    }

    default void bli_obj_set_uplo( uplo_t uplo, obj_t[] obj )
    {
        obj[0] =  obj_t.updateInfo(obj[0],( ( obj[0].info() & ~BLIS_UPLO_BITS ) | uplo.getCValue() ));
    }

    default void bli_obj_set_diag( diag_t diag, obj_t[] obj )
    {
        obj[0] = obj_t.updateInfo(obj[0],( ( obj[0].info() & ~BLIS_UNIT_DIAG_BIT ) | diag.getCValue() ));
    }

    default void bli_obj_alias_to( obj_t[] a, obj_t[] b )
    {
        b[0] = obj_t.updateInfo(a[0], a[0].info());
    }

    default void bli_obj_set_diag_offset( long offset, obj_t[] obj )
    {
        obj[0] = obj_t.updateOffset(obj[0], offset);
    }

    default void bli_obj_set_comp_prec( prec_t dt, obj_t[] obj )
    {
        obj[0] = obj_t.updateInfo(obj[0], ( ( obj[0].info()) & ~BLIS_COMP_PREC_BIT ) | ( dt.getCValue() << BLIS_COMP_PREC_SHIFT ) );
    }

}
