// pages/addGood/addGood.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cateGory:null,
    productDesc: "",
    productName:"" ,
    productStatus:null,
    url: "",
    sellerId: ""
  },

  formSubmit: function (e) {
    let Product = e.detail.value;
    var that = this;

    if ( Product.cateGory == '') {
      wx.showToast({
        title: '请填写商品分类',
        icon: 'none',
        duration: 3000
      })
      return;
    }
    else if(Product.cateGory == '手机'){
     that.setData({
       cateGory: 2
     })
    }
    else if(Product.cateGory == '电脑'){
      that.setData({
        cateGory: 1
      })
     }
     else{
      wx.showToast({
        title: '请填写正确的商品分类',
        icon: 'none',
        duration: 3000
      })
      return;
     }

    if ( Product.productName == ''){
      wx.showToast({
        title: '请填写商品名称',
        icon: 'none',
        duration: 3000
      })
      return;
    }
    if ( Product.productDesc == '') {
      wx.showToast({
        title: '请填写商品描述',
        icon: 'none',
        duration: 3000
      })
      return;
    }

    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/product/save',  
      method: "POST",
      header: {
        'content-type': 'application/json'
      },
      data: {
          "cateGory": that.data.cateGory,
          "productDesc":Product.productDesc,
          "productId": '',
          "productName": Product.productName,
          "productStatus":that.data.productStatus,
          "sales": 0,
          "sellerId": that.data.sellerId,
          "url": Product.url,
        },   
      success: function (res) {
        console.log(res);
        wx.showToast({
          title: '操作成功',
          duration: 3000
        })
        that.setData({
          cateGory:null,
          productDesc: "",
          productName:"" ,
          productStatus:null,
          url: ""
        })
      },
      fail: function(){
        console.log("fail");
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
      sellerId: options.sellerId
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