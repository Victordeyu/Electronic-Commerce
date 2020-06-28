
//import regeneratorRuntime from '../../lib/runtime/runtime';
import {showToast,showModal,getSetting,ChooseAddres,openSetting, chooseAddress}from '../../utils/asyncWx.js';
Page({
  data:{
    address:{},
    cart:[],
    allChecked:false,
    //Procart:[],
    totalPrice:0,
    totalNum:0,
    active: 2,
    openid:"",
  },
  onShow(){
    let money=40000;
    wx.setStorageSync('money',money);
    var that =this;
    
    //获取缓存的收获地址
    const address=wx.getStorageSync('address');
    const openid=wx.getStorageSync('openid');
    this.setData({address});
    this.setData({openid});
    let cart=wx.getStorageSync('cart')||[]; 
    this.setData({cart:cart});
    this.setCart(cart);
    //获取购物车
       wx.request({
         url: getApp().globalData.serviceUrl + '/buyer/shopcart/one?openId='+openid,
         success:(result)=>{
           let resData = result.data.data;
           for(let i=0;i<resData.length;i++){
             wx.request({
               url: getApp().globalData.serviceUrl + '/buyer/product/singleOne?cateGory='+resData[i].cateGory+'&singleId='+resData[i].singleId,
               success: (result)=>{
                 cart[i]=result.data.data;//获取成功
                 cart[i].price=cart[i].price.toFixed(2);
                 cart[i].num=resData[i].count;
                 cart[i].checked=resData[i].checked;
                 wx.setStorageSync('cart',cart);
                 that.setData({cart:cart});
                 that.setCart(cart);
               },
             });
           } //for

         }
       });
  },
  //点击收获地址
  async handleChooseAddress(){
    try {
      const res1 = await getSetting();
      const scopeAddress = res1.authSetting["scope.address"];
      
      if(scopeAddress === false){//打开授权
        await openSetting();
      }
      const res2=await chooseAddress();
      //存入缓存
      wx.setStorageSync('address', res2);
      
    } catch (error) {
      console.log(error);
    }
  
  },
  //商品选中
  handleItemChange(e){
    //获取被修改商品id
    const sin_id=e.currentTarget.dataset.id;
    console.log(sin_id);
    //获取购物车数组
    let {cart}=this.data;
    //找到被修改的视频对象
    let index=cart.findIndex(v=>v.singleId===sin_id);
    //选中状态取反
    cart[index].checked=!cart[index].checked;
    //库存小于0
    if(cart[index].inventory<=0){
      cart[index].checked=false;
      
    wx.showToast({
      title: '库存为0',
      icon:'none'
    });
    }
    //把购物车重新设置会购物车和缓存
    this.setCart(cart);
  },
  //设置购物车状态 重新计算数据 全选 
  setCart(cart){
    var that=this;
    let allChecked=true;
    let totalPrice=0;
    let totalNum=0;
    cart.forEach(v=>{
      if(v.checked&v.inventory>0){
        totalPrice+=v.num*v.price;
        totalNum+=v.num;
      }else if(v.inventory<0){
        v.checked=false;
      }else{
        allChecked=false;
      }
    })
    totalPrice=totalPrice.toFixed(2);
    allChecked=cart.length!=0?allChecked:false;
    for(let i=0;i<cart.length;i++){
      wx.request({
        url: getApp().globalData.serviceUrl + '/buyer/shopcart/add',
          data: {
            cateGory:cart[i].cateGory,
            count:cart[i].num,
            openId:that.data.openid,
            checked:cart[i].checked,
            singleId:cart[i].singleId
          },
          method: "POST",
          header: {
          'content-type': 'application/x-www-form-urlencoded'
          }
      });

    }
    this.setData({
      cart,
      totalNum,
      totalPrice,
      allChecked
    });
    wx.setStorageSync("cart",cart);

  },
  handleItemAllCheck(){
    let {cart,allChecked}=this.data;

    allChecked=!allChecked;
    //修改数据
    cart.forEach(v=>v.checked=allChecked);
    cart.forEach(v=> allChecked=(v.inventory>0?allChecked:false));
    this.setCart(cart);
  },
  //商品数量编辑
  async handleItemNumEdit(e){
    const {operation,id}=e.currentTarget.dataset;
    let {cart}=this.data;

    var that=this;
    const index=cart.findIndex(v=>v.singleId===id);

    //判断是否要删除
    if(cart[index].num===1&&operation===-1){
      
      const res=await showModal({content:"您是否要删除？"});
      if(res.confirm){
      wx.request({
        url: getApp().globalData.serviceUrl + '/buyer/shopcart/delete?openId='+that.data.openid+"&singleId="+cart[index].singleId,
      });
        cart.splice(index,1);
        this.setCart(cart);
      }
      
    }else{
      cart[index].num+=operation;

      wx.request({
        url: getApp().globalData.serviceUrl + '/buyer/shopcart/add',
          data: {
            cateGory:that.data.cart[index].cateGory,
            count:that.data.cart[index].num,
            openId:that.data.openid,
            checked:that.data.cart[index].checked,
            singleId:that.data.cart[index].singleId
          },
          method: "POST",
          header: {
          'content-type': 'application/x-www-form-urlencoded'
          }
      });
      this.setCart(cart);
      
    }
  },
  //结算功能
  async handlePay(){
    //收货地址
    const {address,totalNum}=this.data;
    if(!address.userName){
      await showToast({title:"您还没有选择收货地址"});
      return;
    }
    //有无商品
    if(totalNum===0){
      await showToast({title:"您还没有选购商品"});
      return;
    }
    wx.navigateTo({
      url: '/pages/pay/index'
    });

  },
  onChange(event) {
    // event.detail 的值为当前选中项的索引
    this.setData({ active: event.detail });
    if ( event.detail  == 0) {
      wx.redirectTo({
        url: '/pages/index/index',
      })
    } 
    else if( event.detail ==1){
      wx.redirectTo({
        url: '/pages/category/index',
      })
    }
    else if( event.detail ==2){
      wx.redirectTo({
        url: '/pages/cart/index',
      })
    }
    else {
      wx.redirectTo({
        url: '/pages/user/index',
      })
    }

  }
})