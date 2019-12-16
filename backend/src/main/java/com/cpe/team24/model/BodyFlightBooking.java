package com.cpe.team24.model;

import lombok.Data;

@Data
public class BodyFlightBooking {
    private Long memberId;
    private Long departFlightId;
    private Long returnFlightId;
}
