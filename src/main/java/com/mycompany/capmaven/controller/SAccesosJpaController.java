/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.capmaven.controller;

import com.mycompany.capmaven.controller.exceptions.NonexistentEntityException;
import com.mycompany.capmaven.entity.SAccesos;
import com.mycompany.capmaven.entity.SPerfiles;
import com.mycompany.capmaven.entity.SPerfilesAccesos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class SAccesosJpaController implements Serializable {

    public SAccesosJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SAccesos SAccesos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(SAccesos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SAccesos SAccesos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SAccesos = em.merge(SAccesos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SAccesos.getIdAcceso();
                if (findSAccesos(id) == null) {
                    throw new NonexistentEntityException("The sAccesos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SAccesos SAccesos;
            try {
                SAccesos = em.getReference(SAccesos.class, id);
                SAccesos.getIdAcceso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SAccesos with id " + id + " no longer exists.", enfe);
            }
            em.remove(SAccesos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SAccesos> findSAccesosEntities() {
        return findSAccesosEntities(true, -1, -1);
    }

    public List<SAccesos> findSAccesosEntities(int maxResults, int firstResult) {
        return findSAccesosEntities(false, maxResults, firstResult);
    }

    private List<SAccesos> findSAccesosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SAccesos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SAccesos findSAccesos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SAccesos.class, id);
        } finally {
            em.close();
        }
    }

    public int getSAccesosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SAccesos> rt = cq.from(SAccesos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    // Accesos que no tiene el perfil
    public List<SAccesos> accesosActuales(int id) {
        EntityManager em = getEntityManager();

        List<SAccesos> listAccesos = new ArrayList<>();

        TypedQuery<SAccesos> query;
        query = em.createQuery("SELECT a FROM SAccesos a JOIN SPerfilesAccesos spa "
                + "ON a.idAcceso = spa.sPerfilesAccesosPK.idAcceso "
                + "WHERE spa.sPerfilesAccesosPK.idPerfil = :idPerfil", SAccesos.class);
        query.setParameter("idPerfil", id);

        listAccesos = query.getResultList();

        return listAccesos;
    }

    // Accesos que tiene el perfil
    public List<SAccesos> accesosDisponibles(int id) {

        EntityManager em = getEntityManager();

        List<SAccesos> listAccesos = new ArrayList<>();
        TypedQuery<SAccesos> query;
        query = em.createQuery("SELECT a FROM SAccesos a WHERE a.idAcceso not in "
                + "(SELECT r.sPerfilesAccesosPK.idAcceso FROM SPerfilesAccesos r "
                + "WHERE r.sPerfilesAccesosPK.idPerfil = :idPerfil)", SAccesos.class);
        query.setParameter("idPerfil", id);

        listAccesos = query.getResultList();
        return listAccesos;
    }

    public List<SAccesos> accesosConNombre(List<String> accesosNombres, int idPerfil) {

        List<SAccesos> listAccesos = new ArrayList<>();
        EntityManager em = getEntityManager();

        //Elimina accesos anteriores
        TypedQuery<SPerfilesAccesos> query;
        query = em.createQuery("DELETE FROM SPerfilesAccesos spa WHERE spa.sPerfilesAccesosPK.idPerfil = :idPerfil", SPerfilesAccesos.class);
        em.getTransaction().begin();
        query.setParameter("idPerfil", idPerfil).executeUpdate();
        em.getTransaction().commit();
        
        if (!accesosNombres.isEmpty()) {

            TypedQuery<SAccesos> querySA;
            querySA = em.createNamedQuery("SAccesos.findByNombreAcceso", SAccesos.class);
            querySA.setParameter("nombreAcceso", accesosNombres);

            listAccesos = querySA.getResultList();

            return listAccesos;
        } else {
            return listAccesos;
        }

    }
}
