// pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    account: '',
    password: '',
    exist:null
  },
  onChange: function(event){
    // event.detail 为当前输入的值
    console.log(event.detail);
  },

  accountInput: function(e){
    this.setData({
      account: e.detail.value
    })
  },

  passwordInput: function(e){
    this.setData({
      password: e.detail.value
    })
  },

  onClicklogin: function(e){
    var that = this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/sellerinfo/login', 
      method: "GET",
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        selleraccount: that.data.account,
        sellerpassword: that.data.password
        },   
      success: function (res) {
        let resData = res.data
        that.setData({
          exist: resData.code
        })
        if (that.data.exist == 0){
          console.log("success");
          wx.navigateTo({
            url: '/pages/seller_goods/seller_goods?sellerAccount=' + that.data.account + '&active=goods',
          })
          }
          else if(that.data.exist == 1){
            wx.showToast({
              title:'输入有错误',
              icon: 'none',
              duration: 3000
            })
          }
      },
      fail: function(){
        console.log("fail");
      }
    })

    
  },
  
  onClickRegister: function(){
    wx.navigateTo({
      url: '/pages/register/register',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     console.log(4999);
     console.log(parseInt(4999).toFixed(2));
     console.log((4999.22).toFixed(2));
     console.log((499922.3/100).toFixed(2));
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