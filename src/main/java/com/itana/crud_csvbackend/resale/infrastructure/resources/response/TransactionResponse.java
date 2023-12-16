package com.itana.crud_csvbackend.resale.infrastructure.resources.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


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
