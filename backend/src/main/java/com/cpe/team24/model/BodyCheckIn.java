package com.cpe.team24.model;

public class BodyCheckIn {
    private String email;
    private Long checkInStatusId;
    private Long checkInTypeId;
    private Long flightBookingId;

    public String getEmail() {
        return this.email;
    }

    public Long getCheckInStatusId() {
        return this.checkInStatusId;
    }

    public Long getCheckInTypeId() {
        return this.checkInTypeId;
    }

    public Long getFlightBookingId() {
        return this.flightBookingId;
    }

}
