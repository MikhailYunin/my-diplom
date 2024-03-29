package com.restik.mydiplom.repositories;

import com.restik.mydiplom.entity.Reserve;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ReserveRepository extends CrudRepository<Reserve, Integer> {
    @Query(value = "select * from reserve where table_id in " +
            "(select table_id from tables where restaurant_id = :restaurantId)", nativeQuery = true)
    Collection<Reserve> findByRestaurantId(int restaurantId);
    @Query(value = "select * from reserve where table_id in " +
            "(select table_id from tables where restaurant_id in (SELECT restaurant_id from Restaurant WHERE user_id =:adminId))", nativeQuery = true)
    Collection<Reserve> findReserveByAdminId(Integer adminId);

//    @Modifying
//    @Query("delete from Reserve where reserve_id =:reserveId")
    void deleteReserveById(int reserve_id);



}
