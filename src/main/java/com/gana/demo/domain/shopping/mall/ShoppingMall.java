package com.gana.demo.domain.shopping.mall;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ShoppingMall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> thumbnails;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String openDate;

    @Column(nullable = false)
    private String closeDate;

    @Column(nullable = false)
    private String parking;

    @Column(nullable = false)
    private String facility;

    @Column(nullable = false)
    private String transport;

    @Column(nullable = false)
    private String map;

    @Lob
    @Column
    private String info;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedDate;

    public boolean isModified() {
        return createDate != modifiedDate;
    }

    public static ShoppingMall create(
            String name,
            List<String> thumbnails,
            String address,
            String phone,
            String openDate,
            String closeDate,
            String parking,
            String transport,
            String facility,
            String map,
            String info
    ) {
        return ShoppingMall.builder()
                .name(name)
                .address(address)
                .phone(phone)
                .openDate(openDate)
                .closeDate(closeDate)
                .parking(parking)
                .transport(transport)
                .facility(facility)
                .map(map)
                .info(info)
                .thumbnails(thumbnails)
                .build();
    }

    public ShoppingMall update(
            String name,
            List<String> thumbnails,
            String address,
            String phone,
            String openDate,
            String closeDate,
            String parking,
            String transport,
            String facility,
            String map,
            String info
    ) {
        this.name = name;
        this.thumbnails = thumbnails;
        this.address = address;
        this.phone = phone;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.parking = parking;
        this.transport = transport;
        this.facility = facility;
        this.map = map;
        this.info = info;

        return this;
    }
}
