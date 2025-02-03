package com.example.demo.entity;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class CompanyInfoResponse {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("beta")
    private Double beta;

    @JsonProperty("volAvg")
    private Long volAvg;

    @JsonProperty("mktCap")
    private Long mktCap;

    @JsonProperty("lastDiv")
    private Double lastDiv;

    @JsonProperty("range")
    private String range;

    @JsonProperty("changes")
    private Double changes;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("cik")
    private String cik;

    @JsonProperty("isin")
    private String isin;

    @JsonProperty("cusip")
    private String cusip;

    @JsonProperty("exchange")
    private String exchange;

    @JsonProperty("exchangeShortName")
    private String exchangeShortName;

    @JsonProperty("industry")
    private String industry;

    @JsonProperty("website")
    private String website;

    @JsonProperty("description")
    private String description;

    @JsonProperty("ceo")
    private String ceo;

    @JsonProperty("sector")
    private String sector;

    @JsonProperty("country")
    private String country;

    @JsonProperty("fullTimeEmployees")
    private String fullTimeEmployees;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("zip")
    private String zip;

    @JsonProperty("dcfDiff")
    private Double dcfDiff;

    @JsonProperty("dcf")
    private Double dcf;

    @JsonProperty("image")
    private String image;

    @JsonProperty("ipoDate")
    private String ipoDate;

    @JsonProperty("defaultImage")
    private Boolean defaultImage;

    @JsonProperty("isEtf")
    private Boolean isEtf;

    @JsonProperty("isActivelyTrading")
    private Boolean isActivelyTrading;

    @JsonProperty("isAdr")
    private Boolean isAdr;

    @JsonProperty("isFund")
    private Boolean isFund;
}
