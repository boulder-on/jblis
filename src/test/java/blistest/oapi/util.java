package blistest.oapi;

import jblis.blisFactory;
import jblis.types.num_t;
import jblis.types.obj_t;

import static jblis.oapi.blis_oapi.BLIS_MINUS_ONE;
import static jblis.types.num_t.BLIS_DCOMPLEX;
import static jblis.types.num_t.BLIS_DOUBLE;
import static jblis.types.struc_t.*;
import static jblis.types.uplo_t.BLIS_LOWER;
import static jblis.types.uplo_t.BLIS_UPPER;

public class util {
    public static void main(String[] args)
    {
        obj_t[] norm1 = obj_t.ptr(),
                normi = obj_t.ptr(), normf = obj_t.ptr();
        obj_t[] x = obj_t.ptr(),
                y = obj_t.ptr(),
                a = obj_t.ptr(),
                b = obj_t.ptr(),
                c = obj_t.ptr(),
                d = obj_t.ptr(),
                e = obj_t.ptr(),
                f = obj_t.ptr(),
                g = obj_t.ptr();
        num_t dt;
        long m, n;
        long rs, cs;


        //
        // This file demonstrates working with vector and matrix objects in the
        // context of various utility operations.
        //
        var blis = blisFactory.getBlisOAPI();


        //
        // Example 1: Compute various vector norms.
        //

        System.out.print( "\n#\n#  -- Example 1 --\n#\n\n" );

        // Create a few matrices to work with.
        m = 1; n = 5; rs = 0; cs = 0;
        blis.bli_obj_create( BLIS_DOUBLE,   m, n, rs, cs, x );
        blis.bli_obj_create( BLIS_DCOMPLEX, m, n, rs, cs, y );

        // Let's also create some scalar objects to hold the norms. Note that when
        // computing the norm alpha of a vector 'x', the datatype of alpha must be
        // equal to the real projection of the datatype of 'x'.
        dt = BLIS_DOUBLE;
        blis.bli_obj_create_1x1( dt, norm1 );
        blis.bli_obj_create_1x1( dt, normi );
        blis.bli_obj_create_1x1( dt, normf );

        // Initialize the vectors to random values.
        blis.bli_randv( x );
        blis.bli_randv( y );

        blis.bli_printm( "x:", x, "% 4.3f", "" );

        // Compute the one, infinity, and frobenius norms of 'x'.
        blis.bli_norm1v( x, norm1 );
        blis.bli_normiv( x, normi );
        blis.bli_normfv( x, normf );

        blis.bli_printm( "x: 1-norm:", norm1, "% 4.3f", "" );
        blis.bli_printm( "x: infinity norm:", normi, "% 4.3f", "" );
        blis.bli_printm( "x: frobenius norm:", normf, "% 4.3f", "" );

        blis.bli_printm( "y:", y, "% 4.3f", "" );

        // Compute the one, infinity, and frobenius norms of 'y'. Note that we
        // can reuse the same scalars from before for computing norms of
        // dcomplex matrices, since the real projection of dcomplex is double.
        blis.bli_norm1v( y, norm1 );
        blis.bli_normiv( y, normi );
        blis.bli_normfv( y, normf );

        blis.bli_printm( "y: 1-norm:", norm1, "% 4.3f", "" );
        blis.bli_printm( "y: infinity norm:", normi, "% 4.3f", "" );
        blis.bli_printm( "y: frobenius norm:", normf, "% 4.3f", "" );


        //
        // Example 2: Compute various matrix norms.
        //

        System.out.print( "\n#\n#  -- Example 2 --\n#\n\n" );

        // Create a few matrices to work with.
        m = 5; n = 6; rs = 0; cs = 0;
        blis.bli_obj_create( BLIS_DOUBLE,   m, n, rs, cs, a );
        blis.bli_obj_create( BLIS_DCOMPLEX, m, n, rs, cs, b );

        // Initialize the matrices to random values.
        blis.bli_randm( a );
        blis.bli_randm( b );

        blis.bli_printm( "a:", a, "% 4.3f", "" );

        // Compute the one, infinity, and frobenius norms of 'a'.
        blis.bli_norm1m( a, norm1 );
        blis.bli_normim( a, normi );
        blis.bli_normfm( a, normf );

        blis.bli_printm( "a: 1-norm:", norm1, "% 4.3f", "" );
        blis.bli_printm( "a: infinity norm:", normi, "% 4.3f", "" );
        blis.bli_printm( "a: frobenius norm:", normf, "% 4.3f", "" );

        blis.bli_printm( "b:", b, "% 4.3f", "" );

        // Compute the one-norm of 'b'.
        blis.bli_norm1m( b, norm1 );
        blis.bli_normim( b, normi );
        blis.bli_normfm( b, normf );

        blis.bli_printm( "b: 1-norm:", norm1, "% 4.3f", "" );
        blis.bli_printm( "b: infinity norm:", normi, "% 4.3f", "" );
        blis.bli_printm( "b: frobenius norm:", normf, "% 4.3f", "" );


        //
        // Example 3: Make a real matrix explicitly symmetric (or Hermitian).
        //

        System.out.print( "\n#\n#  -- Example 3 --\n#\n\n" );

        // Create a few matrices to work with.
        m = 4; n = 4; rs = 0; cs = 0;
        blis.bli_obj_create( BLIS_DOUBLE, m, n, rs, cs, c );
        blis.bli_obj_create( BLIS_DOUBLE, m, n, rs, cs, d );

        // Initialize all of 'c' to -1.0 to simulate junk values.
        blis.bli_setm( BLIS_MINUS_ONE.toptr(), c );

        // Set the structure and uplo of 'c'.
        blis.bli_obj_set_struc( BLIS_SYMMETRIC, c );
        blis.bli_obj_set_uplo( BLIS_LOWER, c );

        // Randomize the lower triangle of 'c'.
        blis.bli_randm( c );

        blis.bli_printm( "c (initial state):", c, "% 4.3f", "" );

        // mksymm on a real matrix transposes the stored triangle into the
        // unstored triangle, making the matrix densely symmetric.
        blis.bli_mksymm( c );

        blis.bli_printm( "c (after mksymm on lower triangle):", c, "% 4.3f", "" );

        // Digression: Most people think only of complex matrices as being able
        // to be complex. However, in BLIS, we define Hermitian operations on
        // real matrices, too--they are simply equivalent to the corresponding
        // symmetric operation. For example, when we make a real matrix explicitly
        // Hermitian, the result is indistinguishable from making it symmetric.

        // Initialize all of 'd' to -1.0 to simulate junk values.
        blis.bli_setm( BLIS_MINUS_ONE.toptr(), d );

        blis.bli_obj_set_struc( BLIS_HERMITIAN, d );
        blis.bli_obj_set_uplo( BLIS_LOWER, d );

        // Randomize the lower triangle of 'd'.
        blis.bli_randm( d );

        blis.bli_printm( "d (initial state):", d, "% 4.3f", "" );

        // mkherm on a real matrix behaves the same as mksymm, as there are no
        // imaginary elements to conjugate.
        blis.bli_mkherm( d );

        blis.bli_printm( "d (after mkherm on lower triangle):", d, "% 4.3f", "" );


        //
        // Example 4: Make a complex matrix explicitly symmetric or Hermitian.
        //

        System.out.print( "\n#\n#  -- Example 4 --\n#\n\n" );

        // Create a few matrices to work with.
        m = 4; n = 4; rs = 0; cs = 0;
        blis.bli_obj_create( BLIS_DCOMPLEX, m, n, rs, cs, e );
        blis.bli_obj_create( BLIS_DCOMPLEX, m, n, rs, cs, f );

        // Initialize all of 'e' to -1.0 to simulate junk values.
        blis.bli_setm( BLIS_MINUS_ONE.toptr(), e );

        // Set the structure and uplo of 'e'.
        blis.bli_obj_set_struc( BLIS_SYMMETRIC, e );
        blis.bli_obj_set_uplo( BLIS_UPPER, e );

        // Randomize the upper triangle of 'e'.
        blis.bli_randm( e );

        blis.bli_printm( "e (initial state):", e, "% 4.3f", "" );

        // mksymm on a complex matrix transposes the stored triangle into the
        // unstored triangle.
        blis.bli_mksymm( e );

        blis.bli_printm( "e (after mksymm):", e, "% 4.3f", "" );

        // Initialize all of 'f' to -1.0 to simulate junk values.
        blis.bli_setm( BLIS_MINUS_ONE.toptr(), f );

        // Set the structure and uplo of 'f'.
        blis.bli_obj_set_struc( BLIS_HERMITIAN, f );
        blis.bli_obj_set_uplo( BLIS_UPPER, f );

        // Randomize the upper triangle of 'f'.
        blis.bli_randm( f );

        blis.bli_printm( "f (initial state):", f, "% 4.3f", "" );

        // mkherm on a complex matrix transposes and conjugates the stored
        // triangle into the unstored triangle.
        blis.bli_mkherm( f );

        blis.bli_printm( "f (after mkherm):", f, "% 4.3f", "" );


        //
        // Example 5: Make a real matrix explicitly triangular.
        //

        System.out.print( "\n#\n#  -- Example 5 --\n#\n\n" );

        // Create a few matrices to work with.
        m = 5; n = 5; rs = 0; cs = 0;
        blis.bli_obj_create( BLIS_DOUBLE, m, n, rs, cs, g );

        // Initialize all of 'g' to -1.0 to simulate junk values.
        blis.bli_setm( BLIS_MINUS_ONE.toptr(), g );

        // Set the structure and uplo of 'g'.
        blis.bli_obj_set_struc( BLIS_TRIANGULAR, g );
        blis.bli_obj_set_uplo( BLIS_LOWER, g );

        // Randomize the lower triangle of 'g'.
        blis.bli_randm( g );

        blis.bli_printm( "g (initial state):", g, "% 4.3f", "" );

        // mktrim does not explicitly copy any data, since presumably the stored
        // triangle already contains the data of interest. However, mktrim does
        // explicitly writes zeros to the unstored region.
        blis.bli_mktrim( g );

        blis.bli_printm( "g (after mktrim):", g, "% 4.3f", "" );


        // Free the objects.
        blis.bli_obj_free( norm1 );
        blis.bli_obj_free( normi );
        blis.bli_obj_free( normf );
        blis.bli_obj_free( x );
        blis.bli_obj_free( y );
        blis.bli_obj_free( a );
        blis.bli_obj_free( b );
        blis.bli_obj_free( c );
        blis.bli_obj_free( d );
        blis.bli_obj_free( e );
        blis.bli_obj_free( f );
        blis.bli_obj_free( g );

    }
}
