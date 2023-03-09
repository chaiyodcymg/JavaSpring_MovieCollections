package com.chaiyot.javaspringmoviecollection.model;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class ActorsRepo {
	
	@PersistenceContext
	private EntityManager entityManager; 
	
	@Transactional
	public List<Actors> findAll() {
		Query query = (Query) entityManager.createNativeQuery("SELECT * FROM actors WHERE deleted = 0", Actors.class); 
		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ

	}
	
	

	public Actors findById(Integer id) {
		return entityManager.find(Actors.class, id); // ค้นหา Customer ตาม id
	}
	
	@Transactional
	public Actors  save(Actors  ad) {
		entityManager.persist(ad); // insert กรณีไม่มีค่า id ใน object หรือ update กรณีมีค่า id ใน object
		return ad;
	}
	
	public Actors findByUsernameAndPassword(String username, String password) {
		try {
			Query query = entityManager.createNativeQuery("SELECT * FROM actors WHERE username = ? AND password = ?", Actors.class);
	        query.setParameter(1, username);
	        query.setParameter(2, password);
	        return (Actors) query.getSingleResult();
		        
		} catch (NoResultException e) {
			 return null;
		}

    }

}

