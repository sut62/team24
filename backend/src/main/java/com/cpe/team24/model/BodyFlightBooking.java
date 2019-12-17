package com.cpe.team24.model;

import lombok.Data;

@Data
public class BodyFlightBooking {
    private Long departFlightId;
    private Long returnFlightId;
    private Long memberId;

    //Getter Setter
    public Long getDepartFlightId() {
        return departFlightId;
    }

    public Long getReturnFlightId() {
        return returnFlightId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
