package com.baldwin.bean;

public class RstBeanTest {
    private String packetNo = null;
    private String orderNo = null;
    private String token = null;
    private String date = null;
    private String time = null;
    private String state = null;

    public String getPacketNo() {
        return packetNo;
    }

    public void setPacketNo(String packetNo) {
        this.packetNo = packetNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "rstBeanTest{" +
                "packetNo='" + packetNo + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", token='" + token + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
