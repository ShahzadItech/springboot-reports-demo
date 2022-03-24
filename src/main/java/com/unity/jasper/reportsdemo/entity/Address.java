package com.unity.jasper.reportsdemo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "address")
    private String address;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "district")
    private String district;

    @Column(name = "phone")
    private String phone;

    @Column(name = "postal_code")
    private String postalCode;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "last_update")
    private Date lastUpdate;

}
