<template>
  <el-menu router :default-active="activeIndex" background-color="#393D49" active-text-color="#FFB800" text-color="#ffffff" mode="horizontal" @select="handleSelect">
    <el-menu-item index="/index">
      <svg class="icon" aria-hidden="true" font-size="30px"><use xlink:href="#iconzhuye"></use></svg>
    </el-menu-item>
    <el-menu-item index="/test/case">测试用例</el-menu-item>
  </el-menu>
</template>

<script type="text/ecmascript-6">
  export default {
    data: function() {   
      return {
        activeIndex: undefined
      }
    },
    watch: {
      
    },
    methods:{
      handleSelect(index,  indexPath) {
        this.activeIndex = index
      }
    }
  }
</script>
