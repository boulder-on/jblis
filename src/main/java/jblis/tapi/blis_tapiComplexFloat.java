package jblis.tapi;

import jblis.types.scomplex;
import jblis.types.*;
import jpassport.Passport;
import jpassport.annotations.Critical;
import jpassport.annotations.RefArg;

public interface blis_tapiComplexFloat extends Passport {
    //------LEVEL 1v-----------------
    void bli_caddv(conj_t conjx,
                   long dim_t,
                   scomplex[]x, long incx,
                   @RefArg scomplex[] y, long incy);
    void bli_camaxv(long dim_t,
                    scomplex[]x, long incx,
                    @RefArg long[] dim_t2);
    void bli_caxpyv(conj_t conjx,
                    long dim_t,
                    scomplex[] alpha,
                    scomplex[] x, long incx,
                    @RefArg scomplex[] y, long incy);
    void bli_caxpbyv(conj_t conjx,
                     long dim_t,
                     scomplex[] alpha,
                     scomplex[] x, long incx,
                     scomplex[] beta,
                     @RefArg scomplex[] y, long incy);
    void bli_ccopyv(conj_t conjx,
                    long dim_t,
                    scomplex[] x, long incx,
                    @RefArg(read_back_only = true) scomplex[]y, long incy);
    void bli_cdotv(conj_t conjx,
                   conj_t conjy,
                   long dim_t,
                   scomplex[] x, long incx,
                   scomplex[] y, long incy,
                   @RefArg scomplex[] rho);
    void bli_cdotxv(conj_t conjx,
                    conj_t conjy,
                    long dim_t,
                    scomplex[] alpha,
                    scomplex[] x, long incx,
                    scomplex[] y, long incy,
                    scomplex[] beta,
                    @RefArg scomplex[] rho);
    @Critical
    void bli_cinvertv(long dim_t,
                      @RefArg scomplex[]x, long incx);
    void bli_cinvscalv(conj_t conjalpha,
                       long dim_t,
                       scomplex[] alpha,
                       @RefArg scomplex[]x, long incx);
    void bli_cscalv(conj_t conjalpha,
                    long dim_t,
                    scomplex[] alpha,
                    @RefArg scomplex[] y, long incx);
    void bli_cscal2v(conj_t conjx,
                     long dim_t,
                     scomplex[] alpha,
                     scomplex[]x, long incx,
                     @RefArg scomplex[]y, long incy);
    void bli_csetv(conj_t conjalpha,
                   long dim_t,
                   scomplex[] alpha,
                   @RefArg(read_back_only = true) scomplex[] x, long incx);
    void bli_csubv(conj_t conjx,
                   long dim_t,
                   scomplex[] x, long incx,
                   @RefArg scomplex[] y, long incy);
    void bli_cswapv(long dim_t,
                    @RefArg scomplex[] x, long incx,
                    @RefArg scomplex[] y, long incy);
    void bli_cxpbyv(conj_t conjx,
                    long dim_t,
                    scomplex[] x, long incx,
                    scomplex[] beta,
                    @RefArg scomplex[] y, long incy);

    //------LEVEL 1d-----------------
    void bli_caddd(long diagoffx,
                   diag_t diagx,
                   trans_t transx,
                   long m, long n,
                   scomplex[] x, long rs_x, long cs_x,
                   @RefArg scomplex[] y, long rs_y, long cs_y);
    void bli_caxpyd(long diagoffx,
                    diag_t diagx,
                    trans_t transx,
                    long m, long n,
                    scomplex[] alpha,
                    scomplex[] x, long rs_x, long cs_x,
                    @RefArg scomplex[] y, long rs_y, long cs_y);
    void bli_ccopyd(long diagoffx,
                    diag_t diagx,
                    trans_t transx,
                    long m, long n,
                    scomplex[] x, long rs_x, long cs_x,
                    @RefArg scomplex[] y, long rs_y, long cs_y);
    void bli_cinvertd(long diagoffx,
                      long m, long n,
                      @RefArg scomplex[]x, long rs_x, long cs_x);
    void bli_cinvscald(conj_t conjalpha,
                       long diagoffx,
                       long m, long n,
                       scomplex[] alpha,
                       @RefArg  scomplex[]x, long rs_x, long cs_x);
    void bli_cscald(conj_t conjalpha,
                    long diagoffx,
                    long m, long n,
                    scomplex[] alpha,
                    @RefArg  scomplex[]x, long rs_x, long cs_x);
    void bli_cscal2d(long diagoffx,
                     diag_t diagx,
                     trans_t transx,
                     long m, long n,
                     scomplex[]alpha,
                     scomplex[]x, long rs_x, long cs_x,
                     @RefArg scomplex[]y, long rs_y, long cs_y);
    void bli_csetd(conj_t conjalpha,
                   long diagoffx,
                   long m, long n,
                   scomplex[]alpha,
                   @RefArg scomplex[]x, long rs_x, long cs_x);
    void bli_csetid(long diagoffx,
                    long m, long n,
                    scomplex[]alpha,
                    @RefArg scomplex[]x, long rs_x, long cs_x);
    void bli_cshiftd(long diagoffx,
                     long m, long n,
                     scomplex[]alpha,
                     @RefArg scomplex[]x, long rs_x, long cs_x);
    void bli_csubd(long diagoffx,
                   diag_t diagx,
                   trans_t transx,
                   long m, long n,
                   scomplex[]x, long rs_x, long cs_x,
                   @RefArg scomplex[]y, long rs_y, long cs_y);
    void bli_cxpbyd(long diagoffx,
                    diag_t diagx, trans_t transx,
                    long m, long n,
                    scomplex[]x, long rs_x, long cs_x,
                    scomplex[]beta,
                    @RefArg scomplex[]y, long rs_y, long cs_y);

    //------LEVEL 1m-----------------
    void bli_caddm(long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   trans_t transx,
                   long m, long n,
                   scomplex[]x, long rs_x, long cs_x,
                   @RefArg scomplex[]y, long rs_y, long cs_y);
    void bli_caxpym(long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    trans_t transx,
                    long m, long n,
                    scomplex[]alpha,
                    scomplex[]x, long rs_x, long cs_x,
                    @RefArg scomplex[]y, long rs_y, long cs_y);
    void bli_ccopym(long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    trans_t transx,
                    long m, long n,
                    scomplex[]x, long rs_x, long cs_x,
                    @RefArg scomplex[]y, long rs_y, long cs_y);
    void bli_cinvscalm(conj_t conjalpha,
                       long diagoffx,
                       diag_t diagx,
                       uplo_t uplox,
                       long m, long n,
                       scomplex[]alpha,
                       @RefArg scomplex[]x, long rs_x, long cs_x);
    void bli_cscalm(conj_t conjalpha,
                    long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    long m, long n,
                    scomplex[]alpha,
                    @RefArg scomplex[]x, long rs_x, long cs_x);
    void bli_cscal2m(long diagoffx,
                     diag_t diagx,
                     uplo_t uplox,
                     trans_t transx,
                     long m, long n,
                     scomplex[]alpha,
                     scomplex[]x, long rs_x, long cs_x,
                     @RefArg scomplex[]y, long rs_y, long cs_y);
    void bli_csetm(conj_t conjalpha,
                   long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   long m, long n,
                   scomplex[]alpha,
                   @RefArg scomplex[]x, long rs_x, long cs_x);
    void bli_csubm(long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   trans_t transx,
                   long m, long n,
                   scomplex[]x, long rs_x, long cs_x,
                   @RefArg scomplex[]y, long rs_y, long cs_y);

    //------LEVEL 1f-----------------
    void bli_caxpy2v(conj_t  conjx,
                     conj_t  conjy,
                     long   dim_t,
                     scomplex[]  alphax,
                     scomplex[]  alphay,
                     scomplex[]  x, long incx,
                     scomplex[]  y, long incy,
                     @RefArg scomplex[]  z, long incz
    );
    void bli_cdotaxpyv(conj_t  conjxt,
                       conj_t  conjx,
                       conj_t  conjy,
                       long   dim_t,
                       scomplex[] alpha,
                       scomplex[] x, long incx,
                       scomplex[] y, long incy,
                       @RefArg scomplex[]  rho,
                       @RefArg scomplex[]  z, long incz
    );
    void bli_cdotxf(conj_t conjat,
                    conj_t conjx,
                    long m, long b_n,
                    scomplex[] alpha,
                    scomplex[] a, long inca, long lda,
                    scomplex[] x, long incx,
                    scomplex[] beta,
                    @RefArg scomplex[] y, long incy);
    void bli_cdotxaxpyf(conj_t conjat,
                        conj_t conja,
                        conj_t conjw,
                        conj_t conjx,
                        long m, long b_n,
                        scomplex[]alpha,
                        scomplex[]a, long inca, long lda,
                        scomplex[]w, long incw,
                        scomplex[]x, long incx,
                        scomplex[]beta,
                        @RefArg scomplex[]y, long incy,
                        @RefArg scomplex[]z, long incz);
    //------LEVEL 2-----------------
    void bli_cgemv(trans_t transa,
                   conj_t conjx,
                   long m, long n,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   scomplex[]x, long incx,
                   scomplex[]beta,
                   @RefArg scomplex[]y, long incy);
    void bli_cger(conj_t conjx,
                  conj_t conjy,
                  long m, long n,
                  scomplex[]alpha,
                  scomplex[]x, long incx,
                  scomplex[]y, long incy,
                  @RefArg scomplex[]a, long rs_a, long cs_a);
    void bli_chemv(uplo_t uploa,
                   conj_t conja,
                   conj_t conjx,
                   long m,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   scomplex[]x, long incx,
                   scomplex[]beta,
                   @RefArg scomplex[]y, long incy);
    void bli_cher(uplo_t uploa,
                  conj_t conjx,
                  long m,
                  scomplex[]alpha,
                  scomplex[]x, long incx,
                  @RefArg scomplex[]a, long rs_a, long cs_a);
    void bli_cher2(uplo_t uploa,
                   conj_t conjx,
                   conj_t conjy,
                   long m,
                   scomplex[]alpha,
                   scomplex[]x, long incx,
                   scomplex[]y, long incy,
                   @RefArg scomplex[]a, long rs_a, long cs_a);
    void bli_csymv(uplo_t uploa,
                   conj_t conja,
                   conj_t conjx,
                   long m,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   scomplex[]x, long incx,
                   scomplex[]beta,
                   @RefArg scomplex[]y, long incy);
    void bli_csyr(uplo_t uploa,
                  conj_t conjx,
                  long m,
                  scomplex[]alpha,
                  scomplex[]x, long incx,
                  @RefArg scomplex[]a, long rs_a, long cs_a);
    void bli_csyr2(uplo_t uploa,
                   conj_t conjx,
                   conj_t conjy,
                   long m,
                   scomplex[]alpha,
                   scomplex[]x, long incx,
                   scomplex[]y, long incy,
                   @RefArg scomplex[]a, long rs_a, long cs_a);
    void bli_ctrmv(uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   @RefArg scomplex[]x, long incx);
    void bli_ctrsv(uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   @RefArg scomplex[]x, long incx);
    //------LEVEL 3-----------------
    void bli_cgemm(trans_t transa,
                   trans_t transb,
                   long m, long n, long k,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   scomplex[]b, long rs_b, long cs_b,
                   scomplex[]beta,
                   @RefArg scomplex[]c, long rs_c, long cs_c);
    void bli_cgemmt(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    scomplex[]alpha,
                    scomplex[]a, long rs_a, long cs_a,
                    scomplex[]b, long rs_b, long cs_b,
                    scomplex[]beta,
                    @RefArg scomplex[]c, long rs_c, long cs_c);
    void bli_chemm(side_t side,
                   uplo_t uploa,
                   conj_t conja,
                   trans_t transb,
                   long m, long n,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   scomplex[]b, long rs_b, long cs_b,
                   scomplex[]beta,
                   @RefArg scomplex[]c, long rs_c, long cs_c);
    void bli_cherk(uplo_t uploc,
                   trans_t transa,
                   long m, long k,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   scomplex[]beta,
                   @RefArg scomplex[]c, long rs_c, long cs_c);
    void bli_cher2k(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    scomplex[]alpha,
                    scomplex[]a, long rs_a, long cs_a,
                    scomplex[]b, long rs_b, long cs_b,
                    scomplex[]beta,
                    @RefArg scomplex[]c, long rs_c, long cs_c);
    void bli_csymm(side_t side,
                   uplo_t uploa,
                   conj_t conja,
                   trans_t transb,
                   long m, long n,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   scomplex[]b, long rs_b, long cs_b,
                   scomplex[]beta,
                   @RefArg scomplex[]c, long rs_c, long cs_c);
    void bli_csyrk(uplo_t uploc,
                   trans_t transa,
                   long m, long k,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   scomplex[]beta,
                   @RefArg scomplex[]c, long rs_c, long cs_c);
    void bli_csyr2k(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    scomplex[]alpha,
                    scomplex[]a, long rs_a, long cs_a,
                    scomplex[]b, long rs_b, long cs_b,
                    scomplex[]beta,
                    @RefArg scomplex[]c, long rs_c, long cs_c);
    void bli_ctrmm(side_t side,
                   uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m, long n,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   @RefArg scomplex[]b, long rs_b, long cs_b);
    void bli_ctrmm3(side_t side,
                    uplo_t uploa,
                    trans_t transa,
                    diag_t diaga,
                    trans_t transb,
                    long m, long n,
                    scomplex[]alpha,
                    scomplex[]a, long rs_a, long cs_a,
                    scomplex[]b, long rs_b, long cs_b,
                    scomplex[]beta,
                    @RefArg scomplex[]c, long rs_c, long cs_c);
    void bli_ctrsm(side_t side,
                   uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m, long n,
                   scomplex[]alpha,
                   scomplex[]a, long rs_a, long cs_a,
                   @RefArg scomplex[]b, long rs_b, long cs_b);
    //------Utility-----------------
    void bli_casumv(long n, scomplex[]x, long incx, @RefArg scomplex[]asum);
    void bli_cnorm1m(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, scomplex[]x, long rs_x, long cs_x, @RefArg scomplex[]norm);
    void bli_cnormfm(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, scomplex[]x, long rs_x, long cs_x, @RefArg scomplex[]norm);
    void bli_cnormim(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, scomplex[]x, long rs_x, long cs_x, @RefArg scomplex[]norm);
    void bli_cnorm1v(long n, scomplex[]x, long incx, @RefArg scomplex[]norm);
    void bli_cnormfv(long n, scomplex[]x, long incx, @RefArg scomplex[]norm);
    void bli_cnormiv(long n, scomplex[]x, long incx, @RefArg scomplex[]norm);
    void bli_cmkherm(uplo_t uploa, long m, @RefArg scomplex[]a, long rs_a, long cs_a);
    void bli_cmksymm(uplo_t uploa, long m, @RefArg scomplex[]a, long rs_a, long cs_a);
    void bli_cmktrim(uplo_t uploa, long m, @RefArg scomplex[]a, long rs_a, long cs_a);
    //    void bli_cfprintv(FILE *file, String s1, long n, scomplex[]x, long incx, String format, String s2);
//    void bli_cfprintm(FILE *file, String s1, long m, long n, scomplex[]x, long rs_x, long cs_x, String format, String s2);
    void bli_cprintv(String s1, long n, scomplex[] x, long incx, String format, String s2);
    void bli_cprintm(String s1,
                     long m, long n,
                     scomplex[] x, long rs_x, long cs_x,
                     String format,
                     String s2);
    @Critical
    void bli_crandv(long n, @RefArg scomplex[] x, long incx);
    @Critical
    void bli_crandm(long diagoffx,
                    uplo_t uplox,
                    long m, long n,
                    @RefArg scomplex[]x, long rs_x, long cs_x);
    void bli_csumsqv(long n, scomplex[]x, long incx, @RefArg scomplex[]scale, @RefArg scomplex[]sumsq);
    void bli_cgetsc(scomplex[]chi, @RefArg scomplex[]zeta_r, @RefArg scomplex[]zeta_i);
    void bli_cgetijv(long i, scomplex[] b, long incx, @RefArg scomplex[]ar, @RefArg scomplex[]ai);
    void bli_cgetijm(long i, long j, scomplex[] b, long rs, long cs, @RefArg scomplex[]ar, @RefArg scomplex[]ai);
    void bli_csetsc(scomplex zeta_r, scomplex zeta_i, @RefArg scomplex[]chi);
    void bli_csetijv(scomplex ar, scomplex ai, long i, @RefArg scomplex[] x, long incx);
    void bli_csetijm(scomplex ar, scomplex ai,
                     long i, long j,
                     @RefArg scomplex[]b, long rs, long cs);
    void bli_ceqsc(conj_t conjchi, scomplex[]chi, scomplex[]psi, @RefArg boolean[] is_eq);
    void bli_ceqv(conj_t conjx, long n, scomplex[]x, long incx, scomplex[]y, long incy, @RefArg boolean[]is_eq);
    void bli_ceqm(long diagoffx, diag_t diagx, uplo_t uplox, trans_t transx, long m, long n, scomplex[]x, long rs_x, long cs_x, scomplex[]y, long rs_y, long cs_y, @RefArg boolean[]is_eq);

}
