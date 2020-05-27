package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.BookTypeMapper;
import io.github.jaychoufans.cms.model.BookType;
import io.github.jaychoufans.cms.service.BookTypeService;
import org.springframework.stereotype.Service;

@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {

}
