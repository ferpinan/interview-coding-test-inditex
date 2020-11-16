package ga.ferpinan.pricesrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Object for the Price Model")
public class PriceDto implements Serializable {

    @Schema(description = "Price identifier", example = "1")
    @NotNull
    @NotEmpty
    private Long priceList;

    @Schema(description = "Id of the brand (Foreign key)", example = "1")
    @NotNull
    @NotEmpty
    private Long brandId;

    @Schema(description = "Start date when the price will apply to the product", example = "2020-06-11T12:00:00")
    @NotNull
    @NotEmpty
    private LocalDateTime startDate;

    @Schema(description = "End date when the price will apply to the product", example = "2020-06-13T12:00:00")
    @NotNull
    @NotEmpty
    private LocalDateTime endDate;

    @Schema(description = "Id of the product (Foreign key)", example = "34355")
    @NotNull
    @NotEmpty
    private Long productId;

    @Schema(description = "Price of the product", example = "34.55")
    @NotNull
    @NotEmpty
    private BigDecimal price;

    @Schema(description = "Currency for the price", example = "EUR", required = true)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String currency;
}