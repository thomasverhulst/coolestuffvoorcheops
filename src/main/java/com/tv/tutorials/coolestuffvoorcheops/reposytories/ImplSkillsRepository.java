package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import java.io.Serializable;
import java.util.Optional;

import com.tv.tutorials.coolestuffvoorcheops.model.Skills;

public class ImplSkillsRepository implements SkillsRepository<Skills, Serializable>{

//	@Override
//	public <S extends Skills> S save(S entity) {
////		 if (entityInformation.isNew(entity)) {
////		        em.persist(entity);
////		        return entity;
////		    } else {
////		        return em.merge(entity);
////		    }
//	}

	@Override
	public <S extends Skills> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Skills> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Skills> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Skills> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Skills entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Skills> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Skills> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
