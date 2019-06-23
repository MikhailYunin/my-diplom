package com.restik.mydiplom.repositories;

import com.restik.mydiplom.entity.Tables;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TableRepository extends CrudRepository<Tables, Integer> {
// Версия запроса 1, рабочая
//    @Query(value = "SELECT * FROM Tables WHERE restaurant_id =:restaurantId AND visitors_volume =:visitorsVolume " +
//            "AND table_id NOT IN (SELECT table_id FROM Reserve WHERE reserve_start " +
//            "BETWEEN :dateReserveDeltaMinus AND :dateReserveDeltaPlus ) limit 1  ",nativeQuery = true)
//    Optional<Tables> findFreeTable(Integer restaurantId, Integer visitorsVolume,
//                                    LocalDateTime dateReserveDeltaMinus, LocalDateTime dateReserveDeltaPlus);
// Версия запроса 2
    @Query(value = "SELECT min(visitors_volume) as visitors_volume, table_id, table_num, restaurant_id " +
            "FROM Tables WHERE restaurant_id =:restaurantId AND visitors_volume >=:visitorsVolume  AND table_id " +
            "NOT IN(SELECT table_id FROM Reserve WHERE reserve_start BETWEEN :dateReserveDeltaMinus AND :dateReserveDeltaPlus) group by table_id limit 1", nativeQuery = true)
    Optional<Tables> findFreeTable(Integer restaurantId, Integer visitorsVolume,
                                   LocalDateTime dateReserveDeltaMinus, LocalDateTime dateReserveDeltaPlus);





}
