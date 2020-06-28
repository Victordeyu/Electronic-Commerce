// pages/addSingle/addSingle.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      brand: "",
      color: "",
      cpu: "",
      disk: "",
      inventory: null,
      memory: "",
      price: null,
      productId: "",
      sellerId: "",
      singleId: null,
      url: "",
      category: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
        productId: options.productId,
        category: options.category,
        sellerId: options.sellerId
    })
  },

  formSubmit: function (e) {
    let Single = e.detail.value;

    if ( Single.brand == '') {
      wx.showToast({
        title: '请填写品牌',
        icon: 'none',
        duration: 3000
      })
      return;
    }
    if ( Single.color == ''){
      wx.showToast({
        title: '请填写颜色',
        icon: 'none',
        duration: 3000
      })
      return;
    }
    if ( Single.cpu == '') {
      wx.showToast({
        title: '请填写CPU',
        icon: 'none',
        duration: 3000
      })
      return;
    }
    if (Single.disk  == '') {
      wx.showToast({
        title: '请填写磁盘',
        icon: 'none',
        duration: 3000
      })
      return;
    }
    if (Single.inventory  == '') {
      wx.showToast({
        title: '请填写库存',
        icon: 'none',
        duration: 3000
      })
      return;
    }

    if (Single.memory  == '') {
      wx.showToast({
        title: '请填写内存',
        icon: 'none',
        duration: 3000
      })
      return;
    }
    if (Single.price  == '') {
      wx.showToast({
        title: '请填写价格',
        icon: 'none',
        duration: 3000
      })
      return;
    }
    if (Single.url  == '') {
      wx.showToast({
        title: '请填写图片URL链接',
        icon: 'none',
        duration: 3000
      })
      return;
    }

    var that = this;
    var interFace = '';

    if(that.data.category == 1){
      interFace =  '/seller/product/save/computer';
   }
   else if(that.data.category == 2){
      interFace = '/seller/product/save/phone';
   }
   
    wx.request({
      url: getApp().globalData.serviceUrl + interFace,
      method: "POST",
      header: {
        'content-type': 'application/json'
      },
      data: {
        "brand": Single.brand,
        "color": Single.color,
        "cpu": Single.cpu,
        "disk": Single.disk,
        "inventory": Single.inventory,
        "memory": Single.memory,
        "price": Single.price,
        "productId": that.data.productId,
        "sellerId": that.data.sellerId,
        "singleId": that.data.singleId,
        "url": Single.url
        },   
      success: function (res) {
        console.log(res);
        wx.showToast({
          title: '操作成功',
          duration: 3000
        })
        that.setData({
          brand: "",
          color: "",
          cpu: "",
          disk: "",
          inventory: null,
          memory: "",
          price: null,
          url: "",
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