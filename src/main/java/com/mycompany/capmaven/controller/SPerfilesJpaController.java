/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.capmaven.controller;

import com.mycompany.capmaven.controller.exceptions.IllegalOrphanException;
import com.mycompany.capmaven.controller.exceptions.NonexistentEntityException;
import com.mycompany.capmaven.controller.exceptions.PreexistingEntityException;
import com.mycompany.capmaven.entity.SPerfiles;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.capmaven.entity.SUsuarios;
import java.util.ArrayList;
import java.util.Collection;
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
public class SPerfilesJpaController implements Serializable {

    public SPerfilesJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();

    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Respuesta create(SPerfiles SPerfiles) throws IllegalOrphanException, PreexistingEntityException, Exception {
        Respuesta respuesta = new Respuesta();

        if (SPerfiles.getSUsuariosCollection() == null) {
            SPerfiles.setSUsuariosCollection(new ArrayList<SUsuarios>());
        }
        List<String> illegalOrphanMessages = null;
        SUsuarios idUsuarioModificaOrphanCheck = SPerfiles.getIdUsuarioModifica();
        if (idUsuarioModificaOrphanCheck != null) {
            SPerfiles oldIdPerfilOfIdUsuarioModifica = idUsuarioModificaOrphanCheck.getIdPerfil();
            if (oldIdPerfilOfIdUsuarioModifica != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The SUsuarios " + idUsuarioModificaOrphanCheck + " already has an item of type SPerfiles whose idUsuarioModifica column cannot be null. Please make another selection for the idUsuarioModifica field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios idUsuarioModifica = SPerfiles.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica = em.getReference(idUsuarioModifica.getClass(), idUsuarioModifica.getIdUsuario());
                SPerfiles.setIdUsuarioModifica(idUsuarioModifica);
            }
            Collection<SUsuarios> attachedSUsuariosCollection = new ArrayList<SUsuarios>();
            for (SUsuarios SUsuariosCollectionSUsuariosToAttach : SPerfiles.getSUsuariosCollection()) {
                SUsuariosCollectionSUsuariosToAttach = em.getReference(SUsuariosCollectionSUsuariosToAttach.getClass(), SUsuariosCollectionSUsuariosToAttach.getIdUsuario());
                attachedSUsuariosCollection.add(SUsuariosCollectionSUsuariosToAttach);
            }
            SPerfiles.setSUsuariosCollection(attachedSUsuariosCollection);
            em.persist(SPerfiles);
            if (idUsuarioModifica != null) {
                idUsuarioModifica.setIdPerfil(SPerfiles);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            for (SUsuarios SUsuariosCollectionSUsuarios : SPerfiles.getSUsuariosCollection()) {
                SPerfiles oldIdPerfilOfSUsuariosCollectionSUsuarios = SUsuariosCollectionSUsuarios.getIdPerfil();
                SUsuariosCollectionSUsuarios.setIdPerfil(SPerfiles);
                SUsuariosCollectionSUsuarios = em.merge(SUsuariosCollectionSUsuarios);
                if (oldIdPerfilOfSUsuariosCollectionSUsuarios != null) {
                    oldIdPerfilOfSUsuariosCollectionSUsuarios.getSUsuariosCollection().remove(SUsuariosCollectionSUsuarios);
                    oldIdPerfilOfSUsuariosCollectionSUsuarios = em.merge(oldIdPerfilOfSUsuariosCollectionSUsuarios);
                }
            }
            respuesta.setIdRespuesta(0);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
            respuesta.setHead("Exito");
            respuesta.setMsg("Se ha añadido con éxito el perfil " + SPerfiles.getDescripcion() + ". Id de respuesta ");
            em.getTransaction().commit();
        } catch (Exception ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error al añadir el perfil " + SPerfiles.getDescripcion() + ". Id de respuesta ");
            if (findSPerfiles(SPerfiles.getIdPerfil()) != null) {
                throw new PreexistingEntityException("SPerfiles " + SPerfiles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return respuesta;
    }

    public Respuesta edit(SPerfiles SPerfiles) throws IllegalOrphanException, NonexistentEntityException, Exception {
        Respuesta respuesta = new Respuesta();

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfiles persistentSPerfiles = em.find(SPerfiles.class, SPerfiles.getIdPerfil());
            SUsuarios idUsuarioModificaOld = persistentSPerfiles.getIdUsuarioModifica();
            SUsuarios idUsuarioModificaNew = SPerfiles.getIdUsuarioModifica();
            Collection<SUsuarios> SUsuariosCollectionOld = persistentSPerfiles.getSUsuariosCollection();
            Collection<SUsuarios> SUsuariosCollectionNew = SPerfiles.getSUsuariosCollection();
            List<String> illegalOrphanMessages = null;
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                SPerfiles oldIdPerfilOfIdUsuarioModifica = idUsuarioModificaNew.getIdPerfil();
                if (oldIdPerfilOfIdUsuarioModifica != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The SUsuarios " + idUsuarioModificaNew + " already has an item of type SPerfiles whose idUsuarioModifica column cannot be null. Please make another selection for the idUsuarioModifica field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsuarioModificaNew != null) {
                idUsuarioModificaNew = em.getReference(idUsuarioModificaNew.getClass(), idUsuarioModificaNew.getIdUsuario());
                SPerfiles.setIdUsuarioModifica(idUsuarioModificaNew);
            }
            Collection<SUsuarios> attachedSUsuariosCollectionNew = new ArrayList<SUsuarios>();
            for (SUsuarios SUsuariosCollectionNewSUsuariosToAttach : SUsuariosCollectionNew) {
                SUsuariosCollectionNewSUsuariosToAttach = em.getReference(SUsuariosCollectionNewSUsuariosToAttach.getClass(), SUsuariosCollectionNewSUsuariosToAttach.getIdUsuario());
                attachedSUsuariosCollectionNew.add(SUsuariosCollectionNewSUsuariosToAttach);
            }
            SUsuariosCollectionNew = attachedSUsuariosCollectionNew;
            SPerfiles.setSUsuariosCollection(SUsuariosCollectionNew);
            SPerfiles = em.merge(SPerfiles);
            if (idUsuarioModificaOld != null && !idUsuarioModificaOld.equals(idUsuarioModificaNew)) {
                idUsuarioModificaOld.setIdPerfil(null);
                idUsuarioModificaOld = em.merge(idUsuarioModificaOld);
            }
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                idUsuarioModificaNew.setIdPerfil(SPerfiles);
                idUsuarioModificaNew = em.merge(idUsuarioModificaNew);
            }
            for (SUsuarios SUsuariosCollectionOldSUsuarios : SUsuariosCollectionOld) {
                if (!SUsuariosCollectionNew.contains(SUsuariosCollectionOldSUsuarios)) {
                    SUsuariosCollectionOldSUsuarios.setIdPerfil(null);
                    SUsuariosCollectionOldSUsuarios = em.merge(SUsuariosCollectionOldSUsuarios);
                }
            }
            for (SUsuarios SUsuariosCollectionNewSUsuarios : SUsuariosCollectionNew) {
                if (!SUsuariosCollectionOld.contains(SUsuariosCollectionNewSUsuarios)) {
                    SPerfiles oldIdPerfilOfSUsuariosCollectionNewSUsuarios = SUsuariosCollectionNewSUsuarios.getIdPerfil();
                    SUsuariosCollectionNewSUsuarios.setIdPerfil(SPerfiles);
                    SUsuariosCollectionNewSUsuarios = em.merge(SUsuariosCollectionNewSUsuarios);
                    if (oldIdPerfilOfSUsuariosCollectionNewSUsuarios != null && !oldIdPerfilOfSUsuariosCollectionNewSUsuarios.equals(SPerfiles)) {
                        oldIdPerfilOfSUsuariosCollectionNewSUsuarios.getSUsuariosCollection().remove(SUsuariosCollectionNewSUsuarios);
                        oldIdPerfilOfSUsuariosCollectionNewSUsuarios = em.merge(oldIdPerfilOfSUsuariosCollectionNewSUsuarios);
                    }
                }
            }
            respuesta.setIdRespuesta(0);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
            respuesta.setHead("Exito");
            respuesta.setMsg("Se ha actualizado con éxito el perfi con id " + SPerfiles.getIdPerfil() + ". Id de respuesta ");
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SPerfiles.getIdPerfil();
                if (findSPerfiles(id) == null) {
                    respuesta.setIdRespuesta(-1);
                    respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
                    respuesta.setHead("Error");
                    respuesta.setMsg("Hubo un error en la actualización del perfil con id " + SPerfiles.getIdPerfil() + ". Id de respuesta ");
                    throw new NonexistentEntityException("The sPerfiles with id " + id + " no longer exists.");
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

    public Respuesta destroy(Integer id) throws NonexistentEntityException {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfiles SPerfiles;
            try {
                SPerfiles = em.getReference(SPerfiles.class, id);
                SPerfiles.getIdPerfil();
            } catch (EntityNotFoundException enfe) {
                respuesta.setIdRespuesta(-1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
                respuesta.setHead("Error");
                respuesta.setMsg("Hubo un error en la eliminacion del perfil con id " + id + ". Id de respuesta ");
                throw new NonexistentEntityException("The SPerfiles with id " + id + " no longer exists.", enfe);
            }
            SUsuarios idUsuarioModifica = SPerfiles.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica.setIdPerfil(null);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            Collection<SUsuarios> SUsuariosCollection = SPerfiles.getSUsuariosCollection();
            for (SUsuarios SUsuariosCollectionSUsuarios : SUsuariosCollection) {
                SUsuariosCollectionSUsuarios.setIdPerfil(null);
                SUsuariosCollectionSUsuarios = em.merge(SUsuariosCollectionSUsuarios);
            }
            respuesta.setIdRespuesta(0);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
            respuesta.setHead("Exito");
            respuesta.setMsg("Se ha eliminado con éxito el perfil con id " + id + ". Id de respuesta ");
            em.remove(SPerfiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return respuesta;
    }

    public List<SPerfiles> findSPerfilesEntities() {
        return findSPerfilesEntities(true, -1, -1);
    }

    public List<SPerfiles> findSPerfilesEntities(int maxResults, int firstResult) {
        return findSPerfilesEntities(false, maxResults, firstResult);
    }

    private List<SPerfiles> findSPerfilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SPerfiles.class));
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

    public SPerfiles findSPerfiles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SPerfiles.class, id);
        } finally {
            em.close();
        }
    }

    public int getSPerfilesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SPerfiles> rt = cq.from(SPerfiles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
