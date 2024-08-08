package chain.handler;

import chain.dto.StuEnrollDTO;
import chain.dto.StuEnrollDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 录取处理链管理器
 */
@Component
@Slf4j
@AllArgsConstructor
public class EnrollChainManager {

    private final List<CollageEnrollHandle> handlers;

    @PostConstruct
    public void init() {
        Collections.sort(handlers, AnnotationAwareOrderComparator.INSTANCE);
        log.info("[录取执行顺序：{}]", handlers);
    }

    public void process(StuEnrollDTO dto) {
        log.info("[录取信息：{}]", dto);
        // 获取需开启的执行链路
        List<CollageEnrollHandle> handlerChain = handlers.stream().filter(
                (item) -> item.isEnable(dto)
        ).collect(Collectors.toList());
        log.info("[本次录取处理执行链路：{}]", handlerChain);

        // 预处理
        for (CollageEnrollHandle handler : handlerChain) {
            handler.preHandle(dto);
        }

        List<StuEnrollDetail> details = dto.getDetails();
        // 正式处理
        details.parallelStream().forEach(
                (item) -> {
                    for (CollageEnrollHandle handler : handlerChain) {
                        if (!handler.process(item)) {
                            return;
                        }
                    }
                }
        );

        Map<Boolean, List<StuEnrollDetail>> resultMap = details.stream().collect(Collectors.partitioningBy(StuEnrollDetail::isApplyResult));
        List<StuEnrollDetail> enrolledList = resultMap.get(true);
        List<StuEnrollDetail> failedList = resultMap.get(false);
        log.info("[本次处理总共录取成功{}人,录取人员信息{}]", enrolledList.size(), enrolledList);
        log.info("[本次处理录取失败人数{}人,录取失败人员信息{}]", failedList.size(), failedList);

    }
}
