<template>
  <div>
    

    <el-container>
      <router-view name="mengweijinHeader"/>
      <el-header>
        <router-view name="header"/>
      </el-header>

      <el-container :style="{height: scrollerHeight}">
        <el-aside width="30%">
          <router-view name="aside"/>
        </el-aside>

        <el-main>
          <router-view/>
        </el-main>
      </el-container>

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
        scrollerHeight: (window.innerHeight - 90 - 60 - 60) + 'px'
      }
    },
    computed: {
      
    },
    watch: {
      
    },
    methods:{
      // 滚动区高度
      calcScrollerHeight: function() {
        this.scrollerHeight = (window.innerHeight - document.getElementById('header').offsetHeight - 60 - 60) + 'px';
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