// onShow无法接受形参options
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orders:[],
    tabs:[
      {
        id:0,
        value:"全部订单",
        isActive:true
      },
      {
        id:1,
        value:"待付款",
        isActive:false
      },
      {
        id:2,
        value:"待发货",
        isActive:false
      },
      {
        id:3,
        value:"已取消",
        isActive:false
      },
    ]

  },
  onShow(options){
    let pages = getCurrentPages();
    //获取当前页面
    let currentPage=pages[pages.length-1];
    //获取type
    let type=currentPage.options.type;
    //激活选做标题
    this.changeTitleByIndex(type);
    this.getOrders();
  },
  //获取全部订单列表
  getOrders(){
    const that=this;
    const openid=wx.getStorageSync('openid');
    wx.request({
      url:  getApp().globalData.serviceUrl + '/buyer/order/detail?openId='+openid,
      success: (result)=>{
        let a=result.data.data;
        for(let i=0;i<a.length;i++){
          a[i].orderAccount=a[i].orderAccount.toFixed(2);
        }
        that.setData({orders:a});
      }
    });
  },
  //根据标题索引激活
  changeTitleByIndex(index){
    const{tabs}=this.data;
    for(let i=0;i<4;i++){
      if(index==tabs[i].id){
        tabs[i].isActive=true;
      }
      else{
        tabs[i].isActive=false;
      }
    }
    this.setData({tabs})
  },
  handleTabsItemChange(e){
    //获取索引
    const{index}=e.detail;
    this.changeTitleByIndex(index);
  }
})