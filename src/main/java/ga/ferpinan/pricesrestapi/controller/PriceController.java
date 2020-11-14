package ga.ferpinan.pricesrestapi.controller;

import ga.ferpinan.pricesrestapi.dto.PriceDto;
import ga.ferpinan.pricesrestapi.service.PriceService;
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

@Validated
@RestController
@RequestMapping("/api/price")
@RequiredArgsConstructor
@Log4j2
public class PriceController {

	private final PriceService priceService;

	@GetMapping(value = "/{brandId}/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PriceDto findPriorPrice(
            @PathVariable(value = "brandId") @Valid @NotNull Long brandId,
            @PathVariable(value = "productId") @Valid @NotNull Long productId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(value = "date") @Valid @NotNull LocalDateTime dateBetweenStartAndEndDate
    ) {
	    log.debug("PriceController::findPriorPrice -> Called with brandId {}, productId {} and date {}", brandId, productId, dateBetweenStartAndEndDate);
		return priceService.findPriorPrice(brandId, productId, dateBetweenStartAndEndDate).toDto();
	}

}