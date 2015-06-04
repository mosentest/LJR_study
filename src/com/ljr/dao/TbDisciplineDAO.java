package com.ljr.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ljr.entity.TbDiscipline;
import com.ljr.util.Page;


public class TbDisciplineDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbDisciplineDAO.class);
	// property constants
	public static final String QUESTION = "question";
	public static final String ANSWERS = "answers";
	public static final String SCORE = "score";

	public Integer save(TbDiscipline transientInstance) {
		log.debug("saving TbDiscipline instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			beginTransaction.commit();
			log.debug("save successful");
			return transientInstance.getId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public void delete(TbDiscipline persistentInstance) {
		log.debug("deleting TbDiscipline instance");
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

	public TbDiscipline findById(java.lang.Integer id) {
		log.debug("getting TbDiscipline instance with id: " + id);
		try {
			TbDiscipline instance = (TbDiscipline) getSession().get("com.ljr.entity.TbDiscipline", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbDiscipline instance) {
		log.debug("finding TbDiscipline instance by example");
		try {
			List results = getSession().createCriteria("com.ljr.entity.TbDiscipline").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbDiscipline instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbDiscipline as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByQuestion(Object question) {
		return findByProperty(QUESTION, question);
	}

	public List findByAnswers(Object answers) {
		return findByProperty(ANSWERS, answers);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List<TbDiscipline> findAll(String... params) {
		log.debug("finding all TbDiscipline instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Page<TbDiscipline> findAll(int page, int size, String... params) {
		Page<TbDiscipline> pageList = new Page<TbDiscipline>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbDiscipline instances");
		try {
			Query queryObject = repeatCode(params);
			queryObject.setFirstResult((page - 1) * size);// 显示第几页，当前页
			queryObject.setMaxResults(size);// 每页做多显示的记录数
			List list = queryObject.list();
			pageList.setTotalElement(findAll(params).size(), size);
			pageList.setContent(list);
			return pageList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 题目编号，问题，类型编号，问题类型
	 * @param params
	 * @return
	 */
	private Query repeatCode(String... params) {
		String queryString = "from TbDiscipline";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.id=:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.question like:cquestion and ");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				buffer.append(" tb.tbSubjectType.id=:csid and ");
			}
			if (params[3] != null && !"".equals(params[3].trim())) {
				buffer.append(" tb.tbSubjectType.name like:cname and ");
			}
			buffer.append(" 1=1 ");
		}
		Query queryObject = getSession().createQuery(buffer.toString());
		// 分页显示的操作
		if (params != null && params.length > 0) {
			if (params[0] != null && !"".equals(params[0].trim())) {
				queryObject.setInteger("cid", Integer.parseInt(params[0]));
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				queryObject.setString("cquestion", "%"+params[1]+"%");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				queryObject.setInteger("csid", Integer.parseInt(params[2]));
			}
			if (params[3] != null && !"".equals(params[3].trim())) {
				queryObject.setString("cname", "%"+params[3]+"%");
			}
		}
		return queryObject;
	}
	
	public void merge(TbDiscipline detachedInstance) {
		log.debug("merging TbDiscipline instance");
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

	public void attachDirty(TbDiscipline instance) {
		log.debug("attaching dirty TbDiscipline instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbDiscipline instance) {
		log.debug("attaching clean TbDiscipline instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}