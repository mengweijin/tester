<template>
  <el-tree 
    :data="treeData" 
    :props="defaultProps" 
    @node-click="handleNodeClick" 
    highlight-current
    :expand-on-click-node="false" 
    empty-text="请先创建一个项目">
  </el-tree>
</template>

<script type="text/ecmascript-6">
  export default {
    data: function() {   
      return {
        treeData: [],
        defaultProps: {
          children: 'testApiList',
          label: 'name'
        }
      };
      
    },
    methods:{
      loadTreeData(){
        let _this = this;
        this.$get('/system/test/project/tree')
        .then(function (response) {
          console.log(response)
            _this.treeData = response
        })
      },
      handleNodeClick(data) {
        console.log(data);
      }
    },
    created: function() {
        this.loadTreeData()
    }
  }
</script>

<style>

</style>