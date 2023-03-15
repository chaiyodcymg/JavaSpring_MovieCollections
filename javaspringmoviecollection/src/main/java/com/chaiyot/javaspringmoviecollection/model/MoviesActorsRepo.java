package com.chaiyot.javaspringmoviecollection.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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

	@Transactional
	public List<MoviesActors> findAll() {
		Query query = (Query) entityManager.createQuery("from MoviesActors"); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง
																				// customer
		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ

	}

	public MoviesActors findById(Integer id) {
		return entityManager.find(MoviesActors.class, id); // ค้นหา Customer ตาม id
	}

	@Transactional
	public void delete(Integer id) {
		// ค้นหาตาม id ทีlต้องการลบ
		Query query = entityManager.createNativeQuery(
				"DELETE FROM movies_actors WHERE movies_id = ?");
		query.setParameter(1, id);
		query.executeUpdate();
	}

	public List<RoleActor> findAllID(Integer id) {

		try {

			Query query = entityManager.createNativeQuery(
					"SELECT ma.id, ma.role, a.actor_name, a.actors_id, ma.movies_id, a.image FROM movies_actors ma JOIN actors a ON ma.actors_id = a.actors_id WHERE ma.movies_id = ?");
			query.setParameter(1, id);
			List<Object[]> list = query.getResultList();
			List<RoleActor> ralist = new ArrayList<RoleActor>();
			for (Object[] obj : list) {
				RoleActor ra = new RoleActor();
				ra.setActors_id((int) obj[0]);
				ra.setRole((String) obj[1]);
				ra.setActors_id((int) obj[3]);
				ra.setActorName((String) obj[2]);
				ra.setImage((String) obj[5]);
				ralist.add(ra);
			}
			return ralist;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	
	public List<ShowMovieCategory> findActormovie(Integer id) {

		try {
            String sql = "SELECT m.movies_id , GROUP_CONCAT(c.category) as categories, m.moviename, m.posterimage FROM actors a "
            		+ "   JOIN movies_actors ma ON ma.actors_id = a.actors_id "
            		+ "   JOIN movies m ON ma.movies_id = m.movies_id "
            		+ "   JOIN movies_categories mc ON m.movies_id = mc.movies_id "
            		+ "   JOIN categories c ON c.category_id = mc.category_id "
            		+ "   WHERE ma.actors_id = ? "
            		+ "   GROUP BY m.movies_id;";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, id);
			List<Object[]> list = query.getResultList();
			List<ShowMovieCategory> movlist = new ArrayList<ShowMovieCategory>();
			for (Object[] obj : list) {
				ShowMovieCategory smc = new ShowMovieCategory();
				smc.setMovies_id((int) obj[0]);
				smc.setMoviename((String) obj[2]);
				smc.setCategory((String) obj[1]);
				smc.setPosterimage((String) obj[3]);
				movlist.add(smc);
			}
			return movlist;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	

	@Transactional
	public List<MoviesActors> save(List<MoviesActors> ad) {

		String sql = "INSERT INTO movies_actors(actors_id, movies_id, role, deleted) VALUES ";

		for (int i = 0; i < ad.size(); i++) {
			if (i > 0) {
				sql += ",";
			}
			sql += "(?, ?, ?, ?)";
		}

		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < ad.size(); i++) {
			MoviesActors a = ad.get(i);
			int parameterIndex = i * 4;

			query.setParameter(parameterIndex + 1, a.getActors().getActors_id())
					.setParameter(parameterIndex + 2, a.getMovies().getMovies_id())
					.setParameter(parameterIndex + 3, a.getRole())
					.setParameter(parameterIndex + 4, false);
		}

		query.executeUpdate();
		return ad;
	}
	
	
	public List<RoleActor> searchActor(String actor) {

		try {
            String sql = "SELECT a.actors_id, a.actor_name, a.image, a.birthday, a.gender, a.deleted FROM actors a "
            		+ "  JOIN movies_actors ma ON ma.actors_id = a.actors_id "
            		+ "  WHERE ma.role LIKE '%" + actor + "%' OR a.actor_name LIKE '%" + actor + "%' "
            		+ "  GROUP BY a.actor_name";
			Query query = entityManager.createNativeQuery(sql);

			List<Object[]> list = query.getResultList();
			List<RoleActor> actorlist = new ArrayList<RoleActor>();
			for (Object[] obj : list) {
				RoleActor ract = new RoleActor();
				ract.setActors_id((int) obj[0]);
				ract.setActorName((String) obj[1]);
				ract.setImage((String) obj[2]);
				

				ract.setBirthday((Date) obj[3]);
				ract.setGender((String) obj[4]);
				ract.setDeleted((Boolean) obj[5]);
				actorlist.add(ract);
			}
			return actorlist;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

}
