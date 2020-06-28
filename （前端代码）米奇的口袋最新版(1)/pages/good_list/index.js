// pages/good_list/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    good:[]
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that=this;
    let pages = getCurrentPages();
    let currentPage=pages[pages.length-1];
    let query=currentPage.options.query;
    console.log(query);
    if(query==1){
      wx.request({
        url:  getApp().globalData.serviceUrl + '/buyer/product/cateGory?cateGory=2',
        success: (result) => {
          this.setData({good:result.data.data})
          console.log(result.data.data);
        },
      })
    }else if(query==2){
      wx.request({
        url:  getApp().globalData.serviceUrl + '/buyer/product/cateGory?cateGory=1',
        success: (result) => {
          this.setData({good:result.data.data})
          console.log(result.data.data);
        },
      })
    }else{
      wx.request({
        url:  getApp().globalData.serviceUrl + '/buyer/product/list?type=1',
        success: (result) => {
          this.setData({good:result.data.data})
          console.log(result.data.data);
        },
      })
    }
  },
})