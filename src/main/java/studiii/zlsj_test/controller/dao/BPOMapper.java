package studiii.zlsj_test.controller.dao;

import studiii.zlsj_test.controller.model.BPO;

public interface BPOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(BPO record);

    int insertSelective(BPO record);

    BPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BPO record);

    int updateByPrimaryKey(BPO record);
}