package chain.handler;

public interface Step<T, U> {
    /**
     * 处理步骤，ture继续下一步，false结束当前步骤
     *
     */
    boolean process(T detail);

    /**
     * 是否启用该步骤
     *
     */
    boolean isEnable(U info);
}
