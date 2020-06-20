// pages/home2/components/personal/personal.wxml.js
Component({

  onChange(event) {
    // event.detail 为当前输入的值
    console.log(event.detail);
  },

  onClickIcon() {
    Toast('点击图标');
  },

   /**
   * 组件的属性列表
   */

  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    storename: '',
    addreass: '',
    decription: '',
    phone: ''
  },

  /**
   * 组件的方法列表
   */
  methods: {

   
}

})
