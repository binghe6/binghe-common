package com.binghe.common.enums;

/**
 * 返回码接口,所有返回码类需要实现该接口
 * <p>
 * ResponseCode为所有项目公共返回码类，各项目私有返回码需在各项目中自己实现
 * 2000表示成功，其他均表示失败返回码
 * <p>
 *
 * @author binghe
 */
public interface IResponseCode {

    /**
     * 返回码
     *
     * @return
     */
    int getCode();

    /**
     * 返回码对应的描述（外部展示用）
     *
     * @return
     */
    String getOuterMsg();

    /**
     * 返回码对应的描述（内部用，实际错误描述）
     *
     * @return
     */
    String getInnerMsg();

}
