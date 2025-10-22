package blistest.tapi;

import jblis.blisFactory;
import jblis.types.dcomplex;

import static jblis.blisHelpers.*;
import static jblis.types.conj_t.BLIS_NO_CONJUGATE;
import static jblis.types.diag_t.BLIS_NONUNIT_DIAG;
import static jblis.types.trans_t.*;
import static jblis.types.uplo_t.BLIS_DENSE;

public class level1m {

    public static void main(String[] arg)
    {
        double[]   a, b, c, d, e, f;
        dcomplex[] g;
        dcomplex[] h;
        double[]    alpha, beta, gamma;
        long     m, n;
        long     rs, cs;

        // Initialize some basic constants.
        var   zero        =   ptr(0.0);
        var   one         =   ptr(1.0);
        var   minus_one   =  ptr(-1.0);
        var minus_one_z = ptr(new dcomplex(-1.0, 0.0));


        //
        // This file demonstrates working with matrices and the level-1m
        // operations.
        //


        //
        // Example 1: Create matrices and then broadcast (copy) scalar
        //            values to all elements.
        //

        System.out.println( "\n#\n#  -- Example 1 --\n#" );

        // Create a few matrices to work with. We make them all of the same
        // dimensions so that we can perform operations between them.
        m = 2; n = 3; rs = 1; cs = m;
        a = new double[(int)(m*n)];
        b = new double[(int)(m*n)];
        c = new double[(int)(m*n)];
        d = new double[(int)(m*n)];
        e = new double[(int)(m*n)];

        // Let's initialize some scalars.
        alpha = ptr(2.0);
        beta  = ptr(0.2);
        gamma = ptr(3.0);

        System.out.printf( "alpha:\n% 4.3f\n", alpha[0] );
        System.out.printf( "beta:\n% 4.3f\n", beta[0] );
        System.out.printf( "gamma:\n% 4.3f\n", gamma[0] );

        var blis = blisFactory.getBlisTAPI();

        // Matrices, like vectors, can set by "broadcasting" a constant to every
        // element. Note that the second argument (0) is the diagonal offset.
        // The diagonal offset is only used when the uplo value is something other
        // than BLIS_DENSE (e.g. BLIS_LOWER or BLIS_UPPER).
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, one, a, rs, cs );
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, alpha, b, rs, cs );
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, zero, c, rs, cs );

        blis.bli_dprintm( "a := 1.0", m, n, a, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "b := alpha", m, n, b, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "c := 0.0", m, n, c, rs, cs, "% 4.3f", "" );

        //
        // Example 2: Randomize a matrix object.
        //

        System.out.println( "\n#\n#  -- Example 2 --\n#" );

        blis.bli_drandm( 0, BLIS_DENSE, m, n, e, rs, cs );

        blis.bli_dprintm( "e (randomized):", m, n, e, rs, cs, "% 4.3f", "" );


        //
        // Example 3: Perform element-wise operations on matrices.
        //

        System.out.println( "\n#\n#  -- Example 3 --\n#" );

        // Copy a matrix.
        blis.bli_dcopym( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE, BLIS_NO_TRANSPOSE,
                m, n, e, rs, cs, d, rs, cs );
        blis.bli_dprintm( "d := e", m, n, d, rs, cs, "% 4.3f", "" );

        // Add and subtract vectors.
        blis.bli_daddm( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE, BLIS_NO_TRANSPOSE,
                m, n, a, rs, cs, d, rs, cs );
        blis.bli_dprintm( "d := d + a", m, n, d, rs, cs, "% 4.3f", "" );

        blis.bli_dsubm( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE, BLIS_NO_TRANSPOSE,
                m, n, a, rs, cs, e, rs, cs );
        blis.bli_dprintm( "e := e - a", m, n, e, rs, cs, "% 4.3f", "" );

        // Scale a matrix (destructive).
        blis.bli_dscalm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, alpha, e, rs, cs );
        blis.bli_dprintm( "e := alpha * e", m, n, e, rs, cs, "% 4.3f", "" );

        // Scale a matrix (non-destructive).
        blis.bli_dscal2m( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE, BLIS_NO_TRANSPOSE,
                m, n, beta, e, rs, cs, c, rs, cs );
        blis.bli_dprintm( "c := beta * e", m, n, c, rs, cs, "% 4.3f", "" );

        // Scale and accumulate between matrices.
        blis.bli_daxpym( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE, BLIS_NO_TRANSPOSE,
                m, n, alpha, a, rs, cs, c, rs, cs );
        blis.bli_dprintm( "c := alpha * a", m, n, c, rs, cs, "% 4.3f", "" );

        //
        // Example 4: Copy and transpose a matrix.
        //

        System.out.println( "\n#\n#  -- Example 4 --\n#" );

        // Create an n-by-m matrix into which we can copy-transpose an m-by-n
        // matrix.
        f = new double[(int)(n * m)];
        long rsf = 1, csf = n;

        // Initialize all of 'f' to -1.0 to simulate junk values.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                n, m, minus_one, f, rsf, csf );

        blis.bli_dprintm( "e:", m, n, e, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "f (initial value):", n, m, f, rsf, csf, "% 4.3f", "" );


        // Copy 'e' to 'f', transposing 'e' in the process. Notice that we haven't
        // modified any properties of 'f'. It's the source operand that matters
        // when marking an operand for transposition, not the destination.
        blis.bli_dcopym( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE, BLIS_TRANSPOSE,
                n, m, e, rs, cs, f, rsf, csf );

        blis.bli_dprintm( "f (copied value):", n, m, f, rsf, csf, "% 4.3f", "" );


        //
        // Example 5: Copy and Hermitian-transpose a matrix.
        //

        System.out.println( "\n#\n#  -- Example 5 --\n#" );

        g = mallocdc(m*n);
        h = mallocdc(m*n);

        blis.bli_zrandm( 0, BLIS_DENSE, m, n, g, rs, cs );

        blis.bli_zsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                n, m, minus_one_z, h, rsf, csf );

        blis.bli_zprintm( "g:", m, n, g, rs, cs, "% 4.3f", "" );
        blis.bli_zprintm( "h (initial value):", n, m, h, rsf, csf, "% 4.3f", "" );

        blis.bli_zcopym( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE, BLIS_CONJ_TRANSPOSE,
                n, m, g, rs, cs, h, rsf, csf );

        blis.bli_zprintm( "h (copied value):", n, m, h, rsf, csf, "% 4.3f", "" );

    }
}
