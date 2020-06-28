// pages/singleInfo/singleInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      singles: [],
      productId:"",
      category:null,
      sellerId:""
  },


  addComputer: function(e){
    var that = this;
    wx.navigateTo({
      url: '/pages/addSingle/addSingle?category=' + that.data.category + '&productId=' + that.data.productId + '&sellerId=' + that.data.sellerId
    })
  },

  addPhone: function(e){
    var that = this;
    wx.navigateTo({
      url: '/pages/addSingle/addSingle?category=' + that.data.category + '&productId=' + that.data.productId + '&sellerId=' + that.data.sellerId
    })
  },

  alterInfo: function (e) {
    var cateGory = e.currentTarget.dataset.category;
    var singleId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/alterSingle/alterSingle?singleId=' + singleId + '&cateGory=' + cateGory,
    })
  },

  delete: function(e){
    var singleId = e.currentTarget.dataset.id;
    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/deleteSingle',
      data: {
        cateGory: that.data.category,
        singleId: singleId
      },
      method:'GET',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
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

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
        productId: options.proId,
        category: options.category,
        sellerId: options.sellerId
    })
    console.log(options.proId);
    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/singleAllByProduct',
      data:{
        productId: options.proId
      },
      method:'GET',
      header: {
        'content-type': 'application/x-www-form-urlencoded' 
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          singles: resData.data
        })

        for(let i = 0; i < that.data.singles.length; i++){
          var number = (that.data.singles[i].price).toFixed(2);
          console.log(number);
          var str = "singles[" + i + "].price";
          that.setData({
            [str]: number,
          })
        }
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
    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/singleAllByProduct',
      data:{
        productId: that.data.productId
      },
      method:'GET',
      header: {
        'content-type': 'application/x-www-form-urlencoded' 
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          singles: resData.data
        })
      },
      fail:function(res){
        console.log(".....fail.....");
      }
    })
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