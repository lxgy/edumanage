package com.seehope.dao.core;

import com.seehope.core.page.Page;
import com.seehope.core.page.Pageable;
import com.seehope.core.page.Sort;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

public abstract class BaseDaoWithFind<T> extends BaseDao<T> {
	protected BaseDaoWithFind(Class<T> entityClass) {
		super(entityClass);
	}

	public List<T> findBy(String attribute, Object value) {
		if ((attribute == null) || (attribute.length() < 1) || (value == null)) {
			return null;
		}
		StringBuilder sb = new StringBuilder(128);
		sb.append("select d from ").append(this.mEntityClass.getSimpleName());
		sb.append(" d where d.");
		sb.append(attribute).append("=?1");
		TypedQuery<T> query = getSession().createQuery(sb.toString(), this.mEntityClass);
		query.setParameter(1, value);
		return defaultSort(query.getResultList());
	}

	public Page<T> findBy(String attribute, Object value, Pageable pageable) {
		QueryWhere where = new QueryWhere();
		where.and(attribute, value);
		return findByWhere(where, pageable);
	}

	public List<T> findBy(String[] attributes, Object[] values) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		return findByWhere(where);
	}

	public List<T> findBy(Map<String, Object> by) {
		QueryWhere where = new QueryWhere(by);
		return findByWhere(where);
	}

	public Page<T> findBy(String[] attributes, Object[] values, Pageable pageable) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		return findByWhere(where, pageable);
	}

	public Page<T> findBy(Map<String, Object> by, Pageable pageable) {
		QueryWhere where = new QueryWhere(by);
		return findByWhere(where, pageable);
	}

	public List<T> findLike(String attribute, String value) {
		QueryWhere where = new QueryWhere();
		where.likeAll(attribute, value);
		return findByWhere(where);
	}

	public Page<T> findLike(String attribute, String value, Pageable pageable) {
		QueryWhere where = new QueryWhere();
		where.likeAll(attribute, value);
		return findByWhere(where, pageable);
	}

	public List<T> findByAndLike(String[] attributes, Object[] values, String likeAttribute, String likeValue) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		where.likeAll(likeAttribute, likeValue);
		return findByWhere(where);
	}

	public List<T> findByAndLike(Map<String, Object> by, String likeAttribute, String likeValue) {
		QueryWhere where = new QueryWhere(by);
		where.likeAll(likeAttribute, likeValue);
		return findByWhere(where);
	}

	public Page<T> findByAndLike(String[] attributes, Object[] values, String likeAttribute, String likeValue,
			Pageable pageable) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		where.likeAll(likeAttribute, likeValue);
		return findByWhere(where, pageable);
	}

	public Page<T> findByAndLike(Map<String, Object> by, String likeAttribute, String likeValue, Pageable pageable) {
		QueryWhere where = new QueryWhere(by);
		where.likeAll(likeAttribute, likeValue);
		return findByWhere(where, pageable);
	}

	public List<T> findNotLike(String attribute, String value) {
		QueryWhere where = new QueryWhere();
		where.andNotLikeAll(attribute, value);
		return findByWhere(where);
	}

	public Page<T> findNotLike(String attribute, String value, Pageable pageable) {
		QueryWhere where = new QueryWhere();
		where.andNotLikeAll(attribute, value);
		return findByWhere(where, pageable);
	}

	public List<T> findByAndNotLike(String[] attributes, Object[] values, String likeAttribute, String likeValue) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		where.andNotLikeAll(likeAttribute, likeValue);
		return findByWhere(where);
	}

	public List<T> findByAndNotLike(Map<String, Object> by, String likeAttribute, String likeValue) {
		QueryWhere where = new QueryWhere(by);
		where.andNotLikeAll(likeAttribute, likeValue);
		return findByWhere(where);
	}

	public Page<T> findByAndNotLike(String[] attributes, Object[] values, String likeAttribute, String likeValue,
			Pageable pageable) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		where.andNotLikeAll(likeAttribute, likeValue);
		return findByWhere(where, pageable);
	}

	public Page<T> findByAndNotLike(Map<String, Object> by, String likeAttribute, String likeValue, Pageable pageable) {
		QueryWhere where = new QueryWhere(by);
		where.andNotLikeAll(likeAttribute, likeValue);
		return findByWhere(where, pageable);
	}

	public List<T> findByWhere(QueryWhere where) {
		if ((where == null) || (!where.hasWhere())) {
			return findAll();
		}
		StringBuilder sb = new StringBuilder(128);
		sb.append("select d from ").append(this.mEntityClass.getSimpleName());
		sb.append(" d where").append(where.toQL("d"));

		TypedQuery<T> query = getSession().createQuery(sb.toString(), this.mEntityClass);
		where.toParameters(query);
		return defaultSort(query.getResultList());
	}

	public Page<T> findByWhere(QueryWhere where, Pageable pageable) {
		if ((where == null) || (!where.hasWhere())) {
			return findAll(pageable);
		}
		EntityManager em = getSession();

		int nTotal = 0;
		StringBuilder sb = new StringBuilder(128);
		sb.append(" from ").append(this.mEntityClass.getSimpleName());
		sb.append(" d where").append(where.toQL("d"));

		TypedQuery<Long> countQuery = em.createQuery("select count(d)" + sb, Long.class);
		where.toParameters(countQuery);
		nTotal = ((Long) countQuery.getSingleResult()).intValue();
		if (nTotal < 1) {
			return new Page(null, pageable, nTotal);
		}
		if (pageable.getSort() != null) {
			sb.append(pageable.getSort().toQL());
		}
		TypedQuery<T> query = em.createQuery("select d" + sb.toString(), this.mEntityClass);
		where.toParameters(query);

		return pageQuery(query, pageable, nTotal);
	}

	public List<T> findIn(String attribute, Object... values) {
		QueryWhere where = new QueryWhere();
		where.andIn(attribute, values);
		return findByWhere(where);
	}

	public List<T> findIn(String attribute, Collection<? extends Object> values) {
		QueryWhere where = new QueryWhere();
		where.andIn(attribute, values);
		return findByWhere(where);
	}

	public List<T> findByAndIn(Map<String, Object> by, String attribute, Object... values) {
		QueryWhere where = new QueryWhere(by);
		where.andIn(attribute, values);
		return findByWhere(where);
	}

	public List<T> findByAndIn(Map<String, Object> by, String attribute, int... values) {
		QueryWhere where = new QueryWhere(by);
		where.andIn(attribute, values);
		return findByWhere(where);
	}

	public List<T> findByAndIn(Map<String, Object> by, String attribute, Collection<? extends Object> values) {
		QueryWhere where = new QueryWhere(by);
		where.andIn(attribute, values);
		return findByWhere(where);
	}

	public List<T> findByAndIn(String[] attributes, Object[] values, String inAttribute, Object... inValues) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		where.andIn(inAttribute, inValues);
		return findByWhere(where);
	}

	public List<T> findByAndIn(String[] attributes, Object[] values, String inAttribute, int... inValues) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		where.andIn(inAttribute, inValues);
		return findByWhere(where);
	}

	public List<T> findByAndIn(String[] attributes, Object[] values, String inAttribute,
			Collection<? extends Object> inValues) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		where.andIn(inAttribute, inValues);
		return findByWhere(where);
	}

	public List<T> findNotIn(String attribute, Object... values) {
		QueryWhere where = new QueryWhere();
		where.andNotIn(attribute, values);
		return findByWhere(where);
	}

	public List<T> findNotIn(String attribute, int... values) {
		QueryWhere where = new QueryWhere();
		where.andNotIn(attribute, new Object[] { values });
		return findByWhere(where);
	}

	public List<T> findNotIn(String attribute, Collection<? extends Object> values) {
		QueryWhere where = new QueryWhere();
		where.andNotIn(attribute, values);
		return findByWhere(where);
	}

	public List<T> findByAndNotIn(Map<String, Object> by, String attribute, Object... values) {
		QueryWhere where = new QueryWhere(by);
		where.andNotIn(attribute, values);
		return findByWhere(where);
	}

	public List<T> findByAndNotIn(Map<String, Object> by, String attribute, int... values) {
		QueryWhere where = new QueryWhere(by);
		where.andNotIn(attribute, new Object[] { values });
		return findByWhere(where);
	}

	public List<T> findByAndNotIn(Map<String, Object> by, String attribute, Collection<? extends Object> values) {
		QueryWhere where = new QueryWhere(by);
		where.andNotIn(attribute, values);
		return findByWhere(where);
	}

	public List<T> findByAndNotIn(String[] attributes, Object[] values, String inAttribute, Object... inValues) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		where.andNotIn(inAttribute, inValues);
		return findByWhere(where);
	}

	public List<T> findByAndNotIn(String[] attributes, Object[] values, String inAttribute, int... inValues) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		where.andNotIn(inAttribute, new Object[] { inValues });
		return findByWhere(where);
	}

	public List<T> findByAndNotIn(String[] attributes, Object[] values, String inAttribute,
			Collection<? extends Object> inValues) {
		QueryWhere where = new QueryWhere();
		where.and(attributes, values);
		where.andNotIn(inAttribute, inValues);
		return findByWhere(where);
	}
}
