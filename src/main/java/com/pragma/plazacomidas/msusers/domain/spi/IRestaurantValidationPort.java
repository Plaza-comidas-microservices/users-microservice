package com.pragma.plazacomidas.msusers.domain.spi;

public interface IRestaurantValidationPort {
    boolean isOwnerOfRestaurant(Long restaurantId, Long ownerId);
}
