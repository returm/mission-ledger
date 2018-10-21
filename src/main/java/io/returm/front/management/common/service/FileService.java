package io.returm.front.management.common.service;

import io.returm.front.common.web.service.GenericService;
import io.returm.front.management.common.entity.FileEntity;
import io.returm.front.management.common.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService implements GenericService<FileEntity> {

    /** Mapper */
    @Autowired
    private FileMapper mapper;

    /*
     * (non-Javadoc)
     * @see com.skprobono.admin.common.web.service.GenericService\#get(java.lang.Object)
     */
    @Override
    public FileEntity get(FileEntity entity) {
        return mapper.get(entity);
    }

    /*
     * (non-Javadoc)
     * @see com.skprobono.admin.common.web.service.GenericService\#getList(java.lang.Object)
     */
    @Override
    public List<FileEntity> getList(FileEntity entity) {
        int count = this.getCount(entity);
        entity.getPagingValue(count);
        return mapper.getList(entity);
    }

    /*
     * (non-Javadoc)
     * @see com.skprobono.admin.common.web.service.GenericService\#getAllList(java.lang.Object)
     */
    @Override
    public List<FileEntity> getAllList(FileEntity entity) {
        return mapper.getAllList(entity);
    }

    /* (non-Javadoc)
     * @see com.skprobono.admin.common.web.service.GenericService\#getCount(java.lang.Object)
     */
    @Override
    public Integer getCount(FileEntity entity) {
        return mapper.count(entity);
    }

    /* (non-Javadoc)
     * @see com.skprobono.admin.common.web.service.GenericService\#insert(java.lang.Object)
     */
    @Override
    public FileEntity insert(FileEntity entity) {
        mapper.insert(entity);
        return entity;
    }

    /* (non-Javadoc)
     * @see com.skprobono.admin.common.web.service.GenericService\#update(java.lang.Object)
     */
    @Override
    public FileEntity update(FileEntity entity) {
        mapper.update(entity);
        return entity;
    }

    /* (non-Javadoc)
     * @see com.skprobono.admin.common.web.service.GenericService\#delete(java.lang.Object)
     */
    @Override
    public int delete(FileEntity entity) {
        return mapper.delete(entity);
    }
}
