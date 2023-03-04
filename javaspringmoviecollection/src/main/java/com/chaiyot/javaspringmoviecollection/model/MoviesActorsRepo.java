package com.chaiyot.javaspringmoviecollection.model;
import java.util.ArrayList;
import java.util.Iterator;
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
public class MoviesActorsRepo {
	
	@PersistenceContext
	private EntityManager entityManager; 
	
//	@Transactional
//	public List<MovieActors> findAll() {
//		Query query = (Query) entityManager.createQuery("from MovieActors"); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง customer
//		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ
//
//	}
	
	public MovieActors findById(Integer id) {
		return entityManager.find(MovieActors.class, id); // ค้นหา Customer ตาม id
	}
	
	@Transactional
	public MovieActors  save(MovieActors ad) {
		String sql = "INSERT INTO movie_actors (actors_id, movies_id, role) VALUE (:actors_id,:movies_id,:role)";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> batchArgs = new ArrayList<>();
//		for(int index = 0 ; index < ad.size() ; index++) {
//			Object[] args = new Object[] {ad.get(index).getActors_id().getActors_id(), ad.get(index).getMovies_id().getMovies_id(),ad.get(index).getRole()};
//			batchArgs.add(args);
//			System.out.println(ad.get(index).getActors_id().getActors_id() + " " + ad.get(index).getMovies_id().getMovies_id()+ " "+  ad.get(index).getRole());
			query.setParameter("actors_id", ad.getActors_id().getActors_id());
			query.setParameter("movies_id", ad.getMovies_id().getMovies_id());
			query.setParameter("role", ad.getRole());
			query.executeUpdate();
//		}
//		query.setParameter("batchArgs", batchArgs);
//		query.executeUpdate();
		return ad;
	}
	


}


