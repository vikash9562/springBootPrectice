package com.rays.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.rays.dto.UserDto;

public abstract class BaseDAOImpl<T extends BaseDto> implements BaseDAOInt<T> {

	@PersistenceContext
	public EntityManager entityManager;

	public abstract Class<T> getDTOClass();

	public abstract List<Predicate> getWhereClause(CriteriaBuilder builder, Root qRoot, T dto);

	@Override
	public long add(T dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(T dto) {
		entityManager.merge(dto);

	}

	@Override
	public void delete(T dto) {
		entityManager.remove(dto);
	}

	@Override
	public T findByPk(Long pk) {
		T dto = (T) entityManager.find(UserDto.class, pk);
		return dto;
	}

	@Override
	public T findByUniqeKey(String attribute, String value) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());

		Root<T> qroot = cq.from(getDTOClass());

		Predicate condition = builder.equal(qroot.get(attribute), value);

		cq.where(condition);

		TypedQuery<T> tq = entityManager.createQuery(cq);

		List<T> list = tq.getResultList();

		T dto = null;

		if (list.size() > 0) {

			dto = list.get(0);

		}

		return dto;
	}

	@Override
	public List search(T dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());

		Root<T> qroot = cq.from(getDTOClass());

		List<Predicate> PredicateList = getWhereClause(builder, qroot, dto);

		cq.where(PredicateList.toArray(new Predicate[PredicateList.size()]));

		TypedQuery<T> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {

			tq.setFirstResult(pageNo);
			tq.setMaxResults(pageSize);

		}

		List<T> list = tq.getResultList();

		return list;
	}

}
