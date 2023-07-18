/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.capmaven.controller;

import com.mycompany.capmaven.controller.exceptions.NonexistentEntityException;
import com.mycompany.capmaven.controller.exceptions.PreexistingEntityException;
import com.mycompany.capmaven.entity.SPerfiles;
import com.mycompany.capmaven.entity.SPerfilesAccesos;
import com.mycompany.capmaven.entity.SPerfilesAccesosPK;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import objetos.Respuesta;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class SPerfilesAccesosJpaController implements Serializable {

    public SPerfilesAccesosJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Respuesta create(SPerfilesAccesos SPerfilesAccesos) throws PreexistingEntityException, Exception {
        Respuesta respuesta = new Respuesta();

        if (SPerfilesAccesos.getSPerfilesAccesosPK() == null) {
            SPerfilesAccesos.setSPerfilesAccesosPK(new SPerfilesAccesosPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(SPerfilesAccesos);
            em.getTransaction().commit();

            respuesta.setIdRespuesta(0);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
            respuesta.setHead("Exito");
            respuesta.setMsg("Se han actualizado los accesos con éxito el perfil. Id de respuesta ");
        } catch (Exception ex) {
            if (findSPerfilesAccesos(SPerfilesAccesos.getSPerfilesAccesosPK()) != null) {
                throw new PreexistingEntityException("SPerfilesAccesos " + SPerfilesAccesos + " already exists.", ex);
            }
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Ha habido un error al crear el perfil. Id de respuesta ");
            throw ex;
        } finally {
            if (em != null) {
                em.close();

            }
        }
        return respuesta;
    }

    public Respuesta edit(SPerfilesAccesos SPerfilesAccesos) throws NonexistentEntityException, Exception {
        Respuesta respuesta = new Respuesta();

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfilesAccesos = em.merge(SPerfilesAccesos);
            em.getTransaction().commit();

            respuesta.setIdRespuesta(0);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
            respuesta.setHead("Exito");
            respuesta.setMsg("Se ha editado con éxito el perfil. Id de respuesta ");
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SPerfilesAccesosPK id = SPerfilesAccesos.getSPerfilesAccesosPK();
                if (findSPerfilesAccesos(id) == null) {
                    throw new NonexistentEntityException("The sPerfilesAccesos with id " + id + " no longer exists.");
                }
            }

            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("No se ha podido editar el perfil. Id de respuesta ");

            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return respuesta;
    }

    public Respuesta destroy(SPerfilesAccesosPK id) throws NonexistentEntityException {

        Respuesta respuesta = new Respuesta();

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfilesAccesos SPerfilesAccesos;
            try {
                SPerfilesAccesos = em.getReference(SPerfilesAccesos.class, id);
                SPerfilesAccesos.getSPerfilesAccesosPK();
            } catch (EntityNotFoundException enfe) {
                respuesta.setIdRespuesta(-1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
                respuesta.setHead("Error");
                respuesta.setMsg("No se ha podido eliminar el perfil. Id de respuesta ");
                throw new NonexistentEntityException("The SPerfilesAccesos with id " + id + " no longer exists.", enfe);
            }
            em.remove(SPerfilesAccesos);
            em.getTransaction().commit();
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
            respuesta.setHead("Éxito");
            respuesta.setMsg("Se ha eliminado el perfil. Id de respuesta ");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return respuesta;
    }

    public List<SPerfilesAccesos> findSPerfilesAccesosEntities() {
        return findSPerfilesAccesosEntities(true, -1, -1);
    }

    public List<SPerfilesAccesos> findSPerfilesAccesosEntities(int maxResults, int firstResult) {
        return findSPerfilesAccesosEntities(false, maxResults, firstResult);
    }

    private List<SPerfilesAccesos> findSPerfilesAccesosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SPerfilesAccesos.class));
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

    public SPerfilesAccesos findSPerfilesAccesos(SPerfilesAccesosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SPerfilesAccesos.class, id);
        } finally {
            em.close();
        }
    }

    public int getSPerfilesAccesosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SPerfilesAccesos> rt = cq.from(SPerfilesAccesos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void asignarAccesos(SPerfiles perfil, List<String> accesos, int idUsuario) throws Exception {

        EntityManager em = getEntityManager();

        TypedQuery<SPerfilesAccesos> query;
        query = em.createQuery("DELETE FROM SPerfilesAccesos spa WHERE spa.sPerfilesAccesosPK.idPerfil = :idPerfil", SPerfilesAccesos.class);
        em.getTransaction().begin();
        query.setParameter("idPerfil", perfil.getIdPerfil()).executeUpdate();

        em.getTransaction().commit();

    }
}
