package blistest.oapi;

import jblis.blisFactory;
import jblis.types.num_t;
import jblis.types.obj_t;

import static jblis.oapi.blis_oapi.*;
import static jblis.types.num_t.BLIS_DCOMPLEX;
import static jblis.types.num_t.BLIS_DOUBLE;

public class level1m {
    public static void main(String[] args)
    {
        obj_t[] alpha = obj_t.ptr(),
                beta = obj_t.ptr(), 
                gamma = obj_t.ptr();
        obj_t[] a = obj_t.ptr(), 
                b = obj_t.ptr(), 
                c = obj_t.ptr(), 
                d = obj_t.ptr(), 
                e = obj_t.ptr(), 
                f = obj_t.ptr(), 
                g = obj_t.ptr(), 
                h = obj_t.ptr();
        num_t dt;
        long m, n;
        long rs, cs;

        var blis = blisFactory.getBlisOAPI();

        //
        // This file demonstrates working with matrix objects and the level-1m
        // operations.
        //


        //
        // Example 1: Create matrix objects and then broadcast (copy) scalar
        //            values to all elements.
        //

        System.out.print( "\n#\n#  -- Example 1 --\n#\n\n" );

        // Create a few matrices to work with. We make them all of the same
        // dimensions so that we can perform operations between them.
        dt = BLIS_DOUBLE;
        m = 2; n = 3; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, n, rs, cs, a );
        blis.bli_obj_create( dt, m, n, rs, cs, b );
        blis.bli_obj_create( dt, m, n, rs, cs, c );
        blis.bli_obj_create( dt, m, n, rs, cs, d );
        blis.bli_obj_create( dt, m, n, rs, cs, e );

        // Let's also create and initialize some scalar objects.
        blis.bli_obj_create_1x1( dt, alpha );
        blis.bli_obj_create_1x1( dt, beta );
        blis.bli_obj_create_1x1( dt, gamma );

        blis.bli_setsc( 2.0, 0.0, alpha );
        blis.bli_setsc( 0.2, 0.0, beta );
        blis.bli_setsc( 3.0, 0.0, gamma );

        blis.bli_printm( "alpha:", alpha, "% 4.3f", "" );
        blis.bli_printm( "beta:", beta, "% 4.3f", "" );
        blis.bli_printm( "gamma:", gamma, "% 4.3f", "" );

        // Matrices, like vectors, can set by "broadcasting" a constant to every
        // element.
        blis.bli_setm( BLIS_ONE.toptr(), a );
        blis.bli_setm( alpha, b );
        blis.bli_setm( BLIS_ZERO.toptr(), c );

        blis.bli_printm( "a := 1.0", a, "% 4.3f", "" );
        blis.bli_printm( "b := alpha", b, "% 4.3f", "" );
        blis.bli_printm( "c := 0.0", c, "% 4.3f", "" );


        //
        // Example 2: Randomize a matrix object.
        //

        System.out.print( "\n#\n#  -- Example 2 --\n#\n\n" );

        // Set a matrix to random values.
        blis.bli_randm( e );

        blis.bli_printm( "e (randomized):", e, "% 4.3f", "" );


        //
        // Example 3: Perform element-wise operations on matrices.
        //

        System.out.print( "\n#\n#  -- Example 3 --\n#\n\n" );

        // Copy a matrix.
        blis.bli_copym( e, d );
        blis.bli_printm( "d := e", d, "% 4.3f", "" );

        // Add and subtract vectors.
        blis.bli_addm( a, d );
        blis.bli_printm( "d := d + a", d, "% 4.3f", "" );

        blis.bli_subm( a, e );
        blis.bli_printm( "e := e - a", e, "% 4.3f", "" );

        // Scale a matrix (destructive).
        blis.bli_scalm( alpha, e );
        blis.bli_printm( "e := alpha * e", e, "% 4.3f", "" );

        // Scale a matrix (non-destructive).
        blis.bli_scal2m( beta, e, c );
        blis.bli_printm( "c := beta * e", c, "% 4.3f", "" );

        // Scale and accumulate between matrices.
        blis.bli_axpym( alpha, a, c );
        blis.bli_printm( "c := c + alpha * a", c, "% 4.3f", "" );


        //
        // Example 4: Copy and transpose a matrix.
        //

        System.out.print( "\n#\n#  -- Example 4 --\n#\n\n" );

        // Create an n-by-m matrix into which we can copy-transpose an m-by-n
        // matrix.
        blis.bli_obj_create( dt, n, m, rs, cs, f );

        // Initialize all of 'f' to -1.0 to simulate junk values.
        blis.bli_setm( BLIS_MINUS_ONE.toptr(), f );

        //todo: e is wrong here
        blis.bli_printm( "e:", e, "% 4.3f", "" );
        blis.bli_printm( "f (initial value):", f, "% 4.3f", "" );

        // Since we are going to copy 'e' to 'f', we need to indicate a transpose
        // on 'e', the input operand. Transposition can be indicated by setting a
        // bit in the object. Since it always starts out as "no transpose", we can
        // simply toggle the bit.
        blis.bli_obj_toggle_trans( e );

        // Another way to mark and object for transposition is to set it directly.
        //blis.bli_obj_set_onlytrans( BLIS_TRANSPOSE, e );

        // A third way is to "apply" a transposition. This is equivalent to toggling
        // the transposition when the value being applied is BLIS_TRANSPOSE. If
        // the value applied is BLIS_NO_TRANSPOSE, the transposition bit in the
        // targeted object is unaffected. (Applying transposes is more useful in
        // practice when the 'trans' argument is a variable and not a constant
        // literal.)
        //blis.bli_obj_apply_trans( BLIS_TRANSPOSE, e );
        //blis.bli_obj_apply_trans( BLIS_NO_TRANSPOSE, e );
        //blis.bli_obj_apply_trans( trans, e );

        // Copy 'e' to 'f', transposing 'e' in the process. Notice that we haven't
        // modified any properties of 'f'. It's the source operand that matters
        // when marking an operand for transposition, not the destination.
        blis.bli_copym( e, f );

        blis.bli_printm( "f (copied value):", f, "% 4.3f", "" );


        //
        // Example 5: Copy and Hermitian-transpose a matrix.
        //

        System.out.print( "\n#\n#  -- Example 5 --\n#\n\n" );

        // Create an n-by-m complex matrix into which we can Hermitian-transpose
        // (or, conjugate-transpose) another complex (m-by-n) matrix.
        dt = BLIS_DCOMPLEX;
        blis.bli_obj_create( dt, m, n, rs, cs, g );
        blis.bli_obj_create( dt, n, m, rs, cs, h );

        // Randomize 'g', the input operand.
        blis.bli_randm( g );

        // Initialize all of 'h' to -1.0 to simulate junk values.
        blis.bli_setm( BLIS_MINUS_ONE.toptr(), h );

        blis.bli_printm( "g:", g, "% 4.3f", "" );
        blis.bli_printm( "h (initial value):", h, "% 4.3f", "" );

        // Set both the transpose and conjugation bits.
        blis.bli_obj_toggle_trans( g );
        blis.bli_obj_toggle_conj( g );

        // Copy 'g' to 'h', conjugating and transposing 'g' in the process.
        // Once again, notice that it's the source operand that we've marked for
        // conjugation.
        blis.bli_copym( g, h );

        blis.bli_printm( "h (copied value):", h, "% 4.3f", "" );


        // Free the objects.
        blis.bli_obj_free( alpha );
        blis.bli_obj_free( beta );
        blis.bli_obj_free( gamma );
        blis.bli_obj_free( a );
        blis.bli_obj_free( b );
        blis.bli_obj_free( c );
        blis.bli_obj_free( d );
        blis.bli_obj_free( e );
        blis.bli_obj_free( f );
        blis.bli_obj_free( g );
        blis.bli_obj_free( h );

    }
}
