package com.qin.crxl.comic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.CartoonPhoto;
import com.qin.crxl.comic.entity.CartoonSet;
import com.qin.crxl.comic.entity.CartoonSetComment;
import com.qin.crxl.comic.entity.FollowCartoon;
import com.qin.crxl.comic.entity.HistoryRecord;
import com.qin.crxl.comic.entity.MallCartoonOrder;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.VeryOk;
import com.qin.crxl.comic.entity.vo.CartoonDataVo;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;
import com.qin.crxl.comic.entity.vo.CartoonSetCommentData;
import com.qin.crxl.comic.entity.vo.FriendPhotoData;
import com.qin.crxl.comic.entity.vo.UserEntityData;
import com.qin.crxl.comic.service.CartoonPhotoService;
import com.qin.crxl.comic.service.CartoonService;
import com.qin.crxl.comic.service.CartoonSetService;
import com.qin.crxl.comic.service.FollowCartoonService;
import com.qin.crxl.comic.service.HistoryRecordService;
import com.qin.crxl.comic.service.MallCartoonOrderService;
import com.qin.crxl.comic.service.UserOrderService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.service.VeryOkService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class CartoonPhotoController {

	@Autowired
	private CartoonPhotoService CartoonPhotoService;
	@Autowired
	private UserService userService;
	@Autowired
	private HistoryRecordService historyRecordService;
	@Autowired
	private CartoonService cartoonService;
	@Autowired
	private MallCartoonOrderService mallCartoonOrderService;
	@Autowired
	private CartoonSetService cartoonSetService;
	@Autowired
	private VeryOkService veryOkService;
	@Autowired
	private FollowCartoonService followCartoonService;

	/**
	 * pp
	 * 
	 * @Remarks 每话每个漫画集数>看漫画
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOON_PHOTO_BYCARTOONSET, method = RequestMethod.POST)
	public Model getAllCartoonCommentSetConmmentSon(
			CartoonPhotoData cartoonPhotoData) throws Exception {
		Model model = new Model();
		cartoonPhotoData.clickUser();
		UserEntity userEntity = userService.get(cartoonPhotoData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonPhotoData.getNowPage())) {
			cartoonPhotoData.setNowPage("1");
		}
		int num = 0;
		List<Object> list4 = new ArrayList<Object>();
		List<CartoonPhoto> list = new ArrayList<CartoonPhoto>();
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		if (cartoonPhotoData.getCartoonSetId() != null) {
			CartoonSet cartoonSet = cartoonSetService.get(cartoonPhotoData
					.getCartoonSetId());
			if (cartoonSet == null) {
				return new Model(500, "无此话");
			}
			if (cartoonSet.getMoneyState() == 1) {
				if (userEntity.getVipId() == 2) {
					List<VeryOk> very = veryOkService.getUserSetVseryOk(
							cartoonSet.getId(), userEntity.getUserId());
					if (!ParaClick.clickList(very)) {
						map.put("veryOk", 0);
					} else {
						map.put("veryOk", 1);
					}
					num = CartoonPhotoService
							.getCartoonPhotoNum(
									cartoonPhotoData.getCartoonId(),
									cartoonSet.getId());
					if (num == 0) {
						return new Model(500, "查询信息数量异常");
					}
					list = CartoonPhotoService
							.getALLCartoonPhotoByCartoonSetId(
									cartoonPhotoData.getCartoonId(),
									cartoonSet.getId(),
									cartoonPhotoData.getNowPage());
					if (!ParaClick.clickList(list)) {
						return new Model(500, "查询话集异常");
					}
					for (CartoonPhoto cartoonPhoto : list) {
						CartoonDataVo FriendPhotoData = new CartoonDataVo();
						FriendPhotoData.setSrc(cartoonPhoto.getPhotoUrl());
						FriendPhotoData.setW(cartoonPhoto.getPhotoWidth());
						FriendPhotoData.setH(cartoonPhoto.getPhotoHeight());
						FriendPhotoData.setId(cartoonPhoto.getId());
						list4.add(FriendPhotoData);
					}
					map.put("titile", cartoonSet.getTitile());
					map.put("cartoonSetId", list.get(0).getCartoonSetId());
					if (Integer.parseInt(cartoonPhotoData.getNowPage()) == 1) {
						boolean fl = CartoonPhotoService.addAllParameter(
								cartoonPhotoData, userEntity.getUserId());
						if (!fl) {
							return new Model(500, "修改狀態异常");
						}
					}
				} else {
					MallCartoonOrder mallCartoonOrder = mallCartoonOrderService
							.getHistory(cartoonSet.getCartoonId(),
									cartoonSet.getId(), userEntity.getUserId());
					if (mallCartoonOrder == null) {
						if (userEntity.getVipId() < 1) {
							if (!"1".equals(userEntity.getHobby())) {
								map.put("price", cartoonSet.getPrice());
								map.put("integral", userEntity.getIntegral());
								map.put("titile", cartoonSet.getTitile());
								num = CartoonPhotoService.getCartoonPhotoNum(
										cartoonPhotoData.getCartoonId(),
										cartoonSet.getId());
								if (num == 0) {
									return new Model(500, "查询信息数量异常");
								}
								list = CartoonPhotoService
										.getALLCartoonPhotoByCartoonSetId(
												cartoonPhotoData.getCartoonId(),
												cartoonSet.getId(),
												cartoonPhotoData.getNowPage());
								if (!ParaClick.clickList(list)) {
									return new Model(500, "查询话集异常");
								}
								for (CartoonPhoto cartoonPhoto : list) {
									CartoonDataVo FriendPhotoData = new CartoonDataVo();
									FriendPhotoData.setSrc(cartoonPhoto
											.getPhotoUrl());
									FriendPhotoData.setW(cartoonPhoto
											.getPhotoWidth());
									FriendPhotoData.setH(cartoonPhoto
											.getPhotoHeight());
									FriendPhotoData.setId(cartoonPhoto.getId());
									list4.add(FriendPhotoData);
								}
								map.put("cartoonSetId", list.get(0)
										.getCartoonSetId());
								List<VeryOk> very = veryOkService
										.getUserSetVseryOk(cartoonSet.getId(),
												userEntity.getUserId());
								if (!ParaClick.clickList(very)) {
									map.put("veryOk", 0);
								} else {
									map.put("veryOk", 1);
								}
								model.setError(300);
								model.setNowpage(Integer
										.parseInt(cartoonPhotoData.getNowPage()));
								model.setTotalpage((num + 3 - 1) / 3);
								model.setObj(list4);
								model.setSpare(map);
								return model;
							}
							List<VeryOk> very = veryOkService
									.getUserSetVseryOk(cartoonSet.getId(),
											userEntity.getUserId());
							if (!ParaClick.clickList(very)) {
								map.put("veryOk", 0);
							} else {
								map.put("veryOk", 1);
							}
							num = CartoonPhotoService.getCartoonPhotoNum(
									cartoonPhotoData.getCartoonId(),
									cartoonSet.getId());
							if (num == 0) {
								return new Model(500, "查询信息数量异常");
							}
							list = CartoonPhotoService
									.getALLCartoonPhotoByCartoonSetId(
											cartoonPhotoData.getCartoonId(),
											cartoonSet.getId(),
											cartoonPhotoData.getNowPage());
							if (!ParaClick.clickList(list)) {
								return new Model(500, "查询话集异常");
							}
							for (CartoonPhoto cartoonPhoto : list) {
								CartoonDataVo FriendPhotoData = new CartoonDataVo();
								FriendPhotoData.setSrc(cartoonPhoto
										.getPhotoUrl());
								FriendPhotoData.setW(cartoonPhoto
										.getPhotoWidth());
								FriendPhotoData.setH(cartoonPhoto
										.getPhotoHeight());
								FriendPhotoData.setId(cartoonPhoto.getId());
								list4.add(FriendPhotoData);
							}
							map.put("price", cartoonSet.getPrice());
							map.put("integral", userEntity.getIntegral());
							map.put("titile", cartoonSet.getTitile());
							map.put("cartoonSetId", list.get(0)
									.getCartoonSetId());
							if (userEntity.getIntegral() < cartoonSet
									.getPrice()) {
								model.setError(300);
								model.setNowpage(Integer
										.parseInt(cartoonPhotoData.getNowPage()));
								model.setTotalpage((num + 3 - 1) / 3);
								model.setObj(list4);
								model.setSpare(map);
								return model;
							}
							boolean flag = mallCartoonOrderService
									.BuyThisCartoonSetByIos(
											userEntity.getUserId(),
											cartoonSet.getCartoonId(),
											cartoonSet.getId(),
											cartoonSet.getPrice());
							if (!flag) {
								return new Model(500, "解鎖失敗");
							}

							if (Integer.parseInt(cartoonPhotoData.getNowPage()) == 1) {
								boolean fl = CartoonPhotoService
										.addAllParameter(cartoonPhotoData,
												userEntity.getUserId());
								if (!fl) {
									return new Model(500, "修改狀態异常");
								}
							}
							map.put("titile", cartoonSet.getTitile());
						} else {
							if (!"1".equals(userEntity.getHobby())) {
								List<VeryOk> very = veryOkService
										.getUserSetVseryOk(cartoonSet.getId(),
												userEntity.getUserId());
								if (!ParaClick.clickList(very)) {
									map.put("veryOk", 0);
								} else {
									map.put("veryOk", 1);
								}
								map.put("oldprice",
										Double.parseDouble(cartoonSet
												.getPrice() + ""));
								map.put("price",
										Double.parseDouble(cartoonSet
												.getPrice() * 0.7 + ""));
								map.put("integral", userEntity.getIntegral());
								map.put("titile", cartoonSet.getTitile());
								num = CartoonPhotoService.getCartoonPhotoNum(
										cartoonPhotoData.getCartoonId(),
										cartoonSet.getId());
								if (num == 0) {
									return new Model(500, "查询信息数量异常");
								}
								list = CartoonPhotoService
										.getALLCartoonPhotoByCartoonSetId(
												cartoonPhotoData.getCartoonId(),
												cartoonSet.getId(),
												cartoonPhotoData.getNowPage());
								if (!ParaClick.clickList(list)) {
									return new Model(500, "查询话集异常");
								}
								for (CartoonPhoto cartoonPhoto : list) {
									CartoonDataVo FriendPhotoData = new CartoonDataVo();
									FriendPhotoData.setSrc(cartoonPhoto
											.getPhotoUrl());
									FriendPhotoData.setW(cartoonPhoto
											.getPhotoWidth());
									FriendPhotoData.setH(cartoonPhoto
											.getPhotoHeight());
									FriendPhotoData.setId(cartoonPhoto.getId());
									list4.add(FriendPhotoData);
								}
								map.put("cartoonSetId", list.get(0)
										.getCartoonSetId());
								model.setError(300);
								model.setNowpage(Integer
										.parseInt(cartoonPhotoData.getNowPage()));
								model.setTotalpage((num + 3 - 1) / 3);
								model.setObj(list4);
								model.setSpare(map);
								return model;
							}
							List<VeryOk> very = veryOkService
									.getUserSetVseryOk(cartoonSet.getId(),
											userEntity.getUserId());
							if (!ParaClick.clickList(very)) {
								map.put("veryOk", 0);
							} else {
								map.put("veryOk", 1);
							}
							num = CartoonPhotoService.getCartoonPhotoNum(
									cartoonPhotoData.getCartoonId(),
									cartoonSet.getId());
							if (num == 0) {
								return new Model(500, "查询信息数量异常");
							}
							list = CartoonPhotoService
									.getALLCartoonPhotoByCartoonSetId(
											cartoonPhotoData.getCartoonId(),
											cartoonSet.getId(),
											cartoonPhotoData.getNowPage());
							if (!ParaClick.clickList(list)) {
								return new Model(500, "查询话集异常");
							}
							for (CartoonPhoto cartoonPhoto : list) {
								CartoonDataVo FriendPhotoData = new CartoonDataVo();
								FriendPhotoData.setSrc(cartoonPhoto
										.getPhotoUrl());
								FriendPhotoData.setW(cartoonPhoto
										.getPhotoWidth());
								FriendPhotoData.setH(cartoonPhoto
										.getPhotoHeight());
								FriendPhotoData.setId(cartoonPhoto.getId());
								list4.add(FriendPhotoData);
							}
							map.put("price", cartoonSet.getPrice());
							map.put("integral", userEntity.getIntegral());
							map.put("titile", cartoonSet.getTitile());
							if (userEntity.getIntegral() < cartoonSet
									.getPrice()) {
								model.setError(300);
								model.setNowpage(Integer
										.parseInt(cartoonPhotoData.getNowPage()));
								model.setTotalpage((num + 3 - 1) / 3);
								model.setObj(list4);
								model.setSpare(map);
								return model;
							}
							map.put("cartoonSetId", list.get(0)
									.getCartoonSetId());
							boolean flag = mallCartoonOrderService
									.BuyThisCartoonSetByIos(
											userEntity.getUserId(),
											cartoonSet.getCartoonId(),
											cartoonSet.getId(),
											(int) (cartoonSet.getPrice() * 0.7));
							if (!flag) {
								return new Model(500, "解鎖失敗");
							}
							if (Integer.parseInt(cartoonPhotoData.getNowPage()) == 1) {
								boolean fl = CartoonPhotoService
										.addAllParameter(cartoonPhotoData,
												userEntity.getUserId());
								if (!fl) {
									return new Model(500, "修改狀態异常");
								}
							}
							map.put("titile", cartoonSet.getTitile());
						}
					} else {
						List<VeryOk> very = veryOkService.getUserSetVseryOk(
								cartoonSet.getId(), userEntity.getUserId());
						if (!ParaClick.clickList(very)) {
							map.put("veryOk", 0);
						} else {
							map.put("veryOk", 1);
						}
						num = CartoonPhotoService.getCartoonPhotoNum(
								cartoonPhotoData.getCartoonId(),
								cartoonSet.getId());
						if (num == 0) {
							return new Model(500, "查询信息数量异常");
						}
						list = CartoonPhotoService
								.getALLCartoonPhotoByCartoonSetId(
										cartoonPhotoData.getCartoonId(),
										cartoonSet.getId(),
										cartoonPhotoData.getNowPage());
						if (!ParaClick.clickList(list)) {
							return new Model(500, "查询话集异常");
						}
						for (CartoonPhoto cartoonPhoto : list) {
							CartoonDataVo FriendPhotoData = new CartoonDataVo();
							FriendPhotoData.setSrc(cartoonPhoto.getPhotoUrl());
							FriendPhotoData.setW(cartoonPhoto.getPhotoWidth());
							FriendPhotoData.setH(cartoonPhoto.getPhotoHeight());
							FriendPhotoData.setId(cartoonPhoto.getId());
							list4.add(FriendPhotoData);
						}
						map.put("cartoonSetId", list.get(0).getCartoonSetId());
						if (Integer.parseInt(cartoonPhotoData.getNowPage()) == 1) {
							boolean fl = CartoonPhotoService.addAllParameter(
									cartoonPhotoData, userEntity.getUserId());
							if (!fl) {
								return new Model(500, "修改狀態异常");
							}
						}
						map.put("titile", cartoonSet.getTitile());
					}
				}
			} else {
				List<VeryOk> very = veryOkService.getUserSetVseryOk(
						cartoonSet.getId(), userEntity.getUserId());
				if (!ParaClick.clickList(very)) {
					map.put("veryOk", 0);
				} else {
					map.put("veryOk", 1);
				}
				num = CartoonPhotoService.getCartoonPhotoNum(
						cartoonPhotoData.getCartoonId(), cartoonSet.getId());
				if (num == 0) {
					return new Model(500, "查询信息数量异常");
				}
				list = CartoonPhotoService.getALLCartoonPhotoByCartoonSetId(
						cartoonPhotoData.getCartoonId(), cartoonSet.getId(),
						cartoonPhotoData.getNowPage());
				if (!ParaClick.clickList(list)) {
					return new Model(500, "查询话集异常");
				}
				for (CartoonPhoto cartoonPhoto : list) {
					CartoonDataVo FriendPhotoData = new CartoonDataVo();
					FriendPhotoData.setSrc(cartoonPhoto.getPhotoUrl());
					FriendPhotoData.setW(cartoonPhoto.getPhotoWidth());
					FriendPhotoData.setH(cartoonPhoto.getPhotoHeight());
					FriendPhotoData.setId(cartoonPhoto.getId());
					list4.add(FriendPhotoData);
				}
				map.put("cartoonSetId", list.get(0).getCartoonSetId());
				if (Integer.parseInt(cartoonPhotoData.getNowPage()) == 1) {
					boolean fl = CartoonPhotoService.addAllParameter(
							cartoonPhotoData, userEntity.getUserId());
					if (!fl) {
						return new Model(500, "修改狀態异常");
					}
				}
				map.put("titile", cartoonSet.getTitile());
			}
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonPhotoData.getNowPage()));
		model.setTotalpage((num + 3 - 1) / 3);
		model.setObj(list4);
		model.setSpare(map);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 每话每个漫画集数>上下话
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOONPHOTO_BYIDUP, method = RequestMethod.POST)
	public Model getAllCartoonPhoto(CartoonPhotoData cartoonPhotoData)
			throws Exception {
		Model model = new Model();
		cartoonPhotoData.clickUser();
		if (ParaClick.clickString(cartoonPhotoData.getNowPage())) {
			cartoonPhotoData.setNowPage("1");
		}
		UserEntity userEntity = userService.get(cartoonPhotoData.getUserId());
		if (userEntity == null) {
			return new Model(500, "无用户");
		}
		CartoonSet cartoonSet = cartoonSetService.get(cartoonPhotoData
				.getCartoonSetId());
		if (cartoonSet == null) {
			return new Model(500, "无此话");
		}
		if (Integer.parseInt(cartoonPhotoData.getUp()) == 1) {
			cartoonSet = cartoonSetService.getBySort(cartoonSet.getSort() + 1,
					cartoonPhotoData.getCartoonId());
			if (cartoonSet == null) {
				return new Model(500, "目前没有下一话了");
			}
		} else {
			cartoonSet = cartoonSetService.getBySort(cartoonSet.getSort() - 1,
					cartoonPhotoData.getCartoonId());
			if (cartoonSet == null) {
				return new Model(500, "已经是第一话了");
			}
		}
		int num = 0;
		List<CartoonPhoto> list = new ArrayList<CartoonPhoto>();
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		List<Object> list4 = new ArrayList<Object>();
		if (cartoonSet.getId() != null) {
			if (cartoonSet.getMoneyState() == 1) {
				if (userEntity.getVipId() == 2) {
					List<VeryOk> very = veryOkService.getUserSetVseryOk(
							cartoonSet.getId(), userEntity.getUserId());
					if (!ParaClick.clickList(very)) {
						map.put("veryOk", 0);
					} else {
						map.put("veryOk", 1);
					}
					num = CartoonPhotoService
							.getCartoonPhotoNum(
									cartoonPhotoData.getCartoonId(),
									cartoonSet.getId());
					if (num == 0) {
						return new Model(500, "查询信息数量异常");
					}
					list = CartoonPhotoService
							.getALLCartoonPhotoByCartoonSetId(
									cartoonPhotoData.getCartoonId(),
									cartoonSet.getId(),
									cartoonPhotoData.getNowPage());
					if (!ParaClick.clickList(list)) {
						return new Model(500, "查询话集异常");
					}
					for (CartoonPhoto cartoonPhoto : list) {
						CartoonDataVo FriendPhotoData = new CartoonDataVo();
						FriendPhotoData.setSrc(cartoonPhoto.getPhotoUrl());
						FriendPhotoData.setW(cartoonPhoto.getPhotoWidth());
						FriendPhotoData.setH(cartoonPhoto.getPhotoHeight());
						FriendPhotoData.setId(cartoonPhoto.getId());
						list4.add(FriendPhotoData);
					}
					map.put("titile", cartoonSet.getTitile());
					map.put("cartoonSetId", list.get(0).getCartoonSetId());
					if (Integer.parseInt(cartoonPhotoData.getNowPage()) == 1) {
						boolean fl = CartoonPhotoService.addAllParameter2(
								cartoonSet, userEntity);
						if (!fl) {
							return new Model(500, "修改狀態异常");
						}
					}
				} else {
					MallCartoonOrder mallCartoonOrder = mallCartoonOrderService
							.getHistory(cartoonSet.getCartoonId(),
									cartoonSet.getId(), userEntity.getUserId());
					if (mallCartoonOrder == null) {
						if (userEntity.getVipId() < 1) {
							if (!"1".equals(userEntity.getHobby())) {
								num = CartoonPhotoService.getCartoonPhotoNum(
										cartoonPhotoData.getCartoonId(),
										cartoonSet.getId());
								if (num == 0) {
									return new Model(500, "查询信息数量异常");
								}
								list = CartoonPhotoService
										.getALLCartoonPhotoByCartoonSetId(
												cartoonPhotoData.getCartoonId(),
												cartoonSet.getId(),
												cartoonPhotoData.getNowPage());
								if (!ParaClick.clickList(list)) {
									return new Model(500, "查询话集异常");
								}
								for (CartoonPhoto cartoonPhoto : list) {
									CartoonDataVo FriendPhotoData = new CartoonDataVo();
									FriendPhotoData.setSrc(cartoonPhoto
											.getPhotoUrl());
									FriendPhotoData.setW(cartoonPhoto
											.getPhotoWidth());
									FriendPhotoData.setH(cartoonPhoto
											.getPhotoHeight());
									FriendPhotoData.setId(cartoonPhoto.getId());
									list4.add(FriendPhotoData);
								}
								map.put("cartoonSetId", list.get(0)
										.getCartoonSetId());
								List<VeryOk> very = veryOkService
										.getUserSetVseryOk(cartoonSet.getId(),
												userEntity.getUserId());
								if (!ParaClick.clickList(very)) {
									map.put("veryOk", 0);
								} else {
									map.put("veryOk", 1);
								}
								map.put("price", cartoonSet.getPrice());
								map.put("integral", userEntity.getIntegral());
								map.put("titile", cartoonSet.getTitile());
								model.setError(300);
								model.setNowpage(Integer
										.parseInt(cartoonPhotoData.getNowPage()));
								model.setTotalpage((num + 3 - 1) / 3);
								model.setObj(list4);
								model.setSpare(map);
								return model;
							}
							List<VeryOk> very = veryOkService
									.getUserSetVseryOk(cartoonSet.getId(),
											userEntity.getUserId());
							if (!ParaClick.clickList(very)) {
								map.put("veryOk", 0);
							} else {
								map.put("veryOk", 1);
							}
							num = CartoonPhotoService.getCartoonPhotoNum(
									cartoonPhotoData.getCartoonId(),
									cartoonSet.getId());
							if (num == 0) {
								return new Model(500, "查询信息数量异常");
							}
							list = CartoonPhotoService
									.getALLCartoonPhotoByCartoonSetId(
											cartoonPhotoData.getCartoonId(),
											cartoonSet.getId(),
											cartoonPhotoData.getNowPage());
							if (!ParaClick.clickList(list)) {
								return new Model(500, "查询话集异常");
							}
							for (CartoonPhoto cartoonPhoto : list) {
								CartoonDataVo FriendPhotoData = new CartoonDataVo();
								FriendPhotoData.setSrc(cartoonPhoto
										.getPhotoUrl());
								FriendPhotoData.setW(cartoonPhoto
										.getPhotoWidth());
								FriendPhotoData.setH(cartoonPhoto
										.getPhotoHeight());
								FriendPhotoData.setId(cartoonPhoto.getId());
								list4.add(FriendPhotoData);
							}
							map.put("price", cartoonSet.getPrice());
							map.put("integral", userEntity.getIntegral());
							map.put("titile", cartoonSet.getTitile());
							map.put("cartoonSetId", list.get(0)
									.getCartoonSetId());
							if (userEntity.getIntegral() < cartoonSet
									.getPrice()) {
								model.setError(300);
								model.setNowpage(Integer
										.parseInt(cartoonPhotoData.getNowPage()));
								model.setTotalpage((num + 3 - 1) / 3);
								model.setObj(list4);
								model.setSpare(map);
								return model;
							}
							boolean flag = mallCartoonOrderService
									.BuyThisCartoonSetByIos(
											userEntity.getUserId(),
											cartoonSet.getCartoonId(),
											cartoonSet.getId(),
											cartoonSet.getPrice());
							if (!flag) {
								return new Model(500, "解鎖失敗");
							}
							if (Integer.parseInt(cartoonPhotoData.getNowPage()) == 1) {
								boolean fl = CartoonPhotoService
										.addAllParameter2(cartoonSet,
												userEntity);
								if (!fl) {
									return new Model(500, "修改狀態异常");
								}
							}
							map.put("titile", cartoonSet.getTitile());
						} else {
							if (!"1".equals(userEntity.getHobby())) {
								List<VeryOk> very = veryOkService
										.getUserSetVseryOk(cartoonSet.getId(),
												userEntity.getUserId());
								if (!ParaClick.clickList(very)) {
									map.put("veryOk", 0);
								} else {
									map.put("veryOk", 1);
								}
								map.put("oldprice",
										Double.parseDouble(cartoonSet
												.getPrice() + ""));
								map.put("price",
										Double.parseDouble(cartoonSet
												.getPrice() * 0.7 + ""));
								map.put("integral", userEntity.getIntegral());
								map.put("titile", cartoonSet.getTitile());
								num = CartoonPhotoService.getCartoonPhotoNum(
										cartoonPhotoData.getCartoonId(),
										cartoonSet.getId());
								if (num == 0) {
									return new Model(500, "查询信息数量异常");
								}
								list = CartoonPhotoService
										.getALLCartoonPhotoByCartoonSetId(
												cartoonPhotoData.getCartoonId(),
												cartoonSet.getId(),
												cartoonPhotoData.getNowPage());
								if (!ParaClick.clickList(list)) {
									return new Model(500, "查询话集异常");
								}
								for (CartoonPhoto cartoonPhoto : list) {
									CartoonDataVo FriendPhotoData = new CartoonDataVo();
									FriendPhotoData.setSrc(cartoonPhoto
											.getPhotoUrl());
									FriendPhotoData.setW(cartoonPhoto
											.getPhotoWidth());
									FriendPhotoData.setH(cartoonPhoto
											.getPhotoHeight());
									FriendPhotoData.setId(cartoonPhoto.getId());
									list4.add(FriendPhotoData);
								}
								map.put("cartoonSetId", list.get(0)
										.getCartoonSetId());
								model.setError(300);
								model.setNowpage(Integer
										.parseInt(cartoonPhotoData.getNowPage()));
								model.setTotalpage((num + 3 - 1) / 3);
								model.setObj(list4);
								model.setSpare(map);
								return model;
							}
							List<VeryOk> very = veryOkService
									.getUserSetVseryOk(cartoonSet.getId(),
											userEntity.getUserId());
							if (!ParaClick.clickList(very)) {
								map.put("veryOk", 0);
							} else {
								map.put("veryOk", 1);
							}
							num = CartoonPhotoService.getCartoonPhotoNum(
									cartoonPhotoData.getCartoonId(),
									cartoonSet.getId());
							if (num == 0) {
								return new Model(500, "查询信息数量异常");
							}
							list = CartoonPhotoService
									.getALLCartoonPhotoByCartoonSetId(
											cartoonPhotoData.getCartoonId(),
											cartoonSet.getId(),
											cartoonPhotoData.getNowPage());
							if (!ParaClick.clickList(list)) {
								return new Model(500, "查询话集异常");
							}
							for (CartoonPhoto cartoonPhoto : list) {
								CartoonDataVo FriendPhotoData = new CartoonDataVo();
								FriendPhotoData.setSrc(cartoonPhoto
										.getPhotoUrl());
								FriendPhotoData.setW(cartoonPhoto
										.getPhotoWidth());
								FriendPhotoData.setH(cartoonPhoto
										.getPhotoHeight());
								FriendPhotoData.setId(cartoonPhoto.getId());
								list4.add(FriendPhotoData);
							}
							map.put("price", cartoonSet.getPrice());
							map.put("integral", userEntity.getIntegral());
							map.put("titile", cartoonSet.getTitile());
							map.put("cartoonSetId", list.get(0)
									.getCartoonSetId());
							if (userEntity.getIntegral() < cartoonSet
									.getPrice()) {
								model.setError(300);
								model.setNowpage(Integer
										.parseInt(cartoonPhotoData.getNowPage()));
								model.setTotalpage((num + 3 - 1) / 3);
								model.setObj(list4);
								model.setSpare(map);
								return model;
							}
							boolean flag = mallCartoonOrderService
									.BuyThisCartoonSetByIos(
											userEntity.getUserId(),
											cartoonSet.getCartoonId(),
											cartoonSet.getId(),
											(int) (cartoonSet.getPrice() * 0.7));
							if (!flag) {
								return new Model(500, "解鎖失敗");
							}

							if (Integer.parseInt(cartoonPhotoData.getNowPage()) == 1) {
								boolean fl = CartoonPhotoService
										.addAllParameter2(cartoonSet,
												userEntity);
								if (!fl) {
									return new Model(500, "修改狀態异常");
								}
							}
							map.put("titile", cartoonSet.getTitile());
						}
					} else {
						List<VeryOk> very = veryOkService.getUserSetVseryOk(
								cartoonSet.getId(), userEntity.getUserId());
						if (!ParaClick.clickList(very)) {
							map.put("veryOk", 0);
						} else {
							map.put("veryOk", 1);
						}
						num = CartoonPhotoService.getCartoonPhotoNum(
								cartoonPhotoData.getCartoonId(),
								cartoonSet.getId());
						if (num == 0) {
							return new Model(500, "查询信息数量异常");
						}
						list = CartoonPhotoService
								.getALLCartoonPhotoByCartoonSetId(
										cartoonPhotoData.getCartoonId(),
										cartoonSet.getId(),
										cartoonPhotoData.getNowPage());
						if (!ParaClick.clickList(list)) {
							return new Model(500, "查询话集异常");
						}
						for (CartoonPhoto cartoonPhoto : list) {
							CartoonDataVo FriendPhotoData = new CartoonDataVo();
							FriendPhotoData.setSrc(cartoonPhoto.getPhotoUrl());
							FriendPhotoData.setW(cartoonPhoto.getPhotoWidth());
							FriendPhotoData.setH(cartoonPhoto.getPhotoHeight());
							FriendPhotoData.setId(cartoonPhoto.getId());
							list4.add(FriendPhotoData);
						}
						map.put("cartoonSetId", list.get(0).getCartoonSetId());
						if (Integer.parseInt(cartoonPhotoData.getNowPage()) == 1) {
							boolean fl = CartoonPhotoService
									.addAllParameter2(cartoonSet,
											userEntity);
							if (!fl) {
								return new Model(500, "修改狀態异常");
							}
						}
						// map.put("oldprice", cartoonSet.getPrice());
						// map.put("price", cartoonSet.getPrice() * 0.7);
						// map.put("titile", cartoonSet.getTitile());
						// map.put("integral", userEntity.getIntegral());
						model.setError(200);
						model.setNowpage(Integer.parseInt(cartoonPhotoData
								.getNowPage()));
						model.setTotalpage((num + 3 - 1) / 3);
						model.setObj(list4);
						model.setSpare(map);
						return model;
					}
				}
			} else {
				List<VeryOk> very = veryOkService.getUserSetVseryOk(
						cartoonSet.getId(), userEntity.getUserId());
				if (!ParaClick.clickList(very)) {
					map.put("veryOk", 0);
				} else {
					map.put("veryOk", 1);
				}
				num = CartoonPhotoService.getCartoonPhotoNum(
						cartoonPhotoData.getCartoonId(), cartoonSet.getId());
				if (num == 0) {
					return new Model(500, "查询信息数量异常");
				}
				list = CartoonPhotoService.getALLCartoonPhotoByCartoonSetId(
						cartoonSet.getCartoonId(), cartoonSet.getId(),
						cartoonPhotoData.getNowPage());
				if (!ParaClick.clickList(list)) {
					return new Model(500, "查询话集异常");
				}
				for (CartoonPhoto cartoonPhoto : list) {
					CartoonDataVo FriendPhotoData = new CartoonDataVo();
					FriendPhotoData.setSrc(cartoonPhoto.getPhotoUrl());
					FriendPhotoData.setW(cartoonPhoto.getPhotoWidth());
					FriendPhotoData.setH(cartoonPhoto.getPhotoHeight());
					FriendPhotoData.setId(cartoonPhoto.getId());
					list4.add(FriendPhotoData);
				}
				map.put("cartoonSetId", list.get(0).getCartoonSetId());
				if (Integer.parseInt(cartoonPhotoData.getNowPage()) == 1) {
					boolean fl = CartoonPhotoService.addAllParameter2(
							cartoonSet, userEntity);
					if (!fl) {
						return new Model(500, "修改狀態异常");
					}
				}
				map.put("titile", cartoonSet.getTitile());
			}
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonPhotoData.getNowPage()));
		model.setTotalpage((num + 3 - 1) / 3);
		model.setObj(list4);
		model.setSpare(map);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 每话每个漫画集数>購買次集漫畫（不重複購買）
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.BUY_THIS_CARTOONSET, method = RequestMethod.POST)
	public Model purchaseThisCartoonSet(CartoonPhotoData cartoonPhotoData,
			UserEntityData user) throws Exception {
		Model model = new Model();
		cartoonPhotoData.clickUser();
		UserEntity userEntity = userService.get(cartoonPhotoData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		// if (ParaClick.clickString(cartoonPhotoData.getNowPage())) {
		// cartoonPhotoData.setNowPage("1");
		// }
		List<Object> list4 = new ArrayList<Object>();
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		if (Integer.parseInt(user.getIntegral()) < Integer
				.parseInt(cartoonPhotoData.getPrice())) {
			return new Model(500, "余额不足");
		}
		boolean flag = mallCartoonOrderService.BuyThisCartoonSet(userEntity,
				cartoonPhotoData);
		if (!flag) {
			return new Model(500, "解鎖失敗");
		}
		// int num = CartoonPhotoService.getCartoonPhotoNum(
		// cartoonPhotoData.getCartoonId(),
		// cartoonPhotoData.getCartoonSetId());
		// if (num == 0) {
		// return new Model(500, "查询信息数量异常");
		// }
		// List<CartoonPhoto> list = CartoonPhotoService
		// .getALLCartoonPhotoByCartoonSetId(
		// cartoonPhotoData.getCartoonId(),
		// cartoonPhotoData.getCartoonSetId(),
		// cartoonPhotoData.getNowPage());
		// if (!ParaClick.clickList(list)) {
		// return new Model(500, "查询话集异常");
		// }
		// for (CartoonPhoto cartoonPhoto : list) {
		// CartoonDataVo FriendPhotoData = new CartoonDataVo();
		// FriendPhotoData.setSrc(cartoonPhoto.getPhotoUrl());
		// FriendPhotoData.setW(cartoonPhoto.getPhotoWidth());
		// FriendPhotoData.setH(cartoonPhoto.getPhotoHeight());
		// FriendPhotoData.setId(cartoonPhoto.getId());
		// list4.add(FriendPhotoData);
		// }
		// List<Object> lh=new ArrayList<Object>();
		// for (CartoonPhoto cartoonPhoto : list) {
		// Hashtable<String,Object> map2=new Hashtable<String,Object>();
		// map2.put("photoUrl", cartoonPhoto.getPhotoUrl());
		// lh.add(map2);
		// }
		boolean fl = CartoonPhotoService.addAllParameter(cartoonPhotoData,
				userEntity.getUserId());
		if (!fl) {
			return new Model(500, "修改狀態异常");
		}
		// List<VeryOk> very = veryOkService.getUserSetVseryOk(
		// cartoonPhotoData.getCartoonSetId(), userEntity.getUserId());
		// if (!ParaClick.clickList(very)) {
		// map.put("veryOk", 0);
		// } else {
		// map.put("veryOk", 1);
		// }
		// List<FollowCartoon> followCartoon =
		// followCartoonService.getFollowByid(
		// cartoonPhotoData.getCartoonId(), userEntity.getUserId());
		// if (!ParaClick.clickList(followCartoon)) {
		// map.put("followCartoon", 0);
		// } else {
		// map.put("followCartoon", 1);
		// }
		model.setError(200);
		// model.setMsg("成功购买");
		// model.setNowpage(Integer.parseInt(cartoonPhotoData.getNowPage()));
		// model.setTotalpage((num + 3 - 1) / 3);
		// model.setObj(list4);
		// model.setSpare(map);
		return model;
	}
}
