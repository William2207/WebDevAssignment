package week4.hcmute.services.impl;

import java.util.List;

import week4.hcmute.dao.ICategoryDao;
import week4.hcmute.dao.impl.CategoryDaoImpl;
import week4.hcmute.models.CatogryModel;
import week4.hcmute.services.ICategoryServices;

public class CategoryServiceImpl implements ICategoryServices{

	public ICategoryDao cateDao= new CategoryDaoImpl();
	@Override
	public List<CatogryModel> findAll() {
		
		return cateDao.findAll();
	}

	@Override
	public CatogryModel findById(int id) {
		
		return cateDao.findById(id);
	}

	@Override
	public CatogryModel findByname(String name) {
		
		return cateDao.findByname(name);
		
	}

	@Override
	public void insert(CatogryModel category) {
		CatogryModel cate = this.findByname(category.getCategoryname());
		if(cate.getCategoryname()==null)
		{
			cateDao.insert(category);
		}
		
	}

	@Override
	public void update(CatogryModel category) {
		cateDao.update(category);
		
	}

	@Override
	public void delete(int id) {
		cateDao.delete(id);
		
	}

	@Override
	public void updateStatus(int id, int status) {
		
		cateDao.updateStatus(id, status);
	}

}
