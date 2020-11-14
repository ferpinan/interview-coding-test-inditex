package ga.ferpinan.pricesrestapi.repository;

import ga.ferpinan.pricesrestapi.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PriceRepositoryTest {

    private static final long BRAND_ID = 1L;
    private static final long PRODUCT_ID = 135455L;
    private static final long NON_EXISTING_BRAND_ID = 2L;
    private static final long NON_EXISTING_PRODUCT_ID = 111L;

    @Autowired
    private PriceRepository priceRepository;

    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withExistingBrandExistingProductDate20200614100000_thenReturnPriceWithPriceList10001() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        List<Price> priceOptional = priceRepository.findPriorPriceList(BRAND_ID, PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertEquals(10001, priceOptional.get(0).getPriceList());
    }

    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withExistingBrandExistingProductDate20200614160000_thenReturnPriceWithPriceList10002() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        List<Price> priceOptional = priceRepository.findPriorPriceList(BRAND_ID, PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertEquals(10002, priceOptional.get(0).getPriceList());
    }

    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withExistingBrandExistingProductDate20200614210000_thenReturnPriceWithPriceList10001() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        List<Price> priceOptional = priceRepository.findPriorPriceList(BRAND_ID, PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertEquals(10001, priceOptional.get(0).getPriceList());
    }

    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withExistingBrandExistingProductDate20200615100000_thenReturnPriceWithPriceList10003() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        List<Price> priceOptional = priceRepository.findPriorPriceList(BRAND_ID, PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertEquals(10003, priceOptional.get(0).getPriceList());
    }

    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withExistingBrandExistingProductDate20200615210000_thenReturnPriceWithPriceList10004() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 15, 21, 0, 0);
        List<Price> priceOptional = priceRepository.findPriorPriceList(BRAND_ID, PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertEquals(10004, priceOptional.get(0).getPriceList());
    }

    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withExistingBrandExistingProductDate20200614000000_thenReturnPriceWithPriceList10001() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        List<Price> priceOptional = priceRepository.findPriorPriceList(BRAND_ID, PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertEquals(10001, priceOptional.get(0).getPriceList());
    }

    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withExistingBrandExistingProductDate20201231235959_thenReturnPriceWithPriceList10004() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        List<Price> priceOptional = priceRepository.findPriorPriceList(BRAND_ID, PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertEquals(10004, priceOptional.get(0).getPriceList());
    }

    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withDateBeforeAnyExisting_thenReturnEmptyList() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 14, 0, 0, 0);
        List<Price> priceOptional = priceRepository.findPriorPriceList(BRAND_ID, PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertTrue(priceOptional.isEmpty());
    }
    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withDateAfterAnyExisting_thenReturnEmptyList() {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 14, 0, 0, 0);
        List<Price> priceOptional = priceRepository.findPriorPriceList(BRAND_ID, PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertTrue(priceOptional.isEmpty());
    }

    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withNonExistingBrand_thenReturnEmptyList() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 15, 21, 0, 0);
        List<Price> priceOptional = priceRepository.findPriorPriceList(NON_EXISTING_BRAND_ID, PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertTrue(priceOptional.isEmpty());
    }

    @Test
    @Sql("classpath:createPrices.sql")
    void whenFindPrior_withNonExistingProduct_thenReturnEmptyList() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 15, 21, 0, 0);
        List<Price> priceOptional = priceRepository.findPriorPriceList(BRAND_ID, NON_EXISTING_PRODUCT_ID, localDateTime, PageRequest.of(0,1));
        assertNotNull(priceOptional);
        assertTrue(priceOptional.isEmpty());
    }

}
