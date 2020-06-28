// pages/search/index.js
Page({
  data:{
    goods:[],
    isFocus:false,
    inputValue:""
  },
  TimeId:-1,
  handleInput(e){
    const {value}=e.detail;
    //检测是否为空
    if(!value.trim()){
      this.setData({
        goods:[],
        isFocus:false
      })
      return;
    }
    this.setData({
      isFocus:true
    })
    clearTimeout(this.TimeId);
    this.TimeId=setTimeout(()=>{
      this.search(value);
    },1000)
  },
  search(value){
    var that=this;
    wx.request({
      url:  getApp().globalData.serviceUrl + '/buyer/product/findByNameLike?Keyword='+value,
      success: (result)=>{
        that.setData({
          goods:result.data.data
        })
      }
    });
  },
  handleCancel(){
    this.setData({
      inputValue:"",
      isFocus:false,
      goods:[]
    })
  }
})