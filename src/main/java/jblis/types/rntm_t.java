package jblis.types;

import jpassport.annotations.Array;

import static jblis.constants.blisHiddenConstants.BLIS_NUM_LOOPS;

public record rntm_t(
        // "External" fields: these may be queried by the end-user.
//        timpl_t   thread_impl, //todo fix when enums work in structs
        int   thread_impl, //todo fix when enums work in structs
        boolean      auto_factor,

        long     num_threads,
        @Array(length=6) long[]     thrloop,
        boolean      pack_a, // enable/disable packing of left-hand matrix A.
        boolean      pack_b, // enable/disable packing of right-hand matrix B.
        boolean      l3_sup // enable/disable small matrix handling in level-3 ops.
) {
}
