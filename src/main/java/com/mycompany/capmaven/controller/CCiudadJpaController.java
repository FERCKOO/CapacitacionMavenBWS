/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.capmaven.controller;

import com.mycompany.capmaven.controller.exceptions.NonexistentEntityException;
import com.mycompany.capmaven.controller.exceptions.PreexistingEntityException;
import com.mycompany.capmaven.entity.CCiudad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.capmaven.entity.SUsuarios;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import objetos.Respuesta;
import utils.LocalEntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class CCiudadJpaController implements Serializable {
    
    private EntityManagerFactory emf = null;

    public CCiudadJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Respuesta create(CCiudad CCiudad) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios idUsuario = CCiudad.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                CCiudad.setIdUsuario(idUsuario);
            }
            em.persist(CCiudad);
            if (idUsuario != null) {
                idUsuario.getCCiudadCollection().add(CCiudad);
                idUsuario = em.merge(idUsuario);

                respuesta.setIdRespuesta(0);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
                respuesta.setHead("Exito");
                respuesta.setMsg("Se ha añadido con éxito la ciudad. Id de respuesta ");
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error en el añadido de la ciudad. Id de respuesta ");
            if (findCCiudad(CCiudad.getIdCiudad()) != null) {
                throw new PreexistingEntityException("CCiudad " + CCiudad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return respuesta;
    }

    public Respuesta edit(CCiudad CCiudad) throws NonexistentEntityException, Exception {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CCiudad persistentCCiudad = em.find(CCiudad.class, CCiudad.getIdCiudad());
            SUsuarios idUsuarioOld = persistentCCiudad.getIdUsuario();
            SUsuarios idUsuarioNew = CCiudad.getIdUsuario();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                CCiudad.setIdUsuario(idUsuarioNew);
            }
            CCiudad = em.merge(CCiudad);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getCCiudadCollection().remove(CCiudad);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getCCiudadCollection().add(CCiudad);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            respuesta.setIdRespuesta(0);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
            respuesta.setHead("Exito");
            respuesta.setMsg("Se ha actualizado con éxito la ciudad. Id de respuesta ");
            em.getTransaction().commit();
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error en la actualización de la ciudad. Id de respuesta ");
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CCiudad.getIdCiudad();
                if (findCCiudad(id) == null) {
                    throw new NonexistentEntityException("The cCiudad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return respuesta;
    }

    public Respuesta destroy(Long id) throws NonexistentEntityException {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CCiudad CCiudad;
            try {
                CCiudad = em.getReference(CCiudad.class, id);
                CCiudad.getIdCiudad();
            } catch (EntityNotFoundException enfe) {
                respuesta.setIdRespuesta(-1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
                respuesta.setHead("Error");
                respuesta.setMsg("Hubo un error en la eliminacion de la ciudad. Id de respuesta ");
                throw new NonexistentEntityException("The CCiudad with id " + id + " no longer exists.", enfe);
            }
            SUsuarios idUsuario = CCiudad.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getCCiudadCollection().remove(CCiudad);
                idUsuario = em.merge(idUsuario);
            }
            respuesta.setIdRespuesta(0);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
            respuesta.setHead("Exito");
            respuesta.setMsg("Se ha eliminado con éxito la ciudad. Id de respuesta ");

            em.remove(CCiudad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return respuesta;
    }

    public List<CCiudad> findCCiudadEntities() {
        return findCCiudadEntities(true, -1, -1);
    }

    public List<CCiudad> findCCiudadEntities(int maxResults, int firstResult) {
        return findCCiudadEntities(false, maxResults, firstResult);
    }

    private List<CCiudad> findCCiudadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CCiudad.class));
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

    public CCiudad findCCiudad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CCiudad.class, id);
        } finally {
            em.close();
        }
    }

    public int getCCiudadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CCiudad> rt = cq.from(CCiudad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
