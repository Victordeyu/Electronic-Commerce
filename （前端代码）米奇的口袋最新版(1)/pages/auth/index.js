// pages/auth/index.js
import {login} from "../../utils/asyncWx.js";
Page({
  data:{

  },
  onLoad:function(){
  },
  //获取用户信息openid
 handelGetUserInfo(e){
   var that=this;
  
  let cart=wx.getStorageSync('cart')||[]; 
  try {
   wx.login({
     success: (result)=>{
       wx.request({
         url: 'https://api.weixin.qq.com/sns/jscode2session',
         data: {
           appid:'wxc48c89daddbeb11a',
           secret:'ddd1e180e13b09286135f78206c4ed5a',
           js_code:result.code,
           grant_type:'authorization_code'
         },
         method: 'GET',
         success: (result)=>{
           const{openid}=result.data;
           wx.setStorageSync("openid", openid);
           
          wx.request({
            url: getApp().globalData.serviceUrl + '/buyer/shopcart/one?openId='+openid,
            success:function(result){
              let resData = result.data.data;
              that.setData({a:resData.length});
              for(let i=0;i<resData.length;i++){
                wx.request({
                  url: getApp().globalData.serviceUrl + '/buyer/product/singleOne?cateGory='+resData[i].cateGory+'&singleId='+resData[i].singleId,
                  success: (result)=>{
                    cart[i]=result.data.data;//获取成功
                    cart[i].num=resData[i].count;
                    cart[i].checked=resData[i].checked;
                    wx.setStorageSync('cart',cart);
                  },
                });
              } //for
            }
          });
           wx.navigateBack({
             delta: 1
             });
         }
       });
     }
   });
  } catch (error) {
    console.log(error);
  }
 }
  
})