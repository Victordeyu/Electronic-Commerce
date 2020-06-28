
export function request(params){
    //将传过来的参数结构出来
    return new Promise((resolve,reject)=>{
        // 发起请求          
        wx.request({
            ...params,
            //url:getApp().globalData.serviceUrl + '/buyer/product/allByCateGory',
            success:(res)=>{
                //成功的回调
                resolve(res)
            },
            fail:(err)=>{
                //失败的回调
                reject(err)
            }
        });
    });
}