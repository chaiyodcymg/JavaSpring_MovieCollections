package com.chaiyot.javaspringmoviecollection.model;

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
	
	
	
	
	public HashMap<Integer, List<ShowMovieCategory>> findAllMov() {

		try {
            String sql = "SELECT c.category_id,c.category, mc.movies_id, m.moviename, m.posterimage  FROM "
            		+ "categories c JOIN movies_categories mc ON mc.category_id = c.category_id "
            		+ "JOIN movies m ON mc.movies_id = m.movies_id";
			Query query = entityManager.createNativeQuery(sql);

			List<Object[]> list = query.getResultList();
			HashMap<Integer, List<ShowMovieCategory>> showmovcat = new HashMap<>();
			
//			List<MovCategory> mclist = new ArrayList<MovCategory>();
			int index = 0;
			 String category= "";
			int key = 0;	
			for (int i = 0 ; i < list.size();i++) {

				
				if(key == 0) {			
					key =  (int) list.get(i)[2];
				}else if(key !=  (int) list.get(i)[2] ) {
				
					List<ShowMovieCategory> listmovcat = new ArrayList<>();
				
					
					ShowMovieCategory movcat = new ShowMovieCategory();
					movcat.setCategory(category.substring(0,category.length()-3));
					movcat.setMovies_id((int)list.get(i-1)[2]);
					movcat.setMoviename((String) list.get(i-1)[3]);
					movcat.setPosterimage((String)list.get(i-1)[4]);
					listmovcat.add(movcat);

					showmovcat.put( (int) list.get(i-1)[2] , listmovcat);
					System.out.println("key !=  (int) list.get(i)[2] "+(int) list.get(i-1)[2]);
					category= "";
					key =  (int) list.get(i)[2];
					
					
				}else if( i == list.size()-1 ) {
					List<ShowMovieCategory> listmovcat = new ArrayList<>();
					category += (String) list.get(i)[1];
	
					ShowMovieCategory movcat = new ShowMovieCategory();
					movcat.setCategory(category);
					movcat.setMovies_id((int)list.get(i)[2]);
					movcat.setMoviename((String) list.get(i)[3]);
					movcat.setPosterimage((String)list.get(i)[4]);
					listmovcat.add(movcat);
					
						
						
					showmovcat.put( (int) list.get(i)[2] , listmovcat);
					
					System.out.println("i == list.size()-1 "+(int) list.get(i)[2]);
				}
				
				category += (String) list.get(i)[1]+" , ";

			
				
				index++;
			}
	
	
			return showmovcat;
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
