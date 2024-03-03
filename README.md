# NioLan安卓框架

## 为什么使用NioLan

安卓作为移动端开发不可缺少的一部分，面临目前对于跨端，跨语言，以及跨平台，都需要进行一定的使用，目前对于Flutter,React native,h5,等等都是开发者所必须研究的对象，所以对于NioLan框架，就是为了实现我们快速搭建Android端的同时，也能够实现轻松的跨段操作。

## 使用原生baseBinding操作

### 安卓页面创建要求

1. 需要打开binding,所有的页面尽量继承base包下的内容
2. 对于broadcast静态注册，开发者可选择Notification可以不用继承
3. 对于BaseService,作为开发者提供网络获取自己外部包的方式，也可以是检测Device状态是否正常的基础Service
4. RN框架为提供js外界，用户，或者厂商自主进行处理