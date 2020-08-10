package com.zy.converter.repository;

import com.zy.converter.model.ValCurs;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface ValuteRepository extends CrudRepository<ValCurs,Long>{
    Optional<ValCurs> findTopByOrderByIdDesc();
    boolean existsByDate(Date date);
    Optional<ValCurs>findByDate(Date date);
}
