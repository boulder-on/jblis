package blistest.tapi;

import jblis.blisFactory;
import jblis.types.dcomplex;

import static jblis.blisHelpers.*;
import static jblis.types.conj_t.BLIS_NO_CONJUGATE;
import static jblis.types.diag_t.BLIS_NONUNIT_DIAG;
import static jblis.types.uplo_t.*;

public class util {
    public static void main(String[] args)
    {
        double[]   x;
        dcomplex[] y;
        double[]   a;
        dcomplex[] b;
        double[]   c;
        double[]  d;
        dcomplex[] e;
        dcomplex[] f;
        double[]   g;
        double[]    norm1 = ptr(1.0),
                normi = ptr(1.0),
                normf = ptr(1.0);
        long     m, n;
        long     rs, cs;

        // Initialize some basic constants.
        var   minus_one   =   ptr(-1.0);
        dcomplex[] minus_one_z = ptr( -1.0, 0.0 );

        var blis = blisFactory.getBlisTAPI();

        //
        // This file demonstrates working with vector and matrices in the
        // context of various utility operations.
        //


        //
        // Example 1: Compute various vector norms.
        //

        System.out.println( "\n#\n#  -- Example 1 --\n#" );

        // Create a few matrices to work with.
        m = 1; n = 5; rs = 5; cs = 1;
        x = mallocd( m * n);
        y = mallocdc( m * n);

        // Initialize the vectors to random values.
        blis.bli_drandv( n, x, 1 );
        blis.bli_zrandv( n, y, 1 );

        blis.bli_dprintm( "x", m, n, x, rs, cs, "% 4.3f", "" );

        // Compute the one, infinity, and frobenius norms of 'x'. Note that when
        // computing the norm alpha of a vector 'x', the datatype of alpha must be
        // equal to the real projection of the datatype of 'x'.
        blis.bli_dnorm1v( n, x, 1, norm1 );
        blis.bli_dnormiv( n, x, 1, normi );
        blis.bli_dnormfv( n, x, 1, normf );

        blis.bli_dprintm( "x: 1-norm:", 1, 1, norm1, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "x: infinity norm:", 1, 1, normi, rs, cs, "% 4.3f", "" );
        blis.bli_dprintm( "x: frobenius norm:", 1, 1, normf, rs, cs, "% 4.3f", "" );

        blis.bli_zprintm( "y", m, n, y, rs, cs, "% 4.3f", "" );

        // Compute the one, infinity, and frobenius norms of 'y'. Note that we
        // can reuse the same scalars from before for computing norms of
        // dcomplex matrices, since the real projection of dcomplex is double.
        blis.bli_znorm1v( n, y, 1, norm1 );
        blis.bli_znormiv( n, y, 1, normi );
        blis.bli_znormfv( n, y, 1, normf );

        blis.bli_dprintm( "y: 1-norm:", 1, 1, norm1, 1, 1, "% 4.3f", "" );
        blis.bli_dprintm( "y: infinity norm:", 1, 1, normi, 1, 1, "% 4.3f", "" );
        blis.bli_dprintm( "y: frobenius norm:", 1, 1, normf, 1, 1, "% 4.3f", "" );


        //
        // Example 2: Compute various matrix norms.
        //

        System.out.println( "\n#\n#  -- Example 2 --\n#" );

        // Create a few matrices to work with.
        m = 5; n = 6; rs = 1; cs = m;
        a = mallocd( m * n);
        b = mallocdc( m * n);

        // Initialize the matrices to random values.
        blis.bli_drandm( 0, BLIS_DENSE, m, n, a, rs, cs );
        blis.bli_zrandm( 0, BLIS_DENSE, m, n, b, rs, cs );

        blis.bli_dprintm( "a:", m, n, a, rs, cs, "% 4.3f", "" );

        // Compute the one-norm of 'a'.
        blis.bli_dnorm1m( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, a, rs, cs, norm1 );
        blis.bli_dnormim( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, a, rs, cs, normi );
        blis.bli_dnormfm( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, a, rs, cs, normf );

        blis.bli_dprintm( "a: 1-norm:", 1, 1, norm1, 1, 1, "% 4.3f", "" );
        blis.bli_dprintm( "a: infinity norm:", 1, 1, normi, 1, 1, "% 4.3f", "" );
        blis.bli_dprintm( "a: frobenius norm:", 1, 1, normf, 1, 1, "% 4.3f", "" );

        blis.bli_zprintm( "b:", m, n, b, rs, cs, "% 4.3f", "" );

        // Compute the one-norm of 'b'.
        blis.bli_znorm1m( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, b, rs, cs, norm1 );
        blis.bli_znormim( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, b, rs, cs, normi );
        blis.bli_znormfm( 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, n, b, rs, cs, normf );

        blis.bli_dprintm( "a: 1-norm:", 1, 1, norm1, 1, 1, "% 4.3f", "" );
        blis.bli_dprintm( "a: infinity norm:", 1, 1, normi, 1, 1, "% 4.3f", "" );
        blis.bli_dprintm( "a: frobenius norm:", 1, 1, normf, 1, 1, "% 4.3f", "" );


        //
        // Example 3: Make a real matrix explicitly symmetric (or Hermitian).
        //

        System.out.println( "\n#\n#  -- Example 3 --\n#" );

        // Create a few matrices to work with.
        m = 4; n = 4; rs = 1; cs = m;
        c = mallocd( m * m);
        d = mallocd( m * m);

        // Initialize all of 'c' to -1.0 to simulate junk values.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, minus_one, c, rs, cs );

        // Randomize the lower triangle of 'c'.
        blis.bli_drandm( 0, BLIS_LOWER, m, m, c, rs, cs );

        blis.bli_dprintm( "c (initial state):", m, m, c, rs, cs, "% 4.3f", "" );

        // mksymm on a real matrix transposes the stored triangle into the
        // unstored triangle, making the matrix densely symmetric.
        blis.bli_dmksymm( BLIS_LOWER, m, c, rs, cs );

        blis.bli_dprintm( "c (after mksymm on lower triangle):", m, m, c, rs, cs, "% 4.3f", "" );

        // Digression: Most people think only of complex matrices as being able
        // to be complex. However, in BLIS, we define Hermitian operations on
        // real matrices, too--they are simply equivalent to the corresponding
        // symmetric operation. For example, when we make a real matrix explicitly
        // Hermitian, the result is indistinguishable from making it symmetric.

        // Initialize all of 'd' to -1.0 to simulate junk values.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, minus_one, d, rs, cs );

        // Randomize the lower triangle of 'd'.
        blis.bli_drandm( 0, BLIS_LOWER, m, m, d, rs, cs );

        blis.bli_dprintm( "d (initial state):", m, m, d, rs, cs, "% 4.3f", "" );

        // mkherm on a real matrix behaves the same as mksymm, as there are no
        // imaginary elements to conjugate.
        blis.bli_dmkherm( BLIS_LOWER, m, d, rs, cs );

        blis.bli_dprintm( "c (after mkherm on lower triangle):", m, m, d, rs, cs, "% 4.3f", "" );


        //
        // Example 4: Make a complex matrix explicitly symmetric or Hermitian.
        //

        System.out.println( "\n#\n#  -- Example 4 --\n#" );

        // Create a few matrices to work with.
        m = 4; n = 4; rs = 1; cs = m;
        e = mallocdc( m * m);
        f = mallocdc( m * m);

        // Initialize all of 'e' to -1.0 to simulate junk values.
        blis.bli_zsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, minus_one_z, e, rs, cs );

        // Randomize the upper triangle of 'e'.
        blis.bli_zrandm( 0, BLIS_UPPER, m, m, e, rs, cs );

        blis.bli_zprintm( "e (initial state):", m, m, e, rs, cs, "% 4.3f", "" );

        // mksymm on a complex matrix transposes the stored triangle into the
        // unstored triangle.
        blis.bli_zmksymm( BLIS_UPPER, m, e, rs, cs );

        blis.bli_zprintm( "e (after mksymm on lower triangle):", m, m, e, rs, cs, "% 4.3f", "" );

        // Initialize all of 'f' to -1.0 to simulate junk values.
        blis.bli_zsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, minus_one_z, f, rs, cs );

        // Randomize the upper triangle of 'd'.
        blis.bli_zrandm( 0, BLIS_UPPER, m, m, f, rs, cs );

        blis.bli_zprintm( "f (initial state):", m, m, f, rs, cs, "% 4.3f", "" );

        // mkherm on a real matrix behaves the same as mksymm, as there are no
        // imaginary elements to conjugate.
        blis.bli_zmkherm( BLIS_UPPER, m, f, rs, cs );

        blis.bli_zprintm( "f (after mkherm on lower triangle):", m, m, f, rs, cs, "% 4.3f", "" );


        //
        // Example 5: Make a real matrix explicitly triangular.
        //

        System.out.println( "\n#\n#  -- Example 5 --\n#" );

        // Create a few matrices to work with.
        m = 5; n = 5; rs = 1; cs = m;
        g = mallocd( m * m);

        // Initialize all of 'g' to -1.0 to simulate junk values.
        blis.bli_dsetm( BLIS_NO_CONJUGATE, 0, BLIS_NONUNIT_DIAG, BLIS_DENSE,
                m, m, minus_one, g, rs, cs );

        // Randomize the lower triangle of 'g'.
        blis.bli_drandm( 0, BLIS_LOWER, m, m, g, rs, cs );

        blis.bli_dprintm( "g (initial state):", m, m, g, rs, cs, "% 4.3f", "" );

        // mktrim does not explicitly copy any data, since presumably the stored
        // triangle already contains the data of interest. However, mktrim does
        // explicitly writes zeros to the unstored region.
        blis.bli_dmktrim( BLIS_LOWER, m, g, rs, cs );

        blis.bli_dprintm( "g (after mktrim):", m, m, g, rs, cs, "% 4.3f", "" );

    }
}
