// pages/order_detail/index.js
import {showModal}from '../../utils/asyncWx.js';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderDetailList:[],
    buyerAddress:'',
    buyerName:'',
    buyerPhone:'',
    createTime:'',
    orderAccount:'',
    good:[],
    orderId:'',
    cancel:false,
    status:'',
  },

 
  onLoad: function (options) {
    const{orderId}=options;
    this.getDetail(orderId);
    this.setData({orderId})
  },
  onShow: function () {

  },
  getDetail(orderId){
  
    var that=this;
    wx.request({
      url: getApp().globalData.serviceUrl + '/buyer/order/special?orderId=' + orderId,
      success: (result)=>{
        var data=result.data.data;
        let single=[];
        that.setData({
          buyerAddress:data.buyerAddress,
          buyerName:data.buyerName,
          buyerPhone:data.buyerPhone,
          createTime:data.createTime,
          orderAccount:data.orderAccount.toFixed(2),
          orderDetailList:data.orderDetailList,
          status:data.orderStatus
        })
        for(let i=0;i<that.data.orderDetailList.length;i++){
          wx.request({
            url: getApp().globalData.serviceUrl + '/buyer/product/singleOne?cateGory='
            +that.data.orderDetailList[i].cateGory+'&singleId='+that.data.orderDetailList[i].singleId,
            success: (result)=>{
              single[i]=result.data.data
              single[i].price=result.data.data.price.toFixed(2);
              single[i].num=that.data.orderDetailList[i].count;
              that.setData({good:single});
            }
          });
        }
      },
    });
  },
  async handleCancelOrder(){
    var that=this;
    let money=wx.getStorageSync('money');
    console.log(money);
    const openid=wx.getStorageSync('openid');
    const status=this.data.status;
    console.log(openid);
    const res=await showModal({content:"您是否要取消改订单"});
    if(res.confirm){
      wx.request({
        url:  getApp().globalData.serviceUrl + '/buyer/order/cancel',
        data: {
          openid:openid,
          orderId:that.data.orderId
        },
        success: (result)=>{
          that.setData({cancel:true});
          that.getDetail(that.data.orderId);
          wx.showToast({
            title: '取消订单成功',
          });
        },
        method: "POST",
        header: {
        'content-type': 'application/x-www-form-urlencoded'
        },
      });
      
    }
    if(status===3){
      money=money+this.data.orderAccount;
      console.log(money);
      wx.setStorageSync('money', money);

    }
  },
  async pay(){
    var that=this;
    let money=wx.getStorageSync('money');
    const openid=wx.getStorageSync('openid');
    const res=await showModal({content:"您是否要支付"+this.data.orderAccount+"元"});
    if(res.confirm){
      if(money-this.data.orderAccount>=0){
        money=money-this.data.orderAccount;
        console.log(money);
        wx.setStorageSync('money', money);
        wx.request({
          method: "POST",
          header: {
          'content-type': 'application/x-www-form-urlencoded'
          },
          url: getApp().globalData.serviceUrl + '/buyer/order/pay',
          data: {
            openid:openid,
            orderId :that.data.orderId
          },
          success: (result)=>{
            that.setData({status:3})
          }
          
        });
      }else{
        wx.showToast({
          icon: 'none',
          title: '太穷了，没钱买东西了',
      });
      }
    }
    else{

    }
  },
  handleReturn(e){
    let orderDetail=e.currentTarget.dataset.item;
    let orderId=orderDetail.orderId;
    let singleId=orderDetail.singleId;
    let money=wx.getStorageSync('money');
    console.log('money: '+money);
    wx.showModal({
      title: '您确定要退款吗',
      content: '',
      showCancel: true,
      cancelText: '取消',
      cancelColor: '#000000',
      confirmText: '确定',
      confirmColor: '#3CC51F',
      success: (result) => {
        if(result.confirm){
          wx.request({
            url:  getApp().globalData.serviceUrl + '/buyer/order/canceldetail',
            data: {
              orderId:orderId,
              singleId:singleId
            },
            success: (result)=>{
              this.getDetail(orderId);
              money=money+orderDetail.accountPrice;
              wx.setStorageSync('money', money);
              console.log('money: '+money)
              wx.showToast({
                title: '退款成功',
              });
            },
            method: "POST",
            header: {
            'content-type': 'application/x-www-form-urlencoded'
            },
          });
          
        }
      },
      fail: ()=>{},
      complete: ()=>{}
    });
  }
})