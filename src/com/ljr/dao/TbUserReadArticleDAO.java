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
import com.ljr.entity.TbUserReadArticle;
import com.ljr.util.Page;

public class TbUserReadArticleDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TbUserReadArticleDAO.class);
	// property constants
	public static final String READ_COUNT = "readCount";

	public void save(TbUserReadArticle transientInstance) {
		log.debug("saving TbUserReadArticle instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			transientInstance.setReadCount(1);
			transientInstance.setReadDate(new Date());
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
	 * 查询是否用户收藏了该文章
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public TbQuestionnaireDiscipline checkExist(Integer userId, Integer articleId){
		String queryString = "from TbUserReadArticle as model where "
										+ "model.tbUser.id = ?"
										+ "and model.tbArticle.id = ?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, articleId);
		if(queryObject.list().isEmpty()){
			return null;
		}
		return (TbQuestionnaireDiscipline)queryObject.list().get(0);
	}
	
	public void delete(TbUserReadArticle persistentInstance) {
		log.debug("deleting TbUserReadArticle instance");
		Session session = getSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally{
			session.close();
		}
	}

	public TbUserReadArticle findById(java.lang.Integer id) {
		log.debug("getting TbUserReadArticle instance with id: " + id);
		try {
			TbUserReadArticle instance = (TbUserReadArticle) getSession().get("com.ljr.entity.TbUserReadArticle", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TbUserReadArticle instance) {
		log.debug("finding TbUserReadArticle instance by example");
		try {
			List results = getSession().createCriteria("com.ljr.entity.TbUserReadArticle").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TbUserReadArticle instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TbUserReadArticle as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByReadCount(Object readCount) {
		return findByProperty(READ_COUNT, readCount);
	}

	public List<TbUserReadArticle> findAll(String... params) {
		log.debug("finding all TbUserReadArticle instances");
		try {
			Query queryObject = repeatCode(params);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Page<TbUserReadArticle> findAll(int page, int size, String... params) {
		Page<TbUserReadArticle> pageList = new Page<TbUserReadArticle>();
		pageList.setPageSize(size);
		pageList.setCurrentPage(page);
		log.debug("finding all TbUserReadArticle instances");
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
	 * 编号，用户编号，用户名，文章编号，文章标题
	 * @param params
	 * @return
	 */
	private Query repeatCode(String... params) {
		String queryString = "from TbUserReadArticle";
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
				buffer.append(" tb.tbArticle.id=:aid and ");
			}
			if (params[4] != null && !"".equals(params[4].trim())) {
				buffer.append(" tb.tbArticle.title like:ctitle and ");
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
				queryObject.setInteger("aid", Integer.parseInt(params[3]));
			}
			if (params[4] != null && !"".equals(params[4].trim())) {
				queryObject.setString("ctitle", "%"+params[2]+"%");
			}
		}
		return queryObject;
	}

	public void merge(TbUserReadArticle detachedInstance) {
		log.debug("merging TbUserReadArticle instance");
		try {
			//下次的时候
			detachedInstance.setReadCount(detachedInstance.getReadCount()+1);
			detachedInstance.setUpdateDate(new Date());
			 getSession().merge(detachedInstance);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TbUserReadArticle instance) {
		log.debug("attaching dirty TbUserReadArticle instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TbUserReadArticle instance) {
		log.debug("attaching clean TbUserReadArticle instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}