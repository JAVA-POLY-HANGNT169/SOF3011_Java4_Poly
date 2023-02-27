package com.poly.hangnt169.controller;
/**
 * @author hangnt169
 */

import com.poly.hangnt169.request.LopHocRequest;
import com.poly.hangnt169.response.LopResponse;
import com.poly.hangnt169.service.LopService;
import com.poly.hangnt169.service.impl.LopServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "LopHocController", value = {
        "/lop-hoc/hien-thi",  // GET
        "/lop-hoc/add-update", // POST
        "/lop-hoc/delete",    // GET
        "/lop-hoc/detail",    // GET
})
public class LopHocController extends HttpServlet {

    private LopService lopService = new LopServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiLopHoc(request, response);
        } else if (uri.contains("detail")) {
            this.detailLopHoc(request, response);
        } else {
            this.deleteLopHoc(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add-update")) {
            this.addOrUpdateLopHoc(request, response);
        }
    }

    private void addOrUpdateLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String maLop = request.getParameter("maLop");
        String tenLop = request.getParameter("tenLop");
        String soLuongSV = request.getParameter("soLuongSinhVien");


        LopHocRequest sinhVienRequest = LopHocRequest.builder()
                .id(id == "" ? null : Long.valueOf(id))
                .tenLop(tenLop)
                .maLop(maLop)
                .soLuongSV(soLuongSV == "" ? null : Integer.valueOf(soLuongSV))
                .build();

        HashMap<String, String> errors = lopService.addOrUpdateLopHoc(sinhVienRequest);
        request.setAttribute("errors", errors);
        List<LopResponse> lops = lopService.hienThiDanhSachLop();
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/lophoc/lop-hoc.jsp").forward(request, response);
    }

    private void hienThiLopHoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LopResponse> lops = lopService.hienThiDanhSachLop();
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/lophoc/lop-hoc.jsp").forward(request, response);
    }

    private void detailLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        LopResponse sinhVien = lopService.detailLopHoc(Long.valueOf(id));
        request.setAttribute("lopHoc", sinhVien);
        List<LopResponse> lops = lopService.hienThiDanhSachLop();
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/lophoc/lop-hoc.jsp").forward(request, response);
    }

    private void deleteLopHoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        lopService.removeLopHoc(Long.valueOf(id));
        List<LopResponse> lops = lopService.hienThiDanhSachLop();
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/lophoc/lop-hoc.jsp").forward(request, response);
    }
}
