import axios from 'axios'
import { Message } from 'element-ui'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

// axios中请求配置有baseURL选项，表示请求URL公共部分
axios.defaults.baseURL = process.env.API_ROOT

// 超时
axios.defaults.timeout = 10000

// 添加请求拦截器
axios.interceptors.request.use(
    config => {
        // 在发送请求之前做些什么
        return config
    },
    error => {
        // 对请求错误做些什么
        console.log(error)
        return Promise.error(error)
    }
)

// 添加响应拦截器
axios.interceptors.response.use(
    response => {
        // 对响应数据做点什么
        return response;
    },
    error => {
        // 对响应错误做点什么
        if (error.response.status) {
            switch (error.response.status) {
                case 401:
                    Message.error({ message: "401", duration: 10000, showClose: true })
                    break;
                case 403:
                    Message.error({ message: "403", duration: 10000, showClose: true })
                    break;
                case 404:
                    Message.error({ message: "404: " + error.response.data.error + ' server URL ' + error.response.data.path, duration: 10000, showClose: true })
                    break;
                default:
                    Message.error({ message: error.response.status + ": " + JSON.stringify(error.response.data), duration: 10000, showClose: true })
                    break;
            }
        }
        return Promise.reject(error);
    }
)

export default axios

/**
 * get 请求方法
 * @param {String} url 
 * @param {Object} params 
 */
export function get(url, params = {}) {
    return new Promise((resolve, reject) => {
        axios
            .get(url, {
                params: params
            })
            .then(response => {
                resolve(response.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}

/**
 * post 请求方法
 * @param {String} url 
 * @param {Object} data 
 */
export function post(url, data = {}) {
    return new Promise((resolve, reject) => {
        axios.post(url, data).then(
            response => {
                resolve(response.data)
            },
            error => {
                reject(error)
            }
        )
    })
}

/**
 * patch 方法封装
 * @param {String} url 
 * @param {Object} data 
 */
export function patch(url, data = {}) {
    return new Promise((resolve, reject) => {
        axios.patch(url, data).then(
            response => {
                resolve(response.data)
            },
            error => {
                reject(error)
            }
        )
    })
}

/**
 * put 方法封装
 * @param {String} url 
 * @param {Object} data 
 */
export function put(url, data = {}) {
    return new Promise((resolve, reject) => {
        axios.put(url, data).then(
            response => {
                resolve(response.data)
            },
            error => {
                reject(error)
            }
        )
    })
}

/**
 * delete 请求方法
 * @param {String} url 
 * @param {Object} params 
 */
export function deletes(url, params = {}) {
    return new Promise((resolve, reject) => {
        axios
            .delete(url, {
                params: params
            })
            .then(response => {
                resolve(response.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}