# open_wechat
微信公众平台开发框架，组件化/构建化开发，支持多公众号模式。

## 什么是open_wechat？
open_wechat是一个用Java语言开发的微信公众平台基础开发框架，open_chat致力于暴露尽量简单的接口，减少开发者对微信公众平台开发协议的关注。
open_wechat基于Maven开发，使用spring-mvc作为MVC框架。

## open_wechat可以干什么？
open_wechat可以被用来开发微信公众号管理平台，通过极少的配置就可以RUN现有的sample。但是open_wechat作为一个基础框架，仅对协议进行了封装，
并没有完整的后台管理页面，也没有与固定的数据源进行绑定，尽量减少对业务系统的侵入。

## Quick Start

完善中...

2015年9月5日更新<br>
1. Merge from spring-mvc 分支，将spring-mvc作为将来主要支持的mvc框架<br>
2. 增加Spring MVC Argument Resolver, 当经过wechat拦截器拦截，且方法参数中有WechatObject类型参数，自动赋值。<br>
3. 增加图文消息接口<br>
4. 其他大量微调<br>


加入项目请联系： QQ 228608606
Contributor: Wit Wang, Nicola Liu


