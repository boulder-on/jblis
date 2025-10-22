package blistest.tapi;

import jblis.blisFactory;

import static jblis.blisHelpers.mallocd;
import static jblis.blisHelpers.ptr;
import static jblis.types.conj_t.BLIS_NO_CONJUGATE;
import static jblis.types.diag_t.BLIS_NONUNIT_DIAG;
import static jblis.types.side_t.BLIS_LEFT;
import static jblis.types.trans_t.BLIS_NO_TRANSPOSE;
import static jblis.types.trans_t.BLIS_TRANSPOSE;
import static jblis.types.uplo_t.*;

public class level3 {

    public static void main(String[] args)
    {
        long m, n, k;
        long rsa, csa;
        long rsb, csb;
        long rsc, csc;

        double[] a, b, c;
        double[]  alpha, beta;

        // Initialize some basic constants.
        var zero = ptr(0.0);
        var  one  = ptr(1.0);
        var  two  = ptr(2.0);


        var blis = blisFactory.getBlisTAPI();

        //
        // This file demonstrates level-3 operations.
        //


        //
        // Example 1: Perform a general matrix-matrix multiply (gemm) operation.
        //

        System.out.println( "\n#\n#  -- Example 1 --\n#" );

        // Create some matrix and vector operands to work with.
        m = 4; n = 5; k = 3;
        rsc = 1; csc = m;
        rsa = 1; csa = m;
        rsb = 1; csb = k;
        c = mallocd( m * n);
        a = mallocd( m * k);
        b = mallocd( k * n);

        // Set the scalars to use.
        alpha = ptr(1.0);
        beta  = ptr(1.0);

        // Initialize the matrix operands.
        blis.bli_drandm( 0, BLIS_DENSE, m, k, a, rsa, csa );
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                k, n, one, b, rsb, csb );
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, zero, c, rsc, csc );

        blis.bli_dprintm( "a: randomized", m, k, a, rsa, csa, "% 4.3f", "" );
        blis.bli_dprintm( "b: set to 1.0", k, n, b, rsb, csb, "% 4.3f", "" );
        blis.bli_dprintm( "c: initial value", m, n, c, rsc, csc, "% 4.3f", "" );

        // c := beta * c + alpha * a * b, where 'a', 'b', and 'c' are general.
        blis.bli_dgemm( BLIS_NO_TRANSPOSE, BLIS_NO_TRANSPOSE,
                m, n, k, alpha, a, rsa, csa, b, rsb, csb,
	                     beta, c, rsc, csc );

        blis.bli_dprintm( "c: after gemm", m, n, c, rsc, csc, "% 4.3f", "" );


        //
        // Example 1b: Perform a general matrix-matrix multiply (gemm) operation
        //             with the left input operand (matrix A) transposed.
        //

        System.out.println( "\n#\n#  -- Example 1b --\n#" );

        // Create some matrix and vector operands to work with.
        m = 4; n = 5; k = 3;
        rsc = 1; csc = m;
        rsa = 1; csa = k;
        rsb = 1; csb = k;
        c = mallocd( m * n);
        a = mallocd( k * m);
        b = mallocd( k * n);

        // Set the scalars to use.
        alpha = ptr(1.0);
        beta  = ptr(1.0);

        // Initialize the matrix operands.
        blis.bli_drandm( 0, BLIS_DENSE, k, m, a, rsa, csa );
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                k, n, one, b, rsb, csb );
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, zero, c, rsc, csc );

        blis.bli_dprintm( "a: randomized", k, m, a, rsa, csa, "% 4.3f", "" );
        blis.bli_dprintm( "b: set to 1.0", k, n, b, rsb, csb, "% 4.3f", "" );
        blis.bli_dprintm( "c: initial value", m, n, c, rsc, csc, "% 4.3f", "" );

        // c := beta * c + alpha * a^T * b, where 'a', 'b', and 'c' are general.
        blis.bli_dgemm( BLIS_TRANSPOSE, BLIS_NO_TRANSPOSE,
                m, n, k, alpha, a, rsa, csa, b, rsb, csb,
	                     beta, c, rsc, csc );

        blis.bli_dprintm( "c: after gemm", m, n, c, rsc, csc, "% 4.3f", "" );


        //
        // Example 2: Perform a symmetric rank-k update (syrk) operation.
        //

        System.out.println( "\n#\n#  -- Example 2 --\n#" );

        // Create some matrix and vector operands to work with.
        m = 5; k = 3;
        rsc = 1; csc = m;
        rsa = 1; csa = m;
        c = mallocd( m * m);
        a = mallocd( m * k);

        // Set the scalars to use.
        alpha = ptr(1.0);

        // Initialize the matrix operands.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, zero, c, rsc, csc );
        blis.bli_drandm( 0, BLIS_DENSE, m, k, a, rsa, csa );

        // Randomize the lower triangle of 'c'.
        blis.bli_drandm( 0, BLIS_LOWER, m, n, c, rsc, csc );

        blis.bli_dprintm( "a: set to random values", m, k, a, rsa, csa, "% 4.3f", "" );
        blis.bli_dprintm( "c: initial value (zeros in upper triangle)", m, m, c, rsc, csc, "% 4.3f", "" );

        // c := c + alpha * a * a^T, where 'c' is symmetric and lower-stored.
        blis.bli_dsyrk( BLIS_LOWER, BLIS_NO_TRANSPOSE,
                m, k, alpha, a, rsa, csa,
	                  beta, c, rsc, csc );

        blis.bli_dprintm( "c: after syrk", m, m, c, rsc, csc, "% 4.3f", "" );


        //
        // Example 3: Perform a symmetric matrix-matrix multiply (symm) operation.
        //

        System.out.println( "\n#\n#  -- Example 3 --\n#" );

        // Create some matrix and vector operands to work with.
        m = 5; n = 6;
        rsc = 1; csc = m;
        rsa = 1; csa = m;
        rsb = 1; csb = m;
        c = mallocd( m * n);
        a = mallocd( m * m);
        b = mallocd( m * n);

        // Set the scalars to use.
        alpha = ptr(1.0);
        beta  = ptr(1.0);

        // Initialize matrices 'b' and 'c'.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, one, b, rsb, csb );
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, zero, c, rsc, csc );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, zero, a, rsa, csa );

        // Randomize the upper triangle of 'a'.
        blis.bli_drandm( 0, BLIS_UPPER, m, m, a, rsa, csa );

        blis.bli_dprintm( "a: randomized (zeros in lower triangle)", m, m, a, rsa, csa, "% 4.3f", "" );
        blis.bli_dprintm( "b: set to 1.0", m, n, b, rsb, csb, "% 4.3f", "" );
        blis.bli_dprintm( "c: initial value", m, n, c, rsc, csc, "% 4.3f", "" );

        // c := beta * c + alpha * a * b, where 'a' is symmetric and upper-stored.
        blis.bli_dsymm( BLIS_LEFT, BLIS_UPPER, BLIS_NO_CONJUGATE, BLIS_NO_TRANSPOSE,
                m, n, alpha, a, rsa, csa, b, rsb, csb,
	                  beta, c, rsc, csc );

        blis.bli_dprintm( "c: after symm", m, n, c, rsc, csc, "% 4.3f", "" );

        //
        // Example 4: Perform a triangular matrix-matrix multiply (trmm) operation.
        //

        System.out.println( "\n#\n#  -- Example 4 --\n#" );

        // Create some matrix and vector operands to work with.
        m = 5; n = 4;
        rsa = 1; csa = m;
        rsb = 1; csb = m;
        a = mallocd( m * m);
        b = mallocd( m * n);

        // Set the scalars to use.
        alpha = ptr(1.0);

        // Initialize matrix 'b'.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, one, b, rsb, csb );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, zero, a, rsa, csa );

        // Randomize the lower triangle of 'a'.
        blis.bli_drandm( 0, BLIS_LOWER, m, m, a, rsa, csa );

        blis.bli_dprintm( "a: randomized (zeros in upper triangle)", m, m, a, rsa, csa, "% 4.3f", "" );
        blis.bli_dprintm( "b: initial value", m, n, b, rsb, csb, "% 4.3f", "" );

        // b := alpha * a * b, where 'a' is triangular and lower-stored.
        blis.bli_dtrmm( BLIS_LEFT, BLIS_LOWER, BLIS_NO_TRANSPOSE, BLIS_NONUNIT_DIAG,
                m, n, alpha, a, rsa, csa, b, rsb, csb );

        blis.bli_dprintm( "b: after trmm", m, n, b, rsb, csb, "% 4.3f", "" );


        //
        // Example 5: Perform a triangular solve with multiple right-hand sides
        //            (trsm) operation.
        //

        System.out.println( "\n#\n#  -- Example 5 --\n#" );

        // Create some matrix and vector operands to work with.
        m = 5; n = 4;
        rsa = 1; csa = m;
        rsb = 1; csb = m;
        rsc = 1; csc = m;
        a = mallocd( m * m);
        b = mallocd( m * n);
        c = mallocd( m * n);

        // Set the scalars to use.
        alpha = ptr(1.0);

        // Initialize matrix 'b'.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, one, b, rsb, csb );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, zero, a, rsa, csa );

        // Randomize the lower triangle of 'a'.
        blis.bli_drandm( 0, BLIS_LOWER, m, m, a, rsa, csa );

        // Load the diagonal. By setting the diagonal to something of greater
        // absolute value than the off-diagonal elements, we increase the odds
        // that the matrix is not singular (singular matrices have no inverse).
        blis.bli_dshiftd( 0, m, m, two, a, rsa, csa );

        blis.bli_dprintm( "a: randomized (zeros in upper triangle)", m, m, a, rsa, csa, "% 4.3f", "" );
        blis.bli_dprintm( "b: initial value", m, n, b, rsb, csb, "% 4.3f", "" );

        // solve a * x = alpha * b, where 'a' is triangular and lower-stored, and
        // overwrite b with the solution matrix x.
        blis.bli_dtrsm( BLIS_LEFT, BLIS_LOWER, BLIS_NO_TRANSPOSE, BLIS_NONUNIT_DIAG,
                m, n, alpha, a, rsa, csa, b, rsb, csb );

        blis.bli_dprintm( "b: after trmm", m, n, b, rsb, csb, "% 4.3f", "" );

        // We can confirm the solution by comparing the product of a and x to the
        // original value of b.
        blis.bli_dcopym( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE, BLIS_NO_TRANSPOSE,
                m, n, b, rsb, csb, c, rsc, csc );
        blis.bli_dtrmm( BLIS_LEFT, BLIS_LOWER, BLIS_NO_TRANSPOSE, BLIS_NONUNIT_DIAG,
                m, n, alpha, a, rsa, csa, c, rsc, csc );

        blis.bli_dprintm( "c: should equal initial value of b", m, n, c, rsc, csc, "% 4.3f", "" );

    }
}
