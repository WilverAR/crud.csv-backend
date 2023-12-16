package com.itana.crud_csvbackend.resale.infrastructure.resources.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionResponse {

    private Long id;

    private String month;

    private String town;

    private String flatType;

    private String block;

    private String streetName;

    private String storeyRange;

    private Integer floorAreaSqm;

    private String flatModel;

    private String leaseCommenceDate;

    private Integer resalePrice;
}
