Page({

  /**
   * 页面的初始数据
   */
  data: {
    //轮播图
    swiperList:[],
    //导航
   // catesList:[]
   //楼层
   floorList:[],
   active: 0
  },
  onChange(event) {
    // event.detail 的值为当前选中项的索引
    this.setData({ active: event.detail });
    if ( event.detail  == 0) {
      wx.navigateTo({
        url: '/pages/index/index',
      })
    } 
    else if( event.detail ==1){
      wx.navigateTo({
        url: '/pages/category/index',
      })
    }
    else if( event.detail ==2){
      wx.navigateTo({
        url: '/pages/cart/index',
      })
    }
    else {
      wx.navigateTo({
        url: '/pages/user/index',
      })
    }

  },
 

  onLoad: function (options) {
    /*
    B站视频54集
    //1.发送异步请求获取轮播图数据 原生请求改为promise在B站P54
    */
   //轮播图
    wx.request({
      url: 'https://api-hmugo-web.itheima.net/api/public/v1/home/swiperdata',
      success:(result)=>{
        this.setData({swiperList:result.data.message })
      }
    })
    //导航
   // wx.request({
    //  url: 'https://api-hmugo-web.itheima.net/api/public/v1/home/catitems',
    //  success:(result)=>{
   //     this.setData({catesList:result.data.message })
   //   }
   // })
   //获取楼层数据
   wx.request({
    url: 'https://api-hmugo-web.itheima.net/api/public/v1/home/floordata',
    success:(result)=>{
      this.setData({floorList:result.data.message })
    }
  })

  },
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  },
  onPageScroll:function(){

  },
  onTabItemTap:function(){
    
  }
})