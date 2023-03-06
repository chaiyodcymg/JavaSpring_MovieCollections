package com.chaiyot.javaspringmoviecollection.model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class MoviesActorsRepo {
	
	@PersistenceContext
	private EntityManager entityManager; 


	@Transactional
	public List<MoviesActors> findAll() {
		Query query = (Query) entityManager.createQuery("from MovieActors"); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง customer
		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ

	}
	
	public MoviesActors findById(Integer id) {
		return entityManager.find(MoviesActors.class, id); // ค้นหา Customer ตาม id
	}
	
	@Transactional
	public List<MoviesActors>   save( List<MoviesActors>  ad) {
		
		  String sql = "INSERT INTO movies_actors(actors_id, movies_id, role) " +
		            "VALUES ";

		    for (int i = 0; i <ad.size(); i++) {
		        if (i > 0) {
		            sql += ",";
		        }
		        sql += "(?, ?, ?)";
		    }

		    Query query = entityManager.createNativeQuery(sql);

		    for (int i = 0; i < ad.size(); i++) {
		    	MoviesActors a = ad.get(i);
		        int parameterIndex = i * 3;

		        query.setParameter(parameterIndex + 1, a.getActors().getActors_id())
		             .setParameter(parameterIndex + 2, a.getMovies().getMovies_id())
		             .setParameter(parameterIndex + 3, a.getRole());
		             
		    }

		     query.executeUpdate();

		return ad;
	}
	


}


