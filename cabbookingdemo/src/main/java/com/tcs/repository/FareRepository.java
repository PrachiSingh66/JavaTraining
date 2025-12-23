package com.tcs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.entity.FareEstimation;

@Repository
public interface FareRepository extends JpaRepository<FareEstimation, Integer>{
	Optional<FareEstimation> findBySourceAndDestination(String source,String destination);
}
