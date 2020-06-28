// pages/seller_goods/seller_goods.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    products: [],
    active:"goods",
    sellerId: "",
    btns:["全部商品","上架商品","下架商品"],
    current:0
  },

  allGoods: function(){
    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/sellerid',
      data:{
        sellerId: that.data.sellerId
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          products: resData.data
        })
      },
      fail:function(res){
        console.log(".....fail.....");
      }
    })
  },

  onsaleGoods: function(){
    var that = this;

    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/list',
      data:{
        sellerId: that.data.sellerId,
        type: 1
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          products: resData.data
        })
      },
      fail:function(res){
        console.log(".....fail.....");
      }
    })
  },

  addGood: function () {
    var that = this;
    wx.navigateTo({
      url: '/pages/addGood/addGood?sellerId=' + that.data.sellerId ,
    })
  },

  offsaleGoods: function(){
    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/list',
      data:{
        sellerId: that.data.sellerId,
        type: 0
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          products: resData.data
        })
      },
      fail:function(res){
        console.log(".....fail.....");
      }
    })
  },

  addGood: function () {
    var that = this;
    wx.navigateTo({
      url: '/pages/addGood/addGood?sellerId=' + that.data.sellerId ,
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

  lookUpSingle: function(e){
    var proId = e.currentTarget.dataset.id;
    var category = e.currentTarget.dataset.category;
    var sellerId = e.currentTarget.dataset.sellerid;
    wx.navigateTo({
      url: '/pages/singleInfo/singleInfo?proId=' + proId + '&category=' + category + '&sellerId=' + sellerId
    })
  },

  alterInfo: function (e) {
    var proId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/alterGood/alterGood?proId=' + proId 
    })
  },

  delete: function(e){
    var proId = e.currentTarget.dataset.id;
    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/delete',
      data:{
        productId: proId
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        wx.showToast({
          title: '操作成功',
          duration: 3000
        })
        that.onShow();
      },
      fail:function(res){
        console.log(".....fail.....");
        wx.showToast({
          title: '操作失败',
          icon: 'none',
          duration: 3000
        })
      }
    })
  },


  onsale: function(e){
    var proId = e.currentTarget.dataset.id;
    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/onsale',
      data:{
        productId: proId
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        wx.showToast({
          title: '操作成功',
          duration: 3000
        })
      },
      fail:function(res){
        console.log(".....fail.....");
        wx.showToast({
          title: '操作失败',
          icon: 'none',
          duration: 3000
        })
      }
    })
  },

  offsale: function(e){
    var proId = e.currentTarget.dataset.id;
    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/offsale',
      data:{
        productId: proId
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        wx.showToast({
          title: '操作成功',
          duration: 3000
        })
      },
      fail:function(res){
        console.log(".....fail.....");
        wx.showToast({
          title: '操作失败',
          icon: 'none',
          duration: 3000
        })
      }
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({ 
      active: options.active,
      sellerId: options.sellerAccount
    });

    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/sellerid',
      data:{
        sellerId: that.data.sellerId
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          products: resData.data
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