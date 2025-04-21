package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.ErrorFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorFacturaRepository extends JpaRepository<ErrorFactura,Long> {
}
