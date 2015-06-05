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

import com.ljr.dao.TbDisciplineDAO;
import com.ljr.dao.TbDisciplineOptionDAO;
import com.ljr.dao.TbSubjectTypeDAO;
import com.ljr.dto.DisciplineDTO;
import com.ljr.entity.TbDiscipline;
import com.ljr.entity.TbDisciplineOption;
import com.ljr.entity.TbSubjectType;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;

/**
 * 题目action
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbDisciplineController {
	
	private TbDisciplineDAO dao = new TbDisciplineDAO();
	private TbSubjectTypeDAO typeDAO = new TbSubjectTypeDAO();
	private TbDisciplineOptionDAO optionDAO = new TbDisciplineOptionDAO();
	
	public String msg = "";
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "discipline/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbDiscipline bean = new TbDiscipline();
		try {
			bean = dao.findById(Integer.parseInt(id));
			@SuppressWarnings("unchecked")
			List<TbDisciplineOption> findAll = optionDAO.findAll(bean.getId());
			modelMap.put("update", "update");
			modelMap.put("bean", bean);
			for(int i=0;i<findAll.size();i++){
				modelMap.put("bean"+(i+1), findAll.get(i));
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("discipline/add");
	}
	
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "discipline/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id) {
		try {
			TbDiscipline entity =dao.findById(Integer.parseInt(id));
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("discipline/index");
	}
	

	/**
	 * 编辑信息
	 * @param id
	 * @param name
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "discipline/edit", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse<TbDiscipline> edit(@RequestBody final DisciplineDTO dto) {
		JsonResponse<TbDiscipline> jsonResponse = new JsonResponse<TbDiscipline>();
		try {
			TbDiscipline transientInstance = new TbDiscipline();
			if (dto.getAnswers() == null || "".equals(dto.getAnswers())) {
				msg = "答案不能为空";
				throw new Exception(msg);
			}
			if (dto.getName1() == null ||  "".equals(dto.getName1())) {
				msg = "选项1不能为空";
				throw new Exception(msg);
			}
			if (dto.getName2() == null || "".equals(dto.getName2())) {
				msg = "选项2不能为空";
				throw new Exception(msg);
			}
			if (dto.getName3() == null || "".equals(dto.getName3())) {
				msg = "选项3不能为空";
				throw new Exception(msg);
			}
			if (dto.getName4() == null ||"".equals(dto.getName4())) {
				msg = "选项4不能为空";
				throw new Exception(msg);
			}
			transientInstance.setId(Integer.parseInt(dto.getId()));
			transientInstance.setQuestion(dto.getQuestion());
			transientInstance.setAnswers(dto.getAnswers());
			transientInstance.setScore(5);
			
			TbSubjectType findById = typeDAO.findById(Integer.parseInt(dto.getTpyeId()));
			transientInstance.setTbSubjectType(findById);
			dao.merge(transientInstance);
			
			//保存问题-》选项
			//1
			TbDisciplineOption option1 = new TbDisciplineOption();
			option1.setId(Integer.parseInt(dto.getOptionId1()));
			option1.setContent(dto.getName1());
			option1.setTbDiscipline(transientInstance);
			optionDAO.merge(option1);
			//2
			TbDisciplineOption option2 = new TbDisciplineOption();
			option2.setId(Integer.parseInt(dto.getOptionId2()));
			option2.setContent(dto.getName2());
			option2.setTbDiscipline(transientInstance);
			optionDAO.merge(option2);
			//3
			TbDisciplineOption option3 = new TbDisciplineOption();
			option3.setId(Integer.parseInt(dto.getOptionId3()));
			option3.setContent(dto.getName3());
			option3.setTbDiscipline(transientInstance);
			optionDAO.merge(option3);
			//4
			TbDisciplineOption option4 = new TbDisciplineOption();
			option4.setId(Integer.parseInt(dto.getOptionId4()));
			option4.setContent(dto.getName4());
			option4.setTbDiscipline(transientInstance);
			optionDAO.merge(option4);
			
			jsonResponse.setMsg("修改成功");
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg(msg);
		}
		return jsonResponse;
	}
	
	/**
	 * 添加
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "discipline/add", method = RequestMethod.POST/*, consumes = "application/json"*/)
	@ResponseBody
	public JsonResponse<TbDiscipline> add(@RequestBody final DisciplineDTO dto){
		JsonResponse<TbDiscipline> jsonResponse = new JsonResponse<TbDiscipline>();
		try {
			TbDiscipline transientInstance = new TbDiscipline();
			if (dto.getQuestion() == null || "".equals(dto.getQuestion())) {
				msg = "问题不能为空";
				throw new Exception(msg);
			}
			List findByQuestion = dao.findByQuestion(dto.getQuestion());
			if(!findByQuestion.isEmpty()){
				msg = "该问题已经存在";
				throw new Exception(msg);
			}
			if (dto.getAnswers() == null || "".equals(dto.getAnswers())) {
				msg = "答案不能为空";
				throw new Exception(msg);
			}
			if (dto.getName1() == null || "".equals(dto.getName1())) {
				msg = "选项1不能为空";
				throw new Exception(msg);
			}
			if (dto.getName2() == null || "".equals(dto.getName2())) {
				msg = "选项2不能为空";
				throw new Exception(msg);
			}
			if (dto.getName3() == null || "".equals(dto.getName3())) {
				msg = "选项3不能为空";
				throw new Exception(msg);
			}
			if (dto.getName4() == null || "".equals(dto.getName4())) {
				msg = "选项4不能为空";
				throw new Exception(msg);
			}
			transientInstance.setQuestion(dto.getQuestion());
			transientInstance.setAnswers(dto.getAnswers());
			transientInstance.setScore(5);
			TbSubjectType findById = typeDAO.findById(Integer.parseInt(dto.getTpyeId()));
			transientInstance.setTbSubjectType(findById);
			//返回问题编号
			Integer save = dao.save(transientInstance);
			
			//设置编号
			transientInstance.setId(save);
			//保存问题-》选项
			//1
			TbDisciplineOption option1 = new TbDisciplineOption();
			option1.setContent(dto.getName1());
			option1.setTbDiscipline(transientInstance);
			optionDAO.save(option1);
			//2
			TbDisciplineOption option2 = new TbDisciplineOption();
			option2.setContent(dto.getName2());
			option2.setTbDiscipline(transientInstance);
			optionDAO.save(option2);
			//3
			TbDisciplineOption option3 = new TbDisciplineOption();
			option3.setContent(dto.getName3());
			option3.setTbDiscipline(transientInstance);
			optionDAO.save(option3);
			//4
			TbDisciplineOption option4 = new TbDisciplineOption();
			option4.setContent(dto.getName4());
			option4.setTbDiscipline(transientInstance);
			optionDAO.save(option4);
			jsonResponse.setMsg("添加成功");
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setMsg(msg);
			jsonResponse.setSuccess(false);
		}
		return jsonResponse;
	}
	
	/**
	 * 问题编号，问题，类型编号，类型名称
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "discipline/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbDiscipline> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String question,
															@RequestParam(required = false) final String typeId,
															@RequestParam(required = false) final String typeName){
		JsonResponse<TbDiscipline> jsonResponse = new JsonResponse<TbDiscipline>();
		// 获取对应的参数
		String[] params = new String[] { id, question, typeId, typeName};
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
			Page<TbDiscipline> findAll = dao.findAll(page, size, params);
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
