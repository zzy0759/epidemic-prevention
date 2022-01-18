package com.example.epidemicprevention.module.epidemic.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.epidemicprevention.module.epidemic.entity.Epidemic;
import com.example.epidemicprevention.module.epidemic.mapper.EpidemicMapper;
import com.example.epidemicprevention.module.epidemic.service.EpidemicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.epidemicprevention.module.epidemic.vo.EpidemicAddVO;
import com.example.epidemicprevention.response.ResponseState;
import com.example.epidemicprevention.response.Result;
import com.example.epidemicprevention.util.WrapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Epidemic服务实现类
 * </p>
 *
 * @author zzy
 * @since 2022-01-17
 */
@Service
public class EpidemicServiceImpl extends ServiceImpl<EpidemicMapper, Epidemic> implements EpidemicService {

    @Autowired
    private EpidemicMapper epidemicMapper;

    /**
     * 添加疫情
     *
     * @param epidemicAddVo
     * @return
     */
    @Override
    public Result<Object> insertEpidemic(EpidemicAddVO epidemicAddVo) {
        Epidemic epidemic = new Epidemic(epidemicAddVo.getName(), epidemicAddVo.getSeverityGrade(), epidemicAddVo.getStatus());
        epidemicMapper.insert(epidemic);
        return Result.OK();
    }

    /**
     * 根据条件筛选出疫情对应的病例人数
     *
     * @param name
     * @return
     */
    @Override
    public Result<Object> selectEpidemicWithPatientCount(String name, Integer current, Integer size) {
        Map<String, Object> params = new HashMap<>(5);
        params.put("name", name);
        Page<Epidemic> epidemicPage = new Page<>(current, size);
        final Page<Epidemic> page = epidemicMapper.selectEpidemicWithPatientCount(epidemicPage, params);
        return Result.OK(page);
    }

    /**
     * 分页查询
     *
     * @param current：页码
     * @param size：每页条数
     * @param epidemic：筛选条件
     * @return IPage<Epidemic>
     */
    @Override
    public IPage<Epidemic> getEpidemicPage(Integer current, Integer size, Epidemic epidemic) {
        QueryWrapper<Epidemic> queryWrapper = WrapperUtils.queryWrapper(epidemic);
        Page<Epidemic> epidemicPage = new Page<>(current, size);
        return epidemicMapper.selectPage(epidemicPage, queryWrapper);
    }

    /**
     * 查询所有
     *
     * @param epidemic：筛选条件
     * @return List<Epidemic>
     */
    @Override
    public List<Epidemic> getAll(Epidemic epidemic) {
        QueryWrapper<Epidemic> queryWrapper = WrapperUtils.queryWrapper(epidemic);
        return epidemicMapper.selectList(queryWrapper);
    }

    /**
     * 通过id删除
     *
     * @param id：epidemicId
     * @return Result<Object>
     */
    @Override
    public Result<Object> deleteById(String id) {
        Epidemic epidemic = epidemicMapper.selectById(id);
        if (epidemic == null) {
            return Result.error(ResponseState.TARGET_NOT_EXIST.getValue(), ResponseState.TARGET_NOT_EXIST.getMessage());
        }
        epidemicMapper.deleteById(id);
        return Result.OK();
    }

    /**
     * 批量删除
     *
     * @param ids：id列表
     * @return Result<Object>
     */
    @Override
    public Result<Object> batchDelete(List<String> ids) {
        UpdateWrapper<Epidemic> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids);
        epidemicMapper.delete(updateWrapper);
        return Result.OK();
    }
}