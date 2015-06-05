package com.ljr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbUserCollectDisciplineDAO;
import com.ljr.dto.UserWrongDisciplineDTO;
import com.ljr.entity.TbDiscipline;
import com.ljr.entity.TbUser;
import com.ljr.entity.TbUserCollectDiscipline;
import com.ljr.entity.TbUserWrongDiscipline;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;

/**
 * 收藏题目
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbUserCollectDisciplineController {
	private TbUserCollectDisciplineDAO dao = new TbUserCollectDisciplineDAO();
	
	
	@RequestMapping(value = "usercollectdiscipline/add", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResponse<TbUserCollectDiscipline> add(@RequestBody final UserWrongDisciplineDTO dto){
		JsonResponse<TbUserCollectDiscipline> jsonResponse = new JsonResponse<TbUserCollectDiscipline>();
		try {
			TbUserCollectDiscipline checkExist = dao.checkExist(Integer.parseInt(dto.getUserId()), Integer.parseInt(dto.getDisciplineId()));
			if (checkExist != null) {
				dao.merge(checkExist);
			}
			TbUserCollectDiscipline transientInstance = new TbUserCollectDiscipline();
			TbUser tbUser = new TbUser();
			tbUser.setId(Integer.parseInt(dto.getUserId()));
			transientInstance.setTbUser(tbUser);
			TbDiscipline tbDiscipline = new TbDiscipline();
			tbDiscipline.setId(Integer.parseInt(dto.getDisciplineId()));
			transientInstance.setTbDiscipline(tbDiscipline );
			dao.save(transientInstance);
			jsonResponse.setMsg("添加成功");
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setMsg("添加失败");
			jsonResponse.setSuccess(false);
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "usercollectdiscipline/delete", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResponse<TbUserCollectDiscipline> delete(@RequestBody String id){
		JsonResponse<TbUserCollectDiscipline> jsonResponse = new JsonResponse<TbUserCollectDiscipline>();
		try {
			TbUserCollectDiscipline findById = dao.findById(Integer.parseInt(id));
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
	 * 编号，用户id，用户帐号
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "usercollectdiscipline/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbUserCollectDiscipline> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String userId,
															@RequestParam(required = false) final String username){
		JsonResponse<TbUserCollectDiscipline> jsonResponse = new JsonResponse<TbUserCollectDiscipline>();
		// 获取对应的参数
		String[] params = new String[] { id, userId, username};
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
			Page<TbUserCollectDiscipline> findAll = dao.findAll(page, size, params);
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
