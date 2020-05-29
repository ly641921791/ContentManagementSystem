package io.github.jaychoufans.cms.service.impl;

import io.github.jaychoufans.cms.exception.CmsException;
import io.github.jaychoufans.cms.model.BookInfo;
import io.github.jaychoufans.cms.model.BookLend;
import io.github.jaychoufans.cms.service.BookInfoService;
import io.github.jaychoufans.cms.service.BookLendService;
import io.github.jaychoufans.cms.service.BookService;
import io.github.jaychoufans.cms.utils.WebUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class BookServiceImpl implements BookService {

	@Resource
	private BookInfoService bookInfoService;

	@Resource
	private BookLendService bookLendService;

	@Override
	@Transactional
	public void lend(Long bookId) {
		// 预扣图书库存
		BookInfo bookInfo;
		BookInfo bookInfoNew;
		do {
			bookInfo = bookInfoService.getById(bookId);
			if (bookInfo.getRemaining() < 1) {
				throw new CmsException("B0001", "图书库存不足");
			}
			bookInfoNew = new BookInfo();
			bookInfoNew.setId(bookInfo.getId());
			bookInfoNew.setRemaining(bookInfo.getRemaining() - 1);
			bookInfoNew.setVersion(bookInfo.getVersion());
		}
		while (!bookInfoService.updateById(bookInfoNew));

		// 添加借书记录
		BookLend bookLend = new BookLend();
		bookLend.setUserId(WebUtils.getCurrentUser().getId());
		bookLend.setBookId(bookId);
		bookLendService.save(bookLend);
	}

}
