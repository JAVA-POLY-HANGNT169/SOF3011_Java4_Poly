package com.poly.hangnt169.service.impl;

import com.poly.hangnt169.repository.ChuyenNganhRepository;
import com.poly.hangnt169.response.ChuyenNganhResponse;
import com.poly.hangnt169.service.ChuyenNganhService;

import java.util.List;

/**
 * @author hangnt169
 */
public class ChuyenNganhServiceImpl implements ChuyenNganhService {

    private ChuyenNganhRepository chuyenNganhRepository = new ChuyenNganhRepository();

    @Override
    public List<ChuyenNganhResponse> hienThiDanhSachChuyenNganh() {
        return chuyenNganhRepository.getAll();
    }

}
