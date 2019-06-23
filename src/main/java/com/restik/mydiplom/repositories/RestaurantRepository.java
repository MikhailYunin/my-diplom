package com.restik.mydiplom.repositories;

import com.restik.mydiplom.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    @Query(value = "SELECT * FROM Restaurant WHERE user_id =:adminId", nativeQuery = true)
    Collection<Restaurant> findRestaurantByAdminId(int adminId);
}
