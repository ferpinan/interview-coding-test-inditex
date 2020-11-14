package ga.ferpinan.pricesrestapi.model;

import ga.ferpinan.pricesrestapi.dto.PriceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "prices")
public class Price implements Serializable {

    @Id
    @SequenceGenerator(name = "price_list_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "price_list_id_seq")
    @Column(name = "PRICE_LIST", unique = true, nullable = false)
    private Long priceList;

    @Column(name = "BRAND_ID", nullable = false)
    @NotNull
    @NotEmpty
    private Long brandId;

    @Column(name = "START_DATE", nullable = false)
    @NotNull
    @NotEmpty
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    @NotNull
    @NotEmpty
    private LocalDateTime endDate;

    @Column(name = "PRODUCT_ID", nullable = false)
    @NotNull
    @NotEmpty
    private Long productId;

    @Column(name = "PRIORITY", nullable = false)
    @NotNull
    @NotEmpty
    private Long priority;

    @Column(name = "PRICE", nullable = false)
    @NotNull
    @NotEmpty
    private BigDecimal price;

    @Column(name = "CURRENCY", length = 20, nullable = false)
    @Size(max = 20)
    private String currency;

    public PriceDto toDto() {
        return PriceDto.builder()
                .priceList(priceList)
                .brandId(brandId)
                .startDate(startDate)
                .endDate(endDate)
                .productId(productId)
                .price(price)
                .currency(currency)
                .build();
    }
}