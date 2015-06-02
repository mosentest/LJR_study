package com.ljr.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ljr.entity.TbUserExam;

@Deprecated
public class TbUserExamDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbUserExamDAO.class);
	// property constants
	public static final String SUM = "sum";

	public void save(TbUserExam transientInstance) {
		log.debug("saving TbUserExam instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbUserExam persistentInstance) {
		log.debug("deleting TbUserExam instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbUserExam findById(java.lang.Integer id) {
		log.debug("getting TbUserExam instance with id: " + id);
		try {
			TbUserExam instance = (TbUserExam) getSession().get("com.ljr.entity.TbUserExam", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbUserExam instance) {
		log.debug("finding TbUserExam instance by example");
		try {
			List results = getSession().createCriteria("com.ljr.entity.TbUserExam").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbUserExam instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbUserExam as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySum(Object sum) {
		return findByProperty(SUM, sum);
	}

	public List findAll() {
		log.debug("finding all TbUserExam instances");
		try {
			String queryString = "from TbUserExam";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbUserExam merge(TbUserExam detachedInstance) {
		log.debug("merging TbUserExam instance");
		try {
			TbUserExam result = (TbUserExam) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbUserExam instance) {
		log.debug("attaching dirty TbUserExam instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbUserExam instance) {
		log.debug("attaching clean TbUserExam instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}