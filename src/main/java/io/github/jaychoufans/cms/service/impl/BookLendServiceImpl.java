package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.BookLendMapper;
import io.github.jaychoufans.cms.model.BookLend;
import io.github.jaychoufans.cms.service.BookLendService;
import org.springframework.stereotype.Service;

@Service
public class BookLendServiceImpl extends ServiceImpl<BookLendMapper, BookLend> implements BookLendService {

}
