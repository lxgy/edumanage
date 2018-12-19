package com.seehope.dao.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.seehope.core.page.Page;
import com.seehope.core.page.Pageable;

public abstract class BaseDao<T> {
	private static final Logger _Log = LoggerFactory.getLogger(BaseDao.class);
	public static final String ALIAS = "d";
	protected static final String QUERY_ALL = "select d from %s d";
	protected static final String QUERY_EXISTS = "select count(d) from %s d where d.%s = :id";
	protected static final String QUERY_GET_BY = "select d from %s d where d.%s = ?1";
	protected static final String QUERY_FIND_LIKE = "select d from %s d where d.%s like :like";
	protected static final String QUERY_COUNT_LIKE = "select count(d) from %s d where d.%s like :like";
	protected static final String QUERY_COUNT_ALL = "select count(d) from %s d";
	protected Class<T> mEntityClass;

	public abstract SessionFactory getSessionFactory();

	protected Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	protected BaseDao(Class<T> entityClass) {
		this.mEntityClass = entityClass;
		if (entityClass == null) {
			_Log.warn("Dao \"{}\" no entity class.", getClass().getName());
			Type t = getClass().getGenericSuperclass();
			Type[] params = ((ParameterizedType) t).getActualTypeArguments();
			this.mEntityClass = ((Class) params[0]);
		}
	}

	public T getById(Serializable id) {
		return (T) getSession().get(this.mEntityClass, id);
	}

	public void save(T entity) {
		try {
			Field[] fs = entity.getClass().getDeclaredFields();
			Field[] arrayOfField1;
			int j = (arrayOfField1 = fs).length;
			for (int i = 0; i < j; i++) {
				Field field = arrayOfField1[i];
				if ((field.isAnnotationPresent(Id.class))
						&& ((Integer.class == field.getType()) || (Long.class == field.getType()))) {
					field.setAccessible(true);
					field.set(entity, null);
				}
			}
		} catch (Exception localException) {
		}
		getSession().persist(entity);
	}

	@Transactional
	public void save(Iterable<? extends T> entities) {
		Session s = getSession();
		for (T entity : entities) {
			s.persist(entity);
		}
	}

	public void update(T entity) {
		getSession().merge(entity);
	}

	public void update(Iterable<? extends T> entities) {
		Session s = getSession();
		for (T entity : entities) {
			s.merge(entity);
		}
	}

	public void delete(T entity) {
		if (entity != null) {
			Session s = getSession();

			s.delete(s.merge(entity));
		}
	}

	public void deleteById(Serializable id) {
		if (id != null) {
			Session s = getSession();
			s.delete(s.get(this.mEntityClass, id));
		}
	}

	@Transactional
	public void delete(Iterable<? extends T> entities) {
		for (T entity : entities) {
			delete(entity);
		}
	}

	public boolean exists(Serializable id) {
		Session s = getSession();
		String str = String.format("select count(d) from %s d where d.%s = :id",
				new Object[] { this.mEntityClass.getSimpleName(), getIdAttribute() });
		System.out.println(str);
		Query<Long> q = s.createQuery(str, Long.class);
		q.setParameter("id", id);
		return ((Long) q.getSingleResult()).longValue() > 0L;
	}

	public T getBy(String attribute, Object value) {
		String strQuery = String.format("select d from %s d where d.%s = ?1",
				new Object[] { this.mEntityClass.getSimpleName(), attribute });
		Query<T> query = getSession().createQuery(strQuery, this.mEntityClass);
		query.setParameter(1, value);
		List<T> datas = query.getResultList();
		if ((datas != null) && (datas.size() > 0)) {
			return (T) datas.get(0);
		}
		return null;
	}

	public T getByWhere(QueryWhere where) {
		StringBuilder sb = new StringBuilder(128);
		sb.append("select d from ").append(this.mEntityClass.getSimpleName());
		sb.append(" d where").append(where.toQL("d"));
		TypedQuery<T> query = getSession().createQuery(sb.toString(), this.mEntityClass);
		where.toParameters(query);
		List<T> datas = query.getResultList();
		if ((datas != null) && (datas.size() > 0)) {
			return (T) datas.get(0);
		}
		return null;
	}

	public int getCount(QueryWhere where) {
		StringBuilder sb = new StringBuilder(128);
		sb.append("select count(d) from ").append(this.mEntityClass.getSimpleName());
		sb.append(" d where").append(where.toQL("d"));
		TypedQuery<Long> query = getSession().createQuery(sb.toString(), Long.class);
		where.toParameters(query);
		return ((Long) query.getSingleResult()).intValue();
	}

	public List<T> findAll() {
		String strQuery = String.format("select d from %s d", new Object[] { this.mEntityClass.getSimpleName() });
		return defaultSort(getSession().createQuery(strQuery, this.mEntityClass).getResultList());
	}

	public List<T> findAll(Iterable<? extends Serializable> ids) {
		Session s = getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<T> query = s.getCriteriaBuilder().createQuery(this.mEntityClass);
		Root<T> root = query.from(this.mEntityClass);
		Path<?> path = root.get(getIdAttribute());
		query.where(path.in(new Expression[] { cb.parameter(ids.getClass(), "ids") }));
		return defaultSort(s.createQuery(query).setParameter("ids", ids).getResultList());
	}

	public Page<T> findAll(Pageable pageable) {
		Session s = getSession();

		String strQuery = String.format("select count(d) from %s d",
				new Object[] { this.mEntityClass.getSimpleName() });
		int nTotal = ((Long) s.createQuery(strQuery, Long.class).getSingleResult()).intValue();

		strQuery = String.format("select d from %s d", new Object[] { this.mEntityClass.getSimpleName() });
		if (pageable.getSort() != null) {
			strQuery = strQuery + pageable.getSort().toQL();
		}
		TypedQuery<T> query = s.createQuery(strQuery, this.mEntityClass);
		return pageQuery(query, pageable, nTotal);
	}

	public List<T> defaultSort(List<T> items) {

		if ((items != null) && (items.size() > 0)) {
			Collections.sort(items, new Comparator<T>() {
				public int compare(T arg0, T arg1) {
					Object id0 = null;
					Object id1 = null;
					try {
						Field[] fs = BaseDao.this.getFields(arg0.getClass());
						Field[] arrayOfField1;
						int j = (arrayOfField1 = fs).length;
						for (int i = 0; i < j; i++) {
							Field field = arrayOfField1[i];
							if ((field.isAnnotationPresent(Id.class))
									&& ((Integer.class == field.getType()) || (Long.class == field.getType()))) {
								field.setAccessible(true);
								id0 = field.get(arg0);
							}
						}
						fs = BaseDao.this.getFields(arg1.getClass());
						j = (arrayOfField1 = fs).length;
						for (int i = 0; i < j; i++) {
							Field field = arrayOfField1[i];
							if ((field.isAnnotationPresent(Id.class))
									&& ((Integer.class == field.getType()) || (Long.class == field.getType()))) {
								field.setAccessible(true);
								id1 = field.get(arg1);
							}
						}
						return id0.toString().compareTo(id1.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					return 0;
				}
			});
		}
		return items;
	}

	private Field[] getFields(Class clazz) {
		Field[] fs = clazz.getDeclaredFields();
		Class<?> superclass = clazz.getSuperclass();
		if ((superclass == null) || (superclass.getAnnotation(MappedSuperclass.class) == null)) {
			return fs;
		}
		return (Field[]) ArrayUtils.addAll(fs, getFields(superclass));
	}

	protected Page<T> pageQuery(TypedQuery<T> query, Pageable pageable, int total) {
		query.setMaxResults(pageable.getPagesize());
		query.setFirstResult(pageable.getOffset());
		return new Page(query.getResultList(), pageable, total);
	}

	private String getIdAttribute() {
		Field[] fs = this.mEntityClass.getDeclaredFields();
		Field[] arrayOfField1;
		int j = (arrayOfField1 = fs).length;
		for (int i = 0; i < j; i++) {
			Field field = arrayOfField1[i];
			if ((field.isAnnotationPresent(Id.class))
					&& ((Integer.class == field.getType()) || (Long.class == field.getType()))) {
				field.setAccessible(true);
				return field.getName();
			}
		}
		_Log.error("Entity {} no id.", this.mEntityClass.getName());
		return null;
	}

	public <E> E singleSelect(String jpql, Class<E> _class) {
		Session s = getSession();
		TypedQuery<E> q = s.createQuery(jpql, _class);
		E ret = q.getSingleResult();
		return ret;
	}

	public <E> E singleSelect(String jpql, Class<E> _class, Object... values) {
		Session s = getSession();
		TypedQuery<E> q = s.createQuery(jpql, _class);
		if ((values != null) && (values.length > 0)) {
			for (int i = 0; i < values.length; i++) {
				q.setParameter(1 + i, values[i]);
			}
		}
		E ret = q.getSingleResult();
		return ret;
	}

	public <E> E singleSelect(String jpql, Class<E> _class, Map<String, Object> params) {
		Session s = getSession();
		TypedQuery<E> q = s.createQuery(jpql, _class);
		if (params != null) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		E ret = q.getSingleResult();
		return ret;
	}
}
