package ga.ferpinan.pricesrestapi.repository;

import ga.ferpinan.pricesrestapi.model.Price;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.brandId = :brandId AND p.productId = :productId AND :localDateTime1 BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
    List<Price> findPriorPriceList(Long brandId, Long productId, LocalDateTime localDateTime1, Pageable pageable);
}