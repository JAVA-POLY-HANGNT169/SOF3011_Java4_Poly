package com.poly.hangnt169.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lop")
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_lop")
    private String maLop;

    @Column(name = "ten_lop")
    private String tenLop;

    @Column(name = "so_luong_sv")
    private Integer soLuongSV;

    public Lop(Long id, String maLop, String tenLop, Integer soLuongSV) {
        this.id = id;
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.soLuongSV = soLuongSV;
    }

    public Lop() {
    }

    public Lop(String maLop, String tenLop, Integer soLuongSV) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.soLuongSV = soLuongSV;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public Integer getSoLuongSV() {
        return soLuongSV;
    }

    public void setSoLuongSV(Integer soLuongSV) {
        this.soLuongSV = soLuongSV;
    }
}
