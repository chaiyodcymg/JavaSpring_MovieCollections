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
public class MoviesRepo {
	
	@PersistenceContext
	private EntityManager entityManager; 
	
	@Transactional
	public List<Movies> findAll() {
		Query query = (Query) entityManager.createQuery("from Movies"); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง customer
		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ

	}
	
	public Movies findById(Integer id) {
		return entityManager.find(Movies.class, id); // ค้นหา Customer ตาม id
	}
	
	@Transactional
	public Movies  save(Movies  ad) {
		entityManager.persist(ad); // insert กรณีไม่มีค่า id ใน object หรือ update กรณีมีค่า id ใน object
		return ad;
	}
	


}

