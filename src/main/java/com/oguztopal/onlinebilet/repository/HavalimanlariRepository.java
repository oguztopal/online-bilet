package com.oguztopal.onlinebilet.repository;


import com.oguztopal.onlinebilet.entity.Havalimanlari;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HavalimanlariRepository extends JpaRepository<Havalimanlari,Long> {

        Havalimanlari getByHavalimaniId(Long id);

        List<Havalimanlari> getAllByHavalimaniIdIsNotNull();

        @Query(value = "SELECT H.* FROM havalimanlari H WHERE h.havalimani_id is not null" , nativeQuery = true)
        List<Havalimanlari> havalimanlariGetir();
}