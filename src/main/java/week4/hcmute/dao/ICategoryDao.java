package week4.hcmute.dao;
import java.util.List;

import week4.hcmute.models.CatogryModel;

public interface ICategoryDao {
	List<CatogryModel> findAll();
	CatogryModel findById(int id);
	CatogryModel findByname(String name);
	void insert(CatogryModel category);
	void update(CatogryModel category);
	void delete(int id);
	void updateStatus(int id, int status);
	
	
	
	
}
