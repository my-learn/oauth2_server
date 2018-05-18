# oauth2_server
oauth2_server

错误码(error)|	错误编号(error_code)	|错误描述(error_description)
---|---|---
redirect_uri_mismatch|	21322	|重定向地址不匹配
invalid_request	|21323	|请求不合法
invalid_client	|21324	|client_id或client_secret参数无效
invalid_grant|	21325|	提供的Access Grant是无效的、过期的或已撤销的
unauthorized_client|	21326|	客户端没有权限
expired_token|	21327	|token过期
unsupported_grant_type|	21328	|不支持的 GrantType
unsupported_response_type|	21329	|不支持的 ResponseType
access_denied|	21330	|用户或授权服务器拒绝授予数据访问权限
temporarily_unavailable	|21331	|服务暂时无法访问
