// pages/orderInfo/orderInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderId:'',
    order:{},
    singles:[],
    active:'',
    steps: [
      {
        text: '进度一',
        desc: '买家下单',
      },
      {
        text: '进度二',
        desc: '买家支付',
      },
      {
        text: '进度三',
        desc: '商家发货',
      },
      {
        text: '进度四',
        desc: '交易完成',
      },
    ],
  },


  sendGoods: function(){
    var that = this;

    wx.request({
      url: getApp().globalData.serviceUrl + '/seller/order/send',
      data:{
        orderID: that.data.orderId
      },
      method:'GET',
      header: {
        'content-type': 'application/x-www-form-urlencoded' 
      },
      success:function(res){
        console.log(res.data);
        let resData = res.data;
        if(resData.code == 0){
          wx.showToast({
            title: '操作成功',
            duration: 3000
          })
        }
       that.setData({
         active: 2
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
        orderId: options.orderId,
      })
    
      wx.request({
        url: getApp().globalData.serviceUrl + '/seller/order/detail',
        data:{
          orderId: that.data.orderId
        },
        method:'GET',
        header: {
          'content-type': 'application/x-www-form-urlencoded' // 默认值
        },
        success:function(res){
          console.log(res.data);
          let resData = res.data;
          that.setData({
            order: resData.data
          })
          console.log(that.data.order);
          console.log(that.data.order.orderDetailList);
          console.log(that.data.order.orderDetailList.length);

         if(that.data.order.orderStatus == 0){
            that.setData({
              active: 0,
            })
         }
         else if(that.data.order.orderStatus == 3){
          that.setData({
            active: 1,
          })
         }
         else if(that.data.order.orderStatus == 4){
          that.setData({
            active: 2,
          })
         }
         else if(that.data.order.orderStatus == 1){
          that.setData({
            active: 3,
          })
         }
         else if(that.data.order.orderStatus == 2){
           that.setData({
             active: -1
           })
         }
        
         that.setData({
           singles: []
         })

          for(let i=0; i<that.data.order.orderDetailList.length; i++){
            wx.request({
              url: getApp().globalData.serviceUrl + '/seller/product/singleOne',
              data:{
                cateGory: that.data.order.orderDetailList[i].cateGory,
                singleId: that.data.order.orderDetailList[i].singleId
              },
              method:'GET',
              header: {
                'content-type': 'application/x-www-form-urlencoded' 
              },
              success:function(res){
                console.log(res.data);
                let resData = res.data;
                var array = that.data.singles;
                array.push(resData.data);
                array[i].price = (array[i].price).toFixed(2);
                that.setData({
                  singles: array
                })
              },
              fail:function(res){
                console.log(".....fail.....");
              }
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