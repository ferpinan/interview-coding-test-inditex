package ga.ferpinan.pricesrestapi.service;

import ga.ferpinan.pricesrestapi.exception.PriceNotFoundException;
import ga.ferpinan.pricesrestapi.model.Price;
import ga.ferpinan.pricesrestapi.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que implementa los métodos expuestos en la interfaz RoleService
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class PriceService {

	private final PriceRepository priceRepository;

	@Transactional
	public Price findPriorPrice(Long brandId, Long productId, LocalDateTime dateBetweenStartAndEndDate) {
        log.debug("PriceService::findPriorPrice -> Called with brandId {}, productId {} and date {}", brandId, productId, dateBetweenStartAndEndDate);
        List<Price> priorPriceList = priceRepository.findPriorPriceList(brandId, productId, dateBetweenStartAndEndDate, PageRequest.of(0, 1));
        if(priorPriceList.isEmpty()){
            String errorMessage = String.format("PriceService::findPriorPrice -> Price for brand %s, product %s for date %s not found", brandId, productId, dateBetweenStartAndEndDate);
            log.error(errorMessage);
            throw new PriceNotFoundException(errorMessage);
        }
        return priorPriceList.get(0);
	}

}