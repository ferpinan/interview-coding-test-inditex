package ga.ferpinan.pricesrestapi.controller;

import ga.ferpinan.pricesrestapi.exception.PriceNotFoundException;
import ga.ferpinan.pricesrestapi.model.Price;
import ga.ferpinan.pricesrestapi.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    private static final long BRAND_ID = 1L;
    private static final long PRODUCT_ID = 135455L;
    private static final String INCORRECT_INPUT = "incorrectInput";
    private static final String PRIOR_PRICE_URL_TEMPLATE = "/api/price/%s/%s?date=%s";

    @Autowired
    private MockMvc mockMvc;

	@MockBean
    private PriceService priceService;

    @Test
    void whenFindPriorPrice_withCorrectInputAndCorrectResult_thenReturn200() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 13, 0, 0);

        Price price = Price.builder().build();
        when(priceService.findPriorPrice(BRAND_ID, PRODUCT_ID, localDateTime)).thenReturn(price);

        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, "2020-06-14T13:00:00Z");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertEquals(HttpServletResponse.SC_OK, result.getResponse().getStatus());
    }

    @Test
    void whenFindPriorPrice_withCorrectInputAndDataNotFound_thenReturn404() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 13, 0, 0);

        when(priceService.findPriorPrice(BRAND_ID, PRODUCT_ID, localDateTime)).thenThrow(new PriceNotFoundException());

        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, BRAND_ID, PRODUCT_ID, "2020-06-14T13:00:00Z");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

        assertEquals(HttpServletResponse.SC_NOT_FOUND, result.getResponse().getStatus());
    }

    @Test
    void whenFindPriorPrice_withIncorrectInput_thenReturn400() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 13, 0, 0);

        when(priceService.findPriorPrice(BRAND_ID, PRODUCT_ID, localDateTime)).thenThrow(new PriceNotFoundException());

        String url = String.format(PRIOR_PRICE_URL_TEMPLATE, INCORRECT_INPUT, PRODUCT_ID, "2020-06-14T13:00:00Z");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();

        assertEquals(HttpServletResponse.SC_BAD_REQUEST, result.getResponse().getStatus());
    }
}