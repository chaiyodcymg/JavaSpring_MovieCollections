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
		return entityManager.find(MovieActors.class, id);
	}

	public List<RoleActor> findAllID(Integer id) {
		
		try {
			
			Query query = entityManager.createNativeQuery(
					"SELECT ma.id as id, ma.role, a.actor_name, a.actors_id, ma.movies_id FROM movie_actors ma JOIN actors a ON ma.actors_id = a.actors_id WHERE ma.movies_id = ?");
			query.setParameter(1, id);
			List<Object[]> list = query.getResultList();
			List<RoleActor> ralist = new ArrayList<RoleActor>();
			for (Object[] obj : list) {
				RoleActor ra = new RoleActor();
				ra.setActors_id((int) obj[0]);
				ra.setRole((String)obj[1]);
				ra.setActorName((String)obj[2]);
				ralist.add(ra);
			}
			return ralist;
		} catch (NoResultException e) {
			return null;
		}

	}

//	public List<MovieActors> findAllID(Integer id) {
//		try {
//			Query query = entityManager.createNativeQuery(
//					"SELECT movie_actors.id, movie_actors.role, movie_actors.movies_id,"
//							+ " movie_actors.actors_id FROM movies "
//							+ "JOIN movie_actors ON movies.movies_id = movie_actors.movies_id JOIN actors ON actors.actors_id = movie_actors.actors_id"
//							+ " WHERE movies.movies_id = ?",
//					Actors.class);
//			query.setParameter(1, id);
//		
//			return query.getResultList();
//
//		} catch (NoResultException e) {
//			return null;
//		}
//
//	}

	@Transactional
	public MovieActors save(MovieActors ad) {
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
