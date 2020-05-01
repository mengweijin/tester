<template>
  <div>
    <el-divider content-position="left">选择项目</el-divider>
    <el-select v-model="value" @change="loadApiData" style="width:100%" placeholder="请选择项目">
      <el-option
        v-for="item in options"
        :key="item.id"
        :label="item.name"
        :value="item.id">
      </el-option>
    </el-select>
    <el-divider content-position="left">API接口</el-divider>

    <el-row>
        <el-button @click="handleAddClick()" type="primary" plain  icon="el-icon-plus">创建任务</el-button>
    </el-row>
    <el-table 
        v-loading="loading" 
        element-loading-text="拼命加载中" 
        element-loading-spinner="el-icon-loading" 
        element-loading-background="rgba(0, 0, 0, 0.5)" 
        :data="tableData" 
        :row-style="{height:'40px'}" 
        :cell-style="{padding:'5px 0'}">
        <el-table-column prop="id" label="任务ID" min-width="180" sortable v-if="false"></el-table-column>
        <el-table-column prop="url" label="URL" min-width="300"></el-table-column>
        <el-table-column prop="httpMethod" label="Http Method" min-width="150"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" min-width="200" :formatter="dateTimeFormat" v-if="false"></el-table-column>
        
    </el-table>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data: function() {   
      return {
        loading: false,
        options: [],
        value: '',
        tableData: []
      } 
    },
    methods:{
      loadProjectData() {
        let _this = this;
        this.$get('/system/test/project/list')
        .then(function (response) {
            _this.options = response
        })
      },
      loadApiData() {
        if(this.value !== '') {
          this.loading = true
          let _this = this;
          this.$get('/system/test/api/' + this.value + '/api')
          .then(function (response) {
              _this.tableData = response
              _this.loading = false
          })
        } else {
          this.options = []
        }
      },
      dateTimeFormat(row, column) {
          let date = row[column.property]
          if(date == undefined){return ''}
          return this.$dayjs(date).format("YYYY-MM-DD HH:mm:ss")
      }
    },
    created: function() {
      this.loadProjectData();
    }
  }
</script>

<style>

</style>