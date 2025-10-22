package blistest.oapi;

import jblis.blisFactory;
import jblis.types.num_t;
import jblis.types.obj_t;

import static jblis.types.num_t.*;
import static jblis.types.obj_t.*;

public class obj_basic {

    public static void main(String[] args)
    {
        obj_t[] a1 = ptr(),
                a2 = ptr(),
                a3 = ptr(),
                a4 = ptr(),
                a5 = ptr(),
                a6 = ptr(),
                a7 = ptr(),
                a8 = ptr(),
                a9 = ptr(),
                a10 = ptr(),
                a11 = ptr();
        obj_t[] v1 = ptr(),
                v2 = ptr();
        num_t dt;
        long m, n;
        long rs, cs;

        var blis = blisFactory.getBlisOAPI();
        //
        // This file demonstrates the basics of creating objects in BLIS,
        // inspecting their basic properties, and printing matrix objects.
        //


        //
        // Example 1: Create an object containing a 4x3 matrix of double-
        //            precision real elements stored in column-major order.
        //

        // The matrix dimensions are m = 4 and n = 3. We choose to use column
        // storage (often called column-major storage) and thus we specify
        // that the row stride ("rs" for short) argument is 1 and the column
        // stride ("cs" for short) argument is equal to m = 4. In column
        // storage, cs is known as the leading dimension.
        dt = BLIS_DOUBLE; m  = 4; n  = 3;
        rs = 1; cs = 4;
        blis.bli_obj_create( dt, m, n, rs, cs, a1 );

        // If cs is greater than m, then extra rows (in this case, two) will
        // be allocated beyond the lower edge of the matrix. Sometimes this
        // is desireable for alignment purposes.
        dt = BLIS_DOUBLE; m  = 4; n  = 3;
        rs = 1; cs = 6;
        blis.bli_obj_create( dt, m, n, rs, cs, a2 );


        //
        // Example 2: Create an object containing a 4x3 matrix of double-
        //            precision real elements stored in row-major order.
        //

        // Here, we choose to use row storage (often called row-major storage)
        // and thus we specify that the cs is 1 and rs is equal to n = 3. In
        // row storage, the leading dimension corresponds to rs.
        dt = BLIS_DOUBLE; m  = 4; n  = 3;
        rs = 3; cs = 1;
        blis.bli_obj_create( dt, m, n, rs, cs, a3 );

        // As with the second example, we can cause extra columns (in this
        // case, five) to be allocated beyond the right edge of the matrix.
        dt = BLIS_DOUBLE; m  = 4; n  = 3;
        rs = 8; cs = 1;
        blis.bli_obj_create( dt, m, n, rs, cs, a4 );


        //
        // Example 3: Create objects using other floating-point datatypes.
        //

        // Examples of using the other floating-point datatypes.
        m  = 4; n  = 3;
        rs = 1; cs = 4;
        blis.bli_obj_create( BLIS_FLOAT,    m, n, rs, cs, a5 );
        blis.bli_obj_create( BLIS_SCOMPLEX, m, n, rs, cs, a6 );
        blis.bli_obj_create( BLIS_DCOMPLEX, m, n, rs, cs, a7 );


        //
        // Example 4: Create objects using default (column) storage so that
        //            we avoid having to specify rs and cs manually.
        //

        // Specifying the row and column strides as zero, as is done here, is
        // a shorthand request for the default storage scheme, which is
        // currently (and always has been) column storage. When requesting the
        // default storage scheme with rs = cs = 0, BLIS may insert additional
        // padding for alignment purposes. So, the 3x8 matrix object created
        // below may end up having a row stride that is greater than 3. When
        // in doubt, query the value!
        blis.bli_obj_create( BLIS_FLOAT, 3, 5, 0, 0, a8 );


        //
        // Example 5: Inspect object fields after creation to expose
        //            possible alignment/padding.
        //

        System.out.print( "\n#\n#  -- Example 5 --\n#\n\n" );

        // Let's inspect the amount of padding inserted for alignment. Note
        // the difference between the m dimension and the column stride.
        System.out.printf( "datatype            %s\n", blis.bli_dt_string( blis.bli_obj_dt( a8 ) ) );
        System.out.printf( "datatype size       %d bytes\n", ( int )blis.bli_dt_size( blis.bli_obj_dt( a8 ) ) );
        System.out.printf( "m dim (# of rows):  %d\n", ( int )blis.bli_obj_length( a8 ) );
        System.out.printf( "n dim (# of cols):  %d\n", ( int )blis.bli_obj_width( a8 ) );
        System.out.printf( "row stride:         %d\n", ( int )blis.bli_obj_row_stride( a8 ) );
        System.out.printf( "col stride:         %d\n", ( int )blis.bli_obj_col_stride( a8 ) );


        //
        // Example 6: Inspect object fields after creation of other floating-
        //            point datatypes.
        //

        System.out.print( "\n#\n#  -- Example 6 --\n#\n\n" );

        blis.bli_obj_create( BLIS_DOUBLE,   3, 5, 0, 0, a9 );
        blis.bli_obj_create( BLIS_SCOMPLEX, 3, 5, 0, 0, a10);
        blis.bli_obj_create( BLIS_DCOMPLEX, 3, 5, 0, 0, a11 );

        System.out.printf( "datatype            %s\n", blis.bli_dt_string( blis.bli_obj_dt( a9 ) ) );
        System.out.printf( "datatype size       %d bytes\n", ( int )blis.bli_dt_size( blis.bli_obj_dt( a9 ) ) );
        System.out.printf( "m dim (# of rows):  %d\n", ( int )blis.bli_obj_length( a9 ) );
        System.out.printf( "n dim (# of cols):  %d\n", ( int )blis.bli_obj_width( a9 ) );
        System.out.printf( "row stride:         %d\n", ( int )blis.bli_obj_row_stride( a9 ) );
        System.out.printf( "col stride:         %d\n", ( int )blis.bli_obj_col_stride( a9 ) );

        System.out.print( "\n" );
        System.out.printf( "datatype            %s\n", blis.bli_dt_string( blis.bli_obj_dt( a10 ) ) );
        System.out.printf( "datatype size       %d bytes\n", ( int )blis.bli_dt_size( blis.bli_obj_dt( a10 ) ) );
        System.out.printf( "m dim (# of rows):  %d\n", ( int )blis.bli_obj_length( a10 ) );
        System.out.printf( "n dim (# of cols):  %d\n", ( int )blis.bli_obj_width( a10 ) );
        System.out.printf( "row stride:         %d\n", ( int )blis.bli_obj_row_stride( a10 ) );
        System.out.printf( "col stride:         %d\n", ( int )blis.bli_obj_col_stride( a10 ) );

        System.out.print( "\n" );
        System.out.printf( "datatype            %s\n", blis.bli_dt_string( blis.bli_obj_dt( a11 ) ) );
        System.out.printf( "datatype size       %d bytes\n", ( int )blis.bli_dt_size( blis.bli_obj_dt( a11 ) ) );
        System.out.printf( "m dim (# of rows):  %d\n", ( int )blis.bli_obj_length( a11 ) );
        System.out.printf( "n dim (# of cols):  %d\n", ( int )blis.bli_obj_width( a11 ) );
        System.out.printf( "row stride:         %d\n", ( int )blis.bli_obj_row_stride( a11 ) );
        System.out.printf( "col stride:         %d\n", ( int )blis.bli_obj_col_stride( a11 ) );


        //
        // Example 7: Initialize an object's elements to random values and then
        //            print the matrix.
        //

        System.out.print( "\n#\n#  -- Example 7 --\n#\n\n" );

        // We can set matrices to random values. The default behavior of
        // blis.bli_randm() is to use random values on the internval [-1,1].
        blis.bli_randm( a9 );

        // And we can also print the matrices associated with matrix objects.
        // Notice that the third argument is a printf()-style format specifier.
        // Any valid printf() format specifier can be passed in here, but you
        // still need to make sure that the specifier makes sense for the data
        // being printed. For example, you shouldn't use "%d" when printing
        // elements of type 'float'.
        blis.bli_printm( "matrix 'a9' contents:", a9, "% 4.3f", "" );


        //
        // Example 8: Randomize and then print from an object containing a complex
        //            matrix.
        //

        System.out.print( "\n#\n#  -- Example 8 --\n#\n\n" );

        // When printing complex matrices, the same format specifier gets used
        // for both the real and imaginary parts.
        blis.bli_randm( a11 );
        blis.bli_printm( "matrix 'a11' contents (complex):", a11, "% 4.3f", "" );


        //
        // Example 9: Create, randomize, and print vector objects.
        //

        System.out.print( "\n#\n#  -- Example 9 --\n#\n\n" );

        // Now let's create two vector objects--a row vector and a column vector.
        // (A vector object is like a matrix object, except that it has at least
        // one unit dimension (equal to one).
        blis.bli_obj_create( BLIS_DOUBLE, 4, 1, 0, 0, v1 );
        blis.bli_obj_create( BLIS_DOUBLE, 1, 6, 0, 0, v2 );

        // If we know the object is a vector, we can use blis.bli_randv(), though
        // blis.bli_randm() would work just as well, since any vector is also a matrix.
        blis.bli_randv( v1 );
        blis.bli_randv( v2 );

        // We can print vectors, too.
        blis.bli_printm( "vector 'v1' contents:", v1, "%5.1f", "" );
        blis.bli_printm( "vector 'v2' contents:", v2, "%5.1f", "" );


        // Free all of the objects we created.
        blis.bli_obj_free( a1 );
        blis.bli_obj_free( a2 );
        blis.bli_obj_free( a3 );
        blis.bli_obj_free( a4 );
        blis.bli_obj_free( a5 );
        blis.bli_obj_free( a6 );
        blis.bli_obj_free( a7 );
        blis.bli_obj_free( a8 );
        blis.bli_obj_free( a9 );
        blis.bli_obj_free( a10 );
        blis.bli_obj_free( a11 );
        blis.bli_obj_free( v1 );
        blis.bli_obj_free( v2 );

    }
}
