package com.poly.hangnt169.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sinh_vien")
public class SinhVien {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maSV")
    private String maSV;

    @Column(name = "ten")
    private String ten;

    @Column(name = "email")
    private String email;

    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lop_id", referencedColumnName = "id")
    private Lop lop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chuyen_nganh_id", referencedColumnName = "id")
    private ChuyenNganh chuyenNganh;

}
