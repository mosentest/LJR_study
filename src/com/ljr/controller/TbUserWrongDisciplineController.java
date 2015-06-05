package com.ljr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbUserWrongDisciplineDAO;
import com.ljr.dto.UserWrongDisciplineDTO;
import com.ljr.entity.TbDiscipline;
import com.ljr.entity.TbUser;
import com.ljr.entity.TbUserWrongDiscipline;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;

/**
 * 错误题目
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbUserWrongDisciplineController {
	private TbUserWrongDisciplineDAO dao = new TbUserWrongDisciplineDAO();
	
	@RequestMapping(value = "userwrongdiscipline/add", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResponse<TbUserWrongDiscipline> add(@RequestBody final UserWrongDisciplineDTO dto){
		JsonResponse<TbUserWrongDiscipline> jsonResponse = new JsonResponse<TbUserWrongDiscipline>();
		try {
			TbUserWrongDiscipline checkExist = dao.checkExist(Integer.parseInt(dto.getUserId()), Integer.parseInt(dto.getDisciplineId()));
			if (checkExist != null) {
				dao.merge(checkExist);
			}else{
				TbUserWrongDiscipline transientInstance = new TbUserWrongDiscipline();
				TbUser tbUser = new TbUser();
				tbUser.setId(Integer.parseInt(dto.getUserId()));
				transientInstance.setTbUser(tbUser);
				TbDiscipline tbDiscipline = new TbDiscipline();
				tbDiscipline.setId(Integer.parseInt(dto.getDisciplineId()));
				transientInstance.setTbDiscipline(tbDiscipline );
				dao.save(transientInstance);
			}
			jsonResponse.setMsg("添加成功");
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setMsg("添加失败");
			jsonResponse.setSuccess(false);
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "userwrongdiscipline/delete", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResponse<TbUserWrongDiscipline> delete(@RequestBody String id){
		JsonResponse<TbUserWrongDiscipline> jsonResponse = new JsonResponse<TbUserWrongDiscipline>();
		try {
			//"{\"id\":\"1\"}"
			id = id.substring(id.indexOf(":"), id.indexOf("}"));
			id = id.substring(id.indexOf("\"") + 1, id.length() - 1);
			TbUserWrongDiscipline findById = dao.findById(Integer.parseInt(id));
			dao.delete(findById);
			jsonResponse.setMsg("删除成功");
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setMsg("删除失败");
			jsonResponse.setSuccess(false);
		}
		return jsonResponse;
	}
	
	/**
	 * 编号，用户编号，用户名，题目问题
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "userwrongdiscipline/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbUserWrongDiscipline> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String userId,
															@RequestParam(required = false) final String username/*,*/
															/*@RequestParam(required = false) final String question*/){
		JsonResponse<TbUserWrongDiscipline> jsonResponse = new JsonResponse<TbUserWrongDiscipline>();
		// 获取对应的参数
		String[] params = new String[] { id, userId, username/*, question*/};
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
			Page<TbUserWrongDiscipline> findAll = dao.findAll(page, size, params);
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
