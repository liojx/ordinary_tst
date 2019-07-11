package studiii.zlsj_test.controller.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import studiii.zlsj_test.controller.model.GisDevExtPO;
import studiii.zlsj_test.controller.model.SpaceInfTotalPO;

import java.util.List;

@Repository
public interface GisDevExtPOMapper {
//    long countByExample(GisDevExtPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GisDevExtPO record);

    int insertSelective(GisDevExtPO record);

    GisDevExtPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GisDevExtPO record);

    int updateByPrimaryKey(GisDevExtPO record);

	List<SpaceInfTotalPO> findSpaceInfoByDevIds(@Param("devIds") String devIds);
}