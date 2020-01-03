package com.cpe.team24.model;

public class BodyFlight {
    private Double price;
    private String departDate;
    private String arriveDate;
    private Long airplaneId;
    private Long departAirportId;
    private Long arriveAirportId;

    //Getter
    public Double getPrice() {
        return price;
    }

    public String getDepartDate() {
        return departDate;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public Long getAirplaneId() {
        return airplaneId;
    }

    public Long getDepartAirportId() {
        return departAirportId;
    }

    public Long getArriveAirportId() {
        return arriveAirportId;
    }
}
