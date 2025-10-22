package blistest.tapi;

import jblis.blisFactory;

import static jblis.blisHelpers.ptr;
import static jblis.types.conj_t.BLIS_NO_CONJUGATE;

public class level1v {

    public static void main(String[] args)
    {
        double[]  x, y, z, w, a;
        long m, n;
        long rs, cs;

        // Initialize some basic constants.
        var zero      = ptr(0.0);
        var one       = ptr(1.0);
        var minus_one = ptr(-1.0);

        //
        // This file demonstrates working with vectors and the level-1v
        // operations.
        //


        //
        // Example 1: Create vectors and then broadcast (copy) scalar
        //            values to all elements.
        //
        System.out.println( "\n#\n#  -- Example 1 --\n#" );


        // Create a few vectors to work with. We make them all of the same length
        // so that we can perform operations between them.
        // NOTE: We've chosen to use row vectors here (1x4) instead of column
        // vectors (4x1) to allow for easier reading of standard output (less
        // scrolling).
        m = 1; n = 4; rs = n; cs = 1;
        x = new double[(int)(m * n)];
        y = new double[(int)(m * n)];
        z = new double[(int)(m * n)];
        w = new double[(int)(m * n)];
        a = new double[(int)(m * n)];

        // Let's initialize some scalars.
        var alpha = ptr(2.0);
        var beta  = ptr(0.2);
        var gamma = ptr(3.0);

        System.out.printf( "alpha:\n% 4.3f\n", alpha[0] );
        System.out.printf( "beta:\n% 4.3f\n", beta[0] );
        System.out.printf( "gamma:\n% 4.3f\n", gamma[0] );
        System.out.println();
        var blis = blisFactory.getBlisTAPI();

        blis.bli_dsetv( BLIS_NO_CONJUGATE, n, one, x, 1 );
        blis.bli_dsetv( BLIS_NO_CONJUGATE, n, alpha, y, 1 );
        blis.bli_dsetv( BLIS_NO_CONJUGATE, n, zero, z, 1 );

        // Note that we can use printv or printm to print vectors since vectors
        // are also matrices. We choose to use printm because it honors the
        // orientation of the vector (row or column) when printing, whereas
        // printv always prints vectors as column vectors regardless of their
        // they are 1 x n or n x 1.
        blis.bli_dprintm( "x := 1.0", m, n, x, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "y := alpha", m, n, y, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "z := 0.0", m, n, z, rs, cs, "% 4.3f", "" );

        //
        // Example 2: Randomize a vector.
        //

        System.out.println( "\n#\n#  -- Example 2 --\n#" );

        // Set a vector to random values.
        blis.bli_drandv( n, w, 1 );
        blis.bli_dprintm( "w := randv()", m, n, w, rs, cs, "% 4.3f", "" );

        //
        // Example 3: Perform various element-wise operations on vectors.
        //

        System.out.println( "\n#\n#  -- Example 3 --\n#" );

        // Copy a vector.
        blis.bli_dcopyv( BLIS_NO_CONJUGATE, n, w, 1, a, 1 );
        blis.bli_dprintm( "a := w", m, n, a, rs, cs, "% 4.3f", "" );

        // Add and subtract vectors.
        blis.bli_daddv( BLIS_NO_CONJUGATE, n, y, 1, a, 1 );
        blis.bli_dprintm( "a := a + y", m, n, a, rs, cs, "% 4.3f", "" );

        blis.bli_dsubv( BLIS_NO_CONJUGATE, n, w, 1, a, 1 );
        blis.bli_dprintm( "a := a - w", m, n, a, rs, cs, "% 4.3f", "" );

        // Scale a vector (destructive).
        blis.bli_dscalv( BLIS_NO_CONJUGATE, n, beta, a, 1 );
        blis.bli_dprintm( "a := beta * a", m, n, a, rs, cs, "% 4.3f", "" );

        // Scale a vector (non-destructive).
        blis.bli_dscal2v( BLIS_NO_CONJUGATE, n, gamma, a, 1, z, 1 );
        blis.bli_dprintm( "z := gamma * a", m, n, z, rs, cs, "% 4.3f", "" );

        // Scale and accumulate between vectors.
        blis.bli_daxpyv( BLIS_NO_CONJUGATE, n, alpha, w, 1, x, 1 );
        blis.bli_dprintm( "x := x + alpha * w", m, n, x, rs, cs, "% 4.3f", "" );

        blis.bli_dxpbyv( BLIS_NO_CONJUGATE, n, w, 1, minus_one, x, 1 );
        blis.bli_dprintm( "x := -1.0 * x + w", m, n, x, rs, cs, "% 4.3f", "" );

        // Invert a vector element-wise.
        blis.bli_dinvertv( n, y, 1 );
        blis.bli_dprintm( "y := 1 / y", m, n, y, rs, cs, "% 4.3f", "" );

        // Swap two vectors.
        blis.bli_dswapv( n, x, 1, y, 1 );
        blis.bli_dprintm( "x (after swapping with y)", m, n, x, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "y (after swapping with x)", m, n, y, rs, cs, "% 4.3f", "" );

        //
        // Example 4: Perform contraction-like operations on vectors.
        //

        System.out.println( "\n#\n#  -- Example 4 --\n#" );

        // Perform a dot product.
        blis.bli_ddotv( BLIS_NO_CONJUGATE, BLIS_NO_CONJUGATE, n, a, 1, z, 1, gamma );
        System.out.printf( "gamma := a * z (dot product):\n%5.2f\n\n", gamma[0] );

        // Perform an extended dot product.
        blis.bli_ddotxv( BLIS_NO_CONJUGATE, BLIS_NO_CONJUGATE, n, alpha, a, 1, z, 1, one, gamma );
        System.out.printf( "gamma := 1.0 * gamma + alpha * a * z (accumulate scaled dot product):\n%5.2f\n\n", gamma[0] );
    }

}
