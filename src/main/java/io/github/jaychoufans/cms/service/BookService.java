package io.github.jaychoufans.cms.service;

import io.github.jaychoufans.cms.model.BookLend;

public interface BookService {

	void lend(Long bookId);

	void modLendStatus(BookLend args);

}
