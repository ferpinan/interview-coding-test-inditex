package ga.ferpinan.pricesrestapi.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import ga.ferpinan.pricesrestapi.dto.PriceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    private static final long BRAND_ID = 1L;
    private static final long PRODUCT_ID = 35455;
    private static final String PRIOR_PRICE_URL_TEMPLATE = "/api/price/%s/%s?date=%s";
    private static final String EUR_CURRENCY = "EUR";
    private static final long NON_EXISTING_PRODUCT = 1111L;
    private static final long NON_EXISTING_BRAND = 55L;

    @Autowired
    private MockMvc mockMvc;

    private Gson gson;

    @BeforeEach
    void setUp() {
        // this configuration will make gson parse LocalDateTime object
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                LocalDateTime.parse(json.getAsJsonPrimitive().getAsString())).create();
    }

    @Test
    void whenFindPrior_withExistingBrandExistingProductDate20200614100000_thenReturnPriceWithPriceList1() throws Exception {
        String applicationDate = "2020-06-14T13:00:00Z";
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, applicationDate);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        PriceDto priceDto = gson.fromJson(contentAsString, PriceDto.class);

        assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());

        assertPriceWithId1(priceDto);
    }

    @Test
    void whenFindPrior_withExistingBrandExistingProductDate20200614160000_thenReturnPriceWithPriceList2() throws Exception {
        String applicationDate = "2020-06-14T16:00:00Z";
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, applicationDate);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        PriceDto priceDto = gson.fromJson(contentAsString, PriceDto.class);

        assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        assertPriceWithId2(priceDto);
    }

    @Test
    void whenFindPrior_withExistingBrandExistingProductDate20200614210000_thenReturnPriceWithPriceList1() throws Exception {
        String applicationDate = "2020-06-14T21:00:00Z";
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, applicationDate);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        PriceDto priceDto = gson.fromJson(contentAsString, PriceDto.class);

        assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        assertPriceWithId1(priceDto);
    }

    @Test
    void whenFindPrior_withExistingBrandExistingProductDate20200615100000_thenReturnPriceWithPriceList3() throws Exception {
        String applicationDate = "2020-06-15T10:00:00Z";
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, applicationDate);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        PriceDto priceDto = gson.fromJson(contentAsString, PriceDto.class);

        assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        assertPriceWithId3(priceDto);
    }

    @Test
    void whenFindPrior_withExistingBrandExistingProductDate20200615210000_thenReturnPriceWithPriceList4() throws Exception {
        String applicationDate = "2020-06-15T21:00:00Z";
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, applicationDate);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        PriceDto priceDto = gson.fromJson(contentAsString, PriceDto.class);

        assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        assertPriceWithId4(priceDto);
    }

    @Test
    void whenFindPrior_withExistingBrandExistingProductDate20200614000000_thenReturnPriceWithPriceList1() throws Exception {
        String applicationDate = "2020-06-14T00:00:00Z";
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, applicationDate);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        PriceDto priceDto = gson.fromJson(contentAsString, PriceDto.class);

        assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        assertPriceWithId1(priceDto);
    }

    @Test
    void whenFindPrior_withExistingBrandExistingProductDate20201231235959_thenReturnPriceWithPriceList4() throws Exception {
        String applicationDate = "2020-12-31T23:59:59Z";
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, applicationDate);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        PriceDto priceDto = gson.fromJson(contentAsString, PriceDto.class);

        assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
        assertPriceWithId4(priceDto);
    }

    @Test
    void whenFindPrior_withDateBeforeAnyExisting_thenReturnNotFound() throws Exception {
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, "2019-06-14T13:00:00Z");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

        assertEquals(HttpServletResponse.SC_NOT_FOUND, result.getResponse().getStatus());
    }

    @Test
    void whenFindPrior_withDateAfterAnyExisting_thenReturnNotFound() throws Exception {
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, "2021-06-14T13:00:00Z");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

        assertEquals(HttpServletResponse.SC_NOT_FOUND, result.getResponse().getStatus());
    }

    @Test
    void whenFindPrior_withNonExistingBrand_thenReturnNotFound() throws Exception {
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, NON_EXISTING_BRAND, PRODUCT_ID, "2020-06-14T13:00:00Z");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

        assertEquals(HttpServletResponse.SC_NOT_FOUND, result.getResponse().getStatus());
    }

    @Test
    void whenFindPrior_withNonExistingProduct_thenReturnNotFound() throws Exception {
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, NON_EXISTING_PRODUCT, "2020-06-14T13:00:00Z");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

        assertEquals(HttpServletResponse.SC_NOT_FOUND, result.getResponse().getStatus());
    }

    @Test
    void whenFindPrior_withIncorrectInput_thenReturnNotFound() throws Exception {
        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, "202aaaa0-06-14T13:00:00Z");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();

        assertEquals(HttpServletResponse.SC_BAD_REQUEST, result.getResponse().getStatus());
    }


    private void assertPriceWithId1(PriceDto priceDto) {
        assertEquals(BRAND_ID, priceDto.getBrandId());
        assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), priceDto.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), priceDto.getEndDate());
        assertEquals(1, priceDto.getPriceList());
        assertEquals(PRODUCT_ID, priceDto.getProductId());
        assertEquals(BigDecimal.valueOf(35.5), priceDto.getValue());
        assertEquals(EUR_CURRENCY, priceDto.getCurrency());
    }

    private void assertPriceWithId2(PriceDto priceDto) {
        assertEquals(BRAND_ID, priceDto.getBrandId());
        assertEquals(LocalDateTime.parse("2020-06-14T15:00:00"), priceDto.getStartDate());
        assertEquals(LocalDateTime.parse("2020-06-14T18:30:00"), priceDto.getEndDate());
        assertEquals(2, priceDto.getPriceList());
        assertEquals(PRODUCT_ID, priceDto.getProductId());
        assertEquals(BigDecimal.valueOf(25.45), priceDto.getValue());
        assertEquals(EUR_CURRENCY, priceDto.getCurrency());
    }

    private void assertPriceWithId3(PriceDto priceDto) {
        assertEquals(BRAND_ID, priceDto.getBrandId());
        assertEquals(LocalDateTime.parse("2020-06-15T00:00:00"), priceDto.getStartDate());
        assertEquals(LocalDateTime.parse("2020-06-15T11:00:00"), priceDto.getEndDate());
        assertEquals(3, priceDto.getPriceList());
        assertEquals(PRODUCT_ID, priceDto.getProductId());
        assertEquals(BigDecimal.valueOf(30.5), priceDto.getValue());
        assertEquals(EUR_CURRENCY, priceDto.getCurrency());
    }

    private void assertPriceWithId4(PriceDto priceDto) {
        assertEquals(BRAND_ID, priceDto.getBrandId());
        assertEquals(LocalDateTime.parse("2020-06-15T16:00:00"), priceDto.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), priceDto.getEndDate());
        assertEquals(4, priceDto.getPriceList());
        assertEquals(PRODUCT_ID, priceDto.getProductId());
        assertEquals(BigDecimal.valueOf(38.95), priceDto.getValue());
        assertEquals(EUR_CURRENCY, priceDto.getCurrency());
    }
}
