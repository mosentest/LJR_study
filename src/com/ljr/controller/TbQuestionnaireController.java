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
import com.ljr.dao.TbQuestionnaireDAO;
import com.ljr.dao.TbQuestionnaireDisciplineDAO;
import com.ljr.dao.TbSubjectTypeDAO;
import com.ljr.dto.DisciplineDTO;
import com.ljr.dto.QuestionnaireDTO;
import com.ljr.entity.TbDiscipline;
import com.ljr.entity.TbQuestionnaire;
import com.ljr.entity.TbQuestionnaireDiscipline;
import com.ljr.entity.TbQuestionnaire;
import com.ljr.entity.TbSubjectType;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;
/**
 * 问卷action
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbQuestionnaireController {
	
	public String msg = "";
	
	private TbQuestionnaireDAO dao = new TbQuestionnaireDAO();
	private TbQuestionnaireDisciplineDAO disciplineDAO = new TbQuestionnaireDisciplineDAO();
	private TbSubjectTypeDAO typeDAO = new TbSubjectTypeDAO();
	private TbDisciplineDAO tbDisciplineDAO = new TbDisciplineDAO();
	
	/**
	 * 获取其中一个信息，并跳转页面
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "questionnaire/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbQuestionnaire bean = new TbQuestionnaire();
		try {
			bean = dao.findById(Integer.parseInt(id));
			@SuppressWarnings("unchecked")
			//根据问卷编号，查询问卷题目
			List<TbQuestionnaireDiscipline> findAll = disciplineDAO.findAll(new String[] { "", "" + bean.getId() });
			modelMap.put("update", "update");
			modelMap.put("bean", bean);
			for(int i=0;i<findAll.size();i++){
				modelMap.put("bean"+(i+1), findAll.get(i));
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("questionnaire/add");
	}
	
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "questionnaire/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id) {
		try {
			TbQuestionnaire entity =dao.findById(Integer.parseInt(id));
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("questionnaire/index");
	}
	
	/**
	 * 添加
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "questionnaire/add", method = RequestMethod.POST/*, consumes = "application/json"*/)
	@ResponseBody
	public JsonResponse<TbQuestionnaire> add(@RequestBody final QuestionnaireDTO dto){
		JsonResponse<TbQuestionnaire> jsonResponse = new JsonResponse<TbQuestionnaire>();
		try {
			TbQuestionnaire transientInstance = new TbQuestionnaire();
			if ("".equals(dto.getName())) {
				msg = "问卷名称不能为空";
				throw new Exception(msg);
			}
			@SuppressWarnings("rawtypes")
			List findByQuestion = dao.findByProperty("name", dto.getName());
			if(!findByQuestion.isEmpty()){
				msg = "该问卷名称已经存在";
				throw new Exception(msg);
			}
			if ("".equals(dto.getQuestion1())) {
				msg = "问题1不能为空";
				throw new Exception(msg);
			}
			if ("".equals(dto.getQuestion2())) {
				msg = "问题2不能为空";
				throw new Exception(msg);
			}
			if ("".equals(dto.getQuestion3())) {
				msg = "问题3不能为空";
				throw new Exception(msg);
			}
			if ("".equals(dto.getQuestion4())) {
				msg = "问题4不能为空";
				throw new Exception(msg);
			}
			if ("".equals(dto.getQuestion5())) {
				msg = "问题5不能为空";
				throw new Exception(msg);
			}
			transientInstance.setName(dto.getName());
			TbSubjectType findById = typeDAO.findById(Integer.parseInt(dto.getTypeId()));
			transientInstance.setTbSubjectType(findById);
			//返回问题编号
			Integer save = dao.save(transientInstance);
			
			//设置编号
			transientInstance.setId(save);
			//保存问卷-》问题-》选项
			//1
			TbQuestionnaireDiscipline option1 = new TbQuestionnaireDiscipline();
			//根据id获取信息
			TbDiscipline tbDiscipline1 = tbDisciplineDAO.findById(Integer.parseInt(dto.getQuestion1()));
			option1.setTbDiscipline(tbDiscipline1);
			option1.setTbQuestionnaire(transientInstance);
			disciplineDAO.save(option1);
			//2
			TbQuestionnaireDiscipline option2 = new TbQuestionnaireDiscipline();
			TbDiscipline tbDiscipline2 = tbDisciplineDAO.findById(Integer.parseInt(dto.getQuestion2()));
			option2.setTbDiscipline(tbDiscipline2);
			option2.setTbQuestionnaire(transientInstance);
			disciplineDAO.save(option2);
			//3
			TbQuestionnaireDiscipline option3 = new TbQuestionnaireDiscipline();
			TbDiscipline tbDiscipline3 = tbDisciplineDAO.findById(Integer.parseInt(dto.getQuestion2()));
			option3.setTbDiscipline(tbDiscipline3);
			option3.setTbQuestionnaire(transientInstance);
			disciplineDAO.save(option3);
			//4
			TbQuestionnaireDiscipline option4 = new TbQuestionnaireDiscipline();
			TbDiscipline tbDiscipline4 = tbDisciplineDAO.findById(Integer.parseInt(dto.getQuestion4()));
			option4.setTbDiscipline(tbDiscipline4);
			option4.setTbQuestionnaire(transientInstance);
			disciplineDAO.save(option4);
			//5
			TbQuestionnaireDiscipline option5 = new TbQuestionnaireDiscipline();
			TbDiscipline tbDiscipline5 = tbDisciplineDAO.findById(Integer.parseInt(dto.getQuestion5()));
			option5.setTbDiscipline(tbDiscipline5);
			option5.setTbQuestionnaire(transientInstance);
			disciplineDAO.save(option4);
			
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
	 * 问卷编号，类型编号，类型名称
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "questionnaire/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbQuestionnaire> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String name,
															@RequestParam(required = false) final String typeId,
															@RequestParam(required = false) final String typeName){
		JsonResponse<TbQuestionnaire> jsonResponse = new JsonResponse<TbQuestionnaire>();
		// 获取对应的参数
		String[] params = new String[] { id, name, typeId, typeName };
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
			Page<TbQuestionnaire> findAll = dao.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg("获取成功");
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg("获取失败");
		}
		return jsonResponse;
	}
}
