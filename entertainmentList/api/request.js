
const waitingList = [] // 等待请求队列
const excutingList = [] // 执行队列
const limit = 10 // 最大请求数
let showLogoutModal = true // 是否显示登录过期提示modal

/**
 * 网络请求方法 
 * @param {stirng} url 请求地址
 * @param data 请求参数
 * @param params 请求参数-POST请求下参数以?的方式拼接在url之后
 * @param {string} method 请求方式
 * @param {string} contentType 请求数据格式
 * @param {boolean} loading 是否显示加载中
 * @param {string} field 指定数据字段（例如返回的数组字段不在data中，通过指定该字段返回）  
 * @param {boolean} all 返回全部字段
 * 
 * @return promise
 */
function request({ url, data, params, method = 'GET', contentType, loading = false, field = 'data', all = true }, resolve, reject) {
  const token = ''
  if (loading) {
    uni.showLoading({
      title: '加载中'
    })
  }
  const requsetUrl = "http://192.168.3.83:8080"+url
  const requestTask = uni.request({
    url: requsetUrl,
    data,
    method,
	timeout:60000,
    header: {
      'content-type': contentType || 'application/json;charset=UTF-8',
      'Authorization': 'Bearer ' + token
    },
    success: (res) => {
      if (loading) uni.hideLoading()

      if (!res.data) { reject(res) }

      // 特殊环境打印调式
      // #ifdef MP-WEIXIN
      const { enableDebug } = uni.getAppBaseInfo()
      enableDebug && console.log({ ...res, url: requsetUrl, params: data })
      // #endif

      // #ifdef APP-PLUS
      console.log({ ...res, url: requsetUrl, params: data })
      // #endif

      const { code, msg } = res.data
      if (code === -100) {
        handleLogout()
        reject(res)
      } else if (code !== 200) {
        uni.showToast({ icon: 'none', title: msg })
        if (code === 500) reject(res.data)
      }
      all ? resolve(res.data) : resolve(res.data[field])
    },
    fail: (error) => {
      loading && uni.hideLoading()
      reject(error)
    },
    complete: () => {
      // 删除已执行请求
      const excutingIndex = excutingList.findIndex(item => item === requestTask)
      excutingList.splice(excutingIndex, 1)

      // 执行等待队列
      const [apiFn] = waitingList
      if (excutingList.length === 0 && typeof apiFn === 'function') {
        apiFn()
        waitingList.splice(0, 1)
      }
    }
  })

  // 当前请求加入正在执行队列
  excutingList.push(requestTask)
}

// 处理登录过期
function handleLogout() {
  if (showLogoutModal) {
    showLogoutModal = false
    clearAllRequest()
  }
}

// 中断请求任务
function clearAllRequest() {
  if (excutingList.length > 0) {
    for (let i = 0; i < excutingList.length; i++) {
      const item = excutingList[i]
      if (item && item.abort) {
        item.abort()
      }
    }
    // 清空队列
    excutingList.length = 0
    waitingList.length = 0
  }
}

// 暴露请求方法
function ajaxRequest(options) {
  return new Promise((resolve, reject) => {
    // 正在执行请求大于阈值 ? 当前请求加入等待队列 : 执行
    excutingList.length > limit ?
    waitingList.push(() => { request(options, resolve, reject) }) :
    request(options, resolve, reject)
  })
}

function cancelHttp(){
	clearAllRequest()
}

export default {
	ajaxRequest,
	cancelHttp
}
