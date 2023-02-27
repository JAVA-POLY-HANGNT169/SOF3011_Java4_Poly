package com.poly.hangnt169.service.impl;

import com.poly.hangnt169.entity.Lop;
import com.poly.hangnt169.repository.LopRepository;
import com.poly.hangnt169.request.LopHocRequest;
import com.poly.hangnt169.response.LopResponse;
import com.poly.hangnt169.service.LopService;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author hangnt169
 */
public class LopServiceImpl implements LopService {

    private LopRepository lopRepository = new LopRepository();

    @Override
    public List<LopResponse> hienThiDanhSachLop() {
        return lopRepository.getAll();
    }

    @Override
    public LopResponse detailLopHoc(Long id) {
        return lopRepository.getOne(id);
    }

    @Override
    public void removeLopHoc(Long id) {
        Lop lop = lopRepository.findByID(id);
        lopRepository.delete(lop);
    }

    /**
     * Add Or Update Lop Hoc
     * @param request : Các giá trị của 1 đối tượng lấy từ các ô input bên view
     * @return List loi => Không thoả mãn validate => list lỗi > 0 và ngược lại
     */
    @Override
    public HashMap<String, String> addOrUpdateLopHoc(LopHocRequest request) {
        HashMap<String, String> lists = new HashMap<>();
        // Check validate
        if (StringUtils.isEmpty(request.getMaLop())) {
            lists.put("MA_EMPTY", "Mã lớp không được để trống");
        }
        if (StringUtils.isEmpty(request.getTenLop())) {
            lists.put("TEN_EMPTY", "Tên Lớp không được để trống");
        }
        if (StringUtils.isEmpty(request.getSoLuongSV())) {
            lists.put("NUMBER_EMPTY", "Số lượng trong lớp không được để trống");
        }
        // Khi thoả mãn validate
        if (lists.isEmpty()) {
            Lop sinhVien = new Lop();
            sinhVien.setId(request.getId());
            sinhVien.setTenLop(request.getTenLop());
            sinhVien.setMaLop(request.getMaLop());
            sinhVien.setSoLuongSV(request.getSoLuongSV());
            lopRepository.addOrUpdateSinhVien(sinhVien);
        }
        return lists;
    }

}
