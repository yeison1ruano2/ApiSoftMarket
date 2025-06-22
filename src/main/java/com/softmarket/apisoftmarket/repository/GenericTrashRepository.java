package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.GenericTrash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericTrashRepository extends JpaRepository<GenericTrash,String> {

  @Query(value="SELECT mensaje FROM generic_trash LIMIT 1",nativeQuery = true)
  String findFirstGenericTrash();
}
