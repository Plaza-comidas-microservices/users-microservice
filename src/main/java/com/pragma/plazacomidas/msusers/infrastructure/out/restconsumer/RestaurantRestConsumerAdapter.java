package com.pragma.plazacomidas.msusers.infrastructure.out.restconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.pragma.plazacomidas.msusers.domain.exception.DomainException;
import com.pragma.plazacomidas.msusers.domain.spi.IRestaurantValidationPort;

@Component
public class RestaurantRestConsumerAdapter implements IRestaurantValidationPort {

    private final RestTemplate restTemplate;
    private final String mallServiceUrl;

    public RestaurantRestConsumerAdapter(RestTemplate restTemplate,
                                          @Value("${mall.service.url}") String mallServiceUrl) {
        this.restTemplate = restTemplate;
        this.mallServiceUrl = mallServiceUrl;
    }

    @Override
    public boolean isOwnerOfRestaurant(Long restaurantId, Long ownerId) {
        try {
            String url = mallServiceUrl + "/api/v1/restaurant/" + restaurantId;
            RestaurantValidationResponse response = restTemplate.getForObject(url, RestaurantValidationResponse.class);
            return response != null && ownerId.equals(response.getOwnerId());
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (ResourceAccessException e) {
            throw new DomainException("No se pudo validar el restaurante, el servicio parece no estar disponible ahora");
        }
    }
}
