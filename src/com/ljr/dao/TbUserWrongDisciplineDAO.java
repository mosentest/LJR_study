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

import com.ljr.entity.TbQuestionnaireDiscipline;
import com.ljr.entity.TbUserWrongDiscipline;
import com.ljr.util.Page;


public class TbUserWrongDisciplineDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbUserWrongDisciplineDAO.class);
	// property constants
	public static final String COUNT = "count";

	public void save(TbUserWrongDiscipline transientInstance) {
		log.debug("saving TbUserWrongDiscipline instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			transientInstance.setCount(1);
			transientInstance.setCreateDate(new Date());
			transientInstance.setUpdateDate(new Date());
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
	 * 查询是否存在了用户错误的题目
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public TbUserWrongDiscipline checkExist(Integer userId, Integer disciplineId){
		String queryString = "from TbUserWrongDiscipline as model where "
										+ "model.tbUser.id = ?"
										+ "and model.tbDiscipline.id = ?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, disciplineId);
		if(queryObject.list().isEmpty()){
			return null;
		}
		return (TbUserWrongDiscipline)queryObject.list().get(0);
	}
	public void delete(TbUserWrongDiscipline persistentInstance) {
		log.debug("deleting TbUserWrongDiscipline instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			getSession().delete(persistentInstance);
			beginTransaction.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public TbUserWrongDiscipline findById(java.lang.Integer id) {
		log.debug("getting TbUserWrongDiscipline instance with id: " + id);
		try {
			TbUserWrongDiscipline instance = (TbUserWrongDiscipline) getSession().get("com.ljr.entity.TbUserWrongDiscipline", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbUserWrongDiscipline instance) {
		log.debug("finding TbUserWrongDiscipline instance by example");
		try {
			List results = getSession().createCriteria("com.ljr.entity.TbUserWrongDiscipline").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbUserWrongDiscipline instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbUserWrongDiscipline as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List<TbUserWrongDiscipline> findAll(String... params) {
		log.debug("finding all TbUserWrongDiscipline instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Page<TbUserWrongDiscipline> findAll(int page, int size, String... params) {
		Page<TbUserWrongDiscipline> pageList = new Page<TbUserWrongDiscipline>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbUserWrongDiscipline instances");
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
	 * 编号，用户编号，用户名，题目问题
	 * @param params
	 * @return
	 */
	private Query repeatCode(String... params) {
		String queryString = "from TbUserWrongDiscipline";
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
//			if (params[3] != null && !"".equals(params[3].trim())) {
//				buffer.append(" tb.tbDiscipline.question like:ctitle and ");
//			}
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
//			if (params[3] != null && !"".equals(params[3].trim())) {
//				queryObject.setString("ctitle", "%"+params[3]+"%");
//			}
		}
		return queryObject;
	}
	
	public void merge(TbUserWrongDiscipline detachedInstance) {
		log.debug("merging TbUserWrongDiscipline instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			//下次的时候
			detachedInstance.setCount(detachedInstance.getCount()+1);
			detachedInstance.setUpdateDate(new Date());
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

	public void attachDirty(TbUserWrongDiscipline instance) {
		log.debug("attaching dirty TbUserWrongDiscipline instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbUserWrongDiscipline instance) {
		log.debug("attaching clean TbUserWrongDiscipline instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}