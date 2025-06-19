package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog,Long> {
}
