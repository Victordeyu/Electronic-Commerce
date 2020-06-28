// pages/user/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:[],
    active: 3
  },
  onShow(){
    const userInfo=wx.getStorageSync("userInfo");
    this.setData({userInfo})
  },
  onChange(event) {
    // event.detail 的值为当前选中项的索引
    this.setData({ active: event.detail });
    if ( event.detail  == 0) {
      wx.redirectTo({
        url: '/pages/index/index',
      })
    } 
    else if( event.detail ==1){
      wx.redirectTo({
        url: '/pages/category/index',
      })
    }
    else if( event.detail ==2){
      wx.redirectTo({
        url: '/pages/cart/index',
      })
    }
    else {
      wx.redirectTo({
        url: '/pages/user/index',
      })
    }

  },
})