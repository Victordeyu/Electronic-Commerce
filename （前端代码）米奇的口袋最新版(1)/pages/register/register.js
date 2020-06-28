// pages/register/register.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    openId:"",
    sellerAccount:"",
    sellerMobile:"",
    sellerName:"",
    sellerPassword:"",
  },

  formSubmit: function (e) {
    let Seller = e.detail.value;

    if ( Seller.sellerAccount == '') {
      wx.showToast({
        title: '请填写商品账号',
        icon: 'none',
        duration: 3000
      })
      return;
    }
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
    if ( Seller.sellerPassword == '') {
      wx.showToast({
        title: '请填写密码',
        icon: 'none',
        duration: 3000
      })
      return;
    }

    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/sellerinfo/create', 
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        openId:that.data.openId,
        sellerAccount:Seller.sellerAccount,
        sellerMobile:Seller.sellerMobile,
        sellerName: Seller.sellerName, 
        sellerPassword: Seller.sellerPassword,
        },   
      success: function (res) {
        console.log(res);
        if(res.data.code == 0){
          wx.showToast({
            title: '操作成功',
            duration: 3000
          })
        wx.navigateTo({
          url: '/pages/seller_goods/seller_goods?sellerAccount=' + Seller.sellerAccount + '&active=goods',
        })
      }
      else if(res.data.code == 1){
        wx.showToast({
          title: '账号已存在',
          icon: 'none',
          duration: 3000
        })
      }
      },
      fail: function(){
        console.log("fail");
        wx.showToast({
          title: '操作失败',
          duration: 3000
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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