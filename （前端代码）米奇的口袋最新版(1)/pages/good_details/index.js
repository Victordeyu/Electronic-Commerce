// pages/good_details/index.js

import regeneratorRuntime from '../../lib/runtime/runtime';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    goodsObj:{},
    proObj:{},
    forCart:{},
    show:false,
    sinId:-2,
    in2:-2,
    currentIndex:-1,
    goodurl:"",
    inven:0
  },
  
  showPopup() {
    this.setData({ show: true });
  },

  onClose() {
    this.setData({ show: false });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const{productId}=options;
    this.getGoodsDetail(productId);
    this.getProductDetail(productId);
  },
  //获取单品详情
  getGoodsDetail(productId){
    var that=this;
    wx.request({
      url:getApp().globalData.serviceUrl + '/buyer/product/singleAllByProduct?productId=' +productId,
      success: (result)=>{
        let a=result.data.data;
        for (let i=0;i<a.length;i++){
          a[i].price=(a[i].price).toFixed(2);
          console.log(a[i].price);
        }
        this.setData({goodsObj:a});
        this.setData({forCart:a});
      }
    })
  },
  //获取商品
  getProductDetail(productId){
    wx.request({
      url:getApp().globalData.serviceUrl + '/buyer/product/one?productId='+productId,
      success: (result)=>{
        this.setData({proObj:result.data.data});
        console.log(result.data.data);
      }
    })
  },

  //获取当前选中单品
  getInfo:function(e){

    this.setData({sinId:e.currentTarget.dataset.id});
    this.setData({in2:e.currentTarget.dataset.in});
    this.setData({currentIndex:e.currentTarget.dataset.in});
    this.setData({goodurl:this.data.goodsObj[e.currentTarget.dataset.in].url});
    this.setData({inven:e.currentTarget.dataset.inven});
    console.log(e.currentTarget.dataset.inven);
  },
  handleCartAdd:function(e){

    //获取购物车数组
    let cart=wx.getStorageSync('cart')||[]; 
    
    if(!wx.getStorageSync('openid')){
      wx.navigateTo({
        url: '../../pages/auth/index',
        success: (result)=>{
          
        }
      });
      
    wx.setStorageSync("cart",cart);
    }else{

    if(this.data.sinId===-2){
      wx.showToast({
        icon: 'none',
      title: '请选择规格',
      mask: true
    });
    }else if(this.data.inven<=0){
      wx.showToast({
        icon: 'none',
      title: '暂无库存',
      mask: true
      });
    }else{
    //判断商品在不在购物车数组
    var index2=this.data.in2;

    let index=cart.findIndex(v=>v.singleId===this.data.forCart[index2].singleId);

    var fin;
    var that=this;
    var n;
    var openid=wx.getStorageSync('openid');
    console.log('购物车数组下标-1即第一次添加 '+index);
    if(index ===-1){
      this.data.forCart[index2].num=1;
      this.data.forCart[index2].checked=true;
      cart.push(this.data.forCart[index2]);
      fin=index2;
      n=1;
    }else{
      cart[index].num++;
      fin=index;
      n=cart[index].num;
    }
    //添加回缓存
    wx.setStorageSync('cart',cart);
    //弹窗提示
    wx.showToast({
      title: '添加成功',
      mask: true
    });
    console.log(n);
    wx.request({
      url: getApp().globalData.serviceUrl + '/buyer/shopcart/add',
        data: {
          cateGory:that.data.forCart[fin].cateGory,
          checked:true,
          count:n,
          openId:openid,
          singleId:that.data.forCart[fin].singleId
        },
        method: "POST",
        header: {
        'content-type': 'application/x-www-form-urlencoded'
        }
    });
    }
    
    }
    
  }
  
})