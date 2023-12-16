package com.itana.crud_csvbackend.resale.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itana.crud_csvbackend.shared.domain.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month", nullable = false)
    private String month;

    @Column(name = "town", length = 50, nullable = false)
    private String town;

    @Column(name = "flat_type", length = 50, nullable = false)
    private String flatType;

    @Column(name = "block", length = 50, nullable = false)
    private String block;

    @Column(name = "street_name", length = 50, nullable = false)
    private String streetName;

    @Column(name = "storey_range", length = 50, nullable = false)
    private String storeyRange;

    @Column(name = "floor_area_sqm", nullable = false)
    private Integer floorAreaSqm;

    @Column(name = "flat_model", length = 50, nullable = false)
    private String flatModel;

    @Column(name = "lease_commence_date", nullable = false)
    private String leaseCommenceDate;

    @Column(name = "resale_price", nullable = false)
    private Integer resalePrice;

    public Transaction(String month, String town, String flatType, String block, String streetName, String storeyRange, Integer floorAreaSqm, String flatModel, String leaseCommenceDate, Integer resalePrice) {
        this.month = month;
        this.town = town;
        this.flatType = flatType;
        this.block = block;
        this.streetName = streetName;
        this.storeyRange = storeyRange;
        this.floorAreaSqm = floorAreaSqm;
        this.flatModel = flatModel;
        this.leaseCommenceDate = leaseCommenceDate;
        this.resalePrice = resalePrice;
    }
}
