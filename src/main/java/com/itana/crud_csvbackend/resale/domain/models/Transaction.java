package com.itana.crud_csvbackend.resale.domain.models;

import com.itana.crud_csvbackend.shared.domain.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month", nullable = false)
    private Date month;

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
    private Date leaseCommenceDate;

    @Column(name = "resale_price", nullable = false)
    private Integer resalePrice;

    public Transaction(Date month, String town, String flatType, String block, String streetName, String storeyRange, Integer floorAreaSqm, String flatModel, Date leaseCommenceDate, Integer resalePrice) {
        if (month == null) {
            throw new ValidationException("Month cannot be null");
        }
        if (town == null || town.isEmpty()) {
            throw new ValidationException("Town cannot be null or empty");
        }
        if (flatType == null || flatType.isEmpty()) {
            throw new ValidationException("Flat type cannot be null or empty");
        }
        if (block == null || block.isEmpty()) {
            throw new ValidationException("Block cannot be null or empty");
        }
        if (streetName == null || streetName.isEmpty()) {
            throw new ValidationException("Street name cannot be null or empty");
        }
        if (storeyRange == null || storeyRange.isEmpty()) {
            throw new ValidationException("Storey range cannot be null or empty");
        }
        if (floorAreaSqm == null || floorAreaSqm <= 0) {
            throw new ValidationException("Floor area sqm cannot be null or less than 1");
        }
        if (flatModel == null || flatModel.isEmpty()) {
            throw new ValidationException("Flat model cannot be null or empty");
        }
        if (leaseCommenceDate == null) {
            throw new ValidationException("Lease commence date cannot be null");
        }
        if (resalePrice == null || resalePrice <= 0) {
            throw new ValidationException("Resale price cannot be null or less than 1");
        }
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
