// pages/seller_order/seller_order.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orders: [],
    active:"order",
    sellerId: ""
  },

  allOrders: function(){
    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/order/sellerlist',
      data:{
        sellerAccount: that.data.sellerId
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          orders: resData.data
        })
      },
      fail:function(res){
        console.log(".....fail.....");
      }
    })
  },

  needToSend:function(){
    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/order/list',
      data:{
        sellerAccount: that.data.sellerId
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          orders: resData.data
        })
      },
      fail:function(res){
        console.log(".....fail.....");
      }
    })
  },

  statistics: function(){
    var that = this;
     wx.navigateTo({
       url: '/pages/seller_report/seller_report?sellerAccount=' + that.data.sellerId,
     })
  },

  onChange: function(e) {
    console.log(e.detail)
    // event.detail 的值为当前选中项的索引
    this.setData({ active: e.detail })
    var that = this;
    if(e.detail == "goods"){
      wx.navigateTo({
        url: '/pages/seller_goods/seller_goods?sellerAccount=' + that.data.sellerId + '&active=goods',
      })
    }
    else if(e.detail == "order"){
      wx.navigateTo({
        url: '/pages/seller_order/seller_order?sellerAccount=' + that.data.sellerId + '&active=order',
      })
    }
    else if(e.detail == "personal"){
      wx.navigateTo({
        url: '/pages/seller_my/seller_my?sellerAccount=' + that.data.sellerId + '&active=personal',
      })
    }
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
      sellerId: options.sellerAccount,
      active: options.active
    })

    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/order/sellerlist',
      data:{
        sellerAccount: that.data.sellerId
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          orders: resData.data
        })
      },
      fail:function(res){
        console.log(".....fail.....");
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
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

  }
})