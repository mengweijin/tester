<template>
  <div>
    <router-view name="mengweijinHeader"/>

    <el-container>

      <el-header>
        <router-view name="header"/>
      </el-header>

      <el-main :style="{height: scrollerHeight}" style="width: 90%; margin: 0 auto;">
        <router-view/>
      </el-main>

      <el-footer>
        <router-view name="footer"/>
      </el-footer>

    </el-container>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data: function() {   
      return {
        scrollerHeight: (window.innerHeight - 90 - 60 - 60 - 20) + 'px'
      }
    },
    watch: {
      
    },
    methods:{
      // 滚动区高度
      calcScrollerHeight: function() {
        this.scrollerHeight = (window.innerHeight - document.getElementById('header').offsetHeight - 60 - 60 - 20) + 'px';
      }
    },
    created: function() {
    },
    mounted () {
      let _this = this;
      window.onresize = () => _this.calcScrollerHeight()
    }
  }
</script>