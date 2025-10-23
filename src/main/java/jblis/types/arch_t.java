package jblis.types;

public enum arch_t {
    // NOTE: The C language standard guarantees that the first enum value
    // starts at 0.

    // Intel
    BLIS_ARCH_SKX,
    BLIS_ARCH_KNL,
    BLIS_ARCH_KNC,
    BLIS_ARCH_HASWELL,
    BLIS_ARCH_SANDYBRIDGE,
    BLIS_ARCH_PENRYN,

    // AMD
    BLIS_ARCH_ZEN3,
    BLIS_ARCH_ZEN2,
    BLIS_ARCH_ZEN,
    BLIS_ARCH_EXCAVATOR,
    BLIS_ARCH_STEAMROLLER,
    BLIS_ARCH_PILEDRIVER,
    BLIS_ARCH_BULLDOZER,

    // ARM-SVE
    BLIS_ARCH_ARMSVE,
    BLIS_ARCH_A64FX,

    // ARM-NEON (4 pipes x 128-bit vectors)
    BLIS_ARCH_ALTRAMAX,
    BLIS_ARCH_ALTRA,
    BLIS_ARCH_FIRESTORM,

    // ARM (2 pipes x 128-bit vectors)
    BLIS_ARCH_THUNDERX2,
    BLIS_ARCH_CORTEXA57,
    BLIS_ARCH_CORTEXA53,

    // ARM 32-bit (vintage)
    BLIS_ARCH_CORTEXA15,
    BLIS_ARCH_CORTEXA9,

    // IBM/Power
    BLIS_ARCH_POWER10,
    BLIS_ARCH_POWER9,
    BLIS_ARCH_POWER7,
    BLIS_ARCH_BGQ,

    // RISC-V
    BLIS_ARCH_RV32I,
    BLIS_ARCH_RV64I,
    BLIS_ARCH_RV32IV,
    BLIS_ARCH_RV64IV,

    // SiFive
    BLIS_ARCH_SIFIVE_RVV,
    BLIS_ARCH_SIFIVE_X280,

    // Generic architecture/configuration
    BLIS_ARCH_GENERIC,

    // The total number of defined architectures. This must be last in the
    // list of enums since its definition assumes that the previous enum
    // value (BLIS_ARCH_GENERIC) is given index num_archs-1.
    BLIS_NUM_ARCHS

}
