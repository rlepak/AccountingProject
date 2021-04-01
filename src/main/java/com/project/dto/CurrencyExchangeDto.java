package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class CurrencyExchangeDto {


    public Map<String, Object> consumeCurrentCurrency(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.exchangeratesapi.io/latest?base=USD";

        Object currentCurrency = restTemplate.getForObject(url, Object.class);

        Map<String, Object> getCurrency = (Map<String, Object>) currentCurrency;

        Map<String, Object> getValue = (Map<String, Object>) getCurrency.get("rates");

        return getValue;
    }

    public Map<String, Object> consumePreviewsCurrency(){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.exchangeratesapi.io/history?start_at="+LocalDate.now().minusDays(1)+"&end_at="+LocalDate.now().minusDays(1)+"&base=USD";

        Object previewsCurrency = restTemplate.getForObject(url, Object.class);

        Map<String, Object> getCurrency = (Map<String, Object>) previewsCurrency;

        Map<String, Object> getValue = (Map<String, Object>) getCurrency.get("rates");

        Map<String, Object> getDate = (Map<String, Object>) getValue.get(LocalDate.now().minusDays(1).toString());

        return getDate;
    }
}
