package com.example.pokemon.Repository;

import com.example.pokemon.Model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, JpaSpecificationExecutor<ProductEntity> {

    // Thêm phương thức đếm sản phẩm sắp hết hàng
    @Query("SELECT COUNT(p) FROM ProductEntity p WHERE p.quantity < :threshold")
    long countByQuantityLessThan(@Param("threshold") int threshold);

    // Thêm phương thức tìm sản phẩm sắp hết hàng
    @Query("SELECT p FROM ProductEntity p WHERE p.quantity < :threshold")
    List<ProductEntity> findByQuantityLessThan(@Param("threshold") int threshold);

    // Phân trang
    Page<ProductEntity> findAll(Pageable pageable);

    // ... các phương thức hiện có  quantity 50...
    List<ProductEntity> findTop6ByQuantityLessThanEqualOrderByQuantityAsc(int maxQuantity);

}
