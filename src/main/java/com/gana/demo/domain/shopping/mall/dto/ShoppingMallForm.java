package com.gana.demo.domain.shopping.mall.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingMallForm {
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "썸네일을 업로드 해주세요.")
    private List<String> thumbnails;

    @NotEmpty(message = "주소를 입력해주세요.")
    private String address;

    @NotEmpty(message = "전화번호를 입력해주세요.")
    private String phone;

    @NotEmpty(message = "장날을 입력해주세요.")
    private String openDate;

    @NotEmpty(message = "휴일을 입력해주세요.")
    private String closeDate;

    @NotEmpty(message = "주차 정보를 입력해주세요.")
    private String parking;

    @NotEmpty(message = "교통 정보를 입력해주세요.")
    private String transport;

    @NotEmpty(message = "편의시설을 입력해주세요.")
    private String facility;

    @NotEmpty(message = "위치를 등록 해주세요.")
    private String map;

    @NotEmpty(message = "설명을 입력해주세요.")
    private String info;

    public static ShoppingMallForm of(ShoppingMallFormData data) {
        return ShoppingMallForm.builder()
                .name(data.getName())
                .thumbnails(data.getThumbnails())
                .phone(data.getPhone())
                .address(data.getAddress())
                .openDate(data.getOpenDate())
                .closeDate(data.getCloseDate())
                .parking(data.getParking())
                .transport(data.getTransport())
                .facility(data.getFacility())
                .info(data.getInfo())
                .map(data.getMap())
                .build();
    }
}
