package com.chaiyot.javaspringmoviecollection.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class MovieCategoryRepo {

	@PersistenceContext

	private EntityManager entityManager;

//	@Transactional
//	public List<MovieActors> findAll() {
//		Query query = (Query) entityManager.createQuery("from MovieActors"); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง customer
//		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ
//
//	}

	@Transactional
	public List<MovieCategory> findAll() {
		Query query = (Query) entityManager.createQuery("from MovieCategory"); // สร้างคำสั่ง SELECT ข้อมูลจากตาราง
																				// customer
		return query.getResultList(); // ดึงรายการผลลัพธ์จากการ Query ส่งกลับ

	}

	public MovieCategory findById(Integer id) {
		return entityManager.find(MovieCategory.class, id); // ค้นหา Customer ตาม id
	}

	@Transactional
	public void delete(Integer id) {
		// ค้นหาตาม id ทีlต้องการลบ
		Query query = entityManager.createNativeQuery(
				"DELETE FROM movies_categories WHERE movies_id = ?");
		query.setParameter(1, id);
		query.executeUpdate();
	}
	public List<MovCategory> findAllID(Integer id) {

		try {
            String sql = "SELECT IFNULL(mc.id, 0), c.category_id,c.category, mc.movies_id  "
            		+ "FROM categories c LEFT JOIN movies_categories mc ON mc.category_id = c.category_id "
            		+ "WHERE mc.movies_id = ? OR mc.movies_id IS NULL";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, id);
			List<Object[]> list = query.getResultList();
			List<MovCategory> mclist = new ArrayList<MovCategory>();
			for (Object[] obj : list) {
				MovCategory mc = new MovCategory();
				mc.setMovies_categories_id((int) obj[0]);
				mc.setCategory_id((int) obj[1]);
				mc.setCategory((String) obj[2]);
				mclist.add(mc);
			}
			return mclist;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	@Transactional
	public List<MovieCategory> save(List<MovieCategory> ad) {

		String sql = "INSERT INTO movies_categories(category_id, movies_id, deleted) VALUES ";

		for (int i = 0; i < ad.size(); i++) {
			if (i > 0) {
				sql += ",";
			}
			sql += "(?, ?, ?)";
		}

		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < ad.size(); i++) {
			MovieCategory a = ad.get(i);
			int parameterIndex = i * 3;

			query.setParameter(parameterIndex + 1, a.getCategories().getCategory_id())
					.setParameter(parameterIndex + 2, a.getMovies().getMovies_id())
					.setParameter(parameterIndex + 3,false);
		}

		query.executeUpdate();
		return ad;
	}
}
