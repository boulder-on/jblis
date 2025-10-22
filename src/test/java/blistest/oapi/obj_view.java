package blistest.oapi;

import jblis.blisFactory;
import jblis.oapi.blis_oapi;
import jblis.types.num_t;
import jblis.types.obj_t;

import java.lang.foreign.Arena;

import static jblis.blisHelpers.mallocd;
import static jblis.oapi.blis_oapi.BLIS_ZERO;
import static jblis.types.num_t.BLIS_DOUBLE;

public class obj_view {

    public static void main(String[] args)
    {
        obj_t[] a1 = obj_t.ptr(), a2 = obj_t.ptr();
        obj_t[] v1 = obj_t.ptr(),
                v2 = obj_t.ptr(),
                v3 = obj_t.ptr(),
                v4 = obj_t.ptr(),
                v5 = obj_t.ptr();
        num_t dt;
        long m, n;
        long rs, cs;
        long i, j;
        long mv, nv;


        //
        // This file demonstrates creating and submatrix views into existing matrices.
        //
        var blis = blisFactory.getBlisOAPI();

        try(Arena arena = Arena.ofConfined()) {

            //
            // Example 1: Create an object and then create a submatrix view.
            //

            System.out.print("\n#\n#  -- Example 1 --\n#\n\n");

            // We'll use these parameters for the following examples.
            dt = BLIS_DOUBLE;
            m = 6;
            n = 7;
            rs = 1;
            cs = m;

            // Create an object a1 using bli_obj_create().
            blis.bli_obj_create(dt, m, n, rs, cs, a1);

            // Initialize a1 to contain known values.
            init_dobj_by_cols(blis, a1);

            blis.bli_printm("matrix 'a1' (initial state)", a1, "%5.1f", "");

            // Acquire a 4x3 submatrix view into a1 at (i,j) offsets (1,2).
            i = 1;
            j = 2;
            mv = 4;
            nv = 3;
            blis.bli_acquire_mpart(i, j, mv, nv, a1, v1);

            blis.bli_printm("4x3 submatrix 'v1' at offsets (1,2)", v1, "%5.1f", "");

            // NOTE: Submatrix views should never be passed to blis.bli_obj_free(). It
            // will not cause an immediate error, but it is bad practice. Instead,
            // you should only release the objects that were created directy via
            // blis.bli_obj_create(). In the above example, that means only object a1
            // would be passed to blis.bli_obj_free().


            //
            // Example 2: Modify the contents of a submatrix view.
            //

            System.out.print("\n#\n#  -- Example 2 --\n#\n\n");

            // Modify the first three elements of the first column.
            blis.bli_setijm(-3.0, 0.0, 0, 0, v1);
            blis.bli_setijm(-4.0, 0.0, 1, 0, v1);
            blis.bli_setijm(-5.0, 0.0, 2, 0, v1);

            // Modify the first three elements of the second column.
            blis.bli_setijm(-6.0, 0.0, 0, 1, v1);
            blis.bli_setijm(-7.0, 0.0, 1, 1, v1);
            blis.bli_setijm(-8.0, 0.0, 2, 1, v1);

            // Print the matrix again so we can see the update elements.
            blis.bli_printm("submatrix view 'v1' (modified state)", v1, "%5.1f", "");
            blis.bli_printm("matrix 'a1' (indirectly modified due to changes to 'v1')", a1, "%5.1f", "");


            //
            // Example 3: Create a submatrix view that is "too big".
            //

            System.out.print("\n#\n#  -- Example 3 --\n#\n\n");

            // blis.bli_acquire_mpart() will safely truncate your requested submatrix
            // view dimensions (or even the offsets) if they extend beyond the
            // bounds of the parent object.

            blis.bli_printm("matrix 'a1' (current state)", a1, "%5.1f", "");

            // Acquire a 4x3 submatrix view into a1 at offsets (4,2). Notice how
            // the requested view contains four rows, but the view is created with
            // only two rows because the starting m offset of 4 leaves only two rows
            // left in the parent matrix.
            blis.bli_acquire_mpart(4, 2, 4, 3, a1, v2);

            blis.bli_printm("4x3 submatrix 'v2' at offsets (4,2) -- two rows truncated for safety", v2, "%5.1f", "");


            //
            // Example 4: Create a bufferless object, attach an external buffer, and
            //            then create a submatrix view.
            //

            System.out.print("\n#\n#  -- Example 4 --\n#\n\n");

            // Create a object with known elements using the same approach as the
            // previous example file.
            double[] p1 = mallocd(m * n);
            init_dmatrix_by_cols(m, n, p1, rs, cs);
            blis.bli_obj_create_with_attached_buffer(dt, m, n, arena, p1, rs, cs, a2);

            blis.bli_printm("matrix 'a2' (initial state)", a2, "%5.1f", "" );

            // Acquire a 3x4 submatrix view at offset (2,3).
            blis.bli_acquire_mpart(2, 3, 3, 4, a2, v3 );

            blis.bli_printm("3x4 submatrix view 'v3' at offsets (2,3)", v3, "%5.1f", "" );


            //
            // Example 5: Use a submatrix view to set a region of a larger matrix to
            //            zero.
            //

            System.out.print("\n#\n#  -- Example 5 --\n#\n\n");

            blis.bli_printm("3x4 submatrix view 'v3' at offsets (2,3)", v3, "%5.1f", "" );

            blis.bli_setm( BLIS_ZERO.toptr(), v3 );

            blis.bli_printm("3x4 submatrix view 'v3' (zeroed out)", v3, "%5.1f", "" );

            blis.bli_printm("matrix 'a2' (modified state)", a2, "%5.1f", "" );


            //
            // Example 6: Obtain a submatrix view into a submatrix view.
            //

            System.out.print("\n#\n#  -- Example 6 --\n#\n\n");

            blis.bli_acquire_mpart(1, 1, 5, 6, a2, v4 );

            blis.bli_printm("5x6 submatrix view 'v4' at offsets (1,1) of 'a2'", v4, "%5.1f", "");

            blis.bli_acquire_mpart(1, 0, 4, 5, v4, v5);

            blis.bli_printm("4x5 submatrix view 'v5' at offsets (1,0) of 'v4'", v5, "%5.1f", "");


            // Free the objects we created.
            blis.bli_obj_free(a1);
        }
    }

    static void init_dmatrix_by_rows( long m, long n, double[] a, long rs, long cs )
    {
        long  i, j;

        double alpha = 0.0;

        // Step through a matrix by rows, assigning each element a unique
        // value, starting at 0.
        for ( i = 0; i < m; ++i )
        {
            for ( j = 0; j < n; ++j )
            {
                a[(int)(i*rs + j*cs)] = alpha;

                alpha += 1.0;
            }
        }
    }

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
