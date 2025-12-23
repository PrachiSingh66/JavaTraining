package com.tcs.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcs.irctc.entity.Train;

public interface TrainRepository extends JpaRepository<Train, Long>{
	@Query("Select t from Train t WHERE t.trainId=:trainId")
	Train findPlain(@Param("trainId") Long trainId);
}
