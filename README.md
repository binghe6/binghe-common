# 功能说明
1. 封装统一的ResponseCode
2. controller统一异常处理
3. controller的响应数据统一封装
4. 自定义异常
* ## 封装统一ResponseCode
	1. 新建项目的需要在 ResponseCodeRegion 添加枚举，列出该项目的响应码范围
	2. 响应码枚举实现 IResponseCode 并使用 @ResponseCodeAnnotation 标注引用的响应码类型
* ## controller统一异常处理 
	1. 异常处理在 ExceptionControllerHandler 中，如果需要增加异常的处理，可以在此类中加
* ## controller的响应数据统一封装
	1. 在 ResponseControllerHandler 中对应所有的接口响应数据进行统一封装，数据格式为 ApiResponse
	2. 不需要封装为 ApiResponse 格式的接口，在方法上使用 IgnoreApiResponseAnnotation 注解
* ## 自定义异常
	1. 项目中可以处理的异常，抛出 ApiException
* ## 补充
	1. 全局通用的需要spring容器扫描的类放到 com.binghe.common.scans.common 目录下
	2. 根据项目情况不同，自定义需要spring容器扫描的类放到 com.binghe.common.scans.custom 目录下