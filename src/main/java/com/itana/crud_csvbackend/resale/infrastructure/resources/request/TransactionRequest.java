package com.itana.crud_csvbackend.resale.infrastructure.resources.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;


@Getter
public class TransactionRequest {

    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])$", message = "Month must be in the format YYYY-MM")
    private String month;

    @NotBlank(message = "Town must not be blank")
    private String town;

    @NotBlank(message = "Flat type must not be blank")
    private String flatType;

    @NotBlank(message = "Block must not be blank")
    private String block;

    @NotBlank(message = "Street name must not be blank")
    private String streetName;

    @NotBlank(message = "Storey range must not be blank")
    private String storeyRange;

    @Positive(message = "Floor area sqm must be positive integer")
    private Integer floorAreaSqm;

    @NotBlank(message = "Flat model must not be blank")
    private String flatModel;

    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Lease commence date must be in the format YYYY")
    private String leaseCommenceDate;

    @Positive(message = "Resale price must be positive integer")
    private Integer resalePrice;
}
