Page({

  /**
   * 页面的初始数据
   */
  data: {
   floorList:[],
   
   userInfo:[],
   active: 0
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
 

  onLoad: function (options) {
    /*
    B站视频54集
    //1.发送异步请求获取轮播图数据 原生请求改为promise在B站P54
    */
    //商品
    wx.request({
      url:getApp().globalData.serviceUrl + '/buyer/product/list?type=1',
      
      success: (result)=>{
        this.setData({product:result.data.data});
      }
    })
   //获取楼层数据
   wx.request({
    url: 'https://api-hmugo-web.itheima.net/api/public/v1/home/floordata',
    success:(result)=>{
      this.setData({floorList:result.data.message })
    }
  })

  },
  onShareAppMessage: function () {
    
  },
  onPageScroll:function(){

  },
  onTabItemTap:function(){
    
  }
})