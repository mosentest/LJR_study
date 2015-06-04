package com.ljr.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ljr.entity.TbUserQuestionnaire;
import com.ljr.util.Page;


public class TbUserQuestionnaireDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbUserQuestionnaireDAO.class);

	public void save(TbUserQuestionnaire transientInstance) {
		log.debug("saving TbUserQuestionnaire instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			transientInstance.setCreateDate(new Date());
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

	public void delete(TbUserQuestionnaire persistentInstance) {
		log.debug("deleting TbUserQuestionnaire instance");
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

	public TbUserQuestionnaire findById(java.lang.Integer id) {
		log.debug("getting TbUserQuestionnaire instance with id: " + id);
		try {
			TbUserQuestionnaire instance = (TbUserQuestionnaire) getSession().get("com.ljr.entity.TbUserQuestionnaire", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbUserQuestionnaire instance) {
		log.debug("finding TbUserQuestionnaire instance by example");
		try {
			List results = getSession().createCriteria("com.ljr.entity.TbUserQuestionnaire").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbUserQuestionnaire instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbUserQuestionnaire as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List<TbUserQuestionnaire> findAll(String... params) {
		log.debug("finding all TbUserQuestionnaire instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Page<TbUserQuestionnaire> findAll(int page, int size, String... params) {
		Page<TbUserQuestionnaire> pageList = new Page<TbUserQuestionnaire>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbUserQuestionnaire instances");
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
	 * 编号，用户编号，用户名，类型编号, 类型名称
	 * @param params
	 * @return
	 */
	private Query repeatCode(String... params) {
		String queryString = "from TbUserQuestionnaire";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.id=:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.tbUser.id=:uid and ");
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				buffer.append(" tb.tbUser.username like:cusername and ");
			}
			if (params[3] != null && !"".equals(params[3].trim())) {
				buffer.append(" tb.tbQuestionnaire.name like:cqname and ");
			}
			if (params[4] != null && !"".equals(params[4].trim())) {
				buffer.append(" tb.tbQuestionnaire.tbSubjectType.id=:csid and ");
			}
			if (params[5] != null && !"".equals(params[5].trim())) {
				buffer.append(" tb.tbQuestionnaire.tbSubjectType.name like:cname and ");
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
				queryObject.setInteger("uid", Integer.parseInt(params[1]));
			}
			if (params[2] != null && !"".equals(params[2].trim())) {
				queryObject.setString("cusername", "%"+params[2]+"%");
			}
			if (params[3] != null && !"".equals(params[3].trim())) {
				queryObject.setString("cqname", "%"+params[3]+"%");
			}
			if (params[4] != null && !"".equals(params[4].trim())) {
				queryObject.setInteger("csid", Integer.parseInt(params[4]));
			}
			if (params[5] != null && !"".equals(params[5].trim())) {
				queryObject.setString("cname", "%"+params[5]+"%");
			}
		}
		return queryObject;
	}

	public void merge(TbUserQuestionnaire detachedInstance) {
		log.debug("merging TbUserQuestionnaire instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.merge(detachedInstance);
			log.debug("merge successful");
			beginTransaction.commit();
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public void attachDirty(TbUserQuestionnaire instance) {
		log.debug("attaching dirty TbUserQuestionnaire instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbUserQuestionnaire instance) {
		log.debug("attaching clean TbUserQuestionnaire instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}