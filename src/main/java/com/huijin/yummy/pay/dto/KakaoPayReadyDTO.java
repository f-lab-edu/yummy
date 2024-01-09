package com.huijin.yummy.pay.dto;

import java.time.LocalDateTime;

public class KakaoPayReadyDTO { //Response Body Payload
    private String tid; //결제 건에 대한 고유번호. 결제 준비 API가 성공적으로 호출되면 발급
    private String next_redirect_app_url; //요청한 클라이언트(Client)가 모바일 앱일 경우, 카카오톡 결제 페이지 Redirect URL
    private String next_redirect_mobile_url; //요청한 클라이언트가 모바일 웹일 경우, 카카오톡 결제 페이지 Redirect URL
    private String next_redirect_pc_url; //요청한 클라이언트가 PC 웹일 경우, 카카오톡으로 결제 요청 메시지(TMS)를 보내기 위한 사용자 정보 입력 화면 Redirect URL
    private String android_app_scheme; //카카오페이 결제 화면으로 이동하는 Android 앱 스킴(Scheme) - 내부 서비스용
    private String ios_app_scheme; //카카오페이 결제 화면으로 이동하는 iOS 앱 스킴 - 내부 서비스용
    private LocalDateTime created_at; //결제 준비 요청 시간

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getNext_redirect_app_url() {
        return next_redirect_app_url;
    }

    public void setNext_redirect_app_url(String next_redirect_app_url) {
        this.next_redirect_app_url = next_redirect_app_url;
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

    public String getAndroid_app_scheme() {
        return android_app_scheme;
    }

    public void setAndroid_app_scheme(String android_app_scheme) {
        this.android_app_scheme = android_app_scheme;
    }

    public String getIos_app_scheme() {
        return ios_app_scheme;
    }

    public void setIos_app_scheme(String ios_app_scheme) {
        this.ios_app_scheme = ios_app_scheme;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "KakaoPayReadyDTO{" +
                "tid='" + tid + '\'' +
                ", next_redirect_app_url='" + next_redirect_app_url + '\'' +
                ", next_redirect_mobile_url='" + next_redirect_mobile_url + '\'' +
                ", next_redirect_pc_url='" + next_redirect_pc_url + '\'' +
                ", android_app_scheme='" + android_app_scheme + '\'' +
                ", ios_app_scheme='" + ios_app_scheme + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
