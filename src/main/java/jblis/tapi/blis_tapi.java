package jblis.tapi;

import jblis.types.rntm_t;
import jblis.types.timpl_t;
import jpassport.Passport;
import jpassport.annotations.RefArg;

public interface blis_tapi extends Passport, blis_tapiDouble, blis_tapiFloat, blis_tapiComplexDouble, blis_tapiComplexFloat {

    //float = s,  double = d, float complex = c, double complex = z
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

    void bli_init( );
    void bli_finalize(  );
    void bli_thread_set_num_threads(long value);
    long bli_thread_get_num_threads();
    void bli_thread_set_ways(long jc, long pc, long ic, long jr, long ir);
    void bli_thread_set_thread_impl(timpl_t ti);
    void bli_rntm_init_from_global(@RefArg rntm_t[] rntm);
    void bli_rntm_set_num_threads(long nt, @RefArg rntm_t[] rntm);
    void bli_rntm_set_ways(long jc, long pc, long ic, long jr, long ir, @RefArg rntm_t[] rntm);
}
