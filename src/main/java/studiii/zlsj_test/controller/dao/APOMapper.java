package studiii.zlsj_test.controller.dao;

import studiii.zlsj_test.controller.model.APO;

public interface APOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(APO record);

    int insertSelective(APO record);

    APO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(APO record);

    int updateByPrimaryKey(APO record);
}