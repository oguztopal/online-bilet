package com.oguztopal.onlinebilet.repository;


import com.oguztopal.onlinebilet.entity.Sirketler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SirketlerRepository extends JpaRepository<Sirketler,Long> {

    Sirketler getBySirketId(Long id);

    List<Sirketler> getAllBySirketIdNotNull();

}