package jblis.tapi;

import jblis.types.*;
import jpassport.Passport;
import jpassport.annotations.Critical;
import jpassport.annotations.RefArg;

public interface blis_tapiDouble extends Passport {
    //------LEVEL 1v-----------------
    void bli_daddv(conj_t conjx,
                   long dim_t,
                   double[]x, long incx,
                   @RefArg double[] y, long incy);
    void bli_damaxv(long dim_t,
                    double[]x, long incx,
                    @RefArg long[] dim_t2);
    void bli_daxpyv(conj_t conjx,
                    long dim_t,
                    double[] alpha,
                    double[] x, long incx,
                    @RefArg double[] y, long incy);
    void bli_daxpbyv(conj_t conjx,
                     long dim_t,
                     double[] alpha,
                     double[] x, long incx,
                     double[] beta,
                     @RefArg double[] y, long incy);
    void bli_dcopyv(conj_t conjx,
                    long dim_t,
                    double[] x, long incx,
                    @RefArg(read_back_only = true) double[]y, long incy);
    void bli_ddotv(conj_t conjx,
                   conj_t conjy,
                   long dim_t,
                   double[] x, long incx,
                   double[] y, long incy,
                   @RefArg double[] rho);
    void bli_ddotxv(conj_t conjx,
                    conj_t conjy,
                    long dim_t,
                    double[] alpha,
                    double[] x, long incx,
                    double[] y, long incy,
                    double[] beta,
                    @RefArg double[] rho);
    @Critical
    void bli_dinvertv(long dim_t,
                      @RefArg double[]x, long incx);
    void bli_dinvscalv(conj_t conjalpha,
                       long dim_t,
                       double[] alpha,
                       @RefArg double[]x, long incx);
    void bli_dscalv(conj_t conjalpha,
                    long dim_t,
                    double[] alpha,
                    @RefArg double[] y, long incx);
    void bli_dscal2v(conj_t conjx,
                     long dim_t,
                     double[] alpha,
                     double[]x, long incx,
                     @RefArg double[]y, long incy);
    void bli_dsetv(conj_t conjalpha,
                   long dim_t,
                   double[] alpha,
                   @RefArg(read_back_only = true) double[] x, long incx);
    void bli_dsubv(conj_t conjx,
                   long dim_t,
                   double[] x, long incx,
                   @RefArg double[] y, long incy);
    void bli_dswapv(long dim_t,
                    @RefArg double[] x, long incx,
                    @RefArg double[] y, long incy);
    void bli_dxpbyv(conj_t conjx,
                    long dim_t,
                    double[] x, long incx,
                    double[] beta,
                    @RefArg double[] y, long incy);

    //------LEVEL 1d-----------------
    void bli_daddd(long diagoffx,
                   diag_t diagx,
                   trans_t transx,
                   long m, long n,
                   double[] x, long rs_x, long cs_x,
                   @RefArg double[] y, long rs_y, long cs_y);
    void bli_daxpyd(long diagoffx,
                    diag_t diagx,
                    trans_t transx,
                    long m, long n,
                    double[] alpha,
                    double[] x, long rs_x, long cs_x,
                    @RefArg double[] y, long rs_y, long cs_y);
    void bli_dcopyd(long diagoffx,
                    diag_t diagx,
                    trans_t transx,
                    long m, long n,
                    double[] x, long rs_x, long cs_x,
                    @RefArg double[] y, long rs_y, long cs_y);
    void bli_dinvertd(long diagoffx,
                      long m, long n,
                      @RefArg double[]x, long rs_x, long cs_x);
    void bli_dinvscald(conj_t conjalpha,
                       long diagoffx,
                       long m, long n,
                       double[] alpha,
                       @RefArg  double[]x, long rs_x, long cs_x);
    void bli_dscald(conj_t conjalpha,
                    long diagoffx,
                    long m, long n,
                    double[] alpha,
                    @RefArg  double[]x, long rs_x, long cs_x);
    void bli_dscal2d(long diagoffx,
                     diag_t diagx,
                     trans_t transx,
                     long m, long n,
                     double[]alpha,
                     double[]x, long rs_x, long cs_x,
                     @RefArg double[]y, long rs_y, long cs_y);
    void bli_dsetd(conj_t conjalpha,
                   long diagoffx,
                   long m, long n,
                   double[]alpha,
                   @RefArg double[]x, long rs_x, long cs_x);
    void bli_dsetid(long diagoffx,
                    long m, long n,
                    double[]alpha,
                    @RefArg double[]x, long rs_x, long cs_x);
    void bli_dshiftd(long diagoffx,
                     long m, long n,
                     double[]alpha,
                     @RefArg double[]x, long rs_x, long cs_x);
    void bli_dsubd(long diagoffx,
                   diag_t diagx,
                   trans_t transx,
                   long m, long n,
                   double[]x, long rs_x, long cs_x,
                   @RefArg double[]y, long rs_y, long cs_y);
    void bli_dxpbyd(long diagoffx,
                    diag_t diagx, trans_t transx,
                    long m, long n,
                    double[]x, long rs_x, long cs_x,
                    double[]beta,
                    @RefArg double[]y, long rs_y, long cs_y);

    //------LEVEL 1m-----------------
    void bli_daddm(long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   trans_t transx,
                   long m, long n,
                   double[]x, long rs_x, long cs_x,
                   @RefArg double[]y, long rs_y, long cs_y);
    void bli_daxpym(long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    trans_t transx,
                    long m, long n,
                    double[]alpha,
                    double[]x, long rs_x, long cs_x,
                    @RefArg double[]y, long rs_y, long cs_y);
    void bli_dcopym(long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    trans_t transx,
                    long m, long n,
                    double[]x, long rs_x, long cs_x,
                    @RefArg double[]y, long rs_y, long cs_y);
    void bli_dinvscalm(conj_t conjalpha,
                       long diagoffx,
                       diag_t diagx,
                       uplo_t uplox,
                       long m, long n,
                       double[]alpha,
                       @RefArg double[]x, long rs_x, long cs_x);
    void bli_dscalm(conj_t conjalpha,
                    long diagoffx,
                    diag_t diagx,
                    uplo_t uplox,
                    long m, long n,
                    double[]alpha,
                    @RefArg double[]x, long rs_x, long cs_x);
    void bli_dscal2m(long diagoffx,
                     diag_t diagx,
                     uplo_t uplox,
                     trans_t transx,
                     long m, long n,
                     double[]alpha,
                     double[]x, long rs_x, long cs_x,
                     @RefArg double[]y, long rs_y, long cs_y);
    void bli_dsetm(conj_t conjalpha,
                   long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   long m, long n,
                   double[]alpha,
                   @RefArg double[]x, long rs_x, long cs_x);
    void bli_dsubm(long diagoffx,
                   diag_t diagx,
                   uplo_t uplox,
                   trans_t transx,
                   long m, long n,
                   double[]x, long rs_x, long cs_x,
                   @RefArg double[]y, long rs_y, long cs_y);

    //------LEVEL 1f-----------------
    void bli_daxpy2v(conj_t  conjx,
                     conj_t  conjy,
                     long   dim_t,
                     double[]  alphax,
                     double[]  alphay,
                     double[]  x, long incx,
                     double[]  y, long incy,
                     @RefArg double[]  z, long incz
    );
    void bli_ddotaxpyv(conj_t  conjxt,
                       conj_t  conjx,
                       conj_t  conjy,
                       long   dim_t,
                       double[] alpha,
                       double[] x, long incx,
                       double[] y, long incy,
                       @RefArg double[]  rho,
                       @RefArg double[]  z, long incz
    );
    void bli_ddotxf(conj_t conjat,
                    conj_t conjx,
                    long m, long b_n,
                    double[] alpha,
                    double[] a, long inca, long lda,
                    double[] x, long incx,
                    double[] beta,
                    @RefArg double[] y, long incy);
    void bli_ddotxaxpyf(conj_t conjat,
                        conj_t conja,
                        conj_t conjw,
                        conj_t conjx,
                        long m, long b_n,
                        double[]alpha,
                        double[]a, long inca, long lda,
                        double[]w, long incw,
                        double[]x, long incx,
                        double[]beta,
                        @RefArg double[]y, long incy,
                        @RefArg double[]z, long incz);
    //------LEVEL 2-----------------
    void bli_dgemv(trans_t transa,
                   conj_t conjx,
                   long m, long n,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   double[]x, long incx,
                   double[]beta,
                   @RefArg double[]y, long incy);
    void bli_dger(conj_t conjx,
                  conj_t conjy,
                  long m, long n,
                  double[]alpha,
                  double[]x, long incx,
                  double[]y, long incy,
                  @RefArg double[]a, long rs_a, long cs_a);
    void bli_dhemv(uplo_t uploa,
                   conj_t conja,
                   conj_t conjx,
                   long m,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   double[]x, long incx,
                   double[]beta,
                   @RefArg double[]y, long incy);
    void bli_dher(uplo_t uploa,
                  conj_t conjx,
                  long m,
                  double[]alpha,
                  double[]x, long incx,
                  @RefArg double[]a, long rs_a, long cs_a);
    void bli_dher2(uplo_t uploa,
                   conj_t conjx,
                   conj_t conjy,
                   long m,
                   double[]alpha,
                   double[]x, long incx,
                   double[]y, long incy,
                   @RefArg double[]a, long rs_a, long cs_a);
    void bli_dsymv(uplo_t uploa,
                   conj_t conja,
                   conj_t conjx,
                   long m,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   double[]x, long incx,
                   double[]beta,
                   @RefArg double[]y, long incy);
    void bli_dsyr(uplo_t uploa,
                  conj_t conjx,
                  long m,
                  double[]alpha,
                  double[]x, long incx,
                  @RefArg double[]a, long rs_a, long cs_a);
    void bli_dsyr2(uplo_t uploa,
                   conj_t conjx,
                   conj_t conjy,
                   long m,
                   double[]alpha,
                   double[]x, long incx,
                   double[]y, long incy,
                   @RefArg double[]a, long rs_a, long cs_a);
    void bli_dtrmv(uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   @RefArg double[]x, long incx);
    void bli_dtrsv(uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   @RefArg double[]x, long incx);
    //------LEVEL 3-----------------
    void bli_dgemm(trans_t transa,
                   trans_t transb,
                   long m, long n, long k,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   double[]b, long rs_b, long cs_b,
                   double[]beta,
                   @RefArg double[]c, long rs_c, long cs_c);
    void bli_dgemmt(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    double[]alpha,
                    double[]a, long rs_a, long cs_a,
                    double[]b, long rs_b, long cs_b,
                    double[]beta,
                    @RefArg double[]c, long rs_c, long cs_c);
    void bli_dhemm(side_t side,
                   uplo_t uploa,
                   conj_t conja,
                   trans_t transb,
                   long m, long n,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   double[]b, long rs_b, long cs_b,
                   double[]beta,
                   @RefArg double[]c, long rs_c, long cs_c);
    void bli_dherk(uplo_t uploc,
                   trans_t transa,
                   long m, long k,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   double[]beta,
                   @RefArg double[]c, long rs_c, long cs_c);
    void bli_dher2k(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    double[]alpha,
                    double[]a, long rs_a, long cs_a,
                    double[]b, long rs_b, long cs_b,
                    double[]beta,
                    @RefArg double[]c, long rs_c, long cs_c);
    void bli_dsymm(side_t side,
                   uplo_t uploa,
                   conj_t conja,
                   trans_t transb,
                   long m, long n,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   double[]b, long rs_b, long cs_b,
                   double[]beta,
                   @RefArg double[]c, long rs_c, long cs_c);
    void bli_dsyrk(uplo_t uploc,
                   trans_t transa,
                   long m, long k,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   double[]beta,
                   @RefArg double[]c, long rs_c, long cs_c);
    void bli_dsyr2k(uplo_t uploc,
                    trans_t transa,
                    trans_t transb,
                    long m, long k,
                    double[]alpha,
                    double[]a, long rs_a, long cs_a,
                    double[]b, long rs_b, long cs_b,
                    double[]beta,
                    @RefArg double[]c, long rs_c, long cs_c);
    void bli_dtrmm(side_t side,
                   uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m, long n,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   @RefArg double[]b, long rs_b, long cs_b);
    void bli_dtrmm3(side_t side,
                    uplo_t uploa,
                    trans_t transa,
                    diag_t diaga,
                    trans_t transb,
                    long m, long n,
                    double[]alpha,
                    double[]a, long rs_a, long cs_a,
                    double[]b, long rs_b, long cs_b,
                    double[]beta,
                    @RefArg double[]c, long rs_c, long cs_c);
    void bli_dtrsm(side_t side,
                   uplo_t uploa,
                   trans_t transa,
                   diag_t diaga,
                   long m, long n,
                   double[]alpha,
                   double[]a, long rs_a, long cs_a,
                   @RefArg double[]b, long rs_b, long cs_b);
    //------Utility-----------------
    void bli_dasumv(long n, double[]x, long incx, @RefArg double[]asum);
    void bli_dnorm1m(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, double[]x, long rs_x, long cs_x, @RefArg double[]norm);
    void bli_dnormfm(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, double[]x, long rs_x, long cs_x, @RefArg double[]norm);
    void bli_dnormim(long diagoffx, diag_t diagx, uplo_t uplox, long m, long n, double[]x, long rs_x, long cs_x, @RefArg double[]norm);
    void bli_dnorm1v(long n, double[]x, long incx, @RefArg double[]norm);
    void bli_dnormfv(long n, double[]x, long incx, @RefArg double[]norm);
    void bli_dnormiv(long n, double[]x, long incx, @RefArg double[]norm);
    void bli_dmkherm(uplo_t uploa, long m, @RefArg double[]a, long rs_a, long cs_a);
    void bli_dmksymm(uplo_t uploa, long m, @RefArg double[]a, long rs_a, long cs_a);
    void bli_dmktrim(uplo_t uploa, long m, @RefArg double[]a, long rs_a, long cs_a);
    //    void bli_dfprintv(FILE *file, String s1, long n, double[]x, long incx, String format, String s2);
//    void bli_dfprintm(FILE *file, String s1, long m, long n, double[]x, long rs_x, long cs_x, String format, String s2);
    void bli_dprintv(String s1, long n, double[] x, long incx, String format, String s2);
    void bli_dprintm(String s1,
                     long m, long n,
                     double[] x, long rs_x, long cs_x,
                     String format,
                     String s2);
    @Critical
    void bli_drandv(long n, @RefArg double[] x, long incx);
    @Critical
    void bli_drandm(long diagoffx,
                    uplo_t uplox,
                    long m, long n,
                    @RefArg double[]x, long rs_x, long cs_x);
    void bli_dsumsqv(long n, double[]x, long incx, @RefArg double[]scale, @RefArg double[]sumsq);
    void bli_dgetsc(double[]chi, @RefArg double[]zeta_r, @RefArg double[]zeta_i);
    void bli_dgetijv(long i, double[] b, long incx, @RefArg double[]ar, @RefArg double[]ai);
    void bli_dgetijm(long i, long j, double[] b, long rs, long cs, @RefArg double[]ar, @RefArg double[]ai);
    void bli_dsetsc(double zeta_r, double zeta_i, @RefArg double[]chi);
    void bli_dsetijv(double ar, double ai, long i, @RefArg double[] x, long incx);
    void bli_dsetijm(double ar, double ai,
                     long i, long j,
                     @RefArg double[]b, long rs, long cs);
    void bli_deqsc(conj_t conjchi, double[]chi, double[]psi, @RefArg boolean[] is_eq);
    void bli_deqv(conj_t conjx, long n, double[]x, long incx, double[]y, long incy, @RefArg boolean[]is_eq);
    void bli_deqm(long diagoffx, diag_t diagx, uplo_t uplox, trans_t transx, long m, long n, double[]x, long rs_x, long cs_x, double[]y, long rs_y, long cs_y, @RefArg boolean[]is_eq);
}
