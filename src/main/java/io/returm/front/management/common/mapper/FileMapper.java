package io.returm.front.management.common.mapper;

import io.returm.front.management.common.entity.FileEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FileMapper {

    /**
     * get
     * 조회
     * @method get
     * @param Entity
     * @return
     */
    public FileEntity get(FileEntity Entity) throws DataAccessException;

    /**
     * list (with paging)
     * 리스트
     * @method list
     * @param Entity
     * @return
     */
    public List<FileEntity> getList(FileEntity Entity) throws DataAccessException;

    /**
     * all list
     * 전체 리스트
     * @method list
     * @param Entity
     * @return
     */
    public List<FileEntity> getAllList(FileEntity Entity) throws DataAccessException;

    /**
     * count
     * 카운트
     * @method count
     * @param Entity
     * @return
     */
    public int count(FileEntity Entity) throws DataAccessException;



    /**
     * insert
     * 등록
     * @method insert
     * @param Entity
     * @return
     */
    public int insert(FileEntity Entity) throws DataAccessException;

    /**
     * update
     * 수정
     * @method update
     * @param Entity
     * @return
     */
    public int update(FileEntity Entity) throws DataAccessException;

    /**
     * delete
     * 삭제
     * @method delete
     * @param Entity
     * @return
     */
    public int delete(FileEntity Entity) throws DataAccessException;
}
