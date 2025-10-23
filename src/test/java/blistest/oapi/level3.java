package blistest.oapi;

import jblis.blisFactory;
import jblis.types.num_t;
import jblis.types.obj_t;
import jblis.types.side_t;

import static jblis.oapi.blis_oapi.*;
import static jblis.types.diag_t.BLIS_NONUNIT_DIAG;
import static jblis.types.num_t.BLIS_DOUBLE;
import static jblis.types.side_t.BLIS_LEFT;
import static jblis.types.struc_t.BLIS_SYMMETRIC;
import static jblis.types.struc_t.BLIS_TRIANGULAR;
import static jblis.types.uplo_t.BLIS_LOWER;
import static jblis.types.uplo_t.BLIS_UPPER;

public class level3 {
    public static void main(String[] args)
    {
        num_t dt;
        long m, n, k;
        long rs, cs;
        side_t side;

        obj_t[] a = obj_t.ptr(), 
                b = obj_t.ptr(), 
                c = obj_t.ptr();
        obj_t[] alpha = obj_t.ptr();
        obj_t[] beta = obj_t.ptr();

        var blis = blisFactory.getBlisOAPI();

        //
        // This file demonstrates level-3 operations.
        //


        //
        // Example 1: Perform a general matrix-matrix multiply (gemm) operation.
        //

        System.out.print( "\n#\n#  -- Example 1 --\n#\n\n" );

        // Create some matrix operands to work with.
        dt = BLIS_DOUBLE;
        m = 4; n = 5; k = 3; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, n, rs, cs, c );
        blis.bli_obj_create( dt, m, k, rs, cs, a );
        blis.bli_obj_create( dt, k, n, rs, cs, b );

        // Set the scalars to use.
        alpha = BLIS_ONE.toptr();
        beta  = BLIS_ONE.toptr();

        // Initialize the matrix operands.
        blis.bli_randm( a );
        blis.bli_setm( BLIS_ONE.toptr(), b );
        blis.bli_setm( BLIS_ZERO.toptr(), c );

        blis.bli_printm( "a: randomized", a, "% 4.3f", "" );
        blis.bli_printm( "b: set to 1.0", b, "% 4.3f", "" );
        blis.bli_printm( "c: initial value", c, "% 4.3f", "" );

        // c := beta * c + alpha * a * b, where 'a', 'b', and 'c' are general.
        blis.bli_gemm( alpha, a, b, beta, c );

        blis.bli_printm( "c: after gemm", c, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( b );
        blis.bli_obj_free( c );


        //
        // Example 1b: Perform a general matrix-matrix multiply (gemm) operation
        //             with the left input operand (matrix A) transposed.
        //

        System.out.print( "\n#\n#  -- Example 1b --\n#\n\n" );

        // Create some matrix operands to work with.
        dt = BLIS_DOUBLE;
        m = 4; n = 5; k = 3; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, n, rs, cs, c );
        blis.bli_obj_create( dt, k, m, rs, cs, a );
        blis.bli_obj_create( dt, k, n, rs, cs, b );

        // Set the scalars to use.
        alpha = BLIS_ONE.toptr();
        beta  = BLIS_ONE.toptr();

        // Initialize the matrix operands.
        blis.bli_randm( a );
        blis.bli_setm( BLIS_ONE.toptr(), b );
        blis.bli_setm( BLIS_ZERO.toptr(), c );

        // Set the transpose bit in 'a'.
        blis.bli_obj_toggle_trans( a );

        blis.bli_printm( "a: randomized", a, "% 4.3f", "" );
        blis.bli_printm( "b: set to 1.0", b, "% 4.3f", "" );
        blis.bli_printm( "c: initial value", c, "% 4.3f", "" );

        // c := beta * c + alpha * a^T * b, where 'a', 'b', and 'c' are general.
        blis.bli_gemm( alpha, a, b, beta, c );

        blis.bli_printm( "c: after gemm", c, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( b );
        blis.bli_obj_free( c );


        //
        // Example 2: Perform a symmetric rank-k update (syrk) operation.
        //

        System.out.print( "\n#\n#  -- Example 2 --\n#\n\n" );

        // Create some matrix and vector operands to work with.
        dt = BLIS_DOUBLE;
        m = 5; k = 3; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, m, rs, cs, c );
        blis.bli_obj_create( dt, m, k, rs, cs, a );

        // Set alpha.
        alpha = BLIS_ONE.toptr();

        // Initialize matrix operands.
        blis.bli_setm( BLIS_ZERO.toptr(), c );
        blis.bli_randm( a );

        // Mark matrix 'c' as symmetric and stored in the lower triangle, and
        // then randomize that lower triangle.
        blis.bli_obj_set_struc( BLIS_SYMMETRIC, c );
        blis.bli_obj_set_uplo( BLIS_LOWER, c );
        blis.bli_randm( c );

        blis.bli_printm( "a: set to random values", a, "% 4.3f", "" );
        blis.bli_printm( "c: initial value (zeros in upper triangle)", c, "% 4.3f", "" );

        // c := c + alpha * a * a^T, where 'c' is symmetric and lower-stored.
        blis.bli_syrk( alpha, a, beta, c );

        blis.bli_printm( "c: after syrk", c, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( c );
        blis.bli_obj_free( a );


        //
        // Example 3: Perform a symmetric matrix-matrix multiply (symm) operation.
        //

        System.out.print( "\n#\n#  -- Example 3 --\n#\n\n" );

        // Create some matrix and vector operands to work with.
        dt = BLIS_DOUBLE;
        m = 5; n = 6; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, m, rs, cs, a );
        blis.bli_obj_create( dt, m, n, rs, cs, b );
        blis.bli_obj_create( dt, m, n, rs, cs, c );

        // Set the scalars to use.
        alpha = BLIS_ONE.toptr();
        beta  = BLIS_ONE.toptr();

        // Set the side operand.
        side = BLIS_LEFT;

        // Initialize matrices 'b' and 'c'.
        blis.bli_setm( BLIS_ONE.toptr(),  b );
        blis.bli_setm( BLIS_ZERO.toptr(), c );

        // Zero out all of matrix 'a'. This is optional, but will avoid possibly
        // displaying junk values in the unstored triangle.
        blis.bli_setm( BLIS_ZERO.toptr(), a );

        // Mark matrix 'a' as symmetric and stored in the upper triangle, and
        // then randomize that upper triangle.
        blis.bli_obj_set_struc( BLIS_SYMMETRIC, a );
        blis.bli_obj_set_uplo( BLIS_UPPER, a );
        blis.bli_randm( a );

        blis.bli_printm( "a: randomized (zeros in lower triangle)", a, "% 4.3f", "" );
        blis.bli_printm( "b: set to 1.0", b, "% 4.3f", "" );
        blis.bli_printm( "c: initial value", c, "% 4.3f", "" );

        // c := beta * c + alpha * a * b, where 'a' is symmetric and upper-stored.
        // Note that the first 'side' operand indicates the side from which matrix
        // 'a' is multiplied into 'b'.
        blis.bli_symm( side, alpha, a, b, beta, c );

        blis.bli_printm( "c: after symm", c, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( b );
        blis.bli_obj_free( c );


        //
        // Example 4: Perform a triangular matrix-matrix multiply (trmm) operation.
        //

        System.out.print( "\n#\n#  -- Example 4 --\n#\n\n" );

        // Create some matrix and vector operands to work with.
        dt = BLIS_DOUBLE;
        m = 5; n = 4; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, m, rs, cs, a );
        blis.bli_obj_create( dt, m, n, rs, cs, b );

        // Set the scalars to use.
        alpha = BLIS_ONE.toptr();

        // Set the side operand.
        side = BLIS_LEFT;

        // Initialize matrix 'b'.
        blis.bli_setm( BLIS_ONE.toptr(), b );

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
        blis.bli_printm( "b: initial value", b, "% 4.3f", "" );

        // b := alpha * a * b, where 'a' is triangular and lower-stored.
        blis.bli_trmm( side, alpha, a, b );

        blis.bli_printm( "x: after trmm", b, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( b );


        //
        // Example 5: Perform a triangular solve with multiple right-hand sides
        //            (trsm) operation.
        //

        System.out.print( "\n#\n#  -- Example 5 --\n#\n\n" );

        // Create some matrix and vector operands to work with.
        dt = BLIS_DOUBLE;
        m = 5; n = 4; rs = 0; cs = 0;
        blis.bli_obj_create( dt, m, m, rs, cs, a );
        blis.bli_obj_create( dt, m, n, rs, cs, b );
        blis.bli_obj_create( dt, m, n, rs, cs, c );

        // Set the scalars to use.
        alpha = BLIS_ONE.toptr();

        // Set the side operand.
        side = BLIS_LEFT;

        // Initialize matrix 'b'.
        blis.bli_setm( BLIS_ONE.toptr(), b );

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
        // overwrite b with the solution matrix x.
        blis.bli_trsm( side, alpha, a, b );

        blis.bli_printm( "b: after trsm", b, "% 4.3f", "" );

        // We can confirm the solution by comparing the product of a and x to the
        // original value of b.
        blis.bli_copym( b, c );
        blis.bli_trmm( side, alpha, a, c );

        blis.bli_printm( "c: should equal initial value of b", c, "% 4.3f", "" );

        // Free the objects.
        blis.bli_obj_free( a );
        blis.bli_obj_free( b );
        blis.bli_obj_free( c );
        
    }
}
