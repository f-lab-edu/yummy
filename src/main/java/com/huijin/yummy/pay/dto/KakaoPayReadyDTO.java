package com.huijin.yummy.pay.dto;

public class KakaoPayReadyDTO {
    private String tid; //결제고유번호
    private String next_redirect_mobile_url; //요청한 클라이언트가 모바일 웹
    private String next_redirect_pc_url; //요청한 클라이언트가 PC 웹
    private String partner_order_id; //가맹점 주문번호

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getNext_redirect_mobile_url() {
        return next_redirect_mobile_url;
    }

    public void setNext_redirect_mobile_url(String next_redirect_mobile_url) {
        this.next_redirect_mobile_url = next_redirect_mobile_url;
    }

    public String getNext_redirect_pc_url() {
        return next_redirect_pc_url;
    }

    public void setNext_redirect_pc_url(String next_redirect_pc_url) {
        this.next_redirect_pc_url = next_redirect_pc_url;
    }

    public String getPartner_order_id() {
        return partner_order_id;
    }

    public void setPartner_order_id(String partner_order_id) {
        this.partner_order_id = partner_order_id;
    }

    @Override
    public String toString() {
        return "KakaoPayReadyVO{" +
                "tid='" + tid + '\'' +
                ", next_redirect_mobile_url='" + next_redirect_mobile_url + '\'' +
                ", next_redirect_pc_url='" + next_redirect_pc_url + '\'' +
                ", partner_order_id='" + partner_order_id + '\'' +
                '}';
    }
}
