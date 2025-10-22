package blistest.tapi;

import jblis.blisFactory;

import static jblis.blisHelpers.*;
import static jblis.types.conj_t.BLIS_NO_CONJUGATE;
import static jblis.types.diag_t.BLIS_NONUNIT_DIAG;
import static jblis.types.trans_t.BLIS_NO_TRANSPOSE;
import static jblis.types.trans_t.BLIS_TRANSPOSE;
import static jblis.types.uplo_t.*;

public class level1m_diag {

    public static void main(String[] args)
    {
        double[] a, b, c, d , e, h;
        long m, n;
        long rs, cs;

        // Initialize some basic constants.
        var zero      =   ptr(0.0);
        var minus_one =  ptr(-1.0);


        //
        // This file demonstrates level-1m operations on structured matrices.
        //


        //
        // Example 1: Initialize the upper triangle of a matrix to random values.
        //
        var blis = blisFactory.getBlisTAPI();

        System.out.println( "\n#\n#  -- Example 1 --\n#" );

        // Create a matrix to work with.
        m = 5; n = 5; rs = 1; cs = m;
        a = mallocd( m * n);

        // Set the upper triangle to random values.
        blis.bli_drandm( 0, BLIS_UPPER, m, n, a, rs, cs );

        blis.bli_dprintm( "a: randomize upper part (lower part may contain garbage)",
                m, n, a, rs, cs, "% 4.3f", "" );


        //
        // Example 2: Initialize the upper triangle of a matrix to random values
        //            but also explicitly set the strictly lower triangle to zero.
        //

        System.out.println( "\n#\n#  -- Example 2 --\n#" );

        // Create a matrix to work with.
        m = 5; n = 5; rs = 1; cs = m;
        b = mallocd( m * n);

        // Set the upper triangle to random values.
        blis.bli_drandm( 0, BLIS_UPPER, m, n, b, rs, cs );

        // Set the strictly lower triangle of 'b' to zero (by setting the lower
        // triangle of 'bl' to zero).
        blis.bli_dsetm( BLIS_NO_CONJUGATE, -1, BLIS_NONUNIT_DIAG, BLIS_LOWER,
                m, n, zero, b, rs, cs );

        blis.bli_dprintm( "b: randomize upper part; set strictly lower part to 0.0)",
                m, n, b, rs, cs, "% 4.3f", "" );

        // You may not see the effect of setting the strictly lower part to zero,
        // since those values may already be zero (instead of random junk). So
        // let's set it to something you'll notice, like -1.0.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, -1, BLIS_NONUNIT_DIAG, BLIS_LOWER,
                m, n, minus_one, b, rs, cs );

        blis.bli_dprintm( "b: randomize upper part; set strictly lower part to -1.0)",
                m, n, b, rs, cs, "% 4.3f", "" );


        //
        // Example 3: Copy the lower triangle of an existing matrix to a newly
        //            created (but otherwise uninitialized) matrix.
        //

        System.out.println( "\n#\n#  -- Example 3 --\n#" );

        // Create a matrix to work with.
        m = 5; n = 5; rs = 1; cs = m;
        c = mallocd( m * n );

        blis.bli_dcopym( 0, BLIS_NONUNIT_DIAG, BLIS_LOWER, BLIS_NO_TRANSPOSE,
                m, n, b, rs, cs, c, rs, cs );

        blis.bli_dprintm( "c: copy lower part of b (upper part may contain garbage)",
                m, n, c, rs, cs, "% 4.3f", "" );

        blis.bli_dcopym( 0, BLIS_NONUNIT_DIAG, BLIS_LOWER, BLIS_NO_TRANSPOSE,
                m, n, b, rs, cs, a, rs, cs );

        blis.bli_dprintm( "a: copy lower triangle of b to upper triangular a",
                m, n, a, rs, cs, "% 4.3f", "" );


        //
        // Example 4: Copy the lower triangle of an existing object into the
        //            upper triangle of an existing object.
        //

        System.out.println( "\n#\n#  -- Example 4 --\n#" );

        // Create a matrix to work with.
        m = 5; n = 5; rs = 1; cs = m;
        d = mallocd( m * n);

        // Let's start by setting entire destination matrix to zero.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, zero, d, rs, cs );

        blis.bli_dprintm( "d: initial value (all zeros)",
                m, n, d, rs, cs, "% 4.3f", "" );

        // Let's change a few values of b manually so we can later see the full
        // effect of the transposition.
        blis.bli_dsetijm( 2.0, 0.0, 2, 0, b, rs, cs );
        blis.bli_dsetijm( 3.0, 0.0, 3, 0, b, rs, cs );
        blis.bli_dsetijm( 4.0, 0.0, 4, 0, b, rs, cs );
        blis.bli_dsetijm( 3.1, 0.0, 2, 1, b, rs, cs );
        blis.bli_dsetijm( 3.2, 0.0, 3, 2, b, rs, cs );

        blis.bli_dprintm( "b:",
                m, n, b, rs, cs, "% 4.3f", "" );

        blis.bli_dcopym( 0, BLIS_NONUNIT_DIAG, BLIS_LOWER, BLIS_TRANSPOSE,
                m, n, b, rs, cs, d, rs, cs );

        blis.bli_dprintm( "d: transpose of lower triangle of b copied to d",
                m, n, d, rs, cs, "% 4.3f", "" );


        //
        // Example 5: Create a rectangular matrix (m > n) with a lower trapezoid
        //            containing random values, then set the strictly upper
        //            triangle to zeros.
        //

        System.out.println( "\n#\n#  -- Example 5 --\n#" );

        // Create a matrix to work with.
        m = 6; n = 4; rs = 1; cs = m;
        e = mallocd( m * n);

        // Initialize the entire matrix to -1.0 to simulate junk values.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, minus_one, e, rs, cs );

        blis.bli_dprintm( "e: initial value (all -1.0)",
                m, n, e, rs, cs, "% 4.3f", "" );

        // Randomize the lower trapezoid.
        blis.bli_drandm( 0, BLIS_LOWER, m, n, e, rs, cs );

        blis.bli_dprintm( "e: after lower trapezoid randomized",
                m, n, e, rs, cs, "% 4.3f", "" );

        // Set the upper triangle to zero.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 1, BLIS_NONUNIT_DIAG, BLIS_UPPER,
                m, n, zero, e, rs, cs );

        blis.bli_dprintm( "e: after upper triangle set to zero",
                m, n, e, rs, cs, "% 4.3f", "" );


        //
        // Example 6: Create an upper Hessenberg matrix of random values and then
        //            set the "unstored" values to zero.
        //

        System.out.println( "\n#\n#  -- Example 6 --\n#" );

        // Create a matrix to work with.
        m = 5; n = 5; rs = 1; cs = m;
        h = mallocd( m * n);

        // Initialize the entire matrix to -1.0 to simulate junk values.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, minus_one, h, rs, cs );

        blis.bli_dprintm( "h: initial value (all -1.0)",
                m, n, h, rs, cs, "% 4.3f", "" );

        // Randomize the elements on and above the first subdiagonal.
        blis.bli_drandm( -1, BLIS_UPPER, m, n, h, rs, cs );

        blis.bli_dprintm( "h: after randomizing above first subdiagonal",
                m, n, h, rs, cs, "% 4.3f", "" );

        // Set the region strictly below the first subdiagonal (on or below
        // the second subdiagonal) to zero.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, -2, BLIS_NONUNIT_DIAG, BLIS_LOWER,
                m, n, zero, h, rs, cs );

        blis.bli_dprintm( "h: after setting elements below first subdiagonal to zero",
                m, n, h, rs, cs, "% 4.3f", "" );

    }

}
