package jblis.tapi;

import jblis.types.*;
import jpassport.Passport;
import jpassport.annotations.Critical;
import jpassport.annotations.RefArg;

public interface blis_tapiFloat extends Passport {
    void bli_saddv(conj_t conjx,
                   long dim_t,
                   float[]x, long incx,
                   @RefArg float[] y, long incy);
    void bli_samaxv(long dim_t,
                    float[]x, long incx,
                    @RefArg long[] dim_t2);
    void bli_saxpyv(conj_t conjx,
                    long dim_t,
                    float[] alpha,
                    float[] x, long incx,
                    @RefArg float[] y, long incy);
    void bli_saxpbyv(conj_t conjx,
                     long dim_t,
                     float[] alpha,
                     float[] x, long incx,
                     float[] beta,
                     @RefArg float[] y, long incy);
    void bli_scopyv(conj_t conjx,
                    long dim_t,
                    float[] x, long incx,
                    @RefArg(read_back_only = true) float[]y, long incy);
    void bli_sdotv(conj_t conjx,
                   conj_t conjy,
                   long dim_t,
                   float[] x, long incx,
                   float[] y, long incy,
                   @RefArg float[] rho);
    void bli_sdotxv(conj_t conjx,
                    conj_t conjy,
                    long dim_t,
                    float[] alpha,
                    float[] x, long incx,
                    float[] y, long incy,
                    float[] beta,
                    @RefArg float[] rho);
    @Critical
    void bli_sinvertv(long dim_t,
                      @RefArg float[]x, long incx);
    void bli_sinvscalv(conj_t conjalpha,
                       long dim_t,
                       float[] alpha,
                       @RefArg float[]x, long incx);
    void bli_sscalv(conj_t conjalpha,
                    long dim_t,
                    float[] alpha,
                    @RefArg float[] y, long incx);
    void bli_sscal2v(conj_t conjx,
                     long dim_t,
                     float[] alpha,
                     float[]x, long incx,
                     @RefArg float[]y, long incy);
    void bli_ssetv(conj_t conjalpha,
                   long dim_t,
                   float[] alpha,
                   @RefArg(read_back_only = true) float[] x, long incx);
    void bli_ssubv(conj_t conjx,
                   long dim_t,
                   float[] x, long incx,
                   @RefArg float[] y, long incy);
    void bli_sswapv(long dim_t,
                    @RefArg float[] x, long incx,
                    @RefArg float[] y, long incy);
    void bli_sxpbyv(conj_t conjx,
                    long dim_t,
                    float[] x, long incx,
                    float[] beta,
                    @RefArg float[] y, long incy);

    //------LEVEL 1d-----------------
    void bli_saddd(long diagoffx,
                   diag_t diagx,
                   trans_t transx,
                   long m, long n,
                   float[] x, long rs_x, long cs_x,
                   @RefArg float[] y, long rs_y, long cs_y);
    void bli_saxpyd(long diagoffx,
                    diag_t diagx,
                    trans_t transx,
                    long m, long n,
                    float[] alpha,
                    float[] x, long rs_x, long cs_x,
                    @RefArg float[] y, long rs_y, long cs_y);
    void bli_scopyd(long diagoffx,
                    diag_t diagx,
                    trans_t transx,
                    long m, long n,
                    float[] x, long rs_x, long cs_x,
                    @RefArg float[] y, long rs_y, long cs_y);
    void bli_sinvertd(long diagoffx,
                      long m, long n,
                      @RefArg float[]x, long rs_x, long cs_x);
    void bli_sinvscald(conj_t conjalpha,
                       long diagoffx,
                       long m, long n,
                       float[] alpha,
                       @RefArg  float[]x, long rs_x, long cs_x);
    void bli_sscald(conj_t conjalpha,
                    long diagoffx,
                    long m, long n,
                    float[] alpha,
                    @RefArg  float[]x, long rs_x, long cs_x);
    void bli_sscal2d(long diagoffx,
                     diag_t diagx,
                     trans_t transx,
                     long m, long n,
                     float[]alpha,
                     float[]x, long rs_x, long cs_x,
                     @RefArg float[]y, long rs_y, long cs_y);
    void bli_ssetd(conj_t conjalpha,
                   long diagoffx,
                   long m, long n,
                   float[]alpha,
                   @RefArg float[]x, long rs_x, long cs_x);
    void bli_ssetid(long diagoffx,
                    long m, long n,
                    float[]alpha,
                    @RefArg float[]x, long rs_x, long cs_x);
    void bli_sshiftd(long diagoffx,
                     long m, long n,
                     float[]alpha,
                     @RefArg float[]x, long rs_x, long cs_x);
    void bli_ssubd(long diagoffx,
                   diag_t diagx,
                   trans_t transx,
                   long m, long n,
                   float[]x, long rs_x, long cs_x,
                   @RefArg float[]y, long rs_y, long cs_y);
    void bli_sxpbyd(long diagoffx,
                    diag_t diagx, trans_t transx,
                    long m, long n,
                    float[]x, long rs_x, long cs_x,
                    float[]beta,
                    @RefArg float[]y, long rs_y, long cs_y);

    //------LEVEL 1m-----------------
    void bli_saddm(long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   trans_t transx,
                   long m, long n,
                   float[]x, long rs_x, long cs_x,
                   @RefArg float[]y, long rs_y, long cs_y);
    void bli_saxpym(long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    trans_t transx,
                    long m, long n,
                    float[]alpha,
                    float[]x, long rs_x, long cs_x,
                    @RefArg float[]y, long rs_y, long cs_y);
    void bli_scopym(long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    trans_t transx,
                    long m, long n,
                    float[]x, long rs_x, long cs_x,
                    @RefArg float[]y, long rs_y, long cs_y);
    void bli_sinvscalm(conj_t conjalpha,
                       long diagoffx,
                       diag_t diagx,
                       uplo_t uplox,
                       long m, long n,
                       float[]alpha,
                       @RefArg float[]x, long rs_x, long cs_x);
    void bli_sscalm(conj_t conjalpha,
                    long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    long m, long n,
                    float[]alpha,
                    @RefArg float[]x, long rs_x, long cs_x);
    void bli_sscal2m(long diagoffx,
                     diag_t diagx,
                     uplo_t uplox,
                     trans_t transx,
                     long m, long n,
                     float[]alpha,
                     float[]x, long rs_x, long cs_x,
                     @RefArg float[]y, long rs_y, long cs_y);
    void bli_ssetm(conj_t conjalpha,
                   long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   long m, long n,
                   float[]alpha,
                   @RefArg float[]x, long rs_x, long cs_x);
    void bli_ssubm(long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   trans_t transx,
                   long m, long n,
                   float[]x, long rs_x, long cs_x,
                   @RefArg float[]y, long rs_y, long cs_y);

    //------LEVEL 1f-----------------
    void bli_saxpy2v(conj_t  conjx,
                     conj_t  conjy,
                     long   dim_t,
                     float[]  alphax,
                     float[]  alphay,
                     float[]  x, long incx,
                     float[]  y, long incy,
                     @RefArg float[]  z, long incz
    );
    void bli_sdotaxpyv(conj_t  conjxt,
                       conj_t  conjx,
                       conj_t  conjy,
                       long   dim_t,
                       float[] alpha,
                       float[] x, long incx,
                       float[] y, long incy,
                       @RefArg float[]  rho,
                       @RefArg float[]  z, long incz
    );
    void bli_sdotxf(conj_t conjat,
                    conj_t conjx,
                    long m, long b_n,
                    float[] alpha,
                    float[] a, long inca, long lda,
                    float[] x, long incx,
                    float[] beta,
                    @RefArg float[] y, long incy);
    void bli_sdotxaxpyf(conj_t conjat,
                        conj_t conja,
                        conj_t conjw,
                        conj_t conjx,
                        long m, long b_n,
                        float[]alpha,
                        float[]a, long inca, long lda,
                        float[]w, long incw,
                        float[]x, long incx,
                        float[]beta,
                        @RefArg float[]y, long incy,
                        @RefArg float[]z, long incz);
    //------LEVEL 2-----------------
    void bli_sgemv(trans_t transa,
                   conj_t conjx,
                   long m, long n,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   float[]x, long incx,
                   float[]beta,
                   @RefArg float[]y, long incy);
    void bli_sger(conj_t conjx,
                  conj_t conjy,
                  long m, long n,
                  float[]alpha,
                  float[]x, long incx,
                  float[]y, long incy,
                  @RefArg float[]a, long rs_a, long cs_a);
    void bli_shemv(uplo_t uploa,
                   conj_t conja,
                   conj_t conjx,
                   long m,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   float[]x, long incx,
                   float[]beta,
                   @RefArg float[]y, long incy);
    void bli_sher(uplo_t uploa,
                  conj_t conjx,
                  long m,
                  float[]alpha,
                  float[]x, long incx,
                  @RefArg float[]a, long rs_a, long cs_a);
    void bli_sher2(uplo_t uploa,
                   conj_t conjx,
                   conj_t conjy,
                   long m,
                   float[]alpha,
                   float[]x, long incx,
                   float[]y, long incy,
                   @RefArg float[]a, long rs_a, long cs_a);
    void bli_ssymv(uplo_t uploa,
                   conj_t conja,
                   conj_t conjx,
                   long m,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   float[]x, long incx,
                   float[]beta,
                   @RefArg float[]y, long incy);
    void bli_ssyr(uplo_t uploa,
                  conj_t conjx,
                  long m,
                  float[]alpha,
                  float[]x, long incx,
                  @RefArg float[]a, long rs_a, long cs_a);
    void bli_ssyr2(uplo_t uploa,
                   conj_t conjx,
                   conj_t conjy,
                   long m,
                   float[]alpha,
                   float[]x, long incx,
                   float[]y, long incy,
                   @RefArg float[]a, long rs_a, long cs_a);
    void bli_strmv(uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   @RefArg float[]x, long incx);
    void bli_strsv(uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   @RefArg float[]x, long incx);
    //------LEVEL 3-----------------
    void bli_sgemm(trans_t transa,
                   trans_t transb,
                   long m, long n, long k,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   float[]b, long rs_b, long cs_b,
                   float[]beta,
                   @RefArg float[]c, long rs_c, long cs_c);
    void bli_sgemmt(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    float[]alpha,
                    float[]a, long rs_a, long cs_a,
                    float[]b, long rs_b, long cs_b,
                    float[]beta,
                    @RefArg float[]c, long rs_c, long cs_c);
    void bli_shemm(side_t side,
                   uplo_t uploa,
                   conj_t conja,
                   trans_t transb,
                   long m, long n,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   float[]b, long rs_b, long cs_b,
                   float[]beta,
                   @RefArg float[]c, long rs_c, long cs_c);
    void bli_sherk(uplo_t uploc,
                   trans_t transa,
                   long m, long k,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   float[]beta,
                   @RefArg float[]c, long rs_c, long cs_c);
    void bli_sher2k(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    float[]alpha,
                    float[]a, long rs_a, long cs_a,
                    float[]b, long rs_b, long cs_b,
                    float[]beta,
                    @RefArg float[]c, long rs_c, long cs_c);
    void bli_ssymm(side_t side,
                   uplo_t uploa,
                   conj_t conja,
                   trans_t transb,
                   long m, long n,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   float[]b, long rs_b, long cs_b,
                   float[]beta,
                   @RefArg float[]c, long rs_c, long cs_c);
    void bli_ssyrk(uplo_t uploc,
                   trans_t transa,
                   long m, long k,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   float[]beta,
                   @RefArg float[]c, long rs_c, long cs_c);
    void bli_ssyr2k(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    float[]alpha,
                    float[]a, long rs_a, long cs_a,
                    float[]b, long rs_b, long cs_b,
                    float[]beta,
                    @RefArg float[]c, long rs_c, long cs_c);
    void bli_strmm(side_t side,
                   uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m, long n,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   @RefArg float[]b, long rs_b, long cs_b);
    void bli_strmm3(side_t side,
                    uplo_t uploa,
                    trans_t transa,
                    diag_t diaga,
                    trans_t transb,
                    long m, long n,
                    float[]alpha,
                    float[]a, long rs_a, long cs_a,
                    float[]b, long rs_b, long cs_b,
                    float[]beta,
                    @RefArg float[]c, long rs_c, long cs_c);
    void bli_strsm(side_t side,
                   uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m, long n,
                   float[]alpha,
                   float[]a, long rs_a, long cs_a,
                   @RefArg float[]b, long rs_b, long cs_b);
    //------Utility-----------------
    void bli_sasumv(long n, float[]x, long incx, @RefArg float[]asum);
    void bli_snorm1m(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, float[]x, long rs_x, long cs_x, @RefArg float[]norm);
    void bli_snormfm(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, float[]x, long rs_x, long cs_x, @RefArg float[]norm);
    void bli_snormim(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, float[]x, long rs_x, long cs_x, @RefArg float[]norm);
    void bli_snorm1v(long n, float[]x, long incx, @RefArg float[]norm);
    void bli_snormfv(long n, float[]x, long incx, @RefArg float[]norm);
    void bli_snormiv(long n, float[]x, long incx, @RefArg float[]norm);
    void bli_smkherm(uplo_t uploa, long m, @RefArg float[]a, long rs_a, long cs_a);
    void bli_smksymm(uplo_t uploa, long m, @RefArg float[]a, long rs_a, long cs_a);
    void bli_smktrim(uplo_t uploa, long m, @RefArg float[]a, long rs_a, long cs_a);
    //    void bli_sfprintv(FILE *file, String s1, long n, float[]x, long incx, String format, String s2);
//    void bli_sfprintm(FILE *file, String s1, long m, long n, float[]x, long rs_x, long cs_x, String format, String s2);
    void bli_sprintv(String s1, long n, float[] x, long incx, String format, String s2);
    void bli_sprintm(String s1,
                     long m, long n,
                     float[] x, long rs_x, long cs_x,
                     String format,
                     String s2);
    @Critical
    void bli_srandv(long n, @RefArg float[] x, long incx);
    @Critical
    void bli_srandm(long diagoffx,
                    uplo_t uplox,
                    long m, long n,
                    @RefArg float[]x, long rs_x, long cs_x);
    void bli_ssumsqv(long n, float[]x, long incx, @RefArg float[]scale, @RefArg float[]sumsq);
    void bli_sgetsc(float[]chi, @RefArg float[]zeta_r, @RefArg float[]zeta_i);
    void bli_sgetijv(long i, float[] b, long incx, @RefArg float[]ar, @RefArg float[]ai);
    void bli_sgetijm(long i, long j, float[] b, long rs, long cs, @RefArg float[]ar, @RefArg float[]ai);
    void bli_ssetsc(float zeta_r, float zeta_i, @RefArg float[]chi);
    void bli_ssetijv(float ar, float ai, long i, @RefArg float[] x, long incx);
    void bli_ssetijm(float ar, float ai,
                     long i, long j,
                     @RefArg float[]b, long rs, long cs);
    void bli_seqsc(conj_t conjchi, float[]chi, float[]psi, @RefArg boolean[] is_eq);
    void bli_seqv(conj_t conjx, long n, float[]x, long incx, float[]y, long incy, @RefArg boolean[]is_eq);
    void bli_seqm(long diagoffx, diag_t diagx, uplo_t uplox, trans_t transx, long m, long n, float[]x, long rs_x, long cs_x, float[]y, long rs_y, long cs_y, @RefArg boolean[]is_eq);
}
