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

import com.ljr.entity.TbUserCollectDiscipline;
import com.ljr.util.Page;

public class TbUserCollectDisciplineDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbUserCollectDisciplineDAO.class);

	// property constants

	public void save(TbUserCollectDiscipline transientInstance) {
		log.debug("saving TbUserCollectDiscipline instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			transientInstance.setCollectDate(new Date());
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

	public void delete(TbUserCollectDiscipline persistentInstance) {
		log.debug("deleting TbUserCollectDiscipline instance");
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

	public TbUserCollectDiscipline findById(java.lang.Integer id) {
		log.debug("getting TbUserCollectDiscipline instance with id: " + id);
		try {
			TbUserCollectDiscipline instance = (TbUserCollectDiscipline) getSession().get("com.ljr.entity.TbUserCollectDiscipline", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbUserCollectDiscipline instance) {
		log.debug("finding TbUserCollectDiscipline instance by example");
		try {
			List results = getSession().createCriteria("com.ljr.entity.TbUserCollectDiscipline").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbUserCollectDiscipline instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbUserCollectDiscipline as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TbUserCollectDiscipline> findAll(String... params) {
		log.debug("finding all TbUserCollectDiscipline instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Page<TbUserCollectDiscipline> findAll(int page, int size, String... params) {
		Page<TbUserCollectDiscipline> pageList = new Page<TbUserCollectDiscipline>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbUserCollectDiscipline instances");
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
	 * 编号，用户帐号
	 * @param params
	 * @return
	 */
	private Query repeatCode(String... params) {
		String queryString = "from TbUserCollectDiscipline";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.id=:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.tbUser.username like:cname and ");
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
				queryObject.setString("cname", "%"+params[1]+"%");
			}
		}
		return queryObject;
	}

	public TbUserCollectDiscipline merge(TbUserCollectDiscipline detachedInstance) {
		log.debug("merging TbUserCollectDiscipline instance");
		try {
			TbUserCollectDiscipline result = (TbUserCollectDiscipline) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbUserCollectDiscipline instance) {
		log.debug("attaching dirty TbUserCollectDiscipline instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbUserCollectDiscipline instance) {
		log.debug("attaching clean TbUserCollectDiscipline instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}