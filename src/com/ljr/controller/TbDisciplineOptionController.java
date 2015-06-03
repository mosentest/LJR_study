package com.ljr.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbDisciplineOptionDAO;
import com.ljr.entity.TbDisciplineOption;
import com.ljr.util.JsonResponse;
/**
 * 问题的选项
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbDisciplineOptionController {
	private TbDisciplineOptionDAO dao = new TbDisciplineOptionDAO();

	/**
	 * 问题编号
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "disciplineoption/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbDisciplineOption> list(@RequestParam(required = true) final String id) {
		JsonResponse<TbDisciplineOption> jsonResponse = new JsonResponse<TbDisciplineOption>();
		try {
			@SuppressWarnings("unchecked")
			List<TbDisciplineOption> findAll = dao.findAll(Integer.parseInt(id));
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg("获取成功");
			jsonResponse.setList(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg("获取失败");
		}
		return jsonResponse;
	}
}
