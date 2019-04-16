package com.bsoft.dao_support;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * hibernate查询支持类.
 * <br/>
 * 注入容器标识：hibernateService
 * <br/>
 * 依赖资源:hibernateTemplate
 * @author Wuyong
 *
 */

@Service(value = "hibernateDaoSupport")
public class HibernateDaoSupport {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 单实体载入.
	 * @param clazz 实体类
	 * @param id 实体id
	 * @return 实体对象
	 */
	public <T> T getEntity(Class<T> clazz, Serializable id) {
		T entity = null;
		try {
			entity = hibernateTemplate.get(clazz, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return entity;
	}

	public void saveEntity(Object entity) {
		hibernateTemplate.save(entity);
	}

	public void updateEntity(Object entity) {
		hibernateTemplate.update(entity);
	}

	public void deleteEntity(Object entity) {
		hibernateTemplate.delete(entity);
	}
	
	/**
	 * 命名查询列表.
	 * @param hql HQL语句
	 * @param paramNames 参数名数组
	 * @param values 参数值数组
	 * @return List对象
	 */
	public List<?> getList(String hql, String[] paramNames, Object[] values) {
		List list = null;
		try {
			list = hibernateTemplate.findByNamedParam(hql, paramNames, values);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 占位符查询.
	 * @param hql HQL查询语句
	 * @param values 参数值
	 * @return List对象
	 */
	public List<?> getList(String hql, Object... values) {
		List list = null;
		try {
			list = hibernateTemplate.find(hql, values);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询唯一结果.
	 * @param hql HQL查询语句
	 * @param args 参数
	 * @return Object值
	 */
	public Object uniqueResult(String hql, Object... args) {
		List list = hibernateTemplate.find(hql, args);
		return list.get(0);
	}
	
	/**
	 * SQL查询唯一结果.
	 * @param sql sql
	 * @param args 参数值
	 * @return Object值
	 */
	public Object uniqueResultBySql(final String sql, final Object... args) {
		return hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);

				if (args != null && args.length > 0) {
					for (int i=0; i < args.length; i ++) {
						query.setParameter(i, args[i]);
					}
				}
				
				return query.list().get(0);
			}

		});
	}
	
	/**
	 * 执行sql更新语句
	 * @param sql SQL
	 * @param args 参数
	 * @return Ojbect值
	 */
	public Object executeUpdate(final String sql,final Object...args){
		return hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				
				if (args != null && args.length > 0) {
					for (int i=0; i < args.length; i ++) {
						query.setParameter(i, args[i]);
					}
				}
				
				return Integer.valueOf(query.executeUpdate());
			}

		});
	}
	
	/**
	 * sql分页查询.
	 * @param clazz 映射实体类
	 * @param sql SQL
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @param args 参数
	 * @return List
	 */
	public <T> List<T> findPageBySql(final Class<T> clazz,final String sql,final Integer pageNo,final Integer pageSize, final Object... args) {
		return hibernateTemplate.execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				if (args != null && args.length > 0) {
					for (int i=0; i < args.length; i ++) {
						query.setParameter(i, args[i]);
					}
				}
				
				query.addEntity(clazz);
				
				query.setFirstResult((pageNo - 1) * pageSize);
				
				query.setMaxResults(pageSize);

				return query.list();
			}

		});
	}
	
	/**
	 * SQL列表查询.
	 * @param clazz 映射实体类
	 * @param sql SQL
	 * @param args 参数
	 * @return List
	 */
	public <T> List<T> findBySql(final Class<T> clazz,final String sql, final Object... args) {
		return hibernateTemplate.execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				if (args != null && args.length > 0) {
					for (int i=0; i < args.length; i ++) {
						query.setParameter(i, args[i]);
					}
				}
				
				query.addEntity(clazz);
				
				return query.list();
			}

		});
	}

	/**
	 * Criteria分页查询.
	 * @param clazz 实体类
	 * @param args 条件参数
	 * @param order 排序规则
	 * @param pageNo 页码
	 * @param pageSize 分页大小 
	 * @return List
	 */
	public <T> List<T> findByPage(Class<T> clazz, List<Criterion> args,
			Order order, int pageNo, int pageSize) {
		int startRow = (pageNo - 1) * pageSize;

		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

		if (args != null) {
			for (Criterion c : args) {
				criteria.add(c);
			}
		}

		List<T> data = (List<T>) hibernateTemplate.findByCriteria(criteria,
				startRow, pageSize);

		return data;
	}
	
	public Session getSession(){
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}
}
