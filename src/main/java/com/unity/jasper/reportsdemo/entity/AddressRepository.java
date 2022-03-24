package com.unity.jasper.reportsdemo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {


    List<Address> findAllByDistrictContaining(String name);
}
