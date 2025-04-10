package com.xuebusi.cms.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuebusi.cms.api.common.ResponseResult;
import com.xuebusi.cms.api.model.Block;
import com.xuebusi.cms.api.service.BlockService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/block")
public class BlockController {

    @Resource
    private BlockService blockService;

    @GetMapping("/page/{pageId}")
    public ResponseResult<List<Block>> getByPageId(@PathVariable Integer pageId) {
        List<Block> blocks = blockService.list(
                new QueryWrapper<Block>()
                        .eq("page_id", pageId)
                        .orderByAsc("sort")
        );
        return ResponseResult.success(blocks);
    }

    @PostMapping
    public ResponseResult<Void> create(@RequestBody Block block) {
        blockService.save(block);
        return ResponseResult.success("创建成功");
    }

    @PutMapping("/sort")
    public ResponseResult<Void> updateSort(@RequestBody List<Block> blocks) {
        blocks.forEach(block -> blockService.updateById(block));
        return ResponseResult.success("排序更新成功");
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Void> delete(@PathVariable Integer id) {
        blockService.removeById(id);
        return ResponseResult.success("删除成功");
    }
}