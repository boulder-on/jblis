package blistest.oapi;

import jblis.blisFactory;
import jblis.types.num_t;
import jblis.types.obj_t;

import java.lang.foreign.Arena;

import static jblis.blisHelpers.ptr;
import static jblis.oapi.blis_oapi.BLIS_ONE;
import static jblis.types.num_t.BLIS_DCOMPLEX;
import static jblis.types.num_t.BLIS_DOUBLE;

public class level0 {
    public static void main(String[] args)
    {
        obj_t[] alpha = obj_t.ptr(), 
                beta = obj_t.ptr(), 
                gamma = obj_t.ptr(), 
                kappa = obj_t.ptr(), 
                zeta = obj_t.ptr();
        num_t dt;
        double gamma_d = 0;

        var blis = blisFactory.getBlisOAPI();

        //
        // This file demonstrates working with scalar objects.
        //


        //
        // Example 1: Create a scalar (1x1) object.
        //

        try (Arena arena = Arena.ofConfined()) {
            dt = BLIS_DOUBLE;

            // The easiest way to create a scalar object is with the following
            // convenience function.
            blis.bli_obj_create_1x1(dt, alpha);

            // We could, of course, create an object using our more general-purpose
            // function, using m = n = 1.
            blis.bli_obj_create(dt, 1, 1, 0, 0, beta);

            // We can even attach an external scalar. This function, unlike
            // blis.bli_obj_create_1x1() and blis.bli_obj_create(), does not result in any
            // memory allocation.
            blis.bli_obj_create_1x1_with_attached_buffer(dt, arena, ptr(gamma_d), gamma );

            // There is one more way to create an object. Like the previous method,
            // it also avoids memory allocation by referencing a special "internal"
            // scalar that is invisibly part of every object.
            blis.bli_obj_scalar_init_detached(dt, kappa );

            // Digression: In the most common cases, there is no need to create scalar
            // objects to begin with. That's because BLIS comes with three ready-to-use
            // globally-scoped scalar objects:
            //
            //   obj_t BLIS_MINUS_ONE;
            //   obj_t BLIS_ZERO;
            //   obj_t BLIS ONE;
            //
            // Each of these special objects is provided by blis.h. They can be used
            // wherever a scalar object is expected as an input operand regardless of
            // the datatype of your other operands. Note that you should never try to
            // modify these global scalar objects directly, nor should you ever try to
            // perform an operation *on* the objects (that is, you should never try to
            // update their values, though you can always perform operations *with*
            // them--that's the whole point!).


            //
            // Example 2: Set the value of an existing scalar object.
            //

            System.out.print("\n#\n#  -- Example 2 --\n#\n\n");

            // Once you've created an object, you can set its value via setsc. As with
            // setijm, setsc takes a real and imaginary value, but you can ignore the
            // imaginary argument if your object is real. And even if you pass in a
            // non-zero value, it is ignored for real objects.
            blis.bli_setsc(-4.0, 0.0, alpha );
            blis.bli_setsc(3.0, 1.0, beta );
            blis.bli_setsc(0.5, 0.0, kappa );
            blis.bli_setsc(10.0, 0.0, gamma );

            // BLIS does not have a special print function for scalars, but since a
            // 1x1 is also a vector and a matrix, we can use printv or printm.
            blis.bli_printm("alpha:",  alpha, "% 4.3f", "" );
            blis.bli_printm("beta:",  beta, "% 4.3f", "" );
            blis.bli_printm("kappa:",  kappa, "% 4.3f", "" );
            blis.bli_printm("gamma:",  gamma, "% 4.3f", "" );


            //
            // Example 3: Create and set the value of a complex scalar object.
            //

            System.out.print("\n#\n#  -- Example 3 --\n#\n\n");

            // Create one more scalar, this time a complex scalar, to show how it
            // can be used.
            blis.bli_obj_create_1x1(BLIS_DCOMPLEX,  zeta );
            blis.bli_setsc(3.3, -4.4,  zeta );
            blis.bli_printm("zeta (complex):",  zeta, "% 4.3f", "" );


            //
            // Example 4: Copy scalar objects.
            //

            System.out.print("\n#\n#  -- Example 4 --\n#\n\n");

            // We can copy scalars amongst one another, and we can use the global
            // scalar constants for input operands.
            blis.bli_copysc( beta, gamma );
            blis.bli_printm("gamma (overwritten with beta):", gamma, "% 4.3f", "" );

            blis.bli_copysc(  BLIS_ONE.toptr(), gamma );
            blis.bli_printm("gamma (overwritten with BLIS_ONE):", gamma, "% 4.3f", "" );


            //
            // Example 5: Perform other operations on scalar objects.
            //

            System.out.print("\n#\n#  -- Example 5 --\n#\n\n");

            // BLIS defines a range of basic floating-point operations on scalars.
            blis.bli_addsc( beta, gamma );
            blis.bli_printm("gamma := gamma + beta",  gamma, "% 4.3f", "" );

            blis.bli_subsc(  alpha, gamma );
            blis.bli_printm("gamma := gamma - alpha",  gamma, "% 4.3f", "" );

            blis.bli_divsc(  kappa, gamma );
            blis.bli_printm("gamma := gamma / kappa", gamma, "% 4.3f", "" );

            blis.bli_sqrtsc(  gamma, gamma );
            blis.bli_printm("gamma := sqrt( gamma )",  gamma, "% 4.3f", "" );

            blis.bli_normfsc(  alpha, alpha );
            blis.bli_printm("alpha := normf( alpha ) # normf() = abs() in real domain.", alpha, "% 4.3f", "" );

            // Note that normfsc() allows complex input objects, but requires that the
            // output operand (the second operand) be a real object.
            blis.bli_normfsc(  zeta, alpha );
            blis.bli_printm("alpha := normf( zeta )  # normf() = complex modulus in complex domain.", alpha, "% 4.3f", "" )
            ;

            blis.bli_invertsc(  gamma, gamma );
            blis.bli_printm("gamma := 1.0 / gamma",  gamma, "%4.2f", "" );


            // Only free the objects that resulted in actual allocation.
            blis.bli_obj_free( alpha );
            blis.bli_obj_free( beta );
            blis.bli_obj_free( zeta );
        }
    }
}
