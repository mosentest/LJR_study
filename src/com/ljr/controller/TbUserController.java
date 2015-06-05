package com.ljr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ljr.dao.TbUserDAO;
import com.ljr.dto.UserDTO;
import com.ljr.entity.TbUser;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;
import com.ljr.util.StringUtil;

@Controller
@RequestMapping("/")
public class TbUserController {
	
	private TbUserDAO dao = new TbUserDAO();
	
	private String msg = "";
	
	
	/**
	 * 添加
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "user/edit", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResponse<TbUser> edit(@RequestBody final UserDTO dto){
		JsonResponse<TbUser> jsonResponse = new JsonResponse<TbUser>();
		try {
			if(StringUtil.isEmpty(dto.getPassword())){
				msg="密码不能为空";
				throw new Exception(msg);
			}
			if(StringUtil.isEmpty(dto.getName())){
				msg="名字不能为空";
				throw new Exception(msg);
			}
			TbUser transientInstance = new TbUser();
			transientInstance.setId(Integer.parseInt(dto.getId()));
			transientInstance.setName(dto.getName());
			transientInstance.setUsername(dto.getUsername());
			transientInstance.setPassword(dto.getPassword());
			dao.merge(transientInstance);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg("修改成功");
		} catch (Exception e) {
			jsonResponse.setSuccess(true);
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
	@RequestMapping(value = "user/showOne.html", method = RequestMethod.GET)
	public ModelAndView showOne(@RequestParam final String id,ModelMap modelMap) {
		TbUser bean = new TbUser();
		try {
			bean = dao.findById(Integer.parseInt(id));
			modelMap.put("update", "update");
			modelMap.put("bean", bean);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("user/add");
	}
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	public ModelAndView delete(final String id) {
		try {
			TbUser entity =dao.findById(Integer.parseInt(id));
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("user/index");
	}
	
	/**
	 * 添加
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "user/add", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResponse<TbUser> add(@RequestBody final UserDTO dto){
		JsonResponse<TbUser> jsonResponse = new JsonResponse<TbUser>();
		try {
			if(StringUtil.isEmpty(dto.getUsername())){
				msg="帐号不能为空";
				throw new Exception(msg);
			}
			if(StringUtil.isEmpty(dto.getPassword())){
				msg="密码不能为空";
				throw new Exception(msg);
			}
			if(StringUtil.isEmpty(dto.getName())){
				msg="名字不能为空";
				throw new Exception(msg);
			}
			TbUser transientInstance = new TbUser();
			transientInstance.setName(dto.getName());
			transientInstance.setUsername(dto.getUsername());
			transientInstance.setPassword(dto.getPassword());
			dao.save(transientInstance);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg("添加成功");
		} catch (Exception e) {
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg(msg);
		}
		return jsonResponse;
	}
	
	/**
	 * 编号，用户id，用户帐号
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "user/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbUser> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String username){
		JsonResponse<TbUser> jsonResponse = new JsonResponse<TbUser>();
		// 获取对应的参数
		String[] params = new String[] { id, username};
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
			Page<TbUser> findAll = dao.findAll(page, size, params);
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
