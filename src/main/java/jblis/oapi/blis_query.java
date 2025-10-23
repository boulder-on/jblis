package jblis.oapi;

import jblis.types.arch_t;
import jblis.types.ind_t;
import jblis.types.num_t;
import jpassport.Passport;

public interface blis_query extends Passport {
    String bli_info_get_int_type_size_str(  );
    String bli_info_get_version_str(  );
    arch_t bli_arch_query_id(  );

    String bli_arch_string( arch_t id );

    long bli_info_get_int_type_size(  );
    long bli_info_get_num_fp_types(  );
    long bli_info_get_max_type_size(  );
    long bli_info_get_page_size(  );
    long bli_info_get_simd_num_registers(  );
    long bli_info_get_simd_size(  );
    long bli_info_get_simd_align_size(  );
    long bli_info_get_stack_buf_max_size(  );
    long bli_info_get_stack_buf_align_size(  );
    long bli_info_get_heap_addr_align_size(  );
    long bli_info_get_heap_stride_align_size(  );
    long bli_info_get_pool_addr_align_size_a(  );
    long bli_info_get_pool_addr_align_size_b(  );
    long bli_info_get_pool_addr_align_size_c(  );
    long bli_info_get_pool_addr_align_size_gen(  );
    long bli_info_get_pool_addr_offset_size_a(  );
    long bli_info_get_pool_addr_offset_size_b(  );
    long bli_info_get_pool_addr_offset_size_c(  );
    long bli_info_get_pool_addr_offset_size_gen(  );
//    long bli_info_get_enable_stay_auto_init(  );
    long bli_info_get_enable_blas(  );
    long bli_info_get_enable_cblas(  );
    long bli_info_get_blas_int_type_size(  );
    long bli_info_get_enable_pba_pools(  );
    long bli_info_get_enable_sba_pools(  );
    long bli_info_get_enable_threading(  );
    long bli_info_get_enable_openmp(  );
    long bli_info_get_enable_pthreads(  );
    long bli_info_get_enable_hpx(  );
    long bli_info_get_enable_openmp_as_default(  );
    long bli_info_get_enable_pthreads_as_default(  );
    long bli_info_get_enable_hpx_as_default(  );
    long bli_info_get_thread_jrir_slab(  );
    long bli_info_get_thread_jrir_rr(  );
    long bli_info_get_thread_jrir_tlb(  );
    long bli_info_get_enable_tls(  );
    long bli_info_get_enable_memkind(  );
    long bli_info_get_enable_sandbox(  );

    //String bli_error_string_for_code(long code);
    String bli_info_get_gemm_ukr_impl_string(ind_t method, num_t dt );
    String bli_info_get_gemmtrsm_l_ukr_impl_string( ind_t method, num_t dt );
    String bli_info_get_gemmtrsm_u_ukr_impl_string( ind_t method, num_t dt );
    String bli_info_get_trsm_l_ukr_impl_string( ind_t method, num_t dt );
    String bli_info_get_trsm_u_ukr_impl_string( ind_t method, num_t dt );

    String bli_info_get_gemm_impl_string( num_t dt );
    String bli_info_get_gemmt_impl_string( num_t dt );
    String bli_info_get_hemm_impl_string( num_t dt );
    String bli_info_get_herk_impl_string( num_t dt );
    String bli_info_get_her2k_impl_string( num_t dt );
    String bli_info_get_symm_impl_string( num_t dt );
    String bli_info_get_syrk_impl_string( num_t dt );
    String bli_info_get_syr2k_impl_string( num_t dt );
    String bli_info_get_trmm_impl_string( num_t dt );
    String bli_info_get_trmm3_impl_string( num_t dt );
    String bli_info_get_trsm_impl_string( num_t dt );

    double bli_clock(  );
    double bli_clock_min_diff(double  time_prev_min, double  time_start);
}
