package com.chaiyot.javaspringmoviecollection.model;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class CategorieRepo {
	@PersistenceContext
	private EntityManager entityManager; 
	
	@Transactional
	public List<Categories> findAll() {
		Query query = (Query) entityManager.createQuery("from Categories"); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง customer
		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ

	}
	

	public Categories findById(Integer id) {
		return entityManager.find(Categories.class, id); // ค้นหา Customer ตาม id
	}
	
	@Transactional
	public Categories  save(Categories  ad) {
		entityManager.persist(ad); // insert กรณีไม่มีค่า id ใน object หรือ update กรณีมีค่า id ใน object
		return ad;
	}
	

}
