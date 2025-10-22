package jblis.tapi;

import jblis.types.dcomplex;
import jblis.types.*;
import jpassport.Passport;
import jpassport.annotations.Critical;
import jpassport.annotations.RefArg;

public interface blis_tapiComplexDouble extends Passport {
    //------LEVEL 1v-----------------
    void bli_zaddv(conj_t conjx,
                   long dim_t,
                   dcomplex[]x, long incx,
                   @RefArg dcomplex[] y, long incy);
    void bli_zamaxv(long dim_t,
                    dcomplex[]x, long incx,
                    @RefArg long[] dim_t2);
    void bli_zaxpyv(conj_t conjx,
                    long dim_t,
                    dcomplex[] alpha,
                    dcomplex[] x, long incx,
                    @RefArg dcomplex[] y, long incy);
    void bli_zaxpbyv(conj_t conjx,
                     long dim_t,
                     dcomplex[] alpha,
                     dcomplex[] x, long incx,
                     dcomplex[] beta,
                     @RefArg dcomplex[] y, long incy);
    void bli_zcopyv(conj_t conjx,
                    long dim_t,
                    dcomplex[] x, long incx,
                    @RefArg(read_back_only = true) dcomplex[]y, long incy);
    void bli_zdotv(conj_t conjx,
                   conj_t conjy,
                   long dim_t,
                   dcomplex[] x, long incx,
                   dcomplex[] y, long incy,
                   @RefArg dcomplex[] rho);
    void bli_zdotxv(conj_t conjx,
                    conj_t conjy,
                    long dim_t,
                    dcomplex[] alpha,
                    dcomplex[] x, long incx,
                    dcomplex[] y, long incy,
                    dcomplex[] beta,
                    @RefArg dcomplex[] rho);
    @Critical
    void bli_zinvertv(long dim_t,
                      @RefArg dcomplex[]x, long incx);
    void bli_zinvscalv(conj_t conjalpha,
                       long dim_t,
                       dcomplex[] alpha,
                       @RefArg dcomplex[]x, long incx);
    void bli_zscalv(conj_t conjalpha,
                    long dim_t,
                    dcomplex[] alpha,
                    @RefArg dcomplex[] y, long incx);
    void bli_zscal2v(conj_t conjx,
                     long dim_t,
                     dcomplex[] alpha,
                     dcomplex[]x, long incx,
                     @RefArg dcomplex[]y, long incy);
    void bli_zsetv(conj_t conjalpha,
                   long dim_t,
                   dcomplex[] alpha,
                   @RefArg(read_back_only = true) dcomplex[] x, long incx);
    void bli_zsubv(conj_t conjx,
                   long dim_t,
                   dcomplex[] x, long incx,
                   @RefArg dcomplex[] y, long incy);
    void bli_zswapv(long dim_t,
                    @RefArg dcomplex[] x, long incx,
                    @RefArg dcomplex[] y, long incy);
    void bli_zxpbyv(conj_t conjx,
                    long dim_t,
                    dcomplex[] x, long incx,
                    dcomplex[] beta,
                    @RefArg dcomplex[] y, long incy);

    //------LEVEL 1d-----------------
    void bli_zaddd(long diagoffx,
                   diag_t diagx,
                   trans_t transx,
                   long m, long n,
                   dcomplex[] x, long rs_x, long cs_x,
                   @RefArg dcomplex[] y, long rs_y, long cs_y);
    void bli_zaxpyd(long diagoffx,
                    diag_t diagx,
                    trans_t transx,
                    long m, long n,
                    dcomplex[] alpha,
                    dcomplex[] x, long rs_x, long cs_x,
                    @RefArg dcomplex[] y, long rs_y, long cs_y);
    void bli_zcopyd(long diagoffx,
                    diag_t diagx,
                    trans_t transx,
                    long m, long n,
                    dcomplex[] x, long rs_x, long cs_x,
                    @RefArg dcomplex[] y, long rs_y, long cs_y);
    void bli_zinvertd(long diagoffx,
                      long m, long n,
                      @RefArg dcomplex[]x, long rs_x, long cs_x);
    void bli_zinvscald(conj_t conjalpha,
                       long diagoffx,
                       long m, long n,
                       dcomplex[] alpha,
                       @RefArg  dcomplex[]x, long rs_x, long cs_x);
    void bli_zscald(conj_t conjalpha,
                    long diagoffx,
                    long m, long n,
                    dcomplex[] alpha,
                    @RefArg  dcomplex[]x, long rs_x, long cs_x);
    void bli_zscal2d(long diagoffx,
                     diag_t diagx,
                     trans_t transx,
                     long m, long n,
                     dcomplex[]alpha,
                     dcomplex[]x, long rs_x, long cs_x,
                     @RefArg dcomplex[]y, long rs_y, long cs_y);
    void bli_zsetd(conj_t conjalpha,
                   long diagoffx,
                   long m, long n,
                   dcomplex[]alpha,
                   @RefArg dcomplex[]x, long rs_x, long cs_x);
    void bli_zsetid(long diagoffx,
                    long m, long n,
                    dcomplex[]alpha,
                    @RefArg dcomplex[]x, long rs_x, long cs_x);
    void bli_zshiftd(long diagoffx,
                     long m, long n,
                     dcomplex[]alpha,
                     @RefArg dcomplex[]x, long rs_x, long cs_x);
    void bli_zsubd(long diagoffx,
                   diag_t diagx,
                   trans_t transx,
                   long m, long n,
                   dcomplex[]x, long rs_x, long cs_x,
                   @RefArg dcomplex[]y, long rs_y, long cs_y);
    void bli_zxpbyd(long diagoffx,
                    diag_t diagx, trans_t transx,
                    long m, long n,
                    dcomplex[]x, long rs_x, long cs_x,
                    dcomplex[]beta,
                    @RefArg dcomplex[]y, long rs_y, long cs_y);

    //------LEVEL 1m-----------------
    void bli_zaddm(long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   trans_t transx,
                   long m, long n,
                   dcomplex[]x, long rs_x, long cs_x,
                   @RefArg dcomplex[]y, long rs_y, long cs_y);
    void bli_zaxpym(long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    trans_t transx,
                    long m, long n,
                    dcomplex[]alpha,
                    dcomplex[]x, long rs_x, long cs_x,
                    @RefArg dcomplex[]y, long rs_y, long cs_y);
    void bli_zcopym(long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    trans_t transx,
                    long m, long n,
                    dcomplex[]x, long rs_x, long cs_x,
                    @RefArg dcomplex[]y, long rs_y, long cs_y);
    void bli_zinvscalm(conj_t conjalpha,
                       long diagoffx,
                       diag_t diagx,
                       uplo_t uplox,
                       long m, long n,
                       dcomplex[]alpha,
                       @RefArg dcomplex[]x, long rs_x, long cs_x);
    void bli_zscalm(conj_t conjalpha,
                    long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    long m, long n,
                    dcomplex[]alpha,
                    @RefArg dcomplex[]x, long rs_x, long cs_x);
    void bli_zscal2m(long diagoffx,
                     diag_t diagx,
                     uplo_t uplox,
                     trans_t transx,
                     long m, long n,
                     dcomplex[]alpha,
                     dcomplex[]x, long rs_x, long cs_x,
                     @RefArg dcomplex[]y, long rs_y, long cs_y);
    void bli_zsetm(conj_t conjalpha,
                   long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   long m, long n,
                   dcomplex[]alpha,
                   @RefArg dcomplex[]x, long rs_x, long cs_x);
    void bli_zsubm(long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   trans_t transx,
                   long m, long n,
                   dcomplex[]x, long rs_x, long cs_x,
                   @RefArg dcomplex[]y, long rs_y, long cs_y);

    //------LEVEL 1f-----------------
    void bli_zaxpy2v(conj_t  conjx,
                     conj_t  conjy,
                     long   dim_t,
                     dcomplex[]  alphax,
                     dcomplex[]  alphay,
                     dcomplex[]  x, long incx,
                     dcomplex[]  y, long incy,
                     @RefArg dcomplex[]  z, long incz
    );
    void bli_zdotaxpyv(conj_t  conjxt,
                       conj_t  conjx,
                       conj_t  conjy,
                       long   dim_t,
                       dcomplex[] alpha,
                       dcomplex[] x, long incx,
                       dcomplex[] y, long incy,
                       @RefArg dcomplex[]  rho,
                       @RefArg dcomplex[]  z, long incz
    );
    void bli_zdotxf(conj_t conjat,
                    conj_t conjx,
                    long m, long b_n,
                    dcomplex[] alpha,
                    dcomplex[] a, long inca, long lda,
                    dcomplex[] x, long incx,
                    dcomplex[] beta,
                    @RefArg dcomplex[] y, long incy);
    void bli_zdotxaxpyf(conj_t conjat,
                        conj_t conja,
                        conj_t conjw,
                        conj_t conjx,
                        long m, long b_n,
                        dcomplex[]alpha,
                        dcomplex[]a, long inca, long lda,
                        dcomplex[]w, long incw,
                        dcomplex[]x, long incx,
                        dcomplex[]beta,
                        @RefArg dcomplex[]y, long incy,
                        @RefArg dcomplex[]z, long incz);
    //------LEVEL 2-----------------
    void bli_zgemv(trans_t transa,
                   conj_t conjx,
                   long m, long n,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   dcomplex[]x, long incx,
                   dcomplex[]beta,
                   @RefArg dcomplex[]y, long incy);
    void bli_zger(conj_t conjx,
                  conj_t conjy,
                  long m, long n,
                  dcomplex[]alpha,
                  dcomplex[]x, long incx,
                  dcomplex[]y, long incy,
                  @RefArg dcomplex[]a, long rs_a, long cs_a);
    void bli_zhemv(uplo_t uploa,
                   conj_t conja,
                   conj_t conjx,
                   long m,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   dcomplex[]x, long incx,
                   dcomplex[]beta,
                   @RefArg dcomplex[]y, long incy);
    void bli_zher(uplo_t uploa,
                  conj_t conjx,
                  long m,
                  dcomplex[]alpha,
                  dcomplex[]x, long incx,
                  @RefArg dcomplex[]a, long rs_a, long cs_a);
    void bli_zher2(uplo_t uploa,
                   conj_t conjx,
                   conj_t conjy,
                   long m,
                   dcomplex[]alpha,
                   dcomplex[]x, long incx,
                   dcomplex[]y, long incy,
                   @RefArg dcomplex[]a, long rs_a, long cs_a);
    void bli_zsymv(uplo_t uploa,
                   conj_t conja,
                   conj_t conjx,
                   long m,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   dcomplex[]x, long incx,
                   dcomplex[]beta,
                   @RefArg dcomplex[]y, long incy);
    void bli_zsyr(uplo_t uploa,
                  conj_t conjx,
                  long m,
                  dcomplex[]alpha,
                  dcomplex[]x, long incx,
                  @RefArg dcomplex[]a, long rs_a, long cs_a);
    void bli_zsyr2(uplo_t uploa,
                   conj_t conjx,
                   conj_t conjy,
                   long m,
                   dcomplex[]alpha,
                   dcomplex[]x, long incx,
                   dcomplex[]y, long incy,
                   @RefArg dcomplex[]a, long rs_a, long cs_a);
    void bli_ztrmv(uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   @RefArg dcomplex[]x, long incx);
    void bli_ztrsv(uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   @RefArg dcomplex[]x, long incx);
    //------LEVEL 3-----------------
    void bli_zgemm(trans_t transa,
                   trans_t transb,
                   long m, long n, long k,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   dcomplex[]b, long rs_b, long cs_b,
                   dcomplex[]beta,
                   @RefArg dcomplex[]c, long rs_c, long cs_c);
    void bli_zgemmt(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    dcomplex[]alpha,
                    dcomplex[]a, long rs_a, long cs_a,
                    dcomplex[]b, long rs_b, long cs_b,
                    dcomplex[]beta,
                    @RefArg dcomplex[]c, long rs_c, long cs_c);
    void bli_zhemm(side_t side,
                   uplo_t uploa,
                   conj_t conja,
                   trans_t transb,
                   long m, long n,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   dcomplex[]b, long rs_b, long cs_b,
                   dcomplex[]beta,
                   @RefArg dcomplex[]c, long rs_c, long cs_c);
    void bli_zherk(uplo_t uploc,
                   trans_t transa,
                   long m, long k,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   dcomplex[]beta,
                   @RefArg dcomplex[]c, long rs_c, long cs_c);
    void bli_zher2k(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    dcomplex[]alpha,
                    dcomplex[]a, long rs_a, long cs_a,
                    dcomplex[]b, long rs_b, long cs_b,
                    dcomplex[]beta,
                    @RefArg dcomplex[]c, long rs_c, long cs_c);
    void bli_zsymm(side_t side,
                   uplo_t uploa,
                   conj_t conja,
                   trans_t transb,
                   long m, long n,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   dcomplex[]b, long rs_b, long cs_b,
                   dcomplex[]beta,
                   @RefArg dcomplex[]c, long rs_c, long cs_c);
    void bli_zsyrk(uplo_t uploc,
                   trans_t transa,
                   long m, long k,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   dcomplex[]beta,
                   @RefArg dcomplex[]c, long rs_c, long cs_c);
    void bli_zsyr2k(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    dcomplex[]alpha,
                    dcomplex[]a, long rs_a, long cs_a,
                    dcomplex[]b, long rs_b, long cs_b,
                    dcomplex[]beta,
                    @RefArg dcomplex[]c, long rs_c, long cs_c);
    void bli_ztrmm(side_t side,
                   uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m, long n,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   @RefArg dcomplex[]b, long rs_b, long cs_b);
    void bli_ztrmm3(side_t side,
                    uplo_t uploa,
                    trans_t transa,
                    diag_t diaga,
                    trans_t transb,
                    long m, long n,
                    dcomplex[]alpha,
                    dcomplex[]a, long rs_a, long cs_a,
                    dcomplex[]b, long rs_b, long cs_b,
                    dcomplex[]beta,
                    @RefArg dcomplex[]c, long rs_c, long cs_c);
    void bli_ztrsm(side_t side,
                   uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m, long n,
                   dcomplex[]alpha,
                   dcomplex[]a, long rs_a, long cs_a,
                   @RefArg dcomplex[]b, long rs_b, long cs_b);
    //------Utility-----------------
    void bli_zasumv(long n, dcomplex[]x, long incx, @RefArg dcomplex[]asum);
    void bli_znorm1m(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, dcomplex[]x, long rs_x, long cs_x, @RefArg double[]norm);
    void bli_znormfm(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, dcomplex[]x, long rs_x, long cs_x, @RefArg double[]norm);
    void bli_znormim(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, dcomplex[]x, long rs_x, long cs_x, @RefArg double[]norm);
    void bli_znorm1v(long n, dcomplex[]x, long incx, @RefArg double[]norm);
    void bli_znormfv(long n, dcomplex[]x, long incx, @RefArg double[]norm);
    void bli_znormiv(long n, dcomplex[]x, long incx, @RefArg double[]norm);
    void bli_zmkherm(uplo_t uploa, long m, @RefArg dcomplex[]a, long rs_a, long cs_a);
    void bli_zmksymm(uplo_t uploa, long m, @RefArg dcomplex[]a, long rs_a, long cs_a);
    void bli_zmktrim(uplo_t uploa, long m, @RefArg dcomplex[]a, long rs_a, long cs_a);
    //    void bli_zfprintv(FILE *file, String s1, long n, dcomplex[]x, long incx, String format, String s2);
//    void bli_zfprintm(FILE *file, String s1, long m, long n, dcomplex[]x, long rs_x, long cs_x, String format, String s2);
    void bli_zprintv(String s1, long n, dcomplex[] x, long incx, String format, String s2);
    void bli_zprintm(String s1,
                     long m, long n,
                     dcomplex[] x, long rs_x, long cs_x,
                     String format,
                     String s2);
    @Critical
    void bli_zrandv(long n, @RefArg dcomplex[] x, long incx);
    @Critical
    void bli_zrandm(long diagoffx,
                    uplo_t uplox,
                    long m, long n,
                    @RefArg dcomplex[]x, long rs_x, long cs_x);
    void bli_zsumsqv(long n, dcomplex[]x, long incx, @RefArg dcomplex[]scale, @RefArg dcomplex[]sumsq);
    void bli_zgetsc(dcomplex[]chi, @RefArg dcomplex[]zeta_r, @RefArg dcomplex[]zeta_i);
    void bli_zgetijv(long i, dcomplex[] b, long incx, @RefArg dcomplex[]ar, @RefArg dcomplex[]ai);
    void bli_zgetijm(long i, long j, dcomplex[] b, long rs, long cs, @RefArg dcomplex[]ar, @RefArg dcomplex[]ai);
    void bli_zsetsc(dcomplex zeta_r, dcomplex zeta_i, @RefArg dcomplex[]chi);
    void bli_zsetijv(dcomplex ar, dcomplex ai, long i, @RefArg dcomplex[] x, long incx);
    void bli_zsetijm(dcomplex ar, dcomplex ai,
                     long i, long j,
                     @RefArg dcomplex[]b, long rs, long cs);
    void bli_zeqsc(conj_t conjchi, dcomplex[]chi, dcomplex[]psi, @RefArg boolean[] is_eq);
    void bli_zeqv(conj_t conjx, long n, dcomplex[]x, long incx, dcomplex[]y, long incy, @RefArg boolean[]is_eq);
    void bli_zeqm(long diagoffx, diag_t diagx, uplo_t uplox, trans_t transx, long m, long n, dcomplex[]x, long rs_x, long cs_x, dcomplex[]y, long rs_y, long cs_y, @RefArg boolean[]is_eq);
}
