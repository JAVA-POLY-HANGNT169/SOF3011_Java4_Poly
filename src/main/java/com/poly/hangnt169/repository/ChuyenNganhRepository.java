package com.poly.hangnt169.repository;

import com.poly.hangnt169.entity.ChuyenNganh;
import com.poly.hangnt169.response.ChuyenNganhResponse;
import com.poly.hangnt169.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hangnt169
 */
public class ChuyenNganhRepository {

    public List<ChuyenNganhResponse> getAll() {
        String sql = "SELECT new com.poly.hangnt169.response.ChuyenNganhResponse "
                + " ( l.id ,l.tenChuyenNganh) "
                + " FROM ChuyenNganh l";
        List<ChuyenNganhResponse> chuyenNganhs = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(sql);
            chuyenNganhs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return chuyenNganhs;
    }

    public ChuyenNganh findByID(Long id) {
        ChuyenNganh chuyenNganh = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String sql = "From ChuyenNganh WHERE id =:id";
            Query query = session.createQuery(sql, ChuyenNganh.class);
            query.setParameter("id", id);
            chuyenNganh = (ChuyenNganh) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return chuyenNganh;
    }
}
