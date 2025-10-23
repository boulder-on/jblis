package blistest.oapi;

import jblis.blisFactory;
import jblis.types.num_t;
import jblis.types.obj_t;

import static jblis.oapi.blis_oapi.*;
import static jblis.types.diag_t.BLIS_NONUNIT_DIAG;
import static jblis.types.num_t.BLIS_DOUBLE;
import static jblis.types.struc_t.BLIS_SYMMETRIC;
import static jblis.types.struc_t.BLIS_TRIANGULAR;
import static jblis.types.uplo_t.BLIS_LOWER;
import static jblis.types.uplo_t.BLIS_UPPER;

public class level2 {
    public static void main(String[] args)
    {
        num_t dt;
        long m, n;
        long rs, cs;

        obj_t[] a = obj_t.ptr(), 
                x = obj_t.ptr(), 
                y = obj_t.ptr(),
                b = obj_t.ptr();
        obj_t[] alpha;
        obj_t[] beta;

        var blis = blisFactory.getBlisOAPI();

        //
        // This file demonstrates level-2 operations.
        //


        //
        // Example 1: Perform a general rank-1 update (ger) operation.
        //

        System.out.print( "\n#\n#  -- Example 1 --\n#\n\n" );

        // Create some matrix and vector operands to work with.
        dt = BLIS_DOUBLE;
        m = 4; n = 5; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, n, rs, cs, a );
        blis.bli_obj_create( dt, m, 1, rs, cs, x );
        blis.bli_obj_create( dt, 1, n, rs, cs, y );

        // Set alpha.
        alpha = BLIS_ONE.toptr();

        // Initialize vectors 'x' and 'y'.
        blis.bli_randv( x );
        blis.bli_setv( BLIS_MINUS_ONE.toptr(), y );

        // Initialize 'a' to 1.0.
        blis.bli_setm( BLIS_ONE.toptr(), a );

        blis.bli_printm( "x: set to random values", x, "% 4.3f", "" );
        blis.bli_printm( "y: set to -1.0", y, "% 4.3f", "" );
        blis.bli_printm( "a: initial value", a, "% 4.3f", "" );

        // a := a + alpha * x * y, where 'a' is general.
        blis.bli_ger( alpha, x, y, a );

        blis.bli_printm( "a: after ger", a, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( x );
        blis.bli_obj_free( y );


        //
        // Example 2: Perform a general matrix-vector multiply (gemv) operation.
        //

        System.out.print( "\n#\n#  -- Example 2 --\n#\n\n" );

        // Create some matrix and vector operands to work with.
        dt = BLIS_DOUBLE;
        m = 4; n = 5; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, n, rs, cs, a );
        blis.bli_obj_create( dt, 1, n, rs, cs, x );
        blis.bli_obj_create( dt, 1, m, rs, cs, y );

        // Notice that we created vectors 'x' and 'y' as row vectors, even though
        // we often think of them as column vectors so that the overall problem
        // dimensions remain conformal. Note that this flexibility only comes
        // from the fact that the operation requires those operands to be vectors.
        // If we were instead looking at an operation where the operands were of
        // general shape (such as with the gemm operation), then typically the
        // dimensions matter, and column vectors would not be interchangeable with
        // row vectors and vice versa.

        // Set the scalars to use.
        alpha = BLIS_ONE.toptr();
        beta  = BLIS_ONE.toptr();

        // Initialize vectors 'x' and 'y'.
        blis.bli_setv( BLIS_ONE.toptr(),  x );
        blis.bli_setv( BLIS_ZERO.toptr(), y );

        // Randomize 'a'.
        blis.bli_randm( a );

        blis.bli_printm( "a: randomized", a, "% 4.3f", "" );
        blis.bli_printm( "x: set to 1.0", x, "% 4.3f", "" );
        blis.bli_printm( "y: initial value", y, "% 4.3f", "" );

        // y := beta * y + alpha * a * x, where 'a' is general.
        blis.bli_gemv( alpha, a, x, beta, y );

        blis.bli_printm( "y: after gemv", y, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( x );
        blis.bli_obj_free( y );


        //
        // Example 3: Perform a symmetric rank-1 update (syr) operation.
        //

        System.out.print( "\n#\n#  -- Example 3 --\n#\n\n" );

        // Create some matrix and vector operands to work with.
        dt = BLIS_DOUBLE;
        m = 5; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, m, rs, cs, a );
        blis.bli_obj_create( dt, 1, m, rs, cs, x );

        // Set alpha.
        alpha = BLIS_ONE.toptr();

        // Initialize vector 'x'.
        blis.bli_randv( x );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_setm( BLIS_ZERO.toptr(), a );

        // Mark matrix 'a' as symmetric and stored in the lower triangle, and
        // then randomize that lower triangle.
        blis.bli_obj_set_struc( BLIS_SYMMETRIC, a );
        blis.bli_obj_set_uplo( BLIS_LOWER, a );
        blis.bli_randm( a );

        blis.bli_printm( "x: set to random values", x, "% 4.3f", "" );
        blis.bli_printm( "a: initial value (zeros in upper triangle)", a, "% 4.3f", "" );

        // a := a + alpha * x * x^T, where 'a' is symmetric and lower-stored.
        blis.bli_syr( alpha, x, a );

        blis.bli_printm( "a: after syr", a, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( x );


        //
        // Example 4: Perform a symmetric matrix-vector multiply (symv) operation.
        //

        System.out.print( "\n#\n#  -- Example 4 --\n#\n\n" );

        // Create some matrix and vector operands to work with.
        dt = BLIS_DOUBLE;
        m = 5; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, m, rs, cs, a );
        blis.bli_obj_create( dt, 1, m, rs, cs, x );
        blis.bli_obj_create( dt, 1, m, rs, cs, y );

        // Set the scalars to use.
        alpha = BLIS_ONE.toptr();
        beta  = BLIS_ONE.toptr();

        // Initialize vectors 'x' and 'y'.
        blis.bli_setv( BLIS_ONE.toptr(),  x );
        blis.bli_setv( BLIS_ZERO.toptr(), y );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_setm( BLIS_ZERO.toptr(), a );

        // Mark matrix 'a' as symmetric and stored in the upper triangle, and
        // then randomize that upper triangle.
        blis.bli_obj_set_struc( BLIS_SYMMETRIC, a );
        blis.bli_obj_set_uplo( BLIS_UPPER, a );
        blis.bli_randm( a );

        blis.bli_printm( "a: randomized (zeros in lower triangle)", a, "% 4.3f", "" );
        blis.bli_printm( "x: set to 1.0", x, "% 4.3f", "" );
        blis.bli_printm( "y: initial value", y, "% 4.3f", "" );

        // y := beta * y + alpha * a * x, where 'a' is symmetric and upper-stored.
        blis.bli_symv( alpha, a, x, beta, y );

        blis.bli_printm( "y: after symv", y, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( x );
        blis.bli_obj_free( y );


        //
        // Example 5: Perform a triangular matrix-vector multiply (trmv) operation.
        //

        System.out.print( "\n#\n#  -- Example 5 --\n#\n\n" );

        // Create some matrix and vector operands to work with.
        dt = BLIS_DOUBLE;
        m = 5; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, m, rs, cs, a );
        blis.bli_obj_create( dt, 1, m, rs, cs, x );

        // Set the scalars to use.
        alpha = BLIS_ONE.toptr();

        // Initialize vector 'x'.
        blis.bli_setv( BLIS_ONE.toptr(), x );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_setm( BLIS_ZERO.toptr(), a );

        // Mark matrix 'a' as triangular, stored in the lower triangle, and
        // having a non-unit diagonal. Then randomize that lower triangle.
        blis.bli_obj_set_struc( BLIS_TRIANGULAR, a );
        blis.bli_obj_set_uplo( BLIS_LOWER, a );
        blis.bli_obj_set_diag( BLIS_NONUNIT_DIAG, a );
        blis.bli_randm( a );

        blis.bli_printm( "a: randomized (zeros in upper triangle)", a, "% 4.3f", "" );
        blis.bli_printm( "x: initial value", x, "% 4.3f", "" );

        // x := alpha * a * x, where 'a' is triangular and lower-stored.
        blis.bli_trmv( alpha, a, x );

        blis.bli_printm( "x: after trmv", x, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( x );


        //
        // Example 6: Perform a triangular solve (trsv) operation.
        //

        System.out.print( "\n#\n#  -- Example 6 --\n#\n\n" );

        // Create some matrix and vector operands to work with.
        dt = BLIS_DOUBLE;
        m = 5; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, m, rs, cs, a );
        blis.bli_obj_create( dt, 1, m, rs, cs, b );
        blis.bli_obj_create( dt, 1, m, rs, cs, y );

        // Set the scalars to use.
        alpha = BLIS_ONE.toptr();

        // Initialize vector 'x'.
        blis.bli_setv( BLIS_ONE.toptr(), b );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_setm( BLIS_ZERO.toptr(), a );

        // Mark matrix 'a' as triangular, stored in the lower triangle, and
        // having a non-unit diagonal. Then randomize that lower triangle.
        blis.bli_obj_set_struc( BLIS_TRIANGULAR, a );
        blis.bli_obj_set_uplo( BLIS_LOWER, a );
        blis.bli_obj_set_diag( BLIS_NONUNIT_DIAG, a );
        blis.bli_randm( a );

        // Load the diagonal. By setting the diagonal to something of greater
        // absolute value than the off-diagonal elements, we increase the odds
        // that the matrix is not singular (singular matrices have no inverse).
        blis.bli_shiftd( BLIS_TWO.toptr(), a );

        blis.bli_printm( "a: randomized (zeros in upper triangle)", a, "% 4.3f", "" );
        blis.bli_printm( "b: initial value", b, "% 4.3f", "" );

        // solve a * x = alpha * b, where 'a' is triangular and lower-stored, and
        // overwrite b with the solution vector x.
        blis.bli_trsv( alpha, a, b );

        blis.bli_printm( "b: after trsv", b, "% 4.3f", "" );

        // We can confirm the solution by comparing the product of a and x to the
        // original value of b.
        blis.bli_copyv( b, y );
        blis.bli_trmv( alpha, a, y );

        blis.bli_printm( "y: should equal initial value of b", y, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( b );
    }
}
