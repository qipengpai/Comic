package com.qin.crxl.comic.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonComment;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonCommentData;
import com.qin.crxl.comic.exception.BusinessException;
import com.qin.crxl.comic.service.CartoonCommentService;
import com.qin.crxl.comic.service.CartoonService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.Emoji;
import com.qin.crxl.comic.tool.StringToInt;
import com.qin.game.entity.redis.JedisUtil;

@Service
public class CartoonCommentServiceImpl extends BaseServiceImpl<CartoonComment>
		implements CartoonCommentService {

	/*
	 * @Autowired private CartoonService cartoonService;
	 */

	@Override
	public int getCartoonCommentCount(Cartoon cartoon) {
		// 查看漫画评论人数
		int num = super
				.gettotalpage("SELECT COUNT(*) FROM  CartoonComment WHERE cartoonId='"
						+ cartoon.getId() + "'");
		return num;
	}

	@Override
	// @Cacheable(value="CartoonComment",key="#cartoonId.concat(#bestNew).concat(#nowPage)")
	public List<CartoonComment> getALLCartoonComment(String cartoonId,
			String bestNew, String nowPage) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT  * FROM  CartoonComment  WHERE  1=1 AND cartoonId='"
				+ cartoonId + "' AND deleteCartoonComment=1 ");
		if (Integer.parseInt(bestNew) == 1) {
			sb.append(" ORDER BY commentDate DESC ");
		} else {
			sb.append("  ORDER BY okCount DESC,commentDate ASC ");
		}
		sb.append(" LIMIT " + (Integer.parseInt(nowPage) - 1) * 10 + ",10");
		 List<CartoonComment> list =
		 super.SQL("CC-"+cartoonId+"-"+bestNew+"-"+nowPage,3600,sb.toString(),CartoonComment.class);
//		List<CartoonComment> list = super.SQL(sb.toString(),
//				CartoonComment.class);

		return list;
	}

	@Override
	// @Cacheable(value="CartoonComment",key="#root.methodName.concat(#cartoonCommentData.cartoonId)")
	public int getAllCartoonCommentCount(CartoonCommentData cartoonCommentData) {
		// 查看漫画评论人数
		 int num = super
		 .gettotalpage("CC-"+cartoonCommentData.getCartoonId()+"-Num",3600,"SELECT COUNT(*) FROM  CartoonComment WHERE cartoonId='"
		 + cartoonCommentData.getCartoonId()
		 + "' AND deleteCartoonComment=1");
//		int num = super
//				.gettotalpage("SELECT COUNT(*) FROM  CartoonComment WHERE cartoonId='"
//						+ cartoonCommentData.getCartoonId()
//						+ "' AND deleteCartoonComment=1");
		return num;
	}

	@Override
	// @Cacheable(value="CartoonCommentSon",key="#root.methodName.concat(#id)")
	public int getCartoonComment2Count(String id, String cartoonId) {
		// 查看漫画评论的评论数
		int num = super.gettotalpage("CCS-" + cartoonId + "-" + id + "-Num",
				3600, "SELECT COUNT(*) FROM  CartoonComment WHERE commentId='"
						+ id + "' AND deleteCartoonComment=1 ");
		// int num = super
		// .gettotalpage("SELECT COUNT(*) FROM  CartoonComment WHERE commentId='"
		// + id + "' AND deleteCartoonComment=1 ");
		return num;
	}

	@Override
	// @CacheEvict(value="CartoonComment",allEntries=true)
	public boolean addCartoonComment(CartoonCommentData cartoonCommentData,
			UserEntity userEntity) throws Exception {
		// 评论该剧
		boolean flag = false;
		try {
			// int num = getAllCartoonCommentCount(cartoonCommentData);
			CartoonComment cartoonComment = new CartoonComment();
			cartoonComment.setCartoonId(cartoonCommentData.getCartoonId());
			// cartoonComment.setCommentInfo(Emoji.emojiConvert1(Emoji.filterEmoji(cartoonCommentData.getCommentInfo())));
			cartoonComment.setCommentInfo(StringToInt.toInt(cartoonCommentData
					.getCommentInfo()));
			cartoonComment.setCommentId("0");
			cartoonComment.setCommentDate(DateUtil
					.getdate_yyyy_MM_dd_HH_MM_SS());
			cartoonComment.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			cartoonComment.setUserId(userEntity.getUserId());
			cartoonComment.setAite("0");
			cartoonComment.setOkCount(0);
			cartoonComment.setSort(1);
			cartoonComment.setCommentCount(0);
			cartoonComment.setDeleteCartoonComment(1);
			save(cartoonComment);
			/*
			 * boolean flag2=
			 * cartoonService.addCommentCount(cartoonCommentData.getCartoonId
			 * ()); if(!flag2){ throw new BusinessException("评论异常"); }
			 */
			JedisUtil.batchDel("CC-" + cartoonCommentData.getCartoonId());
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		super.SQL("UPDATE Cartoon SET commentCount=(commentCount+1) WHERE id='"
				+ cartoonCommentData.getCartoonId() + "'");
		return flag;
	}

	@Override
	// @CacheEvict(value="CartoonCommentSon",key="#cartoonCommentData.id",allEntries=false)
	public boolean addCartoonCommentSetConmment(
			CartoonCommentData cartoonCommentData, UserEntity userEntity,
			String cartoonId) {
		// 评论该评论
		boolean flag = false;
		try {
			int num = getAllCartoonCommentCountByComment(cartoonCommentData);
			CartoonComment cartoonComment = new CartoonComment();
			cartoonComment.setCartoonId("0");
			cartoonComment.setCommentInfo(StringToInt.toInt(cartoonCommentData
					.getCommentInfo()));
			cartoonComment.setCommentId(cartoonCommentData.getId());
			cartoonComment.setCommentDate(DateUtil
					.getdate_yyyy_MM_dd_HH_MM_SS());
			cartoonComment.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			cartoonComment.setUserId(userEntity.getUserId());
			cartoonComment.setAite("0");
			cartoonComment.setOkCount(0);
			cartoonComment.setSort(num + 1);
			cartoonComment.setDeleteCartoonComment(1);
			save(cartoonComment);
			boolean flag2 = addCartoonCommentSetConmmentCount(cartoonCommentData);
			if (!flag2) {
				throw new BusinessException("异常");
			}
			// /////////redis
			JedisUtil.batchDel("CCS-" + cartoonId);
			JedisUtil.batchDel("CC-" + cartoonId);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	private int getAllCartoonCommentCountByComment(
			CartoonCommentData cartoonCommentData) {
		// 查看漫画评论人数
		int num = super
				.gettotalpage("SELECT COUNT(*) FROM  CartoonComment WHERE commentId='"
						+ cartoonCommentData.getCommentId() + "'");
		return num;
	}

	@Override
	// @Cacheable(value="CartoonCommentSon",key="#root.methodName.concat(#id).concat(#nowPage)")
	public List<CartoonComment> getALLCartoonCommentByCommentId(String id,
			String nowPage, String cartoonId) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT  * FROM  CartoonComment  WHERE  1=1 AND commentId='"
				+ id + "' ORDER BY commentDate DESC ");
		sb.append(" LIMIT " + (Integer.parseInt(nowPage) - 1) * 10 + ",10");
		List<CartoonComment> list = super.SQL("CCS-" + cartoonId + "-" + id
				+ "-" + nowPage, 3600, sb.toString(), CartoonComment.class);
		// List<CartoonComment> list = super.SQL(sb.toString(),
		// CartoonComment.class);
		return list;
	}

	@Override
	// @Cacheable(value="CartoonCommentSon",key="#root.methodName.concat(#id)")
	public List<CartoonComment> getCartoonCommentTwo(String id, String cartoonId) {
		// 查询没条评论的前两条评论
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT  * FROM  CartoonComment  WHERE  1=1 AND deleteCartoonComment=1 AND commentId='"
				+ id + "' ORDER BY commentDate ASC LIMIT 0,2");
		// ////////redis
		List<CartoonComment> list = super.SQL("CCS-" + cartoonId + "-" + id
				+ "-Two", 3600, sb.toString(), CartoonComment.class);
		// List<CartoonComment> list = super.SQL(sb.toString(),
		// CartoonComment.class);
		return list;
	}

	// @Override
	public boolean addCartoonCommentSetConmmentCount(
			CartoonCommentData cartoonCommentData) {
		// 增加评论的评论次数
		boolean flag = false;
		try {
			CartoonComment cartoonComment = super.get(cartoonCommentData
					.getId());
			if (cartoonComment == null) {
				return flag;
			}
			cartoonComment
					.setCommentCount(cartoonComment.getCommentCount() + 1);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean addOkCount(String id) {
		// 增加評論的點贊數
		boolean flag = false;
		try {
			CartoonComment cartoonComment = super.get(id);
			if (cartoonComment == null) {
				return flag;
			}
			cartoonComment.setOkCount(cartoonComment.getOkCount() + 1);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean subductionOkCount(String id) {
		// 减少点赞数
		boolean flag = false;
		try {
			CartoonComment cartoonComment = super.get(id);
			if (cartoonComment == null) {
				return flag;
			}
			cartoonComment.setOkCount(cartoonComment.getOkCount() - 1);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

}
