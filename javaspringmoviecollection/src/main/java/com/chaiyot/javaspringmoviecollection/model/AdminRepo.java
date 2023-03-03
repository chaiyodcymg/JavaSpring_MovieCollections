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
public class AdminRepo {
	
	@PersistenceContext
	private EntityManager entityManager; 
	
	@Transactional
	public List<Admin> findAll() {
		Query query = (Query) entityManager.createQuery("from Admin"); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง customer
		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ

	}
	
	public Admin findById(Integer id) {
		return entityManager.find(Admin.class, id); // ค้นหา Customer ตาม id
	}
	
	@Transactional
	public Admin  save(Admin  ad) {
		entityManager.persist(ad); // insert กรณีไม่มีค่า id ใน object หรือ update กรณีมีค่า id ใน object
		return ad;
	}
	
	public Admin findByUsernameAndPassword(String username, String password) {
		try {
			Query query = entityManager.createNativeQuery("SELECT * FROM admin WHERE username = ? AND password = ?", Admin.class);
	        query.setParameter(1, username);
	        query.setParameter(2, password);
	        return (Admin) query.getSingleResult();
		        
		} catch (NoResultException e) {
			 return null;
		}

    }

}
