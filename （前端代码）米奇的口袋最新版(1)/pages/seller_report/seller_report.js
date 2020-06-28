// pages/seller_report/seller_report.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sellerId: '',
    products: [],
    singles: []
  },

  goods: function(){
    var that = this;
    that.setData({
      singles: []
    })

    wx.request({
      url: getApp().globalData.serviceUrl + '/statistic/product',
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

  phone: function(){
    var that = this;
    that.setData({
      products: []
    })

    wx.request({
      url: getApp().globalData.serviceUrl + '/statistic/phone',
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

   computer: function(){
    var that = this;
    that.setData({
      products: []
    })

    wx.request({
      url: getApp().globalData.serviceUrl + '/statistic/computer',
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
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
      sellerId: options.sellerAccount
    })

    wx.request({
      url: getApp().globalData.serviceUrl + '/statistic/product',
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