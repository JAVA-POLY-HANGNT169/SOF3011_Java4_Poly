package com.poly.hangnt169.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
