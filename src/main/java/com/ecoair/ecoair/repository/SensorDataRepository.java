package com.ecoair.ecoair.repository;

import com.ecoair.ecoair.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    List<SensorData> findByMac(String mac);

    List<SensorData> findByMacAndTimestampBetween(String mac, LocalDateTime startTime, LocalDateTime endTime);

    List<SensorData> findByGasType(String gasType);

    // ✅ Busca o último registro (pelo ID ou timestamp, se existir)
    Optional<SensorData> findTopByMacOrderByIdDesc(String mac);

    @Query("SELECT s FROM SensorData s WHERE s.mac = :mac ORDER BY s.timestamp DESC")
    List<SensorData> findLatestByMac(@Param("mac") String mac);

    @Query("SELECT s FROM SensorData s WHERE s.timestamp >= :startTime AND s.timestamp <= :endTime ORDER BY s.timestamp DESC")
    List<SensorData> findByTimestampRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query("SELECT s FROM SensorData s WHERE s.mac = :mac AND s.gasType = :gasType ORDER BY s.timestamp DESC")
    List<SensorData> findByMacAndGasType(@Param("mac") String mac, @Param("gasType") String gasType);
}
