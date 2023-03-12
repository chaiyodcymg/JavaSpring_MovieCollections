package com.chaiyot.javaspringmoviecollection.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	public MovieDetail findDetail(Integer id) {

		try {
            String sql = "SELECT GROUP_CONCAT(c.category) as category, m.moviename, "
            		+ "m.image, m.description, m.release_year, a.actor_name, ma.actors_id, ma.role, a.image as actor_image FROM categories c "
            		+ "JOIN movies_categories mc ON mc.category_id = c.category_id "
            		+ "JOIN movies m ON mc.movies_id = m.movies_id "
            		+ "JOIN movies_actors ma ON mc.movies_id = ma.movies_id "
            		+ "JOIN actors a ON ma.actors_id = a.actors_id "
            		+ "WHERE mc.movies_id = ?";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, id);
			Object[] obj = (Object[]) query.getSingleResult();
		
			MovieDetail md = new MovieDetail();
				md.setActors_id((int) obj[6]);
				md.setActor_name((String) obj[5]);
				md.setCategory((String) obj[0]);
				md.setDescription((String) obj[3]);
				md.setImage((String) obj[2]);
				md.setMoviename((String) obj[1]);
				md.setRelease_year((String) obj[4]);
				md.setRole((String) obj[7]);
				md.setActor_image((String) obj[8]);
		
//System.out.println("actname " + md.getActor_name());
			
			return md;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	
	
	public  List<ShowMovieCategory> findAllMov() {

		try {
            String sql = "SELECT GROUP_CONCAT(c.category) as category, mc.movies_id, m.moviename, m.posterimage  FROM "
            		+ "categories c JOIN movies_categories mc ON mc.category_id = c.category_id "
            		+ "JOIN movies m ON mc.movies_id = m.movies_id WHERE m.deleted = 0 GROUP BY movies_id";
			Query query = entityManager.createNativeQuery(sql);

			List<Object[]> list = query.getResultList();

				
			List<ShowMovieCategory> listmovcat = new ArrayList<>();
			for (Object[] obj : list) {
				ShowMovieCategory movcat = new ShowMovieCategory();
				movcat.setMovies_id((int) obj[1]);
				movcat.setMoviename((String) obj[2]);
				movcat.setPosterimage((String) obj[3]);
				movcat.setCategory((String) obj[0]);
				
				listmovcat.add(movcat);
			}
	
	
			return  listmovcat ;
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

	public List<ShowMovieCategory> searchMovie(String movie) {

		try {
            String sql = "SELECT m.movies_id, m.moviename, m.posterimage, GROUP_CONCAT(c.category) as category FROM categories c "
            		+ "	JOIN movies_categories mc ON mc.category_id = c.category_id "
            		+ " JOIN movies m ON mc.movies_id = m.movies_id"
            		+ "	WHERE m.moviename LIKE '%" + movie + "%'"
            		+ " GROUP BY m.moviename;";
			Query query = entityManager.createNativeQuery(sql);

			List<Object[]> list = query.getResultList();
			List<ShowMovieCategory> movcatlist = new ArrayList<ShowMovieCategory>();
			for (Object[] obj : list) {
				ShowMovieCategory movcat = new ShowMovieCategory();
				movcat.setMovies_id((int) obj[0]);
				movcat.setMoviename((String) obj[1]);
				movcat.setPosterimage((String) obj[2]);
				movcat.setCategory((String) obj[3]);
				
				movcatlist.add(movcat);
			}
			return movcatlist;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	

}
