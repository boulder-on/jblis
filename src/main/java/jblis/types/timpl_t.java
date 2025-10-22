package jblis.types;

public enum timpl_t {
    BLIS_SINGLE,
    BLIS_OPENMP,
    BLIS_POSIX,
    BLIS_HPX,

    // BLIS_NUM_THREAD_IMPLS must be last!
    BLIS_NUM_THREAD_IMPLS
}
