package com.pucmm.practica4.services;

import com.pucmm.practica4.entidades.Paste;
import com.pucmm.practica4.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by john on 25/06/17.
 */

public class PasteServices extends GestionDb<Paste>  {

    private static PasteServices instancia;
    private PasteServices (){
        super(Paste.class);
    }

    public static PasteServices getInstancia(){
        if(instancia==null){
            instancia = new PasteServices();
        }
        return instancia;
    }

    public Boolean deleteByDate(long date){
        EntityManager entityManager = getEntityManager();
        Query query= entityManager.createQuery("delete from Paste p where p.fechaExpiracion<=:date");
        query.setParameter("date", date);
        return true;
    }

    public List<Paste> getPasteByCantAccAndPublic(int startPosition){

        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select p from Paste p where p.tipoExposicion =:tipoexposicion ORDER BY p.cantidadVista DESC ");
                query.setParameter("tipoexposicion","public");
                query.setFirstResult(startPosition);
                query.setMaxResults(10);
        return query.getResultList();
    }

    public List<Paste> findLastPaste(int val1){
        EntityManager entityManager = getEntityManager();
        Query query= entityManager.createQuery( "select p from Paste p");
        if(val1<0){
            val1 =13+val1;
            query.setFirstResult(0);
            query.setMaxResults(val1);
        }else{
            query.setFirstResult(val1);
            query.setMaxResults(13);
        }

        return query.getResultList();
    }

}
