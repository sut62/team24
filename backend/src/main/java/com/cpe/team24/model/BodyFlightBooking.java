package com.cpe.team24.model;

import lombok.Data;

@Data
public class BodyFlightBooking {
    private Long departFlightId;
    private Long returnFlightId;

    //Getter Setter
    public Long getDepartFlightId() {
        return departFlightId;
    }

    public Long getReturnFlightId() {
        return returnFlightId;
    }

}
