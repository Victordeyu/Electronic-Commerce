// pages/category/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    leftMenuList:["电脑","手机"],
    rightMenuList:[],
    Cates:[],
    //点击左侧
    currentIndex:0,
    active: 1
    
  },

  onLoad: function (options) {
    this.getCates();
  
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

  getCates(){

    wx.request({
    url:getApp().globalData.serviceUrl + '/buyer/product/allByCateGory',
    
    success: (result)=>{
      this.setData({Cates:result.data.data});
      this.setData({rightMenuList:result.data.data[0].productVOList})
    }
  })
  },

  handleItemTap(e){
    const {index}=e.currentTarget.dataset;
    wx.request({
      url:getApp().globalData.serviceUrl + '/buyer/product/allByCateGory',
      
      success: (result)=>{
        this.setData({Cates:result.data.data});
        this.setData({rightMenuList:result.data.data[index].productVOList})
      }
    })
    //let rightMenuList = Cates[index].productVOList
    this.setData({
      currentIndex:index
    })
  }
})