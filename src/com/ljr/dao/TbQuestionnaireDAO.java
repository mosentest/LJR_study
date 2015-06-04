package com.ljr.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ljr.entity.TbQuestionnaire;
import com.ljr.util.Page;

public class TbQuestionnaireDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbQuestionnaireDAO.class);

	public Integer save(TbQuestionnaire transientInstance) {
		log.debug("saving TbQuestionnaire instance");
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

	public void delete(TbQuestionnaire persistentInstance) {
		log.debug("deleting TbQuestionnaire instance");
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

	public TbQuestionnaire findById(java.lang.Integer id) {
		log.debug("getting TbQuestionnaire instance with id: " + id);
		try {
			TbQuestionnaire instance = (TbQuestionnaire) getSession().get("com.ljr.entity.TbQuestionnaire", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<TbQuestionnaire> findAll(String... params) {
		log.debug("finding all TbQuestionnaire instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Page<TbQuestionnaire> findAll(int page, int size, String... params) {
		Page<TbQuestionnaire> pageList = new Page<TbQuestionnaire>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbQuestionnaire instances");
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
	 * 问卷编号，类型编号，类型名称
	 * @param params
	 * @return
	 */
	private Query repeatCode(String... params) {
		String queryString = "from TbQuestionnaire";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.id=:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.name like:ckname and ");
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
				queryObject.setString("ckname", "%"+params[1] +"%");
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

	public void merge(TbQuestionnaire detachedInstance) {
		log.debug("merging TbQuestionnaire instance");
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

	public void attachDirty(TbQuestionnaire instance) {
		log.debug("attaching dirty TbQuestionnaire instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbQuestionnaire instance) {
		log.debug("attaching clean TbQuestionnaire instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}