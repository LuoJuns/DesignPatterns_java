package chain.handler;

import chain.dto.StuEnrollDTO;
import chain.dto.StuEnrollDetail;
import org.springframework.core.Ordered;

public interface CollageEnrollHandle extends Step<StuEnrollDetail, StuEnrollDTO>, Ordered {
    /**
     * 录取相关操作前的预处理
     *
     */
    default void preHandle(StuEnrollDTO dto) {
    }
}
