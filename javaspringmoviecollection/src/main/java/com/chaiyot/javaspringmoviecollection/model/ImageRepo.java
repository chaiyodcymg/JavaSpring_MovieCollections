package com.chaiyot.javaspringmoviecollection.model;
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
public class ImageRepo {
	
	@PersistenceContext
	private EntityManager entityManager; 
	
	@Transactional
	public List<Image> findAll() {
		Query query = (Query) entityManager.createQuery("from Image"); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง customer
		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ

	}
	
	public Image findById(Integer id) {
		return entityManager.find(Image.class, id); // ค้นหา Customer ตาม id
	}
	
	@Transactional
	public Image  save(Image  ad) {
		entityManager.persist(ad); // insert กรณีไม่มีค่า id ใน object หรือ update กรณีมีค่า id ใน object
		return ad;
	}
	
	public Image findByUsernameAndPassword(String username, String password) {
		try {
			Query query = entityManager.createNativeQuery("SELECT * FROM image WHERE username = ? AND password = ?", Image.class);
	        query.setParameter(1, username);
	        query.setParameter(2, password);
	        return (Image) query.getSingleResult();
		        
		} catch (NoResultException e) {
			 return null;
		}

    }

}

