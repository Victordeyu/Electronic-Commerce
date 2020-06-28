// pages/seller_my/seller_my.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    openId:'',
    sellerId:"",
    sellerMobile:"",
    sellerName:"",
    sellerPassword:"",
    active:"personal"
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

  formSubmit: function (e) {
    let Seller = e.detail.value;


    if ( Seller.sellerMobile == ''){
      wx.showToast({
        title: '请填写手机号码',
        icon: 'none',
        duration: 3000
      })
      return;
    }
    if ( Seller.sellerName == '') {
      wx.showToast({
        title: '请填家名称',
        icon: 'none',
        duration: 3000
      })
      return;
    }

    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/sellerinfo/modify', 
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        openId:that.data.openId,
        sellerAccount: that.data.sellerId,
        sellerMobile:Seller.sellerMobile,
        sellerName: Seller.sellerName, 
        sellerPassword: that.data.sellerPassword,
        },   
      success: function (res) {
        console.log(res);
        wx.showToast({
          title: '操作成功',
          duration: 3000
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
      sellerId: options.sellerAccount,
      active: options.active
    })

    wx.request({
      url: getApp().globalData.serviceUrl + '/sellerinfo/display',
      data:{
        selleraccount: that.data.sellerId
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          openId: resData.data[0].openId,
          sellerMobile: resData.data[0].sellerMobile,
          sellerName: resData.data[0].sellerName,
          sellerPassword: resData.data[0].sellerPassword
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
    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/sellerinfo/display',
      data:{
        selleraccount: that.data.sellerId
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        that.setData({
          openId: resData.data[0].openId,
          sellerMobile: resData.data[0].sellerMobile,
          sellerName: resData.data[0].sellerName,
          sellerPassword: resData.data[0].sellerPassword
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