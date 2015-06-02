package com.ljr.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ljr.entity.TbUserExamDiscipline;

@Deprecated
public class TbUserExamDisciplineDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbUserExamDisciplineDAO.class);
	// property constants
	public static final String ANSWERS = "answers";

	public void save(TbUserExamDiscipline transientInstance) {
		log.debug("saving TbUserExamDiscipline instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TbUserExamDiscipline persistentInstance) {
		log.debug("deleting TbUserExamDiscipline instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TbUserExamDiscipline findById(java.lang.Integer id) {
		log.debug("getting TbUserExamDiscipline instance with id: " + id);
		try {
			TbUserExamDiscipline instance = (TbUserExamDiscipline) getSession().get("com.ljr.entity.TbUserExamDiscipline", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbUserExamDiscipline instance) {
		log.debug("finding TbUserExamDiscipline instance by example");
		try {
			List results = getSession().createCriteria("com.ljr.entity.TbUserExamDiscipline").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbUserExamDiscipline instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbUserExamDiscipline as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAnswers(Object answers) {
		return findByProperty(ANSWERS, answers);
	}

	public List findAll() {
		log.debug("finding all TbUserExamDiscipline instances");
		try {
			String queryString = "from TbUserExamDiscipline";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TbUserExamDiscipline merge(TbUserExamDiscipline detachedInstance) {
		log.debug("merging TbUserExamDiscipline instance");
		try {
			TbUserExamDiscipline result = (TbUserExamDiscipline) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbUserExamDiscipline instance) {
		log.debug("attaching dirty TbUserExamDiscipline instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbUserExamDiscipline instance) {
		log.debug("attaching clean TbUserExamDiscipline instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}