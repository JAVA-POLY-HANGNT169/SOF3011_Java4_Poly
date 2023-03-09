package com.poly.hangnt169.repository;

import com.poly.hangnt169.entity.Lop;
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

    public List<Lop> getAll() {
        List<Lop> lops = new ArrayList<>();
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM Lop ", Lop.class);
            lops = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lops;
    }

    public Lop getOne(Long id) {
        Lop lop = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM Lop WHERE id=:id");
            query.setParameter("id", id);
            lop = (Lop) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lop;
    }

    public Boolean add(Lop lop) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.persist(lop);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public Boolean update(Lop lop) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(lop);
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
