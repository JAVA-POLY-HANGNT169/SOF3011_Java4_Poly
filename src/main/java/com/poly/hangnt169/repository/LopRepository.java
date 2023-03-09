package com.poly.hangnt169.repository;

import com.poly.hangnt169.entity.Lop;
import com.poly.hangnt169.response.LopResponse;
import com.poly.hangnt169.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hangnt169
 */
public class LopRepository {

    public List<LopResponse> getAll() {
        String sql = "SELECT new com.poly.hangnt169.response.LopResponse "
                + " ( l.id ,l.tenLop,l.maLop,l.soLuongSV) "
                + " FROM Lop l";
        List<LopResponse> lops = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(sql);
            lops = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lops;
    }

    public LopResponse getOne(Long id) {
        LopResponse lop = null;
        String sql = "SELECT new com.poly.hangnt169.response.LopResponse "
                + " ( l.id ,l.tenLop,l.maLop,l.soLuongSV) "
                + " FROM Lop l WHERE l.id =:id";

        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            lop = (LopResponse) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lop;
    }

    public Lop findByID(Long id) {
        Lop lop = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            String sql = "From Lop WHERE id =:id";
            Query query = session.createQuery(sql, Lop.class);
            query.setParameter("id", id);
            lop = (Lop) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lop;
    }

    public Boolean addOrUpdateSinhVien(Lop lop) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(lop);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean delete(Lop lop) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(lop);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

}
