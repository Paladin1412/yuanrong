# 圆融系统
```lua
yuanrongbank-soa
├── yuanrong-common -- SSM框架公共模块
└── yuanrong-admin -- 后台管理系统
    ├── yuanrong-admin-dao -- bean、dao接口
    ├── yuanrong-admin-rpc-api -- 服务层接口
    ├── yuanrong-admin-rpc-service -- 服务层实现类
    ├── yuanrong-admin-server -- 管理后台
    ├── yuanrong-admin-mall -- 前台展示系统
    └── yuanrong-admin-web -- 前台管理系统
```

### 权限拦截说明
- 系统不拦截路径 `/*/ex_*`
- 只需要登录，不验证权限`/*/exs_*`
- 其他路径全部都需要授权才能访问
