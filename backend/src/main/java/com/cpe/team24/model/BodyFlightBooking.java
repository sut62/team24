package com.cpe.team24.model;

import lombok.Data;

@Data
public class BodyFlightBooking {
    private Long departFlightId;
    private Long returnFlightId;

    private Long departBaggageAddsOnId;
    private Long returnBaggageAddsOnId;

    //Getter Setter
    public Long getDepartFlightId() {
        return departFlightId;
    }

    public Long getReturnFlightId() {
        return returnFlightId;
    }

    public Long getDepartBaggageAddsOnId() {
        return departBaggageAddsOnId;
    }

    public Long getReturnBaggageAddsOnId() {
        return returnBaggageAddsOnId;
    }
}
