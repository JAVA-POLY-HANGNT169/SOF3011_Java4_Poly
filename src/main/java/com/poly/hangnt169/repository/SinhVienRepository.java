/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.hangnt169.repository;

import com.poly.hangnt169.entity.SinhVien;
import com.poly.hangnt169.response.SinhVienResponse;
import com.poly.hangnt169.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hangnt
 */
public class SinhVienRepository {

    public List<SinhVienResponse> getAll() {
        String sql = "SELECT new com.poly.hangnt169.response.SinhVienResponse "
                + " ( sv.id ,sv.maSV, sv.ten,sv.email,sv.gioiTinh,sv.lop.tenLop," +
                " sv.chuyenNganh.tenChuyenNganh,sv.lop.id,sv.chuyenNganh.id) "
                + " FROM SinhVien sv JOIN ChuyenNganh c "
                + " ON  sv.chuyenNganh.id = c.id JOIN Lop l ON sv.lop.id = l.id";
        List<SinhVienResponse> sinhViens = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(sql);
            sinhViens = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return sinhViens;
    }

    public SinhVienResponse getOne(Long id) {
        SinhVienResponse sinhVien = null;
        String sql = "SELECT new com.poly.hangnt169.response.SinhVienResponse "
                + " ( sv.id ,sv.maSV, sv.ten,sv.email,sv.gioiTinh,sv.lop.tenLop," +
                " sv.chuyenNganh.tenChuyenNganh,sv.lop.id,sv.chuyenNganh.id) "
                + " FROM SinhVien sv JOIN ChuyenNganh c "
                + " ON  sv.chuyenNganh.id = c.id JOIN Lop l ON sv.lop.id = l.id  WHERE sv.id=:id";

        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            sinhVien = (SinhVienResponse) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return sinhVien;
    }

    public SinhVien findByID(Long id) {
        SinhVien sinhVien = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String sql = "From SinhVien WHERE id =:id";
            Query query = session.createQuery(sql, SinhVien.class);
            query.setParameter("id", id);
            sinhVien = (SinhVien) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return sinhVien;
    }

    public Boolean addOrUpdateSinhVien(SinhVien sinhVien) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(sinhVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(SinhVien sinhVien) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(sinhVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

}
