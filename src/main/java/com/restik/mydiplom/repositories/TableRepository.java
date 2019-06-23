/*
package com.restik.mydiplom.repositories;

import com.restik.mydiplom.entity.Tables;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TableRepository extends CrudRepository<Tables, Integer> {

    @Query(value = "SELECT * FROM Tables WHERE restaurant_id =:restaurantId AND visitors_volume =:visitorsVolume " +
            "AND table_id NOT IN (SELECT table_id FROM Reserve WHERE reserve_start " +
            "BETWEEN :dateReserveDeltaMinus AND :dateReserveDeltaPlus ) limit 1  ",nativeQuery = true)
    Optional<Tables> findFreeTable(Integer restaurantId, Integer visitorsVolume,
                                    LocalDateTime dateReserveDeltaMinus, LocalDateTime dateReserveDeltaPlus);

}
*/
