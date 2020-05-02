<template>
  <div>
    <el-row>
        <el-button @click="handleAddClick()" type="primary" plain  icon="el-icon-plus">添加接口</el-button>
    </el-row>
    <el-table 
        v-loading="loading" 
        element-loading-text="拼命加载中" 
        element-loading-spinner="el-icon-loading" 
        element-loading-background="rgba(0, 0, 0, 0.5)" 
        :data="tableData" 
        :row-style="{height:'40px'}" 
        :cell-style="{padding:'5px 0'}">
        <el-table-column prop="id" label="任务ID" min-width="180" sortable v-if="true"></el-table-column>
        <el-table-column prop="url" label="URL" min-width="300"></el-table-column>
        <el-table-column prop="httpMethod" label="请求方式" min-width="100"></el-table-column>
        <el-table-column prop="projectName" label="项目名称" min-width="200"></el-table-column>
        <el-table-column prop="dataSourceName" label="数据源名称" min-width="150"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" min-width="200" :formatter="dateTimeFormat"></el-table-column>
        <el-table-column prop="updateTime" label="最后修改时间" min-width="200" :formatter="dateTimeFormat"></el-table-column>
        <el-table-column fixed="right" label="操作" width="130">
            <template slot-scope="scope">
                <el-button @click="handleDetailClick(scope.row)" type="text" size="medium" title="详细信息">
                  <svg class="icon" aria-hidden="true"><use xlink:href="#icondetail"></use></svg>
                </el-button>
                <el-button @click="handleImportClick(scope.row)" type="text" size="medium" title="导入测试用例">
                  <svg class="icon" aria-hidden="true"><use xlink:href="#iconimport"></use></svg>
                </el-button>
                <el-button @click="handleExportClick(scope.row)" type="text" size="medium" title="导出测试用例">
                  <svg class="icon" aria-hidden="true"><use xlink:href="#iconexport"></use></svg>
                </el-button>
                <el-button @click="handleDeleteClick(scope.$index, scope.row)" type="text" size="medium" icon="el-icon-delete" style="color:red" title="删除">
                </el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 30, 50, 100]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalCount">
    </el-pagination>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data: function() {   
      return {
        loading: true,
        tableData: [],
        currentPage: 1,
        totalCount: 0,
        pageSize: 10,
      } 
    },
    methods:{
      loadTableData(current, size) {
        let _this = this;
        this.$get('/system/test/api', {current: current, size: size})
        .then(function (response) {
          _this.tableData = response.dataList
          _this.totalCount = response.total
          _this.loading = false
        })
      },
      handleSizeChange(val) {
        this.currentPage = 1
        this.pageSize = val
        this.loadTableData(this.currentPage, this.pageSize)
      },
      handleCurrentChange(val) {
        this.currentPage = val
        this.loadTableData(this.currentPage, this.pageSize)
      },
      handleDetailClick(row) {

      },
      handleImportClick(row) {

      },
      handleExportClick(row) {

      },
      handleDeleteClick(index, row) {
        let _this = this
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.$delete('/system/test/api/' + row.id)
            .then(function (res) {
              _this.tableData.splice(index, 1)
              _this.totalCount--
              _this.$message({ message: '操作成功！', type: 'success'});
            })
        })
      },
      dateTimeFormat(row, column) {
        let date = row[column.property]
        if(date == undefined){return ''}
        return this.$dayjs(date).format("YYYY-MM-DD HH:mm:ss")
      }
    },
    created: function() {
      this.loadTableData(this.currentPage, this.pageSize);
    }
  }
</script>

<style>

</style>