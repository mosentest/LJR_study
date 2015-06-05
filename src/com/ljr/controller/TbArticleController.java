package com.ljr.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ljr.dao.TbArticleDAO;
import com.ljr.dao.TbSubjectTypeDAO;
import com.ljr.dto.ArticleDTO;
import com.ljr.entity.TbArticle;
import com.ljr.entity.TbSubjectType;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;
import com.ljr.util.StringUtil;
/**
 * 文章action
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbArticleController {
	private  TbArticleDAO dao = new TbArticleDAO();
	private TbSubjectTypeDAO subjectTypeDAO = new TbSubjectTypeDAO();
	private String msg = "";
	
	/**
	 * 修改信息
	 */
	@RequestMapping(value = "article/edit", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse<TbArticle> edit(@RequestBody final ArticleDTO dto) {
		JsonResponse<TbArticle> jsonResponse = new JsonResponse<TbArticle>();
		try {
			TbArticle entity = new TbArticle();
			if(StringUtil.isEmpty(dto.getContent())){
				msg = "内容不能为空";
				throw  new Exception(msg);
			}
			if(StringUtil.isEmpty(dto.getTypeId())){
				msg = "请选择类型";
				throw  new Exception(msg);
			}
			entity.setId(Integer.parseInt(dto.getId()));
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			TbSubjectType findById = subjectTypeDAO.findById(Integer.parseInt(dto.getTypeId()));
			entity.setTbSubjectType(findById);
			dao.merge(entity);
			jsonResponse.setMsg("修改成功");
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(msg);
		}
		return jsonResponse;
	}
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "article/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id) {
		try {
			TbArticle entity =dao.findById(Integer.parseInt(id));
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("article/index");
	}
	
	/**
	 * 添加信息
	 */
	@RequestMapping(value = "article/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse<TbArticle> add(@RequestBody final ArticleDTO dto) {
		JsonResponse<TbArticle> jsonResponse = new JsonResponse<TbArticle>();
		try {
			TbArticle entity = new TbArticle();
			if(StringUtil.isEmpty(dto.getTitle())){
				msg = "标题不能为空";
				throw  new Exception(msg);
			}
			List findByTitle = dao.findByTitle(dto.getTitle());
			if(!findByTitle.isEmpty()){
				msg = "标题已经存在";
				throw  new Exception(msg);
			}
			if(StringUtil.isEmpty(dto.getContent())){
				msg = "内容不能为空";
				throw  new Exception(msg);
			}
			if(StringUtil.isEmpty(dto.getTypeId())){
				msg = "请选择类型";
				throw  new Exception(msg);
			}
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			TbSubjectType findById = subjectTypeDAO.findById(Integer.parseInt(dto.getTypeId()));
			entity.setTbSubjectType(findById);
			dao.save(entity);
			jsonResponse.setMsg("添加成功");
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(msg);
		}
		return jsonResponse;
	}
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "article/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbArticle course = new TbArticle();
		try {
			course = dao.findById(Integer.parseInt(id));
			modelMap.put("update", "update");
			modelMap.put("bean", course);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("article/add");
	}
	
	/**
	 * 文章编号，标题，类型编号，类型名称
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "article/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbArticle> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String title,
															@RequestParam(required = false) final String typeId,
															@RequestParam(required = false) final String typeName){
		JsonResponse<TbArticle> jsonResponse = new JsonResponse<TbArticle>();
		// 获取对应的参数
		String[] params = new String[] { id, title, typeId, typeName};
		try {
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					// 编码有问题,get传过来的参数
					if (params[i] != null && !"".equals(params[i])) {
						String newStr = new String(params[i].getBytes("iso8859-1"), "UTF-8");
						params[i] = newStr;
					}
				}
			}
			Page<TbArticle> findAll = dao.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg("获取成功");
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg("获取失败");
		}
		return jsonResponse;
	}
}
