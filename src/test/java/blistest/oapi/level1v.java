package blistest.oapi;

import jblis.blisFactory;
import jblis.types.num_t;
import jblis.types.obj_t;

import static jblis.oapi.blis_oapi.*;
import static jblis.types.num_t.BLIS_DOUBLE;

public class level1v {
    public static void main(String[] args)
    {
        obj_t[] alpha = obj_t.ptr(),
                beta = obj_t.ptr(),
                gamma = obj_t.ptr();
        obj_t[] x = obj_t.ptr(),
                y = obj_t.ptr(),
                z = obj_t.ptr(),
                w = obj_t.ptr(),
                a = obj_t.ptr();
        num_t dt;
        long m, n;
        long rs, cs;

        var blis = blisFactory.getBlisOAPI();

        //
        // This file demonstrates working with vector objects and the level-1v
        // operations.
        //


        //
        // Example 1: Create vector objects and then broadcast (copy) scalar
        //            values to all elements.
        //

        System.out.print( "\n#\n#  -- Example 1 --\n#\n\n" );

        // Create a few vectors to work with. We make them all of the same length
        // so that we can perform operations between them.
        // NOTE: We've chosen to use row vectors here (1x4) instead of column
        // vectors (4x1) to allow for easier reading of standard output (less
        // scrolling).
        dt = BLIS_DOUBLE;
        m = 1; n = 4; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, n, rs, cs, x );
        blis.bli_obj_create( dt, m, n, rs, cs, y );
        blis.bli_obj_create( dt, m, n, rs, cs, z );
        blis.bli_obj_create( dt, m, n, rs, cs, w );
        blis.bli_obj_create( dt, m, n, rs, cs, a );

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

        // Vectors can set by "broadcasting" a constant to every element.
        blis.bli_setv( BLIS_ONE.toptr(), x );
        blis.bli_setv( alpha, y );
        blis.bli_setv( BLIS_ZERO.toptr(), z );

        // Note that we can use printv or printm to print vectors since vectors
        // are also matrices. We choose to use printm because it honors the
        // orientation of the vector (row or column) when printing, whereas
        // printv always prints vectors as column vectors regardless of their
        // they are 1 x n or n x 1.
        blis.bli_printm( "x := 1.0", x, "% 4.3f", "" );
        blis.bli_printm( "y := alpha", y, "% 4.3f", "" );
        blis.bli_printm( "z := 0.0", z, "% 4.3f", "" );


        //
        // Example 2: Randomize a vector object.
        //

        System.out.print( "\n#\n#  -- Example 2 --\n#\n\n" );

        // Set a vector to random values.
        blis.bli_randv( w );

        blis.bli_printm( "w := randv()", w, "% 4.3f", "" );


        //
        // Example 3: Perform various element-wise operations on vector objects.
        //

        System.out.print( "\n#\n#  -- Example 3 --\n#\n\n" );

        // Copy a vector.
        blis.bli_copyv( w, a );
        blis.bli_printm( "a := w", a, "% 4.3f", "" );

        // Add and subtract vectors.
        blis.bli_addv( y, a );
        blis.bli_printm( "a := a + y", a, "% 4.3f", "" );

        blis.bli_subv( w, a );
        blis.bli_printm( "a := a - w", a, "% 4.3f", "" );

        // Scale a vector (destructive).
        blis.bli_scalv( beta, a );
        blis.bli_printm( "a := beta * a", a, "% 4.3f", "" );

        // Scale a vector (non-destructive).
        blis.bli_scal2v( gamma, a, z );
        blis.bli_printm( "z := gamma * a", z, "% 4.3f", "" );

        // Scale and accumulate between vectors.
        blis.bli_axpyv( alpha, w, x );
        blis.bli_printm( "x := x + alpha * w", x, "% 4.3f", "" );

        blis.bli_xpbyv( w, BLIS_MINUS_ONE.toptr(), x );
        blis.bli_printm( "x := -1.0 * x + w", x, "% 4.3f", "" );

        // Invert a vector element-wise.
        blis.bli_invertv( y );
        blis.bli_printm( "y := 1 / y", y, "% 4.3f", "" );

        // Swap two vectors.
        blis.bli_swapv( x, y );
        blis.bli_printm( "x (after swapping with y)", x, "% 4.3f", "" );
        blis.bli_printm( "y (after swapping with x)", y, "% 4.3f", "" );


        //
        // Example 4: Perform contraction-like operations on vector objects.
        //

        System.out.print( "\n#\n#  -- Example 4 --\n#\n\n" );

        // Perform a dot product.
        blis.bli_dotv( a, z, gamma );
        blis.bli_printm( "gamma := a * z (dot product)", gamma, "%5.2f", "" );

        // Perform an extended dot product.
        blis.bli_dotxv( alpha, a, z, BLIS_ONE.toptr(), gamma );
        blis.bli_printm( "gamma := 1.0 * gamma + alpha * a * z (accumulate scaled dot product)", gamma, "%5.2f", "" );


        // Free the objects.
        blis.bli_obj_free( alpha );
        blis.bli_obj_free( beta );
        blis.bli_obj_free( gamma );
        blis.bli_obj_free( x );
        blis.bli_obj_free( y );
        blis.bli_obj_free( z );
        blis.bli_obj_free( w );
        blis.bli_obj_free( a );

    }
}
