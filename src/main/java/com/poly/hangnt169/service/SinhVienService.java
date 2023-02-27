package com.poly.hangnt169.service;

import com.poly.hangnt169.request.SinhVienRequest;
import com.poly.hangnt169.response.SinhVienResponse;

import java.util.HashMap;
import java.util.List;

/**
 * @author hangnt169
 */
public interface SinhVienService {

    List<SinhVienResponse> hienThiSinhVien();

    SinhVienResponse detailSinhVien(Long id);

    void removeSinhVien(Long id);

    HashMap<String, String> addOrUpdateSinhVien(SinhVienRequest request);

}
