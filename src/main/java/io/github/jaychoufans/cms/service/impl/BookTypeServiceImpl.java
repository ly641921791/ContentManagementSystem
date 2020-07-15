package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.BookTypeMapper;
import io.github.jaychoufans.cms.model.BookType;
import io.github.jaychoufans.cms.service.BookTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {

	@Resource
	private BookTypeService that;

	@Override
	public boolean removeById(Serializable id) {
		if (id == null || that.getById(id) == null) {
			return false;
		}

		return super.removeById(id);
	}

	@Override
	public void removeTreeByRootId(Long id) {
		if (id == null || that.getById(id) == null) {
			return;
		}

		that.removeById(id);

		BookType args = new BookType();
		args.setParentId(id);
		List<Long> ids = that.list(new QueryWrapper<>(args)).stream().map(BookType::getId).collect(Collectors.toList());

		that.removeTreeByRootIds(ids);
	}

	@Override
	public void removeTreeByRootIds(List<Long> idList) {
		if (CollectionUtils.isEmpty(idList)) {
			return;
		}
		idList.forEach(id -> that.removeTreeByRootId(id));
	}

}
