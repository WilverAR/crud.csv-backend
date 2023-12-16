package com.itana.crud_csvbackend.resale.infrastructure.resources.request;

import lombok.Getter;
import java.util.Date;

@Getter
public class TransactionRequest {
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
