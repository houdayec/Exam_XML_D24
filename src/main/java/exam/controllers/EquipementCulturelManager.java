package exam.controllers;

import exam.database.EntityManager;
import exam.model.EquipementCulturel;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EquipementCulturelManager {

    private static List<EquipementCulturel> localList;

    /**
     * JPQL QUERIES
     */
    private static Query findAllEquipments;

    static{
        // TODO ?
        findAllEquipments  = EntityManager.getEntityManager().createQuery("SELECT b FROM EquipementCulturel b");
        localList = findAllEquipments.getResultList();
        System.out.println("Taille liste locale " + localList.size());
    }

    public List<EquipementCulturel> getAllEquipments(){
        return findAllEquipments.getResultList();
    }

    public EquipementCulturel findEquipmentById(String id){
        return EntityManager.getEntityManager().find(EquipementCulturel.class, id);
    }

    public List<EquipementCulturel> findEquipmentByCity(String nameCity){
        List<EquipementCulturel> fetchedList;
        fetchedList = EntityManager.getEntityManager().createQuery("SELECT b FROM EquipementCulturel b WHERE b.city=:nameCity").setParameter("nameCity",nameCity).getResultList();
        return fetchedList;
    }

}
