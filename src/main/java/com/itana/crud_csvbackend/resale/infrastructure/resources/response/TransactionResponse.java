package com.itana.crud_csvbackend.resale.infrastructure.resources.response;

import lombok.Getter;
import java.util.Date;

@Getter
public class TransactionResponse {
    private Long id;
    private Date month;
    private String town;
    private String flatType;
    private String block;
    private String streetName;
    private String storeyRange;
    private Integer floorAreaSqm;
    private String flatModel;
    private Date leaseCommenceDate;
    private Integer resalePrice;
}
