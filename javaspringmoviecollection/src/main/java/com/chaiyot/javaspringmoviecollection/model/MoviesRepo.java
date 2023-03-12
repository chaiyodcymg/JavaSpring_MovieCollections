package com.chaiyot.javaspringmoviecollection.model;

import java.sql.Date;
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
public class MoviesRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public List<Movies> findAll() {
		Query query = (Query) entityManager.createNativeQuery("SELECT * FROM movies WHERE deleted = 0", Movies.class); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง customer
		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ

	}

	public Movies findById(Integer id) {
		return entityManager.find(Movies.class, id); // ค้นหา Customer ตาม id
	}

	@Transactional
	public Movies save(Movies ad) {
		entityManager.persist(ad); // insert กรณีไม่มีค่า id ใน object หรือ update กรณีมีค่า id ใน object
		return ad;
	}
	
	@Transactional
	public void deleteMovByID(int id) {
		try {
			Query query = entityManager.createNativeQuery("UPDATE movies SET deleted = ? WHERE movies_id = ?");
			query.setParameter(1, true);
	        query.setParameter(2, id);
	        query.executeUpdate();
		    
		} catch (Exception e) {
			System.out.println(e);
		}

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
