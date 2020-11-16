package ga.ferpinan.pricesrestapi.controller;

import ga.ferpinan.pricesrestapi.dto.PriceDto;
import ga.ferpinan.pricesrestapi.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Controller for the management of Prices
 */
@Validated
@RestController
@RequestMapping("/api/price")
@RequiredArgsConstructor
@Log4j2
public class PriceController {

    private final PriceService priceService;

    /**
     * Get the price of a product by its id and brand's id on the given date
     * @param brandId Id of the brand
     * @param productId Id of the product
     * @param dateBetweenStartAndEndDate Date of the price to apply to the product
     * @return Returns the price with most priority in the given date for provided brandId and productId.<br/>
     * Returns 404 if price is not found <br/>
     * Returns 400 if input data is incorrect
     */
    @Operation(summary = "Get the price of a product by its id and brand's id on the given date")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the price",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PriceDto.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input supplied",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Price not found",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping(value = "/{brandId}/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PriceDto findPriorPrice(
            @Parameter(name = "brandId", description = "Id of the brand", example = "1", required = true)
            @PathVariable(value = "brandId") @Valid @NotNull Long brandId,
            @Parameter(name = "productId", description = "Id of the product", example = "35455", required = true)
            @PathVariable(value = "productId") @Valid @NotNull Long productId,
            @Parameter(name = "dateBetweenStartAndEndDate", description = "Date of the price to apply to the product", example = "2020-06-14T13:00:00Z", required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(value = "date") @Valid @NotNull LocalDateTime dateBetweenStartAndEndDate
    ) {
        log.debug("PriceController::findPriorPrice -> Called with brandId {}, productId {} and date {}", brandId, productId, dateBetweenStartAndEndDate);
        return priceService.findPriorPrice(brandId, productId, dateBetweenStartAndEndDate).toDto();
    }

}