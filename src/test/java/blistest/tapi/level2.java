package blistest.tapi;

import jblis.blisFactory;

import static jblis.blisHelpers.mallocd;
import static jblis.blisHelpers.ptr;
import static jblis.types.conj_t.BLIS_NO_CONJUGATE;
import static jblis.types.diag_t.BLIS_NONUNIT_DIAG;
import static jblis.types.trans_t.BLIS_NO_TRANSPOSE;
import static jblis.types.uplo_t.*;

public class level2 {
    public static void main(String[] args)
    {
        double[] a, x, y, b;
        long   m, n;
        long   rs, cs;

        // Initialize some basic constants.
        var   zero        =   ptr(0.0);
        var   one         =   ptr(1.0);
        var   two         =   ptr(2.0);
        var   minus_one   =  ptr(-1.0);

        var blis = blisFactory.getBlisTAPI();

        //
        // This file demonstrates level-2 operations.
        //


        //
        // Example 1: Perform a general rank-1 update (ger) operation.
        //

        System.out.println( "\n#\n#  -- Example 1 --\n#" );

        // Create some matrix and vector operands to work with.
        m = 4; n = 5; rs = 1; cs = m;
        a = mallocd( m * n);
        x = mallocd( m * 1);
        y = mallocd( 1 * n);

        // Let's initialize some scalars.
        var alpha = ptr(1.0);

        // Initialize vectors 'x' and 'y'.
        blis.bli_drandv( m, x, 1 );
        blis.bli_dsetv( BLIS_NO_CONJUGATE, n, minus_one, y, 1 );

        // Initialize 'a' to 1.0.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, one, a, rs, cs );

        blis.bli_dprintm( "x: set to random values", m, 1, x, 1, m, "% 4.3f", "" );
        blis.bli_dprintm( "y: set to -1.0", 1, n, y, n, 1, "% 4.3f", "" );
        blis.bli_dprintm( "a: intial value", m, n, a, rs, cs, "% 4.3f", "" );

        // a := a + alpha * x * y, where 'a' is general.
        blis.bli_dger( BLIS_NO_CONJUGATE, BLIS_NO_CONJUGATE,
                m, n, alpha, x, 1, y, 1, a, rs, cs );

        blis.bli_dprintm( "a: after ger", m, n, a, rs, cs, "% 4.3f", "" );

        //
        // Example 2: Perform a general matrix-vector multiply (gemv) operation.
        //

        System.out.println( "\n#\n#  -- Example 2 --\n#");

        // Create some matrix and vector operands to work with.
        m = 4; n = 5; rs = 1; cs = m;
        a = mallocd( m * n);
        x = mallocd( 1 * n);
        y = mallocd( 1 * m);

        // Set the scalars to use.
        alpha = ptr(1.0);
        var beta  = ptr(1.0);

        // Initialize vectors 'x' and 'y'.
        blis.bli_dsetv( BLIS_NO_CONJUGATE, n, one,  x, 1 );
        blis.bli_dsetv( BLIS_NO_CONJUGATE, m, zero, y, 1 );

        // Randomize 'a'.
        blis.bli_drandm( 0, BLIS_DENSE, m, n, a, rs, cs );

        blis.bli_dprintm( "a: randomized", m, n, a, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "x: set to 1.0", 1, n, x, n, 1, "% 4.3f", "" );
        blis.bli_dprintm( "y: intial value", 1, m, y, m, 1, "% 4.3f", "" );

        // y := beta * y + alpha * a * x, where 'a' is general.
        blis.bli_dgemv( BLIS_NO_TRANSPOSE, BLIS_NO_CONJUGATE,
                m, n, alpha, a, rs, cs, x, 1, beta, y, 1 );

        blis.bli_dprintm( "y: after gemv", 1, m, y, m, 1, "% 4.3f", "" );

        //
        // Example 3: Perform a symmetric rank-1 update (syr) operation.
        //

        System.out.println( "\n#\n#  -- Example 3 --\n#" );

        // Create some matrix and vector operands to work with.
        m = 5; rs = 1; cs = 5;
        a = mallocd( m * m);
        x = mallocd( 1 * m);

        // Set alpha.
        alpha = ptr(1.0);

        // Initialize vector 'x'.
        blis.bli_drandv( m, x, 1 );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, zero, a, rs, cs );

        // Randomize the lower triangle of 'a'.
        blis.bli_drandm( 0, BLIS_LOWER, m, m, a, rs, cs );

        blis.bli_dprintm( "x: set to random values", 1, m, x, m, 1, "% 4.3f", "" );
        blis.bli_dprintm( "a: initial value (zeros in upper triangle)", m, m, a, 1, m, "% 4.3f", "" );

        // a := a + alpha * x * x^T, where 'a' is symmetric and lower-stored.
        blis.bli_dsyr( BLIS_LOWER, BLIS_NO_CONJUGATE, m, alpha, x, 1, a, rs, cs );

        blis.bli_dprintm( "a: after syr", m, m, a, 1, m, "% 4.3f", "" );

        //
        // Example 4: Perform a symmetric matrix-vector multiply (symv) operation.
        //

        System.out.println( "\n#\n#  -- Example 4 --\n#" );

        // Create some matrix and vector operands to work with.
        m = 5;; rs = 1; cs = m;
        a = mallocd( m * m);
        x = mallocd( 1 * m);
        y = mallocd( 1 * m);

        // Set the scalars to use.
        alpha = ptr(1.0);
        beta  = ptr(1.0);

        // Initialize vectors 'x' and 'y'.
        blis.bli_dsetv( BLIS_NO_CONJUGATE, m, one,  x, 1 );
        blis.bli_dsetv( BLIS_NO_CONJUGATE, m, zero, y, 1 );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, zero, a, rs, cs );

        // Randomize 'a'.
        blis.bli_drandm( 0, BLIS_UPPER, m, m, a, rs, cs );

        blis.bli_dprintm( "a: randomized (zeros in lower triangle)", m, m, a, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "x: set to 1.0", 1, m, x, m, 1, "% 4.3f", "" );
        blis.bli_dprintm( "y: intial value", 1, m, y, m, 1, "% 4.3f", "" );

        // y := beta * y + alpha * a * x, where 'a' is symmetric and upper-stored.
        blis.bli_dsymv( BLIS_UPPER, BLIS_NO_CONJUGATE, BLIS_NO_CONJUGATE,
                m, alpha, a, rs, cs, x, 1, beta, y, 1 );

        blis.bli_dprintm( "y: after symv", 1, m, y, m, 1, "% 4.3f", "" );


        //
        // Example 5: Perform a triangular matrix-vector multiply (trmv) operation.
        //

        System.out.println( "\n#\n#  -- Example 5 --\n#" );

        // Create some matrix and vector operands to work with.
        m = 5;; rs = 1; cs = m;
        a = mallocd( m * m);
        x = mallocd( 1 * m);

        // Set the scalars to use.
        alpha = ptr(1.0);

        // Initialize vector 'x'.
        blis.bli_dsetv( BLIS_NO_CONJUGATE, m, one, x, 1 );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, zero, a, rs, cs );

        // Randomize 'a'.
        blis.bli_drandm( 0, BLIS_LOWER, m, m, a, rs, cs );

        blis.bli_dprintm( "a: randomized (zeros in upper triangle)", m, m, a, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "x: intial value", 1, m, x, m, 1, "% 4.3f", "" );

        // x := alpha * a * x, where 'a' is triangular and lower-stored.
        blis.bli_dtrmv( BLIS_LOWER, BLIS_NO_TRANSPOSE, BLIS_NONUNIT_DIAG,
                m, alpha, a, rs, cs, x, 1 );

        blis.bli_dprintm( "x: after trmv", 1, m, x, m, 1, "% 4.3f", "" );

        //
        // Example 6: Perform a triangular solve (trsv) operation.
        //

        System.out.println( "\n#\n#  -- Example 6 --\n#" );

        // Create some matrix and vector operands to work with.
        m = 5;; rs = 1; cs = m;
        a = mallocd( m * m);
        b = mallocd( 1 * m);
        y = mallocd( 1 * m);

        // Set the scalars to use.
        alpha = ptr(1.0);

        // Initialize vector 'x'.
        blis.bli_dsetv( BLIS_NO_CONJUGATE, m, one, b, 1 );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, zero, a, rs, cs );

        // Randomize 'a'.
        blis.bli_drandm( 0, BLIS_LOWER, m, m, a, rs, cs );

        // Load the diagonal. By setting the diagonal to something of greater
        // absolute value than the off-diagonal elements, we increase the odds
        // that the matrix is not singular (singular matrices have no inverse).
        blis.bli_dshiftd( 0, m, m, two, a, rs, cs );

        blis.bli_dprintm( "a: randomized (zeros in upper triangle)", m, m, a, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "b: intial value", 1, m, b, m, 1, "% 4.3f", "" );

        // x := alpha * a * x, where 'a' is triangular and lower-stored.
        blis.bli_dtrsv( BLIS_LOWER, BLIS_NO_TRANSPOSE, BLIS_NONUNIT_DIAG,
                m, alpha, a, rs, cs, x, 1 );

        blis.bli_dprintm( "b: after trsv", 1, m, b, m, 1, "% 4.3f", "" );

        // We can confirm the solution by comparing the product of a and x to the
        // original value of b.
        blis.bli_dcopyv( BLIS_NO_CONJUGATE, m, b, 1, y, 1 );
        blis.bli_dtrmv( BLIS_LOWER, BLIS_NO_TRANSPOSE, BLIS_NONUNIT_DIAG,
                m, alpha, a, rs, cs, y, 1 );

        blis.bli_dprintm( "y: should equal initial value of b", 1, m, y, m, 1, "% 4.3f", "" );

    }
}
