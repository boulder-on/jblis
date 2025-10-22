package blistest.oapi;

import jblis.blisFactory;
import jblis.types.obj_t;
import jblis.types.num_t;
import jpassport.Utils;

import java.lang.foreign.Arena;

import static jblis.blisHelpers.mallocd;
import static jblis.oapi.blis_oapi.toVoidPtr;
import static jblis.types.num_t.BLIS_DOUBLE;

public class obj_attach {
    public static void main(String[] args)
    {
        obj_t[] a1 = obj_t.ptr(), a2 = obj_t.ptr();
        num_t dt;
        long m, n;
        long rs, cs;


        //
        // This file demonstrates interfacing external or existing buffers
        // with BLIS objects.
        //


        //
        // Example 1: Create a bufferless object and then attach an external
        //            buffer to it, specifying column storage.
        //
        var blis = blisFactory.getBlisOAPI();

        System.out.print( "\n#\n#  -- Example 1 --\n#\n\n" );

        // We'll use these parameters for the following examples.
        dt = BLIS_DOUBLE;
        m = 4; n = 5; rs = 1; cs = m;

        try (Arena arena = Arena.ofConfined()) {
            // First we allocate and initialize a matrix by columns.
            double[] p1 = mallocd(m * n);
            init_dmatrix_by_cols(m, n, p1, rs, cs);

            // bli_obj_create() automatically allocates an array large enough to hold
            // of the elements. We can also create a "bufferless" object and then
            // "attach" our own buffer to that object. This is useful when interfacing
            // BLIS objects to an existing application that produces its own matrix
            // arrays/buffers.
            blis.bli_obj_create_without_buffer(dt, m, n, a1);

            // Note that the fourth argument of bli_obj_attach_buffer() is the so-called
            // "imaginary stride". First of all, this stride only has meaning in the
            // complex domain. Secondly, it is a somewhat experimental property of the
            // obj_t, and one that is not fully recognized/utilized throughout BLIS.
            // Thus, the safe thing to do is to always pass in a 0, which is a request
            // for the default (which is actually 1). Please don't use any other value
            // unless you really know what you are doing.
            blis.bli_obj_attach_buffer(arena, p1, rs, cs, 0, a1);

            // Now let's print the matrix so we can see how the element values were
            // assigned.
            blis.bli_printm("matrix 'a1', initialized by columns:", a1, "%5.1f", "");
        }

        //
        // Example 2: Create a bufferless object and then attach an external
        //            buffer to it, specifying row storage.
        //

        System.out.print( "\n#\n#  -- Example 2 --\n#\n\n" );

        try (Arena arena = Arena.ofConfined())
        {
        // Now let's allocate another buffer, but this time we'll initialize it by
        // rows instead of by columns. We'll use the same values for m, n, rs, cs.
        double[] p2 = mallocd( m * n);
        init_dmatrix_by_rows( m, n, p2, rs, cs );

        // Create a new bufferless object and attach the new buffer. This time,
        // instead of calling bli_obj_create_without_buffer() followed by
        // bli_obj_attach_buffer(), we call bli_obj_create_with_attached_buffer(),
        // which is just a convenience wrapper around the former two functions.
        // (Note that the wrapper function omits the imaginary stride argument.)

            blis.bli_obj_create_with_attached_buffer( dt, m, n, arena, p2, rs, cs, a2 );

            // Print the matrix so we can compare it to the first matrix output.
            blis.bli_printm( "matrix 'a2', initialized by rows:", a2, "%5.1f", "" );
        }
        // Please note that after creating an object via either of:
        // - bli_obj_create_without_buffer(), or
        // - bli_obj_create_with_attached_buffer()
        // we do NOT free it! That's because these functions merely initialize the
        // object and do not actually allocate any memory.

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

}
