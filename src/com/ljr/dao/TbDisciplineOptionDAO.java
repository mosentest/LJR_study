package com.ljr.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ljr.entity.TbDisciplineOption;

public class TbDisciplineOptionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbDisciplineOptionDAO.class);
	// property constants
	public static final String CONTENT = "content";

	public void save(TbDisciplineOption transientInstance) {
		log.debug("saving TbDisciplineOption instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			beginTransaction.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public void delete(TbDisciplineOption persistentInstance) {
		log.debug("deleting TbDisciplineOption instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			beginTransaction.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public TbDisciplineOption findById(java.lang.Integer id) {
		log.debug("getting TbDisciplineOption instance with id: " + id);
		try {
			TbDisciplineOption instance = (TbDisciplineOption) getSession().get("com.ljr.entity.TbDisciplineOption", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbDisciplineOption instance) {
		log.debug("finding TbDisciplineOption instance by example");
		try {
			List results = getSession().createCriteria("com.ljr.entity.TbDisciplineOption").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbDisciplineOption instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbDisciplineOption as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}
	
	/**
	 * 按题目查询选项
	 * @param disciplineId
	 * @return
	 */
	public List findAll(int disciplineId) {
		log.debug("finding all TbDisciplineOption instances");
		try {
			String queryString = "from TbDisciplineOption as model where model.tbDiscipline.id = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setInteger(0, disciplineId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void merge(TbDisciplineOption detachedInstance) {
		log.debug("merging TbDisciplineOption instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.merge(detachedInstance);
			beginTransaction.commit();
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public void attachDirty(TbDisciplineOption instance) {
		log.debug("attaching dirty TbDisciplineOption instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbDisciplineOption instance) {
		log.debug("attaching clean TbDisciplineOption instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}