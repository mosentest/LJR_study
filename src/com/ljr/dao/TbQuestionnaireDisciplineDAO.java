package com.ljr.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ljr.entity.TbQuestionnaireDiscipline;
import com.ljr.util.Page;

public class TbQuestionnaireDisciplineDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbQuestionnaireDisciplineDAO.class);

	public void save(TbQuestionnaireDiscipline transientInstance) {
		log.debug("saving TbQuestionnaireDiscipline instance");
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
	
	/**
	 * 查询是否题目已经添加
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public TbQuestionnaireDiscipline checkExist(Integer questionnaireId, Integer disciplineId){
		String queryString = "from TbQuestionnaireDiscipline as model where "
										+ "model.tbQuestionnaire.id = ?"
										+ "and model.tbDiscipline.id=?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, questionnaireId);
		queryObject.setParameter(1, disciplineId);
		if(queryObject.list().isEmpty()){
			return null;
		}
		return (TbQuestionnaireDiscipline)queryObject.list().get(0);
	}
	
	/**
	 * 查询问卷的题目是否超过5条
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<TbQuestionnaireDiscipline> checkOverFive(Integer questionnaireId){
		String queryString = "from TbQuestionnaireDiscipline as model where "
										+ "model.tbQuestionnaire.id = ?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, questionnaireId);
		return queryObject.list();
	}
	
	public void delete(TbQuestionnaireDiscipline persistentInstance) {
		log.debug("deleting TbQuestionnaireDiscipline instance");
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

	public TbQuestionnaireDiscipline findById(java.lang.Integer id) {
		log.debug("getting TbQuestionnaireDiscipline instance with id: " + id);
		try {
			TbQuestionnaireDiscipline instance = (TbQuestionnaireDiscipline) getSession().get("com.ljr.entity.TbQuestionnaireDiscipline", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbQuestionnaireDiscipline instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbQuestionnaireDiscipline as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TbQuestionnaireDiscipline> findAll(String... params) {
		log.debug("finding all TbQuestionnaireDiscipline instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Page<TbQuestionnaireDiscipline> findAll(int page, int size, String... params) {
		Page<TbQuestionnaireDiscipline> pageList = new Page<TbQuestionnaireDiscipline>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbQuestionnaireDiscipline instances");
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
	 * 编号，问卷编号
	 * @param params
	 * @return
	 */
	private Query repeatCode(String... params) {
		String queryString = "from TbQuestionnaireDiscipline";
		StringBuffer buffer = new StringBuffer();
		buffer.append(queryString);
		if (params != null && params.length > 0) {
			buffer.append(" as tb where ");
			if (params[0] != null && !"".equals(params[0].trim())) {
				buffer.append(" tb.id=:cid and ");
			}
			if (params[1] != null && !"".equals(params[1].trim())) {
				buffer.append(" tb.tbQuestionnaire.id=:csid and ");
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
				queryObject.setInteger("csid", Integer.parseInt(params[1]));
			}
		}
		return queryObject;
	}

	public void merge(TbQuestionnaireDiscipline detachedInstance) {
		log.debug("merging TbQuestionnaireDiscipline instance");
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

	public void attachDirty(TbQuestionnaireDiscipline instance) {
		log.debug("attaching dirty TbQuestionnaireDiscipline instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbQuestionnaireDiscipline instance) {
		log.debug("attaching clean TbQuestionnaireDiscipline instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}