import axios from 'axios'

//封装的axios请求的工具库，可以请求后台的接口，request对象来post ip和端口已在此处确定
const request = axios.create({ //new一个axios对象
    baseURL: 'http://localhost:9090',  // 全局所有接口都会加上'/api'
})

// request 拦截器 拦截请求
// 请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {//可以加一些请求参数
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // config.headers['token'] = user.token;  // 设置请求头
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {//解析
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request