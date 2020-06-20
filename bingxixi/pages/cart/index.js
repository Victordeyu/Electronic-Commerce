
//import regeneratorRuntime from '../../lib/runtime/runtime';
import {getSetting,ChooseAddres,openSetting, chooseAddress}from '../../utils/asyncWx.js';
Page({
  data:{
    address:{},
    cart:[]
  },
  onShow(){
    //获取缓存的收获地址
    const address=wx.getStorageSync('address');
    //获取缓存的购物车数据
//    const cart=wx.getStorageSync('cart');
    this.setData({
      address
    })

  },
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
  
  }
})
/*复杂版本 没用utils的
Page({
   handleChooseAddress(){
    wx-wx.getSetting({
      success: (result) => {
        //获取权限状态
        const scopeAddress =result.authSetting["scope.address"];
        if(scopeAddress===true||scopeAddress===undefined){
          wx.chooseAddress({
            success: (result1) => {
              console.log(result1);
            }
          })
        }
        else{
          //用户曾经拒绝过打开授权
          wx.openSetting({
            success:(result2)=>{
              wx.chooseAddress({
                success:(result3)=>{
                  console.log(result3)
                }
              })
            }
          })
        }
      }
    })
  }

})
*/