package com.albatros.springsecurity.data.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TenderDto {
    private int ID;
    private String Date;
    private String TenderName;
    private String Customer;
    private String Category;
    private String Region;
    private Long Price;
    private String EndTime;
    private String Etp;
    private String TenderLink;
    private String TenderLinkInner;
    @JsonProperty("User_id")
    private int UserId;
    private String CustomerINN;
}
