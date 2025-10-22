package blistest.oapi;

import jblis.blisFactory;
import jblis.oapi.blis_oapi;
import jblis.types.num_t;
import jblis.types.obj_t;

import java.lang.foreign.Arena;

import static jblis.blisHelpers.mallocd;
import static jblis.blisHelpers.ptr;
import static jblis.types.num_t.BLIS_DCOMPLEX;
import static jblis.types.num_t.BLIS_DOUBLE;

public class obj_ij {
    public static void main(String[] args)
    {
        obj_t[] a1 = obj_t.ptr(),
                a2 = obj_t.ptr(),
                a3 = obj_t.ptr();
        num_t dt;
        long m, n;
        long rs, cs;
        long i, j;

        var blis = blisFactory.getBlisOAPI();
        //
        // This file demonstrates accessing and updating individual matrix elements
        // through the BLIS object API.
        //


        //
        // Example 1: Create an object and then individually access/view some of
        //            its elements.
        //

        System.out.print( "\n#\n#  -- Example 1 --\n#\n\n" );

        try (Arena arena = Arena.ofConfined()) {
            // We'll use these parameters for the following examples.
            dt = BLIS_DOUBLE;
            m = 4;
            n = 5;
            rs = 1;
            cs = m;

            // Create a object with known elements using the same approach as the
            // previous example file.
            double[] p1 = mallocd(m * n);
            init_dmatrix_by_cols(m, n, p1, rs, cs);
            blis.bli_obj_create_with_attached_buffer(dt, m, n, arena, p1, rs, cs, a1);

            blis.bli_printm("matrix 'a1' (initial state)", a1, "%5.1f", "");

            // Regardless of how we create our object--whether via bli_obj_create() or
            // via attaching an existing buffer to a bufferless object--we can access
            // individual elements by specifying their offsets. The output value is
            // broken up by real and imaginary component. (When accessing real matrices,
            // the imaginary component will always be zero.)
            i = 1;
            j = 3;
            double[] alpha_r = ptr(0.0), alpha_i = ptr(0.0);
            blis.bli_getijm(i, j, a1,  alpha_r,alpha_i );

            // Here, we print out the element "returned" by bli_getijm().
            System.out.printf("element (%2d,%2d) of matrix 'a1' (real + imag): %5.1f + %5.1f\n", (int) i, (int) j, alpha_r[0], alpha_i[0]);

            // Let's query a few more elements.
            i = 0;
            j = 2;
            blis.bli_getijm(i, j, a1,  alpha_r, alpha_i );

            System.out.printf("element (%2d,%2d) of matrix 'a1' (real + imag): %5.1f + %5.1f\n", (int) i, (int) j, alpha_r[0], alpha_i[0]);

            i = 3;
            j = 4;
            blis.bli_getijm(i, j, a1,  alpha_r, alpha_i );

            System.out.printf("element (%2d,%2d) of matrix 'a1' (real + imag): %5.1f + %5.1f\n", (int) i, (int) j, alpha_r[0], alpha_i[0]);

            System.out.print("\n");


            //
            // Example 2: Modify individual elements of an existing matrix.
            //

            System.out.print("\n#\n#  -- Example 2 --\n#\n\n");

            // Now let's change a few elements. Even if we set the imaginary
            // argument to a non-zero value, argument is ignored since we're
            // modifying a real matrix. If a1 were a complex object, those
            // values would be stored verbatim into the appropriate matrix
            // elements (see example for a3 below).
            alpha_r = ptr(-3.0);
            alpha_i = ptr(0.0);
            i = 1;
            j = 3;
            blis.bli_setijm(alpha_r[0], alpha_i[0], i, j, a1);

            alpha_r = ptr(-9.0);
            alpha_i = ptr(-1.0);
            i = 0;
            j = 2;
            blis.bli_setijm(alpha_r[0], alpha_i[0], i, j, a1);

            alpha_r = ptr(-7.0);
            alpha_i = ptr(2.0);
            i = 3;
            j = 4;
            blis.bli_setijm(alpha_r[0], alpha_i[0], i, j, a1);

            // Print the matrix again so we can see the update elements.
            blis.bli_printm("matrix 'a1' (modified state)", a1, "%5.1f", "");

            // Next, let's create a regular object (with a buffer) and then
            // initialize its elements using blis.bli_setijm().
            blis.bli_obj_create(dt, m, n, rs, cs, a2);

            // See definition of init_dobj_by_cols() below.
            init_dobj_by_cols(blis, a2);

            // Because we initialized a2 in the same manner as a1 (by columns),
            // it should contain the same initial state as a1.
            blis.bli_printm("matrix 'a2'", a2, "%5.1f", "");


            //
            // Example 3: Modify individual elements of an existing complex matrix.
            //

            System.out.print("\n#\n#  -- Example 3 --\n#\n\n");

            // Create and initialize a complex object.
            dt = BLIS_DCOMPLEX;
            blis.bli_obj_create(dt, m, n, rs, cs, a3 );

            // Initialize the matrix elements. (See definition of init_dobj_by_cols()
            // below).
            init_zobj_by_cols(blis, a3 );

            // Print the complex matrix.
            blis.bli_printm("matrix 'a3' (initial state)", a3, "%5.1f", "" );

            i = 3;
            j = 0;
            blis.bli_getijm(i, j, a3, alpha_r, alpha_i );
            alpha_r[0] *= -1.0;
            alpha_i[0] *= -1.0;
            blis.bli_setijm(alpha_r[0], alpha_i[0], i, j, a3 );

            i = 3;
            j = 4;
            blis.bli_getijm(i, j, a3, alpha_r, alpha_i );
            alpha_r[0] *= -1.0;
            alpha_i[0] *= -1.0;
            blis.bli_setijm(alpha_r[0], alpha_i[0], i, j,  a3 );

            i = 0;
            j = 4;
            blis.bli_getijm(i, j, a3, alpha_r, alpha_i );
            alpha_r[0] *= -1.0;
            alpha_i[0] *= -1.0;
            blis.bli_setijm(alpha_r[0], alpha_i[0], i, j, a3 );

            // Print the matrix again so we can see the update elements.
            blis.bli_printm("matrix 'a3' (modified state)", a3, "%5.1f", "" );

            // Free the objects we created.
            blis.bli_obj_free(a2);
            blis.bli_obj_free(a3);
        }
    }

    // -----------------------------------------------------------------------------


    static void init_dmatrix_by_cols( long m, long n, double[] a, long rs, long cs )
    {
        long  i, j;

        double alpha = 0.0;

        // Step through a matrix by columns, assigning each element a unique
        // value, starting at 0.
        for ( j = 0; j < n; ++j )
        {
            for ( i = 0; i < m; ++i )
            {
                a[(int)(i*rs + j*cs)] = alpha;
                alpha += 1.0;
            }
        }
    }

    static void init_dobj_by_cols(blis_oapi blis, obj_t[] a )
    {
        long m = blis.bli_obj_length( a );
        long n = blis.bli_obj_width( a );
        long i, j;

        double alpha = 0.0;

        // Step through a matrix by columns, assigning each element a unique
        // value, starting at 0.
        for ( j = 0; j < n; ++j )
        {
            for ( i = 0; i < m; ++i )
            {
                blis.bli_setijm( alpha, 0.0, i, j, a );
                alpha += 1.0;
            }
        }
    }

    static void init_zobj_by_cols(blis_oapi blis, obj_t[] a )
    {
        long m = blis.bli_obj_length( a );
        long n = blis.bli_obj_width( a );
        long i, j;

        double alpha = 0.0;

        // Step through a matrix by columns, assigning each real and imaginary
        // element a unique value, starting at 0.
        for ( j = 0; j < n; ++j )
        {
            for ( i = 0; i < m; ++i )
            {
                blis.bli_setijm( alpha, alpha + 1.0, i, j, a );

                alpha += 2.0;
            }
        }
    }


}
