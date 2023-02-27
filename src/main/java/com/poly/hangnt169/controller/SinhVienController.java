package com.poly.hangnt169.controller;

import com.poly.hangnt169.request.SinhVienRequest;
import com.poly.hangnt169.response.ChuyenNganhResponse;
import com.poly.hangnt169.response.LopResponse;
import com.poly.hangnt169.response.SinhVienResponse;
import com.poly.hangnt169.service.ChuyenNganhService;
import com.poly.hangnt169.service.LopService;
import com.poly.hangnt169.service.SinhVienService;
import com.poly.hangnt169.service.impl.ChuyenNganhServiceImpl;
import com.poly.hangnt169.service.impl.LopServiceImpl;
import com.poly.hangnt169.service.impl.SinhVienServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author hangnt169
 */
@WebServlet(name = "SinhVienController", value = {
        "/sinh-vien/hien-thi",  // GET
        "/sinh-vien/add-update",        // POST
        "/sinh-vien/delete",    // GET
        "/sinh-vien/detail",    // GET
})
public class SinhVienController extends HttpServlet {

    private SinhVienService sinhVienService = new SinhVienServiceImpl();
    private LopService lopService = new LopServiceImpl();
    private ChuyenNganhService chuyenNganhService = new ChuyenNganhServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiSinhVien(request, response);
        } else if (uri.contains("detail")) {
            this.detailSinhVien(request, response);
        } else {
            this.deleteSinhVien(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if (uri.contains("add-update")) {
            this.addOrUpdateSinhVien(request, response);
        }
    }

    private void addOrUpdateSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        SinhVien sv = new SinhVien();
//        try {
//            BeanUtils.populate(sv, request.getParameterMap());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String id = request.getParameter("id");
        String mssv = request.getParameter("mssv");
        String ten = request.getParameter("ten");
        String email = request.getParameter("email");
        String gioiTinh = request.getParameter("gioiTinh");
        String lopID = request.getParameter("lop");
        String chuyenNganhID = request.getParameter("chuyenNganh");


        SinhVienRequest sinhVienRequest = SinhVienRequest.builder()
                .id(id == "" ? null : Long.valueOf(id))
                .ma(mssv)
                .ten(ten)
                .email(email)
                .gioiTinh(Boolean.valueOf(gioiTinh))
                .lopID(Long.valueOf(lopID))
                .chuyenNganhID(Long.valueOf(chuyenNganhID))
                .build();

        HashMap<String, String> errors = sinhVienService.addOrUpdateSinhVien(sinhVienRequest);
        request.setAttribute("errors", errors);
        List<SinhVienResponse> sinhViens = sinhVienService.hienThiSinhVien();
        List<ChuyenNganhResponse> chuyenNganhs = chuyenNganhService.hienThiDanhSachChuyenNganh();
        List<LopResponse> lops = lopService.hienThiDanhSachLop();
        request.setAttribute("sinhViens", sinhViens);
        request.setAttribute("chuyenNganhs", chuyenNganhs);
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/sinhvien/sinh-vien.jsp").forward(request, response);
    }

    private void hienThiSinhVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SinhVienResponse> sinhViens = sinhVienService.hienThiSinhVien();
        List<ChuyenNganhResponse> chuyenNganhs = chuyenNganhService.hienThiDanhSachChuyenNganh();
        List<LopResponse> lops = lopService.hienThiDanhSachLop();
        request.setAttribute("sinhViens", sinhViens);
        request.setAttribute("chuyenNganhs", chuyenNganhs);
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/sinhvien/sinh-vien.jsp").forward(request, response);
    }

    private void detailSinhVien(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        SinhVienResponse sinhVien = sinhVienService.detailSinhVien(Long.valueOf(id));
        request.setAttribute("sinhVien", sinhVien);
        List<SinhVienResponse> sinhViens = sinhVienService.hienThiSinhVien();
        List<ChuyenNganhResponse> chuyenNganhs = chuyenNganhService.hienThiDanhSachChuyenNganh();
        List<LopResponse> lops = lopService.hienThiDanhSachLop();
        request.setAttribute("sinhViens", sinhViens);
        request.setAttribute("chuyenNganhs", chuyenNganhs);
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/sinhvien/sinh-vien.jsp").forward(request, response);
    }

    private void deleteSinhVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        sinhVienService.removeSinhVien(Long.valueOf(id));
        List<SinhVienResponse> sinhViens = sinhVienService.hienThiSinhVien();
        List<ChuyenNganhResponse> chuyenNganhs = chuyenNganhService.hienThiDanhSachChuyenNganh();
        List<LopResponse> lops = lopService.hienThiDanhSachLop();
        request.setAttribute("sinhViens", sinhViens);
        request.setAttribute("chuyenNganhs", chuyenNganhs);
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/sinhvien/sinh-vien.jsp").forward(request, response);
    }

}
