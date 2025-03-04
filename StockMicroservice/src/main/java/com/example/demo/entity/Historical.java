package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Historical{

    @JsonProperty("date")
    private String date;

    @JsonProperty("open")
    private Double open;

    @JsonProperty("high")
    private Double high;

    @JsonProperty("low")
    private Double low;

    @JsonProperty("close")
    private Double close;

    @JsonProperty("adjClose")
    private Double adjClose;

    @JsonProperty("volume")
    private Long volume;

    @JsonProperty("unadjustedVolume")
    private Long unadjustedVolume;

    @JsonProperty("change")
    private Double change;

    @JsonProperty("changePercent")
    private Double changePercent;

    @JsonProperty("vwap")
    private Double vwap;

    @JsonProperty("label")
    private String label;

    @JsonProperty("changeOverTime")
    private Double changeOverTime;

    @Override
    public String toString() {
        return "HistoricalData [date=" + date + ", open=" + open + ", high=" + high + ", low=" + low
                + ", close=" + close + ", adjClose=" + adjClose + ", volume=" + volume
                + ", unadjustedVolume=" + unadjustedVolume + ", change=" + change + ", changePercent=" + changePercent
                + ", vwap=" + vwap + ", label=" + label + ", changeOverTime=" + changeOverTime + "]";
    }
}
