package com.example.epidemicprevention.module.epidemic.service;

import com.example.epidemicprevention.module.epidemic.vo.EpidemicAddVO;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.module.epidemic.entity.Epidemic;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Epidemic服务类
 * </p>
 *
 * @author zzy
 * @date 2022-01-17
 */
public interface EpidemicService extends IService<Epidemic> {
    /**
     * 添加疫情
     *
     * @param epidemicAddVo
     * @return
     */
    Result<Object> insertEpidemic(EpidemicAddVO epidemicAddVo);

    /**
     * 分页查询
     *
     * @param current：页码
     * @param size：每页条数
     * @param epidemic：筛选条件
     * @return IPage<Epidemic>
     */
    IPage<Epidemic> getEpidemicPage(Integer current, Integer size, Epidemic epidemic);

    /**
     * 查询所有
     *
     * @param epidemic：筛选条件
     * @return List<Epidemic>
     */
    List<Epidemic> getAll(Epidemic epidemic);

    /**
     * 通过id删除
     *
     * @param id：epidemicId
     * @return Result<Object>
     */
    Result<Object> deleteById(String id);

    /**
     * 批量删除
     *
     * @param ids：id列表
     * @return Result<Object>
     */
    Result<Object> batchDelete(List<String> ids);
}
