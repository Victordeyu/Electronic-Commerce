
//import regeneratorRuntime from '../../lib/runtime/runtime';
import {showToast,showModal,getSetting,ChooseAddres,openSetting, chooseAddress}from '../../utils/asyncWx.js';
Page({
  data:{
    address:{},
    cart:[],//过滤后的购物车
    addressall:"",
    seller:[],
    totalPrice:0,
    totalNum:0,
    buyerOpenId:"",
    order_id:[],
  },
  onShow(){
    //获取缓存的收获地址
    const address=wx.getStorageSync('address');
    //获取缓存的购物车数据
    let cart=wx.getStorageSync('cart')||[];
    let openid=wx.getStorageSync('openid');
    //过滤后的购物车
    cart=cart.filter(v=>v.checked);
    
   // for(var i=0;i<seccart.length;i++){
   //   seller.push(seccart[i].sellerId);
   // }
    this.setData({address});
    
    let totalPrice=0;
    let totalNum=0;
    cart.forEach(v=>{
        totalPrice+=v.num*v.price;
        totalNum+=v.num;
    });
    totalPrice=totalPrice.toFixed(2);
    this.setData({
      cart,
      totalNum,
      totalPrice,
      address,
      addressall:this.data.address.provinceName + this.data.address.cityName + this.data.address.countyName + this.data.address.detailInfo,
      openid
    });
    //this.getCartPro();
  },
  async handleOrderPay(){
    let money=wx.getStorageSync('money');
    console.log(money);
    const cart1= this.data.cart;
    //过滤seller有几个
    let seller1=[];
    for(let i=0;i<cart1.length;i++){
      seller1[i]=cart1[i].sellerId;
    }
    seller1.sort();
    for(let i=0;i<seller1.length;){
      if(seller1[i]===seller1[i+1]){
        seller1.splice(i,1);
      }else{
        i++;
      }
    }
    console.log(seller1);
    this.setData({seller:seller1});//seller存回去
    var that=this;
    //判断缓存有无openid
    const openid=wx.getStorageSync("openid");
    if(!openid){
      wx.navigateTo({
        url: '/pages/auth/index'
      });
      return;
    }
    //创建订单
    //准备参数 固定信息
    const buyerAddress=this.data.addressall;//用户地址
    const buyerName=this.data.address.userName;//用户名称
    const buyerPhone=this.data.address.telNumber;
    const buyerOpenId=this.data.openid;
    const orderAccount=this.data.totalPrice;//总价
    //变动信息
    const seller=this.data.seller;
    const cart=this.data.cart;
    var oid=[];//订单id
    //每个商家都有一个订单
    for(let i=0;i<seller.length;i++){
      let goods=[];
      //这个goods是同一个商家的商品
      for(var j=0;j<cart.length;j++){
        if(cart[j].sellerId===seller[i]){
          goods.push({
            singleId:cart[j].singleId,
            count:cart[j].num,
            productPrice:cart[j].price,
            cateGory:cart[j].cateGory,
            accountPrice:cart[j].num*cart[j].price,
          })
        }
      }
      const se=this.data.seller[i];
      //将同一个商家的单品列表转换为string
      var str = JSON.stringify(goods); 
      //这个goods是同一个商家的商品
      console.log(str);
      wx.request({
        url: getApp().globalData.serviceUrl + '/buyer/order/create',
        data: {
          buyerAddress:buyerAddress,
          buyerName:buyerName,
          buyerOpenId:openid,
          buyerPhone:buyerPhone,
          items:str,
          sellerAccount:se
        },
        method: "POST",
        header: {
        'content-type': 'application/x-www-form-urlencoded'
        },
        success: (result)=>{
          oid[i]=result.data.data;
        }
      });

    }
    //支付
    const res=await showModal({content:"您是否要支付"+this.data.totalPrice+"元"});
      if(res.confirm){
        if(money-orderAccount>=0){
          money=money-orderAccount;
          console.log(money);
          wx.setStorageSync('money', money);
          for(let i=0;i<oid.length;i++){
            console.log(buyerOpenId);
            console.log(oid[i]);
            wx.request({
              method: "POST",
              header: {
              'content-type': 'application/x-www-form-urlencoded'
              },
              url: getApp().globalData.serviceUrl + '/buyer/order/pay',
              data: {
                openid:buyerOpenId,
                orderId :oid[i].orderId
              },
              success: (result)=>{
                console.log(result);
              }
              
            });
          
          }
        }else{
          wx.showToast({
            icon: 'none',
            title: '太穷了，没钱买东西了',
        });
        }
      }
      else{

      }
      //删除购物车的内容
      let newCArt=wx.getStorageSync("cart");
      let ca=wx.getStorageSync("cart");
      newCArt=newCArt.filter(v=>!v.checked);//剩余的商品
      wx.request({
        url: getApp().globalData.serviceUrl + '/buyer/shopcart/deleteall?openId='+openid,
        success: (result)=>{
          wx.setStorageSync("cart", newCArt);
          this.setData({cart:newCArt});
        },
      });
      wx.navigateTo({
        url: '/pages/order/index?type=0'
      });
  },

})