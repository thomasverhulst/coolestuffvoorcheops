// package com.tv.tutorials.coolestuffvoorcheops.reposytories;
//
// import java.io.Serializable;
//
// import com.tv.tutorials.coolestuffvoorcheops.model.Skills;
//
// public class ImplSkillRepository2 implements SkillsRepository2<Skills,
// Serializable> {
//
//
//
//
// @Override
// public Long count() {
// // TODO Auto-generated method stub
// return null;
// }
//
//
// @Override
// public <S extends Skills> S save(entity) {
// Skills p = findOne(entity.getId());
// if (p == null) {
// save(entity);
// return entity;
// }else
// // entiteit bestaat al
// {
// p= entity;
// save(p);
// return p;
// }
// }
//
// @Override
// public void delete(Skills entity) {
// // TODO Auto-generated method stub
//
// }
//
//
// @Override
// public Iterable<Skills> findAll() {
// // TODO Auto-generated method stub
// return null;
// }
//
//
// @Override
// public Skills findOne(Serializable primaryKey) {
//
// return null;
// }
//
// }
