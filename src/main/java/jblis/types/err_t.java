package jblis.types;

import jpassport.enums.EnumInt;

public enum err_t implements EnumInt {
    // Generic error codes
    BLIS_SUCCESS                               (  -1),
    BLIS_FAILURE                               (  -2),

    BLIS_ERROR_CODE_MIN                        (  -9),

    // General errors
    BLIS_INVALID_ERROR_CHECKING_LEVEL          ( -10),
    BLIS_UNDEFINED_ERROR_CODE                  ( -11),
    BLIS_NULL_POINTER                          ( -12),
    BLIS_NOT_YET_IMPLEMENTED                   ( -13),
    BLIS_OUT_OF_BOUNDS                         ( -14),
    BLIS_LOCK_FAILURE                          ( -15),

    // Parameter-specific errors
    BLIS_INVALID_SIDE                          ( -20),
    BLIS_INVALID_UPLO                          ( -21),
    BLIS_INVALID_TRANS                         ( -22),
    BLIS_INVALID_CONJ                          ( -23),
    BLIS_INVALID_DIAG                          ( -24),
    BLIS_INVALID_MACHVAL                       ( -25),
    BLIS_EXPECTED_NONUNIT_DIAG                 ( -26),

    // Datatype-specific errors
    BLIS_INVALID_DATATYPE                      ( -30),
    BLIS_EXPECTED_FLOATING_POINT_DATATYPE      ( -31),
    BLIS_EXPECTED_NONINTEGER_DATATYPE          ( -32),
    BLIS_EXPECTED_NONCONSTANT_DATATYPE         ( -33),
    BLIS_EXPECTED_REAL_DATATYPE                ( -34),
    BLIS_EXPECTED_INTEGER_DATATYPE             ( -35),
    BLIS_INCONSISTENT_DATATYPES                ( -36),
    BLIS_EXPECTED_REAL_PROJ_OF                 ( -37),
    BLIS_EXPECTED_REAL_VALUED_OBJECT           ( -38),
    BLIS_INCONSISTENT_PRECISIONS               ( -39),

    // Dimension-specific errors
    BLIS_NONCONFORMAL_DIMENSIONS               ( -40),
    BLIS_EXPECTED_SCALAR_OBJECT                ( -41),
    BLIS_EXPECTED_VECTOR_OBJECT                ( -42),
    BLIS_UNEQUAL_VECTOR_LENGTHS                ( -43),
    BLIS_EXPECTED_SQUARE_OBJECT                ( -44),
    BLIS_UNEXPECTED_OBJECT_LENGTH              ( -45),
    BLIS_UNEXPECTED_OBJECT_WIDTH               ( -46),
    BLIS_UNEXPECTED_VECTOR_DIM                 ( -47),
    BLIS_UNEXPECTED_DIAG_OFFSET                ( -48),
    BLIS_NEGATIVE_DIMENSION                    ( -49),

    // Stride-specific errors
    BLIS_INVALID_ROW_STRIDE                    ( -50),
    BLIS_INVALID_COL_STRIDE                    ( -51),
    BLIS_INVALID_DIM_STRIDE_COMBINATION        ( -52),

    // Structure-specific errors
    BLIS_EXPECTED_GENERAL_OBJECT               ( -60),
    BLIS_EXPECTED_HERMITIAN_OBJECT             ( -61),
    BLIS_EXPECTED_SYMMETRIC_OBJECT             ( -62),
    BLIS_EXPECTED_TRIANGULAR_OBJECT            ( -63),

    // Storage-specific errors
    BLIS_EXPECTED_UPPER_OR_LOWER_OBJECT        ( -70),

    // Partitioning-specific errors
    BLIS_INVALID_3x1_SUBPART                   ( -80),
    BLIS_INVALID_1x3_SUBPART                   ( -81),
    BLIS_INVALID_3x3_SUBPART                   ( -82),

    // Control tree-specific errors
    BLIS_UNEXPECTED_NULL_CONTROL_TREE          ( -90),

    // Packing-specific errors
    BLIS_PACK_SCHEMA_NOT_SUPPORTED_FOR_UNPACK  (-100),
    BLIS_PACK_SCHEMA_NOT_SUPPORTED_FOR_PART    (-101),

    // Buffer-specific errors
    BLIS_EXPECTED_NONNULL_OBJECT_BUFFER        (-110),

    // Memory errors
    BLIS_MALLOC_RETURNED_NULL                  (-120),

    // Internal memory pool errors
    BLIS_INVALID_PACKBUF                       (-130),
    BLIS_EXHAUSTED_CONTIG_MEMORY_POOL          (-131),
    BLIS_INSUFFICIENT_STACK_BUF_SIZE           (-132),
    BLIS_ALIGNMENT_NOT_POWER_OF_TWO            (-133),
    BLIS_ALIGNMENT_NOT_MULT_OF_PTR_SIZE        (-134),

    // Object-related errors
    BLIS_EXPECTED_OBJECT_ALIAS                 (-140),

    // Architecture-related errors
    BLIS_INVALID_ARCH_ID                       (-150),
    BLIS_UNINITIALIZED_GKS_CNTX                (-151),
    BLIS_INVALID_UKR_ID                        (-152),

    // Blocksize-related errors
    BLIS_MC_DEF_NONMULTIPLE_OF_MR              (-160),
    BLIS_MC_MAX_NONMULTIPLE_OF_MR              (-161),
    BLIS_NC_DEF_NONMULTIPLE_OF_NR              (-162),
    BLIS_NC_MAX_NONMULTIPLE_OF_NR              (-163),
    BLIS_KC_DEF_NONMULTIPLE_OF_KR              (-164),
    BLIS_KC_MAX_NONMULTIPLE_OF_KR              (-165),
    BLIS_MR_NOT_EVEN_FOR_REAL_TYPE             (-166),
    BLIS_PACKMR_NOT_EVEN_FOR_REAL_TYPE         (-167),
    BLIS_NR_NOT_EVEN_FOR_REAL_TYPE             (-168),
    BLIS_PACKNR_NOT_EVEN_FOR_REAL_TYPE         (-169),

    BLIS_ERROR_CODE_MAX                        (-170);

    private final int value;

    err_t(int v)
    {
        value = v;
    }


    @Override
    public int getCValue() {
        return value;
    }
}
