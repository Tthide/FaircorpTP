package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dto.ApiGouvAdressDto;
import com.emse.spring.faircorp.dto.ApiGouvFeatureDto;
import com.emse.spring.faircorp.dto.ApiGouvResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController // (1)
@RequestMapping("/api/address") // (2)
@Transactional // (3)
@CrossOrigin
@Service
public class AdressSearchService {


    private RestTemplate restTemplate;

    /*
        @Bean
        public void SearchService(RestTemplateBuilder restTemplateBuilder) {

            this.restTemplate=restTemplateBuilder
                    .rootUri("https://api-adresse.data.gouv.fr")
                    .build();
        }
    */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder
                .build();
    }

    @GetMapping
    @Secured("ROLE_ADMIN") // (1)
    public List<ApiGouvAdressDto> findAdress(String criteria) {
        String params = criteria.replaceAll("\\s*\\s* ", "+");
        RestTemplate restTemplate = restTemplate(new RestTemplateBuilder());

        String uri = UriComponentsBuilder.fromUriString("/search")
                .queryParam("q", params)
                .queryParam("limit", 15)
                .build().toUriString();
        uri = "https://api-adresse.data.gouv.fr" + uri;

        System.out.println(uri);
        restTemplate = restTemplate(new RestTemplateBuilder());
        ApiGouvResponseDto Response = restTemplate
                .getForObject(uri, ApiGouvResponseDto.class);
        List<ApiGouvAdressDto> adresses = new ArrayList<ApiGouvAdressDto>();
        List<ApiGouvFeatureDto> apiGouvFeatureDto = Response.getFeatures();

        for (int j = 0; j < apiGouvFeatureDto.size(); j++) {
            adresses.add(apiGouvFeatureDto
                    .get(j)
                    .getProperties());
        }


        return adresses;
    }


}
