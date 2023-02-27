package com.poly.hangnt169.service;

import com.poly.hangnt169.request.LopHocRequest;
import com.poly.hangnt169.response.LopResponse;

import java.util.HashMap;
import java.util.List;

/**
 * @author hangnt169
 */
public interface LopService {

    List<LopResponse> hienThiDanhSachLop();

    LopResponse detailLopHoc(Long id);

    void removeLopHoc(Long id);

    HashMap<String, String> addOrUpdateLopHoc(LopHocRequest request);

}
