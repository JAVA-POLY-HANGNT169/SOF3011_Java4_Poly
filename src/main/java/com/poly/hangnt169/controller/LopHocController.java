package com.poly.hangnt169.controller;
/**
 * @author hangnt169
 */

import com.poly.hangnt169.entity.Lop;
import com.poly.hangnt169.repository.LopRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LopHocController", value = {
        "/lop-hoc/hien-thi",  // GET
        "/lop-hoc/add",      // POST
        "/lop-hoc/update",      // POST
        "/lop-hoc/delete",    // GET
        "/lop-hoc/view-update",// GET
        "/lop-hoc/view-add", // GET
        "/lop-hoc/detail"    // GET
})
public class LopHocController extends HttpServlet {

    private LopRepository lopRepository = new LopRepository();

    /**
     * Tập hợp của tất cả các yêu cầu ( hiển thị hoặc lấy dữ liệu )=> Method GET
     *
     * @param request   : yêu cầu gửi đi
     * @param response: kết quả trả về
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        // Đường dẫn chứa hien-thi => Gọi tới hàm hiển thị tất cả dữ liệu
        if (uri.contains("hien-thi")) {
            this.hienThiLopHoc(request, response);
        } // Hiển thị view - update => Gọi tới hàm view update
        else if (uri.contains("view-update")) {
            this.viewUpdateLopHoc(request, response);
        } // Hiển thị view - add => Gọi tới hàm view add
        else if (uri.contains("view-add")) {
            this.viewAdd(request, response);
        } // Hiển thị detail => Gọi tới hàm detail
        else if (uri.contains("detail")) {
            this.detailLopHoc(request, response);
        }// Hiển thị delete => Gọi tới hàm delete
        else if (uri.contains("delete")) {
            this.deleteLopHoc(request, response);
        } // Còn lại quay về trang hiển thị lên table
        else {
            this.hienThiLopHoc(request, response);
        }
    }

    /**
     * Xử lý dữ liệu => Add hoăc Update
     *
     * @param request:  yêu cầu gửi đi
     * @param response: kết quả nhận về
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        // Add đối tượng => Gọi tới hàm add
        if (uri.contains("add")) {
            this.addLopHoc(request, response);
        }// Update đối tượng => Gọi tới hàm update
        else if (uri.contains("update")) {
            this.updateLopHoc(request, response);
        }// Còn lại quay về trang hiển thị lên table
        else {
            this.hienThiLopHoc(request, response);
        }
    }

    /**
     * Hiển thị tất cả dũ liệu lớp học đang có trong DB
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void hienThiLopHoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tất cả dữ liệu từ DB
        List<Lop> lops = lopRepository.getAll();
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/lop-hoc.jsp").forward(request, response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/view/view-add-lop-hoc.jsp");
    }

    private void viewUpdateLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        Lop lop = lopRepository.getOne(Long.valueOf(id));
        request.setAttribute("lopHoc", lop);
        List<Lop> lops = lopRepository.getAll();
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/view-update-lop-hoc.jsp").forward(request, response);
    }

    private void detailLopHoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Lop lop = lopRepository.getOne(Long.valueOf(id));
        request.setAttribute("lopHoc", lop);
        request.getRequestDispatcher("/view/detail-lop-hoc.jsp").forward(request, response);
    }


    /**
     * Xoá lớp học trong hệ thống
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteLopHoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy giá trị có tên là id từ đường dẫn xuống
        String id = request.getParameter("id");
        // Tìm xem có tìm thấy đối tương có ID cần tìm không
        Lop lop = lopRepository.getOne(Long.valueOf(id));
        // Xoá đối tượng đấy
        lopRepository.delete(lop);
        // Hiển thị toàn bộ dữ liệu trong trang chính
        List<Lop> lops = lopRepository.getAll();
        request.setAttribute("lops", lops);
        request.getRequestDispatcher("/view/lop-hoc.jsp").forward(request, response);
    }

    /**
     * Thêm thông tin của 1 lớp học vào hệ thống
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void addLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Lấy dữ liệu từ đường dẫn xuống
        String maLop = request.getParameter("maLop");
        String tenLop = request.getParameter("tenLop");
        String soLuongSV = request.getParameter("soLuongSinhVien");
        // Kiểm tra mã lớp trống
        if (maLop.isEmpty()) {
            request.setAttribute("maEmpty", "Mã không được trống");
            request.getRequestDispatcher("/view/view-add-lop-hoc.jsp").forward(request, response);
        }
        // Kiểm tra tên lớp trống
        if (tenLop.isEmpty()) {
            request.setAttribute("tenEmpty", "Tên không được trống");
            request.getRequestDispatcher("/view/view-add-lop-hoc.jsp").forward(request, response);
        }
        // Kiểm tra số lượng SV trống
        if (soLuongSV.isEmpty()) {
            request.setAttribute("soLuongSVEmpty", "Số lượng SV không được trống");
            request.getRequestDispatcher("/view/view-add-lop-hoc.jsp").forward(request, response);
        }
        // Nếu thoả mãn đk => Add
        if (!maLop.isEmpty() && !tenLop.isEmpty() && !soLuongSV.isEmpty()) {
            Lop lop = new Lop(maLop, tenLop, Integer.valueOf(soLuongSV));
            lopRepository.add(lop);
            response.sendRedirect("/lop-hoc/hien-thi");
        }
    }

    /**
     * Sửa thông tin 1 đối tượng
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void updateLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Lấy giá trị từ đường dẫn xuống
        String id = request.getParameter("id");
        String maLop = request.getParameter("maLop");
        String tenLop = request.getParameter("tenLop");
        String soLuongSV = request.getParameter("soLuongSinhVien");
        // Kiểm tra tên lớp trống
        if (tenLop.isEmpty()) {
            request.setAttribute("tenEmpty", "Tên không được trống");
            request.getRequestDispatcher("/view/view-update-lop-hoc.jsp").forward(request, response);
        }
        // Kiểm tra số lương SV trống
        if (soLuongSV.isEmpty()) {
            request.setAttribute("soLuongSVEmpty", "Số lượng SV không được trống");
            request.getRequestDispatcher("/view/view-update-lop-hoc.jsp").forward(request, response);
        }
        // Nếu thoả mãn đk => Update
        if (!tenLop.isEmpty() && !soLuongSV.isEmpty()) {
            Lop lop = new Lop(Long.valueOf(id), maLop, tenLop, Integer.valueOf(soLuongSV));
            lopRepository.update(lop);
            response.sendRedirect("/lop-hoc/hien-thi");
        }
    }

}
