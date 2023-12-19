package com.itana.crud_csvbackend.resale.infrastructure.resources.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
}
